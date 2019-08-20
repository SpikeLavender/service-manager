package com.chinamobile.demo.entities;


public class IdentityUser {
	private Long userId;
	private String userName;


	public IdentityUser() {

	}

	public IdentityUser(String userName) {
		this.userName = userName;
	}

	public IdentityUser(Long userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
