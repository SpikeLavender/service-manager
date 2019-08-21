package com.chinamobile.demo.service;

import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManageService {

	@Autowired
	OrderInfoMapper orderInfoMapper;

	public String createOrder(OrderInfo orderInfo) {

		orderInfoMapper.createOrder(orderInfo);
		return null;
	}
}
