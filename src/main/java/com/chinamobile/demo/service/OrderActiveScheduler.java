package com.chinamobile.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.chinamobile.demo.entities.CommonEnums;
import com.chinamobile.demo.entities.OrderInfo;
import com.chinamobile.demo.entities.SystemException;
import com.chinamobile.demo.mapper.ActiveInfoMapper;
import com.chinamobile.demo.mapper.OrderInfoMapper;
import com.chinamobile.demo.utils.RestClient;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Component
public class OrderActiveScheduler implements ApplicationRunner {
	@Autowired
	ActiveInfoMapper activeInfoMapper;
	@Autowired
	OrderInfoMapper orderInfoMapper;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		orderActiveTask();
	}

	public void orderActiveTask(){
		ScheduledThreadPoolExecutor scheduledThreadPoolExecutorRun = new ScheduledThreadPoolExecutor(
				1, new BasicThreadFactory.Builder().namingPattern("schedule-pool-run-%d").daemon(false).build());
		// 第一个参数是任务，第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间,第四个参数是时间单位
		scheduledThreadPoolExecutorRun.scheduleAtFixedRate(this::updateRunActiveStatus, 0L, 5L, TimeUnit.SECONDS);

		ScheduledThreadPoolExecutor scheduledThreadPoolExecutorStop = new ScheduledThreadPoolExecutor(
				1, new BasicThreadFactory.Builder().namingPattern("schedule-pool-stop-%d").daemon(false).build());
		// 第一个参数是任务，第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间,第四个参数是时间单位
		scheduledThreadPoolExecutorStop.scheduleAtFixedRate(this::updateStopActiveStatus, 0L, 5L, TimeUnit.SECONDS);
	}

	private void updateRunActiveStatus() {
		//todo: 查询数据库active_info中startTime > current_time且status < running的数据，请求onap激活服务并更新为running
		//todo: 查询数据库active_info中endTime < current_time且status == running的数据，请求onap停止服务并更新为stop
		//List<OrderInfo> orders= orderInfoMapper.getOrderListWithActive(queryJson);
		Map<String, Object> readyMap = new HashMap<>();
		readyMap.put("currentDate", new Date());
		readyMap.put("runActiveStatus", CommonEnums.ActiveStatusEnum.RUNNING);
		List<Long> ids = activeInfoMapper.getReadyActive(readyMap);

		for (int i = 0; i < ids.size(); i++) {
			readyMap.clear();
			readyMap.put("id", ids.get(i));
			//请求onap运行接口
			try {
				ResponseEntity response = RestClient.sendBandWidthEvent(RestClient.ABNORMAL);
				if(response.getStatusCodeValue() == HttpStatus.ACCEPTED.value() || Objects.equals(response.getBody(), "Accepted")){
					readyMap.put("activeStatus", CommonEnums.ActiveStatusEnum.RUNNING);
					activeInfoMapper.updateStatus(readyMap);
				} else {
					//todo: 请求失败的话，是重试还是跳过？
				}
			} catch (SystemException e) {
				//todo: 日志，重试机制
			}
		}
	}
	private void updateStopActiveStatus() {
		//todo: 查询数据库active_info中startTime > current_time且status < running的数据，请求onap激活服务并更新为running
		//todo: 查询数据库active_info中endTime < current_time且status == running的数据，请求onap停止服务并更新为stop
		//List<OrderInfo> orders= orderInfoMapper.getOrderListWithActive(queryJson);
		Map<String, Object> readyMap = new HashMap<>();
		readyMap.put("currentDate", new Date());
		readyMap.put("runActiveStatus", CommonEnums.ActiveStatusEnum.RUNNING);
		readyMap.put("stopActiveStatus", CommonEnums.ActiveStatusEnum.STOPPED);
		List<Long> ids = activeInfoMapper.getRunningActive(readyMap);
		for (int i = 0; i < ids.size(); i++) {
			//todo:请求onap停止接口
			readyMap.clear();
			readyMap.put("id", ids.get(i));
			readyMap.put("activeStatus", CommonEnums.ActiveStatusEnum.STOPPED);
			activeInfoMapper.updateStatus(readyMap);
		}
	}
}
