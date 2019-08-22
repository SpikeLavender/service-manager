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

import javax.servlet.http.HttpServletRequest;
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
	                              HttpServletRequest httpServletRequest) {
		try {
			logger.debug("order5G start");
			String token = httpServletRequest.getHeader("token");
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
	public ResponseEntity listOrder(HttpServletRequest httpServletRequest) {
		try {
			logger.debug("listOrder start");
			String token = httpServletRequest.getHeader("token");
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
	public void login(HttpServletRequest httpServletRequest){
		try {
			String token = httpServletRequest.getHeader("token");
			tokenManagerService.logout(token);
			logger.info("logout success");
		} catch (Exception e) {
			logger.error("logout fail,", e.getMessage());
		}
	}
}
