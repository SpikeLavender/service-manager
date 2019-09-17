package com.chinamobile.demo.mapper;

import com.chinamobile.demo.entities.ActiveInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ActiveInfoMapper {
	int createActive(Map<String, Object> map);
	int createActive(ActiveInfo activeInfo);
	List<Long> getReadyActive(Map<String, Object> map);
	List<Long> getRunningActive(Map<String, Object> map);
	int updateStatus(Map<String, Object> map);
}
