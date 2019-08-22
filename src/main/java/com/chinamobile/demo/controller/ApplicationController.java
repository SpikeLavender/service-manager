package com.chinamobile.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.entities.ResponseEntity;
import com.chinamobile.demo.entities.UserInfo;
import com.chinamobile.demo.service.OrderManageService;
import com.chinamobile.demo.service.TokenManagerService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
@RestController
@RequestMapping(value = "/v1/openapi")
@Api(value = "ONS 5G业务开发系统")
public class ApplicationController {

	@Autowired
	TokenManagerService tokenManagerService;

	@Autowired
	OrderManageService orderManageService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * @ApiOperation(value = "接口说明", httpMethod ="接口请求方式", response ="接口返回参数类型", notes ="接口发布说明"
	 * @ApiParam(required = "是否必须参数", name ="参数名称", value ="参数具体描述"
	 */
	@ApiOperation(value = "login interface")
	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody UserInfo userInfo){
		try {
			logger.debug("Start login, use name is " + userInfo.getUsername());
			Long id = tokenManagerService.authorize(userInfo);
			if (id == null) {
				logger.info("login fail, user name or password error");
				return new ResponseEntity("40001", "user name or password error");
			}
			userInfo.setId(id);
			String token = tokenManagerService.login(userInfo);
			JSONObject resJson = new JSONObject();
			resJson.put("token", token);
			logger.debug("login success, the user name is " + userInfo.getUsername());
			return new ResponseEntity("200", "get token success", resJson);
		} catch (Exception e) {
			logger.error("login fail, the user name is " + userInfo.getUsername(), e.getMessage());
			return new ResponseEntity("40002", e.getMessage());
		}
	}

	@PostMapping(value = "/order5G")
	public ResponseEntity order5G(@RequestBody OrderInfo orderInfo,
	                              @RequestHeader String token) {
		try {
			logger.debug("order5G start");
			Integer userId = tokenManagerService.authorizeToken(token);
			orderInfo.setUserId(userId);
			Long orderId = orderManageService.createOrder(orderInfo);
			JSONObject resJson = new JSONObject();
			resJson.put("orderId", orderId);
			logger.debug("order5G success, orderId is " + orderId);
			return new ResponseEntity("200", "success", resJson);
		} catch (Exception e) {
			logger.error("order5G fail,", e.getMessage());
			return new ResponseEntity("40002", e.getMessage());
		}
	}

	@GetMapping(value = "/listOrder")
	public ResponseEntity listOrder(@RequestHeader String token) {
		try {
			logger.debug("listOrder start");
			Integer userId = tokenManagerService.authorizeToken(token);
			List<OrderInfo> orders = orderManageService.getOrder(userId);
			logger.debug("listOrder success,", orders.toString());
			return new ResponseEntity("200", "success", orders);
		} catch (Exception e) {
			logger.error("listOrder fail,", e.getMessage());
			return new ResponseEntity("40002", e.getMessage());
		}
	}

	@DeleteMapping(value = "/logout")
	public void login(@RequestHeader String token){
		try {
			tokenManagerService.logout(token);
			logger.info("logout success");
		} catch (Exception e) {
			logger.error("logout fail,", e.getMessage());
		}
	}
}
