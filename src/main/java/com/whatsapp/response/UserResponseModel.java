package com.whatsapp.response;

public class UserResponseModel {
	private long id;
	private String userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String mobilePhone;
	private String userType;
	private String userStatus;

	private int totalPages;
	private String profilePicture;

	private String offline;

	public String getOffline() {
		return offline;
	}

	public void setOffline(String offline) {
		this.offline = offline;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
	
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
}
