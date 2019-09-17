package com.chinamobile.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.chinamobile.demo.entities.ActiveInfo;
import com.chinamobile.demo.entities.CommonEnums.OrderStatusEnum;
import com.chinamobile.demo.entities.CommonEnums.ActiveStatusEnum;
import com.chinamobile.demo.entities.DemoException;
import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.entities.Pagination;
import com.chinamobile.demo.enums.RunStatusCodeEnum;
import com.chinamobile.demo.mapper.ActiveInfoMapper;
import com.chinamobile.demo.mapper.OrderInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;;

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
	@Autowired
	ActiveInfoMapper activeInfoMapper;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Long createOrder(OrderInfo orderInfo) {
		//TODO: calc the fee
		orderInfo.setFee(calcFee(orderInfo));
		orderInfo.setOrderStatus(OrderStatusEnum.SUCCESS);
//		ResponseEntity response = RestClient.sendBandWidthEvent(RestClient.ABNORMAL);
//		if(response.getStatusCodeValue() == HttpStatus.ACCEPTED.value() || Objects.equals(response.getBody(), "Accepted")){
//			orderInfo.setOrderStatus(OrderStatusEnum.READY);
//			//orderInfoMapper.createOrder(orderInfo);
//		} else {
//			orderInfo.setOrderStatus(OrderStatusEnum.CREATE_FAIL);
//		}
		orderInfoMapper.createOrder(orderInfo);
		return orderInfo.getId();
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

	public Pagination getOrder(JSONObject queryJson) {
		//JSONObject queryJson;
		if (queryJson.containsKey("page") && queryJson.getIntValue("page") > 0
				&& queryJson.containsKey("size") && queryJson.getIntValue("size") > 0) {
			queryJson.put("startIndex", (queryJson.getIntValue("page") - 1)
					* queryJson.getIntValue("size"));
			queryJson.put("pageSize", queryJson.getIntValue("size"));
		}
		//List<OrderInfo> orders= orderInfoMapper.getOrderList(queryJson);
		List<OrderInfo> orders= orderInfoMapper.getOrderListWithActive(queryJson);
		Pagination pagination = new Pagination();
		pagination.setOrderInfoList(orders);
		Integer orderCount = orderInfoMapper.getOrderCount(queryJson.getInteger("userId"));
		pagination.setTotal(orderCount);
		logger.debug("get order list success: " + orders.toString());
		return pagination;
	}

	public Map<String, Object> activeOrder(ActiveInfo activeInfo) throws DemoException{
		Map<String, Object> resMap = new HashMap();
		resMap.put("orderId", activeInfo.getOrderId());
		Map<String, Object> map = new HashMap<>();
		map.put("id", activeInfo.getOrderId());
		List<OrderInfo> orderInfos = orderInfoMapper.getOrderList(map);
		if (orderInfos == null || orderInfos.isEmpty()) {
			throw new DemoException(RunStatusCodeEnum.ORDER_NOT_EXIST);
		} else {
			String orderTime = orderInfos.get(0).getOrderTime();
			//todo: 判断是否在有效区间内
			if (Long.parseLong(orderTime.split("\\|")[0]) > activeInfo.getStartTime().getTime()
					|| Long.parseLong(orderTime.split("\\|")[1]) < activeInfo.getEndTime().getTime()) {
				throw new DemoException(RunStatusCodeEnum.ACTIVE_TIME_UNVAILD);
			} else {
				activeInfoMapper.createActive(activeInfo);
				resMap.put("id", activeInfo.getId());
				resMap.put("activeStatus", ActiveStatusEnum.WAITING);
				resMap.put("description", "create active event success");
				return resMap;
			}
		}
	}
}
