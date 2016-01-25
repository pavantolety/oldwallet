package com.oldwallet.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.dao.ExceptionObjDAO;
import com.oldwallet.dao.GenerateCouponDAO;
import com.oldwallet.enums.CouponStatus;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.FundAllocation;
import com.oldwallet.model.FundAllocationForm;
import com.oldwallet.model.SaveConfiguration;
import com.oldwallet.util.CouponCodeUtil;
import com.oldwallet.util.EncryptCouponUtil;

@Controller
public class GenerateCouponsController {
	
	private static final String RESULT = "result";
	private static final String MESSAGE = "message";
	private static final String FILE_NAME = "GenerateCouponsController";
	
	private static final Logger LOGGER = Logger.getLogger(GenerateCouponsController.class);
	
	@Autowired
	GenerateCouponDAO generateCouponDAO;
	
	@Autowired
	CouponDAO couponDAO;
	
	@Autowired
	ExceptionObjDAO exceptionDAO;
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/saveConfiguration", method = RequestMethod.POST)
	public String saveConfiguration(ModelMap modelMap,SaveConfiguration saveConfiguration) {
		boolean result=generateCouponDAO.saveConfiguration(saveConfiguration);
		if(result==true){
			List<String> coupons= CouponCodeUtil.generateCoupons(saveConfiguration);
			Iterator<String> itr =  coupons.iterator();
			while(itr.hasNext()){
				Coupon c =  new Coupon();
				SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					c.setValidFrom(format2.format(format1.parse(saveConfiguration.getValidFrom())));
				} catch (ParseException e1) {
					exceptionDAO.saveException("From Date Parse Exception", e1.getMessage(), FILE_NAME, "saveConfiguration");
					e1.printStackTrace();
				}
				try {
					c.setValidTo(format2.format(format1.parse(saveConfiguration.getValidTo())));
				} catch (ParseException e1) {
					exceptionDAO.saveException("To Date Parse Exception", e1.getMessage(), FILE_NAME, "saveConfiguration");
					e1.printStackTrace();
				}
				String securedEncryptCouponCode;
				try {
					securedEncryptCouponCode = EncryptCouponUtil.enccd(itr.next().toUpperCase());
					
					c.setCouponCode(securedEncryptCouponCode);
				} catch (Exception e) {
					LOGGER.log(Priority.ERROR, e);
					e.printStackTrace();
					exceptionDAO.saveException("Coupon Encryption Exception", e.getMessage(), "GenerateCouponsController.java", "saveConfiguration");
				} 
				
				c.setRedeemStatus(CouponStatus.NEW.toString());
			    couponDAO.createGeneratedCouponData(c);
			}
			
			modelMap.put(RESULT, "success");
			modelMap.put(MESSAGE, "Successfully Created");
		}else{
			modelMap.put(RESULT, "failure");
			modelMap.put(MESSAGE, "Failed to create.");
		}
		return PageView.CREATECOUPONS;
	}
	
	@RequestMapping(value="/addFundAllocation", method = RequestMethod.POST)
	public String saveFundForCoupon(ModelMap modelMap,@ModelAttribute("fundAllocationForm") FundAllocationForm fundAllocationForm){
		boolean isCreated =  false;
		if(fundAllocationForm!=null){
			List<FundAllocation> fundAllocation = fundAllocationForm.getFundAllocation();
			
			if(fundAllocation!=null && fundAllocation.size()>0){
				for(FundAllocation fundAllocationData : fundAllocation){
					System.out.println("1"+fundAllocationData.getCategoryCode());
					System.out.println("2"+fundAllocationData.getCouponValue());
					if(fundAllocationData !=null){
						FundAllocation fd = couponDAO.getFundAllocationDataByCode(fundAllocationData.getCategoryCode());
						if(fd==null){
					     isCreated = couponDAO.createFundAllocation(fundAllocationData);
						}else{
							fd.setAvailableCount((NumberUtils.toLong(fd.getAvailableCount())+NumberUtils.toLong(fundAllocationData.getTotalCouponCount()))+"");
							fd.setTotalCouponCount((NumberUtils.toLong(fd.getTotalCouponCount())+NumberUtils.toLong(fundAllocationData.getTotalCouponCount()))+"");
							fd.setCouponValue((NumberUtils.toLong(fd.getCouponValue())+NumberUtils.toLong(fundAllocationData.getCouponValue()))+"");
							fd.setRedeemedCount((NumberUtils.toLong(fd.getRedeemedCount())+NumberUtils.toLong(fundAllocationData.getTotalCouponCount()))+"");
							
							isCreated = couponDAO.updateFundAllocationData(fd);
						}
					
					}
				}
				if(isCreated){
					modelMap.put(RESULT, "success");
					modelMap.put(MESSAGE, "Successfully Created");
				}else{
					modelMap.put(RESULT, "failure");
					modelMap.put(MESSAGE, "Successfully Created");
				}
			}
		}
		return "";
	}
}
