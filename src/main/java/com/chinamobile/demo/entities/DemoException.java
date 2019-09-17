package com.chinamobile.demo.entities;

import com.chinamobile.demo.enums.RunStatusCodeEnum;

public class DemoException extends Exception{
	/**
	 * 自定义异常信息
	 */
	private String msg;
	/**
	 * 状态码
	 */
	private int code = 500;


	public DemoException(RunStatusCodeEnum errorCodeEnum) {
		super(errorCodeEnum.toString());
		this.msg = errorCodeEnum.getMsg();
		this.code = errorCodeEnum.getCode();
	}

	/**
	 * 获取异常信息
	 * @return
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 获取状态码
	 * @return
	 */
	public int getCode() {
		return code;
	}
}
