package com.chinamobile.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.chinamobile.demo.entities.CommonEnums.OrderStatusEnum;
import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.entities.Pagination;
import com.chinamobile.demo.mapper.OrderInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

//	public List<OrderInfo> getOrder(Integer userId, Integer page, Integer size,
//	                                String status, String level, String type) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("userId", userId);
//		if (page != null && size != null) {
//			map.put("startIndex", (page - 1) * size);
//			map.put("pageSize", size);
//		}
//		if (CommonUtil.isStrNotEmpty(status)) {
//			map.put("orderStatus", status);
//		}
//		if (CommonUtil.is(level)) {
//			map.put("serviceLevel", level);
//		}
//		if (CommonUtil.isStrEmpty(type)) {
//			map.put("sliceType", type);
//		}
//		List<OrderInfo> orders= orderInfoMapper.getOrderList(map);
//		return orders;
//	}

	public Pagination getOrder(JSONObject queryJson) {
		//JSONObject queryJson;
		if (queryJson.containsKey("page") && queryJson.getIntValue("page") > 0
				&& queryJson.containsKey("size") && queryJson.getIntValue("size") > 0) {
			queryJson.put("startIndex", (queryJson.getIntValue("page") - 1)
					* queryJson.getIntValue("size"));
			queryJson.put("pageSize", queryJson.getIntValue("size"));
		}
		List<OrderInfo> orders= orderInfoMapper.getOrderList(queryJson);
		Pagination pagination = new Pagination();
		pagination.setOrderInfoList(orders);
		Integer orderCount = orderInfoMapper.getOrderCount(queryJson.getInteger("userId"));
		pagination.setTotal(orderCount);
		logger.debug("get order list success: " + orders.toString());
		return pagination;
	}
}
