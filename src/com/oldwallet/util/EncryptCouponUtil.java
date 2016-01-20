package com.oldwallet.util;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.oldwallet.config.SystemParams;
public class EncryptCouponUtil {
	
	
	public static String enccd(String couponCode) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
		Key aesKey = new SecretKeySpec(SystemParams.SECRET_KEY.toString().getBytes(), SystemParams.ENCRYP_ALGO.toString());
		Cipher cipher = Cipher.getInstance(SystemParams.ENCRYP_ALGO.toString());
        // encrypt the text
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(couponCode.getBytes());
		return new BASE64Encoder().encode(encrypted);
		
	}
	public static String deccd(String enCouponCode) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Key aesKey = new SecretKeySpec(SystemParams.SECRET_KEY.toString().getBytes(), SystemParams.ENCRYP_ALGO.toString());
		Cipher cipher = Cipher.getInstance(SystemParams.ENCRYP_ALGO.toString());
		// now convert the string to byte array
        // for decryption
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(enCouponCode);
		byte[] decorded = cipher.doFinal(decordedValue);
      
		return  new String(decorded);
	}
	public static boolean  checkcd(String pCoupon , String enCouponCode) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException{
		Key aesKey = new SecretKeySpec(SystemParams.SECRET_KEY.toString().getBytes(), SystemParams.ENCRYP_ALGO.toString());
		Cipher cipher = Cipher.getInstance(SystemParams.ENCRYP_ALGO.toString());
		boolean isDecoded =  false;
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(enCouponCode);
		byte[] decorded = cipher.doFinal(decordedValue);
         if(pCoupon.equalsIgnoreCase(new String(decorded))){
        	 isDecoded =  true;
         }
		return isDecoded ;
	}
	
    
}
