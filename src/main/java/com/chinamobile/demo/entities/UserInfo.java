package com.chinamobile.demo.entities;


import io.swagger.annotations.ApiModelProperty;

import java.util.Date;


/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
public class UserInfo {
	@ApiModelProperty(hidden = true)
	private long id;

	@ApiModelProperty(required = true, example = "hetengjiao")
	private String username;

	@ApiModelProperty(required = true, example = "hetengjiao")
	private String password;

	@ApiModelProperty(hidden = true)
	private String token;

	@ApiModelProperty(hidden = true)
	private Date createTime;

	@ApiModelProperty(hidden = true)
	private Date updateTime;

	@ApiModelProperty(hidden = true)
	private Long expireTime;

	public UserInfo() {

	}

	public UserInfo(String token) {
		this.token = token;
	}

	public UserInfo(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
}
