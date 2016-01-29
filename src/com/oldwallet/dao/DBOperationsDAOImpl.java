package com.oldwallet.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DBOperationsDAOImpl implements DBOperationsDAO{

	private static final String CREATE_DB_OPERATIONS_LOG = "INSERT INTO DB_OPERATIONS_LOG (SOURCE_FILE,SOURCE_METHOD,METHOD,RESULT,DATE_CREATED) VALUES(?,?,?,?,NOW())";
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public boolean createDBOperation(String fileName, String sourceMethod, String method, String operationResult) {
		// TODO Auto-generated method stub
		boolean isUpdated = false;
		int result = jdbcTemplate.update(CREATE_DB_OPERATIONS_LOG,fileName,sourceMethod,method,operationResult);
		if(result>0){
			isUpdated = true;
		}
		return isUpdated;
	}
	
	
}
