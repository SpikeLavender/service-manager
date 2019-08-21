package com.chinamobile.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.entities.ResponseEntity;
import com.chinamobile.demo.entities.UserInfo;
import com.chinamobile.demo.service.OrderManageService;
import com.chinamobile.demo.service.TokenManagerService;
import com.chinamobile.demo.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

	@PostMapping(value = "/login")
	public ResponseEntity login(@RequestBody UserInfo userInfo){
		try {
			Long id = tokenManagerService.authorize(userInfo);
			if (id == null) {
				return new ResponseEntity("40001", "user name or password error");
			}
			userInfo.setId(id);
			String token = tokenManagerService.login(userInfo);
			JSONObject resJson = new JSONObject();
			resJson.put("token", token);
			return new ResponseEntity("200", "get token success", resJson);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("40002", e.getMessage());
		}
	}

	@PostMapping(value = "/order5G")
	public ResponseEntity order5G(@RequestBody OrderInfo orderInfo,
	                              HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("token");
		try {
			Integer userId = tokenManagerService.authorizeToken(token);
			orderInfo.setUserId(userId);
			Long orderId = orderManageService.createOrder(orderInfo);
			JSONObject resJson = new JSONObject();
			resJson.put("orderId", orderId);
			return new ResponseEntity("200", "success", resJson);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("40002", e.getMessage());
		}
	}

	@GetMapping(value = "/listOrder")
	public ResponseEntity listOrder(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("token");
		try {
			Integer userId = tokenManagerService.authorizeToken(token);
			List<OrderInfo> orders = orderManageService.getOrder(userId);
			return new ResponseEntity("200", "success", orders);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("40002", e.getMessage());
		}
	}
}
