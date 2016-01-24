package com.oldwallet.dao;


public interface ExceptionObjDAO {

	public boolean saveException(String exceptionName,String exceptionMessage, String sourceFile, String sourceMethod);
}
