package com.oldwallet.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.oldwallet.model.SaveConfiguration;

public class CouponCodeUtil {
	
	public static List<String> generateCoupons(SaveConfiguration saveConfiguration) {
		List<String> coupons = new ArrayList<String>();
		
		String part1Type = saveConfiguration.getTypeA();
		int part1Length = saveConfiguration.getTypeALength();
		String part2Type = saveConfiguration.getTypeB();
		int part2Length = saveConfiguration.getTypeBLength();
		String part3Type = saveConfiguration.getTypeC();
		int part3Length = saveConfiguration.getTypeCLength();
		long totalCoupons = saveConfiguration.getCouponCount();
		
		int numLength, charLength, alphaNumLenght;
		List<String> couponPart1 = null, couponPart2 = null, couponPart3 = null;
		
		if(part1Type.equalsIgnoreCase("Numeric") && part2Type.equalsIgnoreCase("Numeric") && part3Type.equalsIgnoreCase("Numeric")) {
			for(int i = 0; i< totalCoupons; i++) {
				String couponCode =  String.valueOf(saveConfiguration.getCouponLength() < 1 ? 0 : new Random()
		        .nextInt((9 * (int) Math.pow(10, saveConfiguration.getCouponLength() - 1)) - 1)
		        + (int) Math.pow(10, saveConfiguration.getCouponLength() - 1));
				coupons.add(couponCode);
			}
				
		} else if(part1Type.equalsIgnoreCase("Character") && part2Type.equalsIgnoreCase("Character") && part3Type.equalsIgnoreCase("Character")) {
			for(int i = 0; i< totalCoupons; i++) {
				List<String> couponCode =  getRandomCharacters((int)saveConfiguration.getCouponLength(), totalCoupons);
				coupons.addAll(couponCode);
			}
				
		} else if(part1Type.equalsIgnoreCase("AlphaNumeric") && part2Type.equalsIgnoreCase("AlphaNumeric") && part3Type.equalsIgnoreCase("AlphaNumeric")) {
			for(int i = 0; i< totalCoupons; i++) {
				List<String> couponCode = getRandomAlphaNumerics((int)saveConfiguration.getCouponLength(), totalCoupons);
				coupons.addAll(couponCode);
			}
				
		}  
		
		else {
			if(("Character").equalsIgnoreCase(part1Type)) {
				charLength = part1Length;
				couponPart1 = getRandomCharacters(charLength, totalCoupons);
				
			} else if(("Numeric").equalsIgnoreCase(part1Type)) {
				numLength = part1Length;
				couponPart1 = getRandomNumbers(numLength, totalCoupons);
			} else if(("AlphaNumeric").equalsIgnoreCase(part1Type)) {
				alphaNumLenght = part1Length;
				couponPart1 = getRandomAlphaNumerics(alphaNumLenght, totalCoupons);
			}
			
			if(("Character").equalsIgnoreCase(part2Type)) {
				charLength = part2Length;
				couponPart2 = getRandomCharacters(charLength, totalCoupons);
			} else if(("Numeric").equalsIgnoreCase(part2Type)) {
				numLength = part2Length;
				couponPart2 = getRandomNumbers(numLength, totalCoupons);
			} else if(("AlphaNumeric").equalsIgnoreCase(part2Type)) {
				alphaNumLenght = part2Length;
				couponPart2 = getRandomAlphaNumerics(alphaNumLenght, totalCoupons);
			}
			
			if(("Character").equalsIgnoreCase(part3Type)) {
				charLength = part3Length;
				couponPart3 = getRandomCharacters(charLength, totalCoupons);
			} else if(("Numeric").equalsIgnoreCase(part3Type)) {
				numLength = part3Length;
				couponPart3 = getRandomNumbers(numLength, totalCoupons);
			} else if(("AlphaNumeric").equalsIgnoreCase(part3Type)) {
				alphaNumLenght = part3Length;
				couponPart3 = getRandomAlphaNumerics(alphaNumLenght, totalCoupons);
			}
			if(couponPart1 != null && couponPart2 != null && couponPart3 != null) {
			coupons = generateFullCouponCode(couponPart1, couponPart2, couponPart3);
			}
			
		}
		
		return coupons;
	}
	
	public static List<String> getRandomNumbers(int numLength, long maxCount) {
		List<String> randomNums = new ArrayList<String>();
		
		for(long i = 0; i<maxCount; i++) {
			String num = RandomStringUtils.randomNumeric(numLength);
			randomNums.add(num);
		}
		
		return randomNums;
	}
	
	public static List<String> getRandomCharacters(int charLength, long maxCount) {
		List<String> randomChars = new ArrayList<String>();		
		for(long i = 0; i<maxCount; i++) {
			String chars = RandomStringUtils.randomAlphabetic(charLength);
			randomChars.add(chars);
		}
		return randomChars;		
	}
	
	public static List<String> getRandomAlphaNumerics(int alphaNumericLength, long maxCount) {
		List<String> randomAlphaNumerics = new ArrayList<String>();		
		for(long i = 0; i<maxCount; i++) {
			String alphaNumeric = RandomStringUtils.randomAlphanumeric(alphaNumericLength);
			randomAlphaNumerics.add(alphaNumeric);
		}
		return randomAlphaNumerics;		
	}
	
	public static List<String> generateFullCouponCode(List<String> part1, List<String> part2, List<String> part3 ) {
		List<String> coupons = new ArrayList<String>();
		
		for(int i = 0; i<part1.size(); i++) {
			String fullCoupon = part1.get(i)+part2.get(i)+part3.get(i);
			coupons.add(fullCoupon);					
		}
		
		return coupons;
	}

}
