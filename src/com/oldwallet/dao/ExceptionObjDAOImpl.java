package com.oldwallet.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.oldwallet.model.ExceptionObj;

@Repository
public class ExceptionObjDAOImpl implements ExceptionObjDAO {

		private static final String SAVE_EXCEPTION = "INSERT INTO EXCEPTIONS_AUDIT_LOG (EXCEPTION_NAME, EXCEPTION_MESSAGE, EXCEPTION_SOURCE_FILE, EXCEPTION-SOURCE_METHOD) VALUES(?,?,?,?)";
		
		private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean saveException(ExceptionObj exceptionObj) {
		boolean isInserted = false;
		int result = jdbcTemplate.update(SAVE_EXCEPTION, exceptionObj.getExceptionName(), exceptionObj.getExceptionMessage(), exceptionObj.getExceptionSourceFile(), exceptionObj.getExceptionSourceMethod());
		if (result > 0) {
			isInserted = true;
		}
		return isInserted;
	}

}
