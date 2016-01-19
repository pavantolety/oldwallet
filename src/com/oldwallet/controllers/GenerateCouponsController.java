package com.oldwallet.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.dao.GenerateCouponDAO;
import com.oldwallet.enums.CouponStatus;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.SaveConfiguration;
import com.oldwallet.util.CouponCodeUtil;
import com.oldwallet.util.ExceptionObjUtil;
import com.opencsv.CSVReader;

@Controller
public class GenerateCouponsController {
	
	@Autowired
	GenerateCouponDAO generateCouponDAO;
	
	@Autowired
	CouponDAO couponDAO;
	
	@RequestMapping(value = "/saveConfiguration", method = RequestMethod.POST)
	public String saveConfiguration(ModelMap modelMap,SaveConfiguration saveConfiguration) {
		boolean result=generateCouponDAO.saveConfiguration(saveConfiguration);
		if(result==true){
			List<String> coupons= CouponCodeUtil.generateCoupons(saveConfiguration);
			
			System.out.println(coupons);
			Iterator<String> itr =  coupons.iterator();
			while(itr.hasNext()){
				Coupon c =  new Coupon();
				c.setCouponCode(itr.next());
				c.setRedeemStatus(CouponStatus.NEW.toString());
				boolean isCreated = couponDAO.createGeneratedCouponData(c);
			}
			
			modelMap.put("result", "success");
			modelMap.put("message", "Successfully Created");
		}else{
			modelMap.put("result", "failure");
			modelMap.put("message", "Failed to create.");
		}
		return PageView.CREATECOUPONS;
	}
	
}
