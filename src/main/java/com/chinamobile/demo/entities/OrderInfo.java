package com.chinamobile.demo.entities;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private long id;
	private long userId;
	private String serviceName;
	private String serviceLevel;
	private String sliceType;
	private String orderTime;
	private String areaList;
	private String userList;
	private String appList;
	private Date createTime;
	private Date updateTime;
	private String orderStatus;
	private Double fee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public String getSliceType() {
		return sliceType;
	}

	public void setSliceType(String sliceType) {
		this.sliceType = sliceType;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getAreaList() {
		return areaList;
	}

	public void setAreaList(String areaList) {
		this.areaList = areaList;
	}

	public String getUserList() {
		return userList;
	}

	public void setUserList(String userList) {
		this.userList = userList;
	}

	public String getAppList() {
		return appList;
	}

	public void setAppList(String appList) {
		this.appList = appList;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}
}
