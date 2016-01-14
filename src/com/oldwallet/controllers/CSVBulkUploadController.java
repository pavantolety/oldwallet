package com.oldwallet.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
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
import com.oldwallet.model.CouponData;
import com.oldwallet.model.ExceptionObj;
import com.oldwallet.model.GoogleResponse;
import com.oldwallet.model.LatLong;
import com.oldwallet.model.Result;
import com.oldwallet.model.Transaction;
import com.opencsv.CSVReader;

@Controller
public class CSVBulkUploadController {

	@Autowired
	CSVBulkUploadDAO csvBulkUploadDAO;

	@Autowired
	TransactionDAO transactionDAO;

	@Autowired
	ExceptionObjDAO exceptionObjDAO;

	@Autowired
	CouponDAO couponDAO;

	private static final Logger LOGGER = Logger.getLogger(CSVBulkUploadController.class);

	@SuppressWarnings({ "deprecation", "resource" })
	@RequestMapping(value = "/csvBulkUpload", method = { RequestMethod.GET, RequestMethod.POST })
	public String bulkUpload(ModelMap modelMap, CouponData couponData) throws IOException, ParseException {
		MultipartFile multipartFile = couponData.getFile();
		boolean uploaded = false;
		if (couponData.getFile() != null) {
			byte[] content = multipartFile.getBytes();
			LOGGER.debug("Lenghtn :: "+content.length);
			if (content.length > 0) {
				InputStream is = null;
				is = new ByteArrayInputStream(content);
				CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(is)), ',', '\'', 1);
				String[] nextLine;
				while ((nextLine = reader.readNext()) != null) {
					// nextLine[] is an array of values from the line
					if (nextLine.length > 0) {
						CouponData couponData1 = new CouponData();
						try {
							couponData1.setCouponCode(nextLine[0]);
							couponData1.setCouponValue(nextLine[1]);
							couponData1.setCountryCode(nextLine[2]);
							couponData1.setCouponHideLocation(nextLine[3]);
							couponData1.setReedemStatus(nextLine[4]);
							couponData1.setValidityPeriod(nextLine[5]);
							SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");
							SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
							couponData1.setValidFrom(format2.format(format1.parse(nextLine[6])));
							couponData1.setValidTo(format2.format(format1.parse(nextLine[7])));
							couponData1.setAvailableRedemptions(Long.parseLong(nextLine[8]));

							uploaded = csvBulkUploadDAO.createCouponData(couponData1);
							if (!uploaded) {
								modelMap.put("status", "Upload Failed!,Data is already uploaded");
							}
						} catch (DuplicateKeyException de) {
							LOGGER.log(Priority.ERROR, de);
							ExceptionObj exceptionObj = new ExceptionObj();
							exceptionObj.setExceptionMessage(de.getMessage());
							exceptionObj.setExceptionName("csvBulkUpload Exception");
							exceptionObj.setExceptionSourceFile("CSVBulkUploadController.java");
							exceptionObj.setExceptionSourceMethod("bulkUpload");
							exceptionObjDAO.saveException(exceptionObj);

						} catch (Exception e) {
							LOGGER.log(Priority.ERROR, e);
							ExceptionObj exceptionObj = new ExceptionObj();
							exceptionObj.setExceptionMessage(e.getMessage());
							exceptionObj.setExceptionName("csvBulkUpload Exception");
							exceptionObj.setExceptionSourceFile("CSVBulkUploadController.java");
							exceptionObj.setExceptionSourceMethod("bulkUpload");
							exceptionObjDAO.saveException(exceptionObj);
						}
						LOGGER.debug("BREAK :: ");
					}
				}
			}
		} else {
			modelMap.put("status", "Upload your file");
		}
		if (uploaded) {
			modelMap.put("status", "Uploaded Successfully");
		}
		return PageView.ADMINHOME;
	}

	@RequestMapping(value = "/bulkUpload", method = RequestMethod.GET)
	public String bulkUpload(ModelMap modelMap, HttpSession session) {

		return "csvBulkUpload";

	}

	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getTrackedCouponsMap", method = RequestMethod.GET)
	public void getTrackedCouponsMap(ModelMap modelMap)	throws JsonParseException, JsonMappingException, IOException {
		List<CouponData> coupnDataList = csvBulkUploadDAO.getCouponTrackingData();
		LOGGER.debug("Data  for tracking>>>>>>>>>>>>>>");
		JSONArray list1 = new JSONArray();
		JSONArray list2 = new JSONArray();
		if (coupnDataList != null) {
			for (CouponData couponData : coupnDataList) {

				if (couponData.getCountryCode() != null) {
					LOGGER.debug("statius :: "+couponData.getReedemStatus());
					if (couponData.getReedemStatus().equalsIgnoreCase(CouponStatus.NEW.toString())) {
						JSONObject obj = new JSONObject();
						LOGGER.debug("LOCATION :: "+couponData.getCouponHideLocation());
						LOGGER.debug("email" + couponData.getReedemedBy());
						LatLong lt = getLatLongByAddress(couponData
								.getCouponHideLocation());
						obj.put("code", couponData.getCountryCode());
						obj.put("name", couponData.getCouponHideLocation());
						obj.put("value", couponData.getCouponCount());
						obj.put("latitude", lt.getLat());
						obj.put("logitude", lt.getLonngi());
						list1.add(obj);
					}
					LOGGER.debug("NOT EMPTY ::");
					List<Transaction> transactionList = transactionDAO.getRedeemedCouponData();
					LOGGER.debug("NOT EMPTY ::");
					if (transactionList.size() > 0) {
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
					}
				}

			}
		}
		modelMap.put("mapData1", list1);
		modelMap.put("mapData2", list2);
	}

	public LatLong getLatLongByAddress(String address) throws IOException {
		LatLong lt = null;
		URL url = new URL(URL + "?address="+ URLEncoder.encode(address, "UTF-8") + "&sensor=false");
		URLConnection conn = url.openConnection();

		InputStream in = conn.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		GoogleResponse res = (GoogleResponse) mapper.readValue(in,GoogleResponse.class);
		in.close();
		if (res.getStatus().equals("OK")) {
			for (Result result : res.getResults()) {
				lt = new LatLong();
				LOGGER.debug("Lattitude of address is :: "+result.getGeometry().getLocation().getLat());
				LOGGER.debug("Longitude of address is :: "+result.getGeometry().getLocation().getLng());
				LOGGER.debug("Location is :: "+result.getGeometry().getLocation_type());

				lt.setLat(result.getGeometry().getLocation().getLat());
				lt.setLonngi(result.getGeometry().getLocation().getLng());
			}
		} else {
			LOGGER.debug(res.getStatus());
		}
		return lt;
	}

}
