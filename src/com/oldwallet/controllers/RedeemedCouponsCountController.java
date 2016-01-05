package com.oldwallet.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.dao.TransactionDAO;
import com.oldwallet.model.MonthlyRedeemCouponsCount;

@Controller
public class RedeemedCouponsCountController {
	
	@Autowired
	TransactionDAO transactionDAO;

	private static Logger log = Logger.getLogger(RedeemedCouponsCountController.class);
	@RequestMapping(value="/couponsCountByMonth", method=RequestMethod.GET)
	public void redeemedCouponsCountByMonth(ModelMap modelMap) {
		log.debug("Beginning of redeemedCouponsCountByMonth ::");
		
		List<MonthlyRedeemCouponsCount> couponsCounts = transactionDAO.getRedeemedCouponsCountByMonth();
		
		modelMap.put("couponsCount", couponsCounts);
		
		log.debug("End of redeemedCouponsCountByMonth ::");
	}
}
