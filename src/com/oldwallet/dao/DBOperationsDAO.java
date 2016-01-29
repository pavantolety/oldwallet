package com.oldwallet.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface DBOperationsDAO {
	
	public boolean createDBOperation(String fileName, String sourceMethod, String method, String operationResult);
}
