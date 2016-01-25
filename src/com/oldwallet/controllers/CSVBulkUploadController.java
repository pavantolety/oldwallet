package com.oldwallet.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.CSVBulkUploadDAO;
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.dao.ExceptionObjDAO;
import com.oldwallet.dao.TransactionDAO;
import com.oldwallet.enums.CouponStatus;
import com.oldwallet.model.AdminSession;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.CouponData;
import com.oldwallet.model.GoogleResponse;
import com.oldwallet.model.LatLong;
import com.oldwallet.model.Result;
import com.oldwallet.model.Transaction;
import com.oldwallet.util.EncryptCouponUtil;
import com.opencsv.CSVReader;

@Controller
public class CSVBulkUploadController {

	private static final Logger LOGGER = Logger.getLogger(CSVBulkUploadController.class);

	private static final String STATUS = "status";

	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

	@Autowired
	CSVBulkUploadDAO csvBulkUploadDAO;

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	CouponDAO couponDAO;
	
	@Autowired
	ExceptionObjDAO exceptionDAO;

	@SuppressWarnings({ "deprecation" })
	@RequestMapping(value = "/csvBulkUpload", method = { RequestMethod.GET,RequestMethod.POST })
	public String bulkUpload(ModelMap modelMap, CouponData couponData, HttpSession session, HttpServletRequest request) {
		AdminSession adminSession = (AdminSession) session.getAttribute("adminSession");
		if(adminSession==null) {
			return PageView.ADMINLOGIN;
		}
		if("GET".equalsIgnoreCase(request.getMethod())) {
			return PageView.ADMINHOME;
		}
		MultipartFile multipartFile = couponData.getFile();
		String fileName = multipartFile.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf("."));
		System.out.println("FIle Ext :: "+fileExt);
		System.out.println("FILE SIZE :: "+multipartFile.getSize());
		if(!".CSV".equalsIgnoreCase(fileExt)) {
			System.out.println("File is not in proper format ::");
			modelMap.put(STATUS, "Please Upload csv file only.");
			return PageView.ADMINHOME;
		}
		if(multipartFile.getSize()>=9195255) {
			System.out.println("File size is too big ::");
			modelMap.put(STATUS, "File size is too big.");
			return PageView.ADMINHOME;
		}
		boolean uploaded = false;
		if (couponData.getFile() != null) {
			byte[] content = null;
			try {
				content = multipartFile.getBytes();
			} catch (IOException ioe) {
				LOGGER.log(Priority.ERROR, ioe);
			}

			if (content != null && content.length > 0) {
				InputStream is = null;
				is = new ByteArrayInputStream(content);
				CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(is)), ',', '\'', 1);
				String[] nextLine;
				try {
					while ((nextLine = reader.readNext()) != null) {
						// nextLine[] is an array of values from the line
						System.out.println("COLUMNS COUNT :: "+nextLine.length);
						if (nextLine.length==5) {
							CouponData couponData1 = new CouponData();
							try {
								couponData1.setCouponCode(EncryptCouponUtil.enccd(nextLine[0].toUpperCase()));
								couponData1.setCouponValue("0");
								couponData1.setCountryCode(nextLine[1]);
								couponData1.setCouponHideLocation(nextLine[2]);
								couponData1.setReedemStatus("NEW");
								SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
								SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
								couponData1.setValidFrom(format2.format(format1.parse(nextLine[3])));
								couponData1.setValidTo(format2.format(format1.parse(nextLine[4])));
								int diffInDays = (int) ((format1.parse(nextLine[4]).getTime() - format1.parse(nextLine[3]).getTime()) / (1000 * 60 * 60 * 24));
								couponData1.setValidityPeriod(diffInDays+"");								
								couponData1.setAvailableRedemptions(1);

								uploaded = csvBulkUploadDAO.createCouponData(couponData1);
								if (!uploaded) {
									modelMap.put(STATUS,"Upload Failed!,Data is already uploaded");
								}
							} catch (DuplicateKeyException de) {
								LOGGER.log(Priority.ERROR, de);
								exceptionDAO.saveException("csvBulkUpload Exception",de.getMessage(),"CSVBulkUploadController.java","bulkUpload");

							} catch (Exception e) {
								LOGGER.log(Priority.ERROR, e);
								try {
									exceptionDAO.saveException("csvBulkUpload Exception",e.getMessage(),"CSVBulkUploadController.java","bulkUpload");
								}catch(Exception ee) {
									ee.printStackTrace();
								}
							}
							LOGGER.debug("BREAK :: ");
						} else {
							modelMap.put(STATUS, "File format is not currect.");
							return PageView.ADMINHOME;
						}
					}
				} catch (IOException e) {
					LOGGER.log(Priority.ERROR, e);
				}
			}
		} else {
			modelMap.put(STATUS, "Upload your file");
		}
		if (uploaded) {
			modelMap.put(STATUS, "Uploaded Successfully");
		}
		return PageView.ADMINHOME;
	}

	@RequestMapping(value = "/bulkUpload", method = RequestMethod.GET)
	public String bulkUpload() {

		return "csvBulkUpload";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getTrackedCouponsMap", method = RequestMethod.GET)
	public void getTrackedCouponsMap(ModelMap modelMap) throws Exception {
		//List<CouponData> coupnDataList = csvBulkUploadDAO.getCouponTrackingData();
		List<Coupon> couponDataList = couponDAO.getCouponDataByRedeemed();
		LOGGER.debug("Data  for tracking>>>>>>>>>>>>>>");
		JSONArray list1 = new JSONArray();
		JSONArray list2 = new JSONArray();
		if (!couponDataList.isEmpty()) {
			for (Coupon couponData : couponDataList) {
						JSONObject obj = new JSONObject();
						obj.put("code","code");
						obj.put("name", "name");
						obj.put("value",couponDataList.size());
						obj.put("latitude", couponData.getLatitude());
						obj.put("longitude", couponData.getLongitude());
						list1.add(obj);
				/*	List<Transaction> transactionList = transactionDAO.getRedeemedCouponData();
					if (!transactionList.isEmpty()) {
						for (Transaction tr : transactionList) {
							LOGGER.debug("NOT EMPTY ::");
							JSONObject obj2 = new JSONObject();
							obj2.put("code", couponData.getCountryCode());
							obj2.put("name", couponData.getCouponHideLocation());
							obj2.put("value", tr.getCouponCount());
							obj2.put("latitude", tr.getLatitude());
							obj2.put("logitude", tr.getLongitude());
							list2.add(obj2);
						}
			}*/

			}
		}
		modelMap.put("mapData1", list1);
		/*modelMap.put("mapData2", list2);*/
	}

	public LatLong getLatLongByAddress(String address) throws IOException {
		LatLong lt = null;
		URL url = new URL(URL + "?address="+ URLEncoder.encode(address, "UTF-8") + "&sensor=false");
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse res = (GoogleResponse) mapper.readValue(in,GoogleResponse.class);
		in.close();
		if ("OK".equalsIgnoreCase(res.getStatus())) {
			for (Result result : res.getResults()) {
				lt = new LatLong();
				LOGGER.debug("Lattitude of address is :: "+ result.getGeometry().getLocation().getLat());
				LOGGER.debug("Longitude of address is :: "+ result.getGeometry().getLocation().getLng());
				LOGGER.debug("Location is :: "+ result.getGeometry().getLocation_type());

				lt.setLat(result.getGeometry().getLocation().getLat());
				lt.setLonngi(result.getGeometry().getLocation().getLng());
			}
		} else {
			LOGGER.debug(res.getStatus());
		}
		return lt;
	}

}
