package com.chinamobile.demo.mapper;

import com.chinamobile.demo.entities.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */

@Mapper
public interface OrderInfoMapper {
	int createOrder(Map<String, Object> map);
	int createOrder(OrderInfo orderInfo);
	List<OrderInfo> getOrderList(Map<String, Object> map);
	Integer getOrderCount(@Param("userId") Integer userId);
	List<OrderInfo> getOrderListWithActive(Map<String, Object> map);
}
