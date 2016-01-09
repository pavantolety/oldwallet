package com.oldwallet.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.CSVBulkUploadDAO;
import com.oldwallet.model.CouponData;
import com.opencsv.CSVReader;



@Controller
public class CSVBulkUploadController {
	
	@Autowired
	CSVBulkUploadDAO csvBulkUploadDAO;
	
private static Logger log = Logger.getLogger(CSVBulkUploadController.class);
	
	@RequestMapping(value="/csvBulkUpload",method = { RequestMethod.GET, RequestMethod.POST })
	public String bulkUpload(ModelMap modelMap, CouponData couponData, HttpSession session) throws IOException, ParseException {
		  MultipartFile multipartFile =  couponData.getFile();
		  boolean uploaded =  false;
		  if(couponData.getFile()!=null){
		    byte[] content = multipartFile.getBytes();
		    System.out.println("Lenghtn>>>>"+content.length);
		    if(content.length>0){
	        InputStream is = null;
	        is = new ByteArrayInputStream(content);
		  CSVReader reader = new CSVReader(new BufferedReader(new InputStreamReader(is)),',','\'', 1);
		     String [] nextLine;
		     while ((nextLine = reader.readNext()) != null) {
		        // nextLine[] is an array of values from the line
		    	 if(nextLine.length>0){
		    		 CouponData couponData1 =  new CouponData();
		    		 //for(int i=0;i<nextLine.length;i++){
		              try{
		              couponData1.setCouponCode(nextLine[0]);
		              couponData1.setCouponValue(nextLine[1]);
		              couponData1.setCountryCode(nextLine[2]);
		              couponData1.setCouponHideLocation(nextLine[3]);
		              couponData1.setReedemStatus(nextLine[4]);
		              couponData1.setValidityPeriod(nextLine[5]);
		              SimpleDateFormat format1 =  new SimpleDateFormat("MM-dd-yyyy");
		              SimpleDateFormat format2 =  new SimpleDateFormat("yyyy-MM-dd");
		              couponData1.setValidFrom(format2.format(format1.parse(nextLine[6])));
		              couponData1.setValidTo(format2.format(format1.parse(nextLine[7])));
		              couponData1.setAvailableRedemptions(Long.parseLong(nextLine[8]));
		               
		               uploaded = csvBulkUploadDAO.createCouponData(couponData1);
		               if(!uploaded){
		            	   modelMap.put("status", "Uploaded failed-Data is already uploaded");
		               }
		               }catch (DuplicateKeyException de) {
						de.printStackTrace();
		               }catch(Exception e){
		            	   e.printStackTrace();
		               }
		              //}
		    		 System.out.println( "BREAK DSDSD>>>>");
		    		 }
		    	 }
		    	}
		    }else{
		    	 modelMap.put("status", "Upload your file");
		     }
		     if(uploaded){
		     modelMap.put("status", "Uploaded Successfully");
		     }
	return PageView.ADMINHOME;
	}
	
	
	@RequestMapping(value="/bulkUpload", method=RequestMethod.GET)
	public String bulkUpload(ModelMap modelMap, HttpSession session) {
		
	
	return "csvBulkUpload";

	}

	@RequestMapping(value="/getTrackedCouponsMap", method=RequestMethod.GET)
	public void getTrackedCouponsMap(ModelMap modelMap, HttpSession session) {
		List<CouponData> coupnDataList = csvBulkUploadDAO.getCouponTrackingData();
		System.out.println("Data  for tracking>>>>>>>>>>>>>>");
		JSONArray list = new JSONArray();
	 	if(coupnDataList!=null) {
	         for(CouponData couponData : coupnDataList){
	         	 if(couponData.getCountryCode()!=null){
	 	        	 JSONObject obj = new JSONObject();
	 	        	 if(couponData.getReedemStatus().equalsIgnoreCase("NEW")){
	 	        	 obj.put("code",couponData.getCountryCode());
	 	        	 obj.put("name", couponData.getCouponHideLocation());
	 	        	 obj.put("value", couponData.getCouponCount());
	 	        	 obj.put("color","#ff0000");
	 	        	 
	 	        	 }else if(couponData.getReedemStatus().equalsIgnoreCase("EXPIRED")){
	 	        		 obj.put("code",couponData.getRedeemedLocationCode());
		 	        	 obj.put("name", couponData.getRedeemedLocation());
		 	        	 obj.put("value", couponData.getCouponCount());
		 	        	 obj.put("color","#00ff00");
	 	        	 }
	 	        	list.add(obj);
	 	        	 }	 
	         }
	 		}
	 	
	 	modelMap.put("mapData", list);
	
	

	}
	
	
}

