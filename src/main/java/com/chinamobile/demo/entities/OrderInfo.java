package com.chinamobile.demo.entities;

import com.chinamobile.demo.entities.CommonEnums.ServiceLevelEnum;
import com.chinamobile.demo.entities.CommonEnums.SliceTypeEnum;
import com.chinamobile.demo.entities.CommonEnums.OrderStatusEnum;
import com.chinamobile.demo.entities.CommonEnums.ServiceTypeEnum;
import com.chinamobile.demo.utils.CommonUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
@ApiModel
public class OrderInfo {

	@ApiModelProperty(hidden = true)
	private long id;

	@ApiModelProperty(hidden = true)
	private long userId;

	@ApiModelProperty(example = "service")
	private String serviceName;

	@ApiModelProperty(example = "SHARED")
	private ServiceTypeEnum serviceType = ServiceTypeEnum.PRIVATE;

	@ApiModelProperty(example = "STANDARD")
	private ServiceLevelEnum serviceLevel = ServiceLevelEnum.STANDARD;

	@ApiModelProperty(example = "EMBB")
	private SliceTypeEnum sliceType = SliceTypeEnum.EMBB;

	@ApiModelProperty(example = "1566460103997|1566462073997")
	private String orderTime;

	@ApiModelProperty(example = "1200")
	private Long durationTime = null;

	@ApiModelProperty(example = "area1|area2|area3")
	private String areaList;

	@ApiModelProperty(example = "user1|user2|user3")
	private String userList;

	@ApiModelProperty(example = "app1|app2|app3")
	private String appList;

	@ApiModelProperty(hidden = true)
	private Date createTime;

	@ApiModelProperty(hidden = true)
	private Date updateTime;

	@ApiModelProperty(hidden = true)
	private OrderStatusEnum orderStatus;

	@ApiModelProperty(hidden = true)
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

	public ServiceTypeEnum getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceTypeEnum serviceType) {
		this.serviceType = serviceType;
	}

	public ServiceLevelEnum getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(ServiceLevelEnum serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public SliceTypeEnum getSliceType() {
		return sliceType;
	}

	public void setSliceType(SliceTypeEnum sliceType) {
		this.sliceType = sliceType;
	}

	public String getOrderTime() {
		if (CommonUtil.isStrEmpty(orderTime)){
			this.setOrderTime(orderTime);
		}
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		//first judge order time
		if (CommonUtil.isStrNotEmpty(orderTime)) {
			this.orderTime = orderTime;
		} else if (durationTime != null){
			Long endTime = System.currentTimeMillis() + durationTime * 60 * 1000;
			this.orderTime = System.currentTimeMillis() + "|" + endTime;
		}
	}

	public Long getDurationTime() {
		return Long.parseLong(orderTime.split("\\|")[1])
				- Long.parseLong(orderTime.split("\\|")[0]);
	}

	public void setDurationTime(Long durationTime) {
		this.durationTime = durationTime;
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

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}
}
