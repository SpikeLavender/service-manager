package com.chinamobile.demo.mapper;

import com.chinamobile.demo.entities.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */

@Mapper
public interface OrderInfoMapper {
	OrderInfo createOrder(Map<String, Object> map);
	OrderInfo createOrder(OrderInfo orderInfo);
}
