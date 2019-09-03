package com.chinamobile.demo.entities;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
public class ResponseEntity<T> {
	/**
	 *包装类用来传输外部数据
	 */

	/*状态码*/

	private Object statusCode;

	/*状态信息*/
	private String message;

	/*内容*/
	private T data;

	public ResponseEntity() {
	}

	public ResponseEntity(Object statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public ResponseEntity(Object statusCode, String message, T data) {
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public Object getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Object statusCode) {
		this.statusCode = statusCode;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public ResponseEntity addMessage(String message){
		this.message = message;
		return this;
	}


//	public ResponseEntity putData(String key, Object value) {
//		data.put(key, value);
//		return this;
//	}

	public static ResponseEntity repeat() {
		return new ResponseEntity(
				BaseRespStatus.REPEAT.getCode(),
				BaseRespStatus.REPEAT.getMessage());
	}

	public static ResponseEntity ok() {
		return new ResponseEntity(
				BaseRespStatus.SUCCESS.getCode(),
				BaseRespStatus.SUCCESS.getMessage());
	}

	public static ResponseEntity notFound() {
		return new ResponseEntity(
				BaseRespStatus.NOT_FOND.getCode(),
				BaseRespStatus.NOT_FOND.getMessage());    }

	public static ResponseEntity errorRequest() {
		return new ResponseEntity(
				BaseRespStatus.ERROR_REQUEST.getCode(),
				BaseRespStatus.ERROR_REQUEST.getMessage());    }

	public static ResponseEntity forbidden() {
		return new ResponseEntity(
				BaseRespStatus.FORBIDDEN.getCode(),
				BaseRespStatus.FORBIDDEN.getMessage());    }

	public static ResponseEntity unauthorized() {
		return new ResponseEntity(
				BaseRespStatus.UNAUTHORIZED.getCode(),
				BaseRespStatus.UNAUTHORIZED.getMessage());    }

	public static ResponseEntity serverError() {
		return new ResponseEntity(
				BaseRespStatus.SERVER_ERROR.getCode(),
				BaseRespStatus.SERVER_ERROR.getMessage());    }

	public static ResponseEntity error(){
		return new ResponseEntity(
				BaseRespStatus.ERROR.getCode(),
				BaseRespStatus.ERROR.getMessage());
	}

}
