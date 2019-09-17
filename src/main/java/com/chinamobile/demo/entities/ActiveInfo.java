package com.chinamobile.demo.entities;

import com.chinamobile.demo.utils.CommonUtil;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class ActiveInfo {

	@ApiModelProperty(hidden = true)
	private long id;

	@ApiModelProperty(hidden = true)
	private long orderId;

	@ApiModelProperty(example = "1200")
	private Long activeDuration = null;

	@ApiModelProperty(example = "1566460103997|1566462073997")
	private String activeTime;

	@ApiModelProperty(hidden = true)
	private Date startTime = null;

	@ApiModelProperty(hidden = true)
	private Date endTime = null;

	@ApiModelProperty(hidden = true)
	private CommonEnums.ActiveStatusEnum activeStatus = CommonEnums.ActiveStatusEnum.WAITING;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Long getActiveDuration() {
		return activeDuration;
	}

	public void setActiveDuration(Long activeDuration) {
		this.activeDuration = activeDuration;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public Date getStartTime() {
		if (startTime == null) {
			setStartTime(null);
		}
		return startTime;
	}

	public void setStartTime(Date startTime) {
		if (startTime == null) {
			if (CommonUtil.isStrNotEmpty(this.activeTime)) {
				this.startTime = new Date(Long.parseLong(activeTime.split("\\|")[0]));
			} else if (this.activeDuration != null) {
				this.startTime = new Date();
			}
		} else {
			this.startTime = startTime;
		}
	}

	public Date getEndTime() {
		if (endTime == null) {
			setEndTime(null);
		}
		return endTime;
	}

	public void setEndTime(Date endTime) {
		if (endTime == null) {
			if (CommonUtil.isStrNotEmpty(this.activeTime)) {
				this.endTime = new Date(Long.parseLong(activeTime.split("\\|")[1]));
			} else if (this.activeDuration != null) {
				this.endTime = new Date(System.currentTimeMillis() + this.activeDuration);
			}
		} else {
			this.endTime = endTime;
		}
	}

	public CommonEnums.ActiveStatusEnum getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(CommonEnums.ActiveStatusEnum activeStatus) {
		this.activeStatus = activeStatus;
	}
}
