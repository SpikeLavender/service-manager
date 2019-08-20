package com.chinamobile.demo.entities;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
public class Token {
	private String id;
	private String userName;
	private Long expireTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

}
