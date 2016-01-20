package com.oldwallet.controllers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
import com.oldwallet.util.EncryptCouponUtil;

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
			Iterator<String> itr =  coupons.iterator();
			while(itr.hasNext()){
				Coupon c =  new Coupon();
				String securedEncryptCouponCode;
				try {
					securedEncryptCouponCode = EncryptCouponUtil.enccd(itr.next());
					c.setCouponCode(securedEncryptCouponCode);
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
	
}
