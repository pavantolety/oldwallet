package com.oldwallet.model;

import java.io.Serializable;

public class AdminSession implements Serializable {

	private static final long serialVersionUID = 614203144845463226L;

	private long id;
	private String emailAddress;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
