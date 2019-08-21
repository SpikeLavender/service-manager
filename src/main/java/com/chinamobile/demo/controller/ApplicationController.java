package com.chinamobile.demo.controller;


import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.entities.ResponseEntity;
import com.chinamobile.demo.entities.UserInfo;
import com.chinamobile.demo.service.OrderManageService;
import com.chinamobile.demo.service.TokenManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
	public ResponseEntity login(@RequestBody UserInfo user){
		try {
			return tokenManagerService.login(user);
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
			Long userId = tokenManagerService.authorizeToken(token);
			orderInfo.setUserInfoId(userId);
			orderManageService.createOrder(orderInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity("40002", "success");
	}
}
