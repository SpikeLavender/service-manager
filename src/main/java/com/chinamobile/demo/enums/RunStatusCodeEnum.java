package com.chinamobile.demo.enums;

public enum RunStatusCodeEnum {
	/**
	 * 成功状态码
	 */
	SUCCESS(200, "success"),
	/**
	 * 参数为空
	 */
	PARAM_EMPTY(400001, "PARAM EMPTY"),
	/**
	 * 订单不存在
	 */
	ORDER_NOT_EXIST(400200, "order is not exist"),
	/**
	 * 激活时间不合法
	 */
	ACTIVE_TIME_UNVAILD(400300, "time is unvaild"),
	/**
	 * 登录失败
	 */
	LOGIN_FAIL(401000, "Authorized Failed, user name or password error"),
	/**
	 * token 过期
	 */
	TOKEN_EXPIRE(401001, "Authorize fail, the token has expire"),
	/**
	 * 不合法的用户
	 */
	TOKEN_UNVAILD(401002, "Authorized Failed: the token is unvaild"),
	/**
	 * 系统异常
	 */
	SYSTEM_ERROR(500, "Server Inter error: ");
	/**
	 * 状态码
	 */
	private Integer code;
	/**
	 * 异常信息
	 */
	private String msg;

	/**
	 * 异常枚举信息
	 *
	 * @param code 状态码
	 * @param msg  信息
	 */
	RunStatusCodeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 获取状态码
	 *
	 * @return
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 获取信息
	 *
	 * @return
	 */
	public String getMsg() {
		return msg;
	}


	/**
	 *重写toString方法在控制台显示自定义异常信息
	 * @return
	 */
	@Override
	public String toString() {
		return "[异常码:"+this.code+" 异常信息:"+this.msg+"]";
	}
}
