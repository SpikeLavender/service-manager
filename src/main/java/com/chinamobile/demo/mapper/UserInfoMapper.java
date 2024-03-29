package com.chinamobile.demo.mapper;

import com.chinamobile.demo.entities.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
@Mapper
public interface UserInfoMapper {
	int addUserInfo(Map<String, Object> userInfo);
	UserInfo getUserInfo(Map<String, Object> userInfo);
	int update(Map<String, Object> userInfo);
}
