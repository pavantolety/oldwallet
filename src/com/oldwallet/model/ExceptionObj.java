package com.oldwallet.model;

public class ExceptionObj {

	private long id;
	private String exceptionName;
	private String exceptionMessage;
	private String exceptionSourceFile;
	private String exceptionSourceMethod;
	private String dateCreated;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExceptionName() {
		return exceptionName;
	}

	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getExceptionSourceFile() {
		return exceptionSourceFile;
	}

	public void setExceptionSourceFile(String exceptionSourceFile) {
		this.exceptionSourceFile = exceptionSourceFile;
	}

	public String getExceptionSourceMethod() {
		return exceptionSourceMethod;
	}

	public void setExceptionSourceMethod(String exceptionSourceMethod) {
		this.exceptionSourceMethod = exceptionSourceMethod;
	}

}
