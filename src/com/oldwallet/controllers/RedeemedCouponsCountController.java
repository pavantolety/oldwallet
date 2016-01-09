package com.oldwallet.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oldwallet.dao.TransactionDAO;
import com.oldwallet.model.MonthlyCouponsCount;
import com.oldwallet.model.MonthlyRedeemCouponsCount;

@Controller
public class RedeemedCouponsCountController {
	
	@Autowired
	TransactionDAO transactionDAO;

	private static Logger log = Logger.getLogger(RedeemedCouponsCountController.class);
	@RequestMapping(value="/couponsCountByMonth", method=RequestMethod.GET)
	public void redeemedCouponsCountByMonth(ModelMap modelMap) {
		log.debug("Beginning of redeemedCouponsCountByMonth ::");
		
		List<MonthlyRedeemCouponsCount> couponsCounts1 = transactionDAO.getRedeemedCouponsCountByMonth();
		
		modelMap.put("couponsCount", couponsCounts1);

		List<MonthlyCouponsCount> couponsCounts = transactionDAO.getMonthlyCouponsCount();
		
		List<MonthlyCouponsCount> couponsCounts2 = transactionDAO.getExpiredMonthlyCouponsCount();
	     String [] months =  new String[12];
	     months[0]="January";months[1]="February";months[2]="March";months[3]="April";months[4]="May";
	     months[5]="June";months[6]="July";months[7]="August";months[8]="September";months[9]="October";
	     months[10]="November";months[11]="December";
	     modelMap.put("months",months);
	     int [] redeemedCount =  new int[12];
	     int [] coupons =  new int[12];
	     int [] expiredCoupons =  new int[12];
	     if(couponsCounts1.size()>0){
	     for(MonthlyRedeemCouponsCount mc :couponsCounts1){
	    	 //TODO need to write in the for loop
		        if(NumberUtils.toInt(mc.getMonth())!=0&&mc.getMonth()!=null){
		        	if(NumberUtils.toInt(mc.getMonth())==1){
		        		System.out.println("coupons s >>>>>>>>>>>>"+coupons[0]);
		        		if(redeemedCount[0]==0){
		        			redeemedCount[0]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==2){
		        		if(redeemedCount[1]==0){
		        			redeemedCount[1]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==3){
		        		if(redeemedCount[2]==0){
		        			redeemedCount[2]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==4){
		        		if(redeemedCount[3]==0){
		        			redeemedCount[3]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==5){
		        		if(redeemedCount[4]==0){
		        			redeemedCount[4]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==6){
		        		if(redeemedCount[5]==0){
		        			redeemedCount[5]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==7){
		        		if(redeemedCount[6]==0){
		        			redeemedCount[6]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==8){
		        		if(redeemedCount[7]==0){
		        			redeemedCount[7]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==9){
		        		if(redeemedCount[8]==0){
		        			redeemedCount[8]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==10){
		        		if(redeemedCount[9]==0){
		        			redeemedCount[9]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==11){
		        		if(redeemedCount[10]==0){
		        			redeemedCount[10]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==12){
		        		if(redeemedCount[11]==0){
		        			redeemedCount[11]=NumberUtils.toInt(mc.getCount());
		        		}
		        	}
		        }
		     }
	     }
	     if(couponsCounts2.size()>0){
	     for(MonthlyCouponsCount mc :couponsCounts2){
	    	 //TODO need to write in the for loop
		        if(NumberUtils.toInt(mc.getMonth())!=0&&mc.getMonth()!=null){
		        	if(NumberUtils.toInt(mc.getMonth())==1){
		        		System.out.println("coupons s >>>>>>>>>>>>"+coupons[0]);
		        		if(expiredCoupons[0]==0){
		        			expiredCoupons[0]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==2){
		        		if(expiredCoupons[1]==0){
		        			expiredCoupons[1]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==3){
		        		if(expiredCoupons[2]==0){
		        			expiredCoupons[2]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==4){
		        		if(expiredCoupons[3]==0){
		        			expiredCoupons[3]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==5){
		        		if(expiredCoupons[4]==0){
		        			expiredCoupons[4]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==6){
		        		if(expiredCoupons[5]==0){
		        			expiredCoupons[5]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==7){
		        		if(expiredCoupons[6]==0){
		        			expiredCoupons[6]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==8){
		        		if(expiredCoupons[7]==0){
		        			expiredCoupons[7]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==9){
		        		if(expiredCoupons[8]==0){
		        			expiredCoupons[8]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==10){
		        		if(expiredCoupons[9]==0){
		        			expiredCoupons[9]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==11){
		        		if(expiredCoupons[10]==0){
		        			expiredCoupons[10]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}if(NumberUtils.toInt(mc.getMonth())==12){
		        		if(expiredCoupons[11]==0){
		        			expiredCoupons[11]=NumberUtils.toInt(mc.getToltalCouponsCount());
		        		}
		        	}
		        }
		     }
	     }
	     if(couponsCounts.size()>0){
	     for(MonthlyCouponsCount mc :couponsCounts){
	    	 // TODO need to write in the for loop
	        if(NumberUtils.toInt(mc.getMonth())!=0&&mc.getMonth()!=null){
	        	if(NumberUtils.toInt(mc.getMonth())==1){
	        		System.out.println("coupons s >>>>>>>>>>>>"+coupons[0]);
	        		if(coupons[0]==0){
	        		coupons[0]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==2){
	        		if(coupons[1]==0){
	        		coupons[1]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==3){
	        		if(coupons[2]==0){
	        		coupons[2]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==4){
	        		if(coupons[3]==0){
	        		coupons[3]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==5){
	        		if(coupons[4]==0){
	        		coupons[4]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==6){
	        		if(coupons[5]==0){
	        		coupons[5]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==7){
	        		if(coupons[6]==0){
	        		coupons[6]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==8){
	        		if(coupons[7]==0){
	        		coupons[7]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==9){
	        		if(coupons[8]==0){
	        		coupons[8]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==10){
	        		if(coupons[9]==0){
	        		coupons[9]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==11){
	        		if(coupons[10]==0){
	        		coupons[10]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==12){
	        		if(coupons[11]==0){
	        		coupons[11]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}
	        }
	     }
	     }
	     modelMap.put("months",months);
	     modelMap.put("expiredCoupons", expiredCoupons);
	     modelMap.put("redeemedCount",redeemedCount);
	     modelMap.put("coupons", coupons);
	    
		log.debug("End of redeemedCouponsCountByMonth ::");
	}
	
	@RequestMapping(value="/getAllCouponsByMonth", method=RequestMethod.GET)
	public void getAllCouponsByMonth(ModelMap modelMap) {
		log.debug("Beginning of redeemedCouponsCountByMonth ::");
		
		List<MonthlyCouponsCount> couponsCounts = transactionDAO.getMonthlyCouponsCount();
		List<MonthlyCouponsCount> couponsCounts2 = transactionDAO.getExpiredMonthlyCouponsCount();
	     String [] months =  new String[12];
	     months[0]="January";months[1]="February";months[2]="March";months[3]="April";months[4]="May";
	     months[5]="June";months[6]="July";months[7]="August";months[8]="September";months[9]="October";
	     months[10]="November";months[11]="December";
	     modelMap.put("months",months);
	     int [] coupons =  new int[12];
	     
	     for(MonthlyCouponsCount mc :couponsCounts){
	    	 
	        if(NumberUtils.toInt(mc.getMonth())!=0&&mc.getMonth()!=null){
	        	if(NumberUtils.toInt(mc.getMonth())==1){
	        		System.out.println("coupons s >>>>>>>>>>>>"+coupons[0]);
	        		if(coupons[0]==0){
	        		coupons[0]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==2){
	        		if(coupons[1]==0){
	        		coupons[1]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==3){
	        		if(coupons[2]==0){
	        		coupons[2]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==4){
	        		if(coupons[3]==0){
	        		coupons[3]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==5){
	        		if(coupons[4]==0){
	        		coupons[4]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==6){
	        		if(coupons[5]==0){
	        		coupons[5]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==7){
	        		if(coupons[6]==0){
	        		coupons[6]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==8){
	        		if(coupons[7]==0){
	        		coupons[7]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==9){
	        		if(coupons[8]==0){
	        		coupons[8]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==10){
	        		if(coupons[9]==0){
	        		coupons[9]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==11){
	        		if(coupons[10]==0){
	        		coupons[10]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}if(NumberUtils.toInt(mc.getMonth())==12){
	        		if(coupons[11]==0){
	        		coupons[11]=NumberUtils.toInt(mc.getToltalCouponsCount());
	        		}
	        	}
	        }
	     }
	     modelMap.put("months",months);
	     modelMap.put("coupons", coupons);
	    
	     modelMap.put("couponsCount", couponsCounts);
		
		log.debug("End of redeemedCouponsCountByMonth ::");
	}
}
