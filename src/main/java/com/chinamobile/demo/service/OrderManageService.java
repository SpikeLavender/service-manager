package com.chinamobile.demo.service;

import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderManageService {

	@Autowired
	OrderInfoMapper orderInfoMapper;

	public Long createOrder(OrderInfo orderInfo) {
		orderInfoMapper.createOrder(orderInfo);
		Long orderId = orderInfo.getId();
		return orderId;
	}

	public List<OrderInfo> getOrder(Integer userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		List<OrderInfo> orders= orderInfoMapper.getOrderList(map);
		return orders;
	}
}
