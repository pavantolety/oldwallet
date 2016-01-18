package com.oldwallet.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import com.oldwallet.dao.GenerateCouponDAO;
import com.oldwallet.model.SaveConfiguration;
import com.oldwallet.util.ExceptionObjUtil;
import com.opencsv.CSVReader;

@Controller
public class GenerateCouponsController {
	
	@Autowired
	GenerateCouponDAO generateCouponDAO;
	
	@RequestMapping(value = "/saveConfiguration", method = RequestMethod.POST)
	public String saveConfiguration(ModelMap modelMap,SaveConfiguration saveConfiguration) {
		boolean result=generateCouponDAO.saveConfiguration(saveConfiguration);
		if(result==true){
			find(saveConfiguration.getCouponCount(),saveConfiguration.getCouponLength(),saveConfiguration.getTypeA(),
					saveConfiguration.getTypeALength(),saveConfiguration.getTypeB())
			modelMap.put("Result", "success");
			modelMap.put("message", "Successfully Created");
		}else{
			modelMap.put("message", "Failed to create.");
		}
		return PageView.CREATECOUPONS;
	}
	
	@RequestMapping(value = "/generateCoupons", method = RequestMethod.POST)
	public String generateCoupon(ModelMap modelMap,SaveConfiguration saveConfiguration) {
		long result=generateCouponDAO.getDataById(saveConfiguration);
		if(result>0){
			modelMap.put("Result", "success");
			modelMap.put("message", "Successfully Created");
		}else{
			modelMap.put("message", "Failed to create.");
		}
		return PageView.CREATECOUPONS;
	}
	
	public static void find(int n,int length,ArrayList<Integer> lentharray,ArrayList<String> typearray) {
        String str1, coupan="";
                
        StringBuilder sb=new StringBuilder(length);
        Random r = new Random();

        System.out.println("\n\t Unique codes are \n\n");
        for(int i=0;i<n;i++){
        	coupan="";
            for(int j=0;j<length;j++){
            	String type=typearray.get(j);
            	if("NUM".equalsIgnoreCase(type))
                    str1="0123456789";
                    else if("ALF".equalsIgnoreCase(type))
                    	str1="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    else  str1="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            	sb=new StringBuilder(lentharray.get(j));
            	for(int k=0;k<lentharray.get(j);k++)
                     	sb.append(str1.charAt(r.nextInt(str1.length())));
                coupan=coupan+sb.toString()+"-";
                sb.delete(0,length);
                System.out.print("  "+coupan);
                
            }
           System.out.println("----------");         
           
        }
    }
	
}
