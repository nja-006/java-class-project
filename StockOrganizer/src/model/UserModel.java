package model;

import java.util.Date;

public class UserModel {
	private int adminID;
	private String adminName,adminEmail,adminPassword;
	private Date adminDOB;
	public int getAdminID() {
		return adminID;
	}
	
	
	public UserModel(int adminID, String adminName, String adminEmail, String adminPassword, Date adminDOB) {
		super();
		this.adminID = adminID;
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.adminDOB = adminDOB;
	}


	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	
	public String getAdminName() {
		return adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getAdminEmail() {
		return adminEmail;
	}
	
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	
	public String getAdminPassword() {
		return adminPassword;
	}
	
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	public Date getAdminDOB() {
		return adminDOB;
	}
	
	public void setAdminDOB(Date adminDOB) {
		this.adminDOB = adminDOB;
	}
	
	

}
