package com.onlinePharmacySystem.model;

import java.sql.Date;

public class User {
	private int USER_ID;
	private String FULL_NAME;
	private String EMAIL;
	private String PASSWORD;
	private String USER_ROLE;
	private Date CREATED_AT;
	
	
	
	public User() {
		
	}

	public User(String fULL_NAME, String eMAIL, String pASSWORD, String uSER_ROLE, Date cREATED_AT) {
		super();
		FULL_NAME = fULL_NAME;
		EMAIL = eMAIL;
		PASSWORD = pASSWORD;
		USER_ROLE = uSER_ROLE;
		CREATED_AT = cREATED_AT;
	}
	
	public int getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(int uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getFULL_NAME() {
		return FULL_NAME;
	}
	public void setFULL_NAME(String fULL_NAME) {
		FULL_NAME = fULL_NAME;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getUSER_ROLE() {
		return USER_ROLE;
	}
	public void setUSER_ROLE(String uSER_ROLE) {
		USER_ROLE = uSER_ROLE;
	}
	public Date getCREATED_AT() {
		return CREATED_AT;
	}
	public void setCREATED_AT(Date cREATED_AT) {
		CREATED_AT = cREATED_AT;
	}

}
