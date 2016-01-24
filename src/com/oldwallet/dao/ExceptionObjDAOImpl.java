package com.oldwallet.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExceptionObjDAOImpl implements ExceptionObjDAO {

	private static final String SAVE_EXCEPTION = "INSERT INTO EXCEPTIONS_AUDIT_LOG (EXCEPTION_NAME, EXCEPTION_MESSAGE, EXCEPTION_SOURCE_FILE, EXCEPTION-SOURCE_METHOD) VALUES(?,?,?,?)";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean saveException(String exceptionName,String exceptionMessage, String sourceFile, String sourceMethod) {
		boolean isInserted = false;
		try {
		int result = jdbcTemplate.update(SAVE_EXCEPTION,exceptionName, exceptionMessage, sourceFile, sourceMethod);
		if (result > 0) {
			isInserted = true;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isInserted;
	}

}
