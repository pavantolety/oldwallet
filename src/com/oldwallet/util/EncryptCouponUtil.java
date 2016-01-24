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

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.oldwallet.config.SystemParams;
import com.oldwallet.dao.ExceptionObjDAO;

public class EncryptCouponUtil {
	
	private static final Logger LOGGER = Logger.getLogger(EncryptCouponUtil.class);
	
	private static final String FILE_NAME = "EncryptCouponUtil.java";
	
	private static final String METHOD_NAME1 = "enccd";
	
	private static final String METHOD_NAME2 = "deccd";
	
	private static final String METHOD_NAME3 = "checkcd";
	
	@Autowired
	static
	ExceptionObjDAO exceptionDAO;

	private EncryptCouponUtil() {

	}

	@SuppressWarnings("deprecation")
	public static String enccd(String couponCode) {
		Key aesKey = new SecretKeySpec(SystemParams.SECRET_KEY.toString()
				.getBytes(), SystemParams.ENCRYP_ALGO.toString());
		Cipher cipher;
		byte[] encrypted = null;
		try {
			cipher = Cipher.getInstance(SystemParams.ENCRYP_ALGO.toString());
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			encrypted = cipher.doFinal(couponCode.getBytes());
		} catch (NoSuchAlgorithmException  e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("NoSuchAlgorithmException", e.getMessage(), FILE_NAME, METHOD_NAME1);
		}catch (NoSuchPaddingException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("NoSuchPaddingException", e.getMessage(), FILE_NAME, METHOD_NAME1);
		}
		// encrypt the text
		catch (InvalidKeyException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("InvalidKeyException", e.getMessage(), FILE_NAME, METHOD_NAME1);
		} catch (IllegalBlockSizeException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("IllegalBlockSizeException", e.getMessage(), FILE_NAME, METHOD_NAME1);
		} catch (BadPaddingException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("BadPaddingException", e.getMessage(), FILE_NAME, METHOD_NAME1);
		}

		return new BASE64Encoder().encode(encrypted);

	}

	@SuppressWarnings("deprecation")
	public static String deccd(String enCouponCode) {
		Key aesKey = new SecretKeySpec(SystemParams.SECRET_KEY.toString()
				.getBytes(), SystemParams.ENCRYP_ALGO.toString());
		Cipher cipher;
		byte[] decordedValue;
		byte[] decorded = null;
		try {
			cipher = Cipher.getInstance(SystemParams.ENCRYP_ALGO.toString());
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			decordedValue = new BASE64Decoder().decodeBuffer(enCouponCode);
			decorded = cipher.doFinal(decordedValue);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("NoSuchAlgorithmException", e.getMessage(), FILE_NAME, METHOD_NAME2);
		} catch (NoSuchPaddingException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("NoSuchPaddingException", e.getMessage(), FILE_NAME, METHOD_NAME2);
		} catch (InvalidKeyException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("InvalidKeyException", e.getMessage(), FILE_NAME, METHOD_NAME2);
		} catch (IOException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("IOException", e.getMessage(), FILE_NAME, METHOD_NAME2);
		} catch (IllegalBlockSizeException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("IllegalBlockSizeException", e.getMessage(), FILE_NAME, METHOD_NAME2);
		} catch (BadPaddingException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("BadPaddingException", e.getMessage(), FILE_NAME, METHOD_NAME2);
		}

		return new String(decorded);
	}

	@SuppressWarnings("deprecation")
	public static boolean checkcd(String pCoupon, String enCouponCode) {
		Key aesKey = new SecretKeySpec(SystemParams.SECRET_KEY.toString()
				.getBytes(), SystemParams.ENCRYP_ALGO.toString());
		Cipher cipher;
		boolean isDecoded = false;
		try {
			cipher = Cipher.getInstance(SystemParams.ENCRYP_ALGO.toString());
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decordedValue = new BASE64Decoder()
					.decodeBuffer(enCouponCode);
			byte[] decorded = cipher.doFinal(decordedValue);
			if (pCoupon.equalsIgnoreCase(new String(decorded))) {
				isDecoded = true;
			}
		} catch (NoSuchAlgorithmException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("NoSuchAlgorithmException", e.getMessage(), FILE_NAME, METHOD_NAME3);
		} catch (NoSuchPaddingException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("NoSuchPaddingException", e.getMessage(), FILE_NAME, METHOD_NAME3);
		} catch (InvalidKeyException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("InvalidKeyException", e.getMessage(), FILE_NAME, METHOD_NAME3);
		} catch (IOException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("IOException", e.getMessage(), FILE_NAME, METHOD_NAME3);
		} catch (IllegalBlockSizeException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("IllegalBlockSizeException", e.getMessage(), FILE_NAME, METHOD_NAME3);
		} catch (BadPaddingException e) {
			LOGGER.log(Priority.ERROR, e);
			exceptionDAO.saveException("BadPaddingException", e.getMessage(), FILE_NAME, METHOD_NAME3);
		}

		return isDecoded;
	}

}
