package com.chinamobile.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.entities.ResponseEntity;
import com.chinamobile.demo.entities.UserInfo;
import com.chinamobile.demo.service.OrderManageService;
import com.chinamobile.demo.service.TokenManagerService;
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
	@CrossOrigin(origins = "*")
	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody UserInfo userInfo){
		try {
			logger.debug("Start login, use name is " + userInfo.getUsername());
			Long id = tokenManagerService.authorize(userInfo);
			if (id == null) {
				logger.debug("login fail, user name or password error");
				return new ResponseEntity("401", "Authorized Failed: user name or password error");
			}
			userInfo.setId(id);
			String token = tokenManagerService.login(userInfo);
			JSONObject resJson = new JSONObject();
			resJson.put("token", token);
			logger.debug("login success, the user name is " + userInfo.getUsername());
			return new ResponseEntity("200", "get token success", resJson);
		} catch (Exception e) {
			logger.error("login fail, the user name is " + userInfo.getUsername(), e.getMessage());
			return new ResponseEntity("500", "Server Inter error" + e.getMessage());
		}
	}

	@CrossOrigin(origins = "*")
	@PostMapping(value = "/order5G")
	public ResponseEntity order5G(@RequestBody OrderInfo orderInfo,
	                              @RequestHeader String token) {
		try {
			logger.debug("order5G start");
			StringBuilder msg = new StringBuilder();
			Integer userId = tokenManagerService.authorizeToken(token, msg);
			if (userId == null) {
				logger.error("order5G fail,", msg);
				return new ResponseEntity("401", "Authorized Failed: " + msg.toString());
			}
			orderInfo.setUserId(userId);
			Long orderId = orderManageService.createOrder(orderInfo);
			JSONObject resJson = new JSONObject();
			resJson.put("orderId", orderId);
			resJson.put("fee", orderInfo.getFee());
			logger.debug("order5G success, orderId is " + orderId);
			return new ResponseEntity("200", "success", resJson);
		} catch (Exception e) {
			logger.error("order5G fail,", e.getMessage());
			return new ResponseEntity("500", "Server Inter error" + e.getMessage());
		}
	}

	@CrossOrigin(origins = "*")
	@GetMapping(value = "/listOrder")
	public ResponseEntity listOrder(@RequestHeader String token,
	                                @RequestParam(value = "page", required = false)
			                                    Integer page,
	                                @RequestParam(value = "size", required = false)
			                                    Integer size,
	                                @RequestParam(value = "status", required = false)
			                                    String status,
	                                @RequestParam(value = "level", required = false)
			                                    String level,
	                                @RequestParam(value = "type", required = false)
			                                    String type) {
		try {
			logger.debug("listOrder start");
			StringBuilder msg = new StringBuilder();
			Integer userId = tokenManagerService.authorizeToken(token, msg);
			if (userId == null) {
				logger.error("listOrder fail,", msg);
				return new ResponseEntity("401", "Authorized Failed: " + msg.toString());
			}
			List<OrderInfo> orders = orderManageService.getOrder(userId, page, size, status, level, type);
			logger.debug("listOrder success,", orders.toString());
			return new ResponseEntity("200", "success", orders);
		} catch (Exception e) {
			logger.error("listOrder fail,", e.getMessage());
			return new ResponseEntity("500", "Server Inter error" + e.getMessage());
		}
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping(value = "/logout")
	public ResponseEntity login(@RequestHeader String token){
		try {
			tokenManagerService.logout(token);
			logger.info("logout success");
			return new ResponseEntity("204", "logout success");
		} catch (Exception e) {
			logger.error("logout fail,", e.getMessage());
			return new ResponseEntity("500", "Server Inter error");
		}
	}
}
