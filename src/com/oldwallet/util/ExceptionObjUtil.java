package com.oldwallet.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.oldwallet.dao.ExceptionObjDAO;
import com.oldwallet.model.ExceptionObj;

public final class ExceptionObjUtil {

	@Autowired
	static ExceptionObjDAO exceptionObjDAO;

	private ExceptionObjUtil() {

	}

	public static void saveException(String exceptionName,
			String exceptionMessage, String sourceFile, String sourceMethod) {

		ExceptionObj exceptionObj = new ExceptionObj();

		exceptionObj.setExceptionMessage(exceptionMessage);
		exceptionObj.setExceptionName(exceptionName);
		exceptionObj.setExceptionSourceFile(sourceFile);
		exceptionObj.setExceptionSourceMethod(sourceMethod);

		exceptionObjDAO.saveException(exceptionObj);
	}
}
