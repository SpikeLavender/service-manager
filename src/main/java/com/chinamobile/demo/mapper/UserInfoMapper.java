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
	int addUserInfo(UserInfo userInfo);
	List<UserInfo> getUserInfo(UserInfo userInfo);
	int update(UserInfo userInfo);
}
