package com.chinamobile.demo.entities;


import java.util.Date;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
public class UserInfo {
	private long id;
	private String userName;
	private String password;
	private Date createTime;
	private Date updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
