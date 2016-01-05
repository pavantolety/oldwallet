package com.oldwallet.model;

import org.springframework.web.multipart.MultipartFile;

public class CouponData {

	private long id ;
    private MultipartFile file;
    private long eventId;
    private String couponCode;
    private String couponValue;
    private String couponHideLocation;
    private String reedemStatus;
    private String reedemedBy;
    private String reedemedDate;
    private String location;
    private double amount;
    private String validityPeriod;
    private String validFrom;
    private String validTo;
    
    
    
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getCouponValue() {
		return couponValue;
	}
	public void setCouponValue(String couponValue) {
		this.couponValue = couponValue;
	}
	public String getCouponHideLocation() {
		return couponHideLocation;
	}
	public void setCouponHideLocation(String couponHideLocation) {
		this.couponHideLocation = couponHideLocation;
	}
	public String getReedemStatus() {
		return reedemStatus;
	}
	public void setReedemStatus(String reedemStatus) {
		this.reedemStatus = reedemStatus;
	}
	public String getReedemedBy() {
		return reedemedBy;
	}
	public void setReedemedBy(String reedemedBy) {
		this.reedemedBy = reedemedBy;
	}
	public String getReedemedDate() {
		return reedemedDate;
	}
	public void setReedemedDate(String reedemedDate) {
		this.reedemedDate = reedemedDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getValidityPeriod() {
		return validityPeriod;
	}
	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}
	public String getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}
	public String getValidTo() {
		return validTo;
	}
	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
    
    
	
	
}