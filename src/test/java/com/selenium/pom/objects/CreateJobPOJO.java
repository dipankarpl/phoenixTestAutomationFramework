package com.selenium.pom.objects;

public class CreateJobPOJO {

	private Long imei;
	private String fname;
	private String lname;
	private Long contact;
	private String email;

	public CreateJobPOJO(Long imei, String fname, String lname, Long contact, String email) {
		super();
		this.imei = imei;
		this.fname = fname;
		this.lname = lname;
		this.contact = contact;
		this.email = email;
	}

	public Long getImei() {
		return imei;
	}

	public void setImei(Long imei) {
		this.imei = imei;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Long getContact() {
		return contact;
	}

	public void setContact(Long contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
