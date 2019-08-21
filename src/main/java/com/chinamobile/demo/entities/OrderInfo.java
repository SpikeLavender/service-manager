package com.chinamobile.demo.entities;

import java.util.Date;
import java.util.List;

public class OrderInfo {
	private long id;
	private long userInfoId;
	private String serviceName;
	private String serviceLevel;
	private String sliceType;
	private List<Date> orderTime;
	private List<String> areaList;
	private List<String> userList;
	private List<String> appList;
	private Date create_time;
	private Date update_time;
	private String orderStatus;
	private Double fee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(long userInfoId) {
		this.userInfoId = userInfoId;
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

	public List<Date> getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(List<Date> orderTime) {
		this.orderTime = orderTime;
	}

	public List<String> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<String> areaList) {
		this.areaList = areaList;
	}

	public List<String> getUserList() {
		return userList;
	}

	public void setUserList(List<String> userList) {
		this.userList = userList;
	}

	public List<String> getAppList() {
		return appList;
	}

	public void setAppList(List<String> appList) {
		this.appList = appList;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
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
