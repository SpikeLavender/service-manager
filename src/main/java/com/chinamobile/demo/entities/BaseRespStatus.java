package com.chinamobile.demo.entities;


/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 * TODO: 待优化
 *
 */
public enum BaseRespStatus {

	REPEAT(1101,"数据库内容已存在"),
	SUCCESS(200, "request success"),
	ERROR_REQUEST(1400, "错误的请求"),
	UNAUTHORIZED(1401, "没有授权"),
	FORBIDDEN(1403, "没有权限访问"),
	ERROR_USERNAME(14031, "用户名错误"),
	ERROR_PASSWORD(14032, "密码错误"),
	TOKEN_EXPIRED(14033, "TOKEN过期"),
	NOT_FOND(1404, "页面不存在"),
	SERVER_ERROR(1500, "服务器错误"),
	ERROR(9999, "其他异常");

	private Integer code;
	private String message;

	BaseRespStatus(Integer code, String message) {
		this.code = code;
		this.message = message;
	}


	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
