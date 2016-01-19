package com.oldwallet.controllers;

import java.util.Iterator;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.constraints.PageView;
import com.oldwallet.dao.CouponDAO;
import com.oldwallet.dao.GenerateCouponDAO;
import com.oldwallet.enums.CouponStatus;
import com.oldwallet.model.Coupon;
import com.oldwallet.model.SaveConfiguration;
import com.oldwallet.util.CouponCodeUtil;

@Controller
public class GenerateCouponsController {
	
	private static final String RESULT = "result";
	private static final String MESSAGE = "message";
	
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
				String securedEncryptCouponCode =  BCrypt.hashpw(itr.next(),BCrypt.gensalt(12));
				c.setCouponCode(securedEncryptCouponCode);
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
	
}
