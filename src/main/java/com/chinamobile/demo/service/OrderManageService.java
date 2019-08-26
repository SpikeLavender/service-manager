package com.chinamobile.demo.service;


import com.chinamobile.demo.entities.CommonEnums.OrderStatusEnum;
import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.mapper.OrderInfoMapper;
import com.chinamobile.demo.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
@Service
public class OrderManageService {

	@Autowired
	OrderInfoMapper orderInfoMapper;

	public Long createOrder(OrderInfo orderInfo) {
		//TODO: calc the fee
		orderInfo.setFee(calcFee(orderInfo));
		orderInfo.setOrderStatus(OrderStatusEnum.SUCCESS);
		orderInfoMapper.createOrder(orderInfo);
		Long orderId = orderInfo.getId();
		return orderId;
	}

	private Double calcFee(OrderInfo orderInfo) {
		//TODO: calc the fee
		//fee = times * seviceLevel * sliceType;
		String orderTime = orderInfo.getOrderTime();
		String[] times = orderTime.split("\\|");
		Double time = (Long.parseLong(times[1]) - Long.parseLong(times[0])) / (1000. * 60 * 60);
		return time * orderInfo.getServiceLevel().getFee()
				* orderInfo.getSliceType().getFee();
	}

	public List<OrderInfo> getOrder(Integer userId, Integer page, Integer size,
	                                String status, String level, String type) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		if (page != null && size != null) {
			map.put("startIndex", (page - 1) * size);
			map.put("pageSize", size);
		}
		if (CommonUtil.isStrEmpty(status)) {
			map.put("orderStatus", status);
		}
		if (CommonUtil.isStrEmpty(level)) {
			map.put("serviceLevel", level);
		}
		if (CommonUtil.isStrEmpty(type)) {
			map.put("sliceType", type);
		}
		List<OrderInfo> orders= orderInfoMapper.getOrderList(map);
		return orders;
	}
}
