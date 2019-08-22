package com.chinamobile.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.chinamobile.demo.entities.UserInfo;
import com.chinamobile.demo.mapper.UserInfoMapper;
import com.chinamobile.demo.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */
@Service
public class TokenManagerService {

	@Value("${token.vaildTime}")
	private Long vaildTime;

	@Value("${token.key}")
	private String encryptKey;

	@Autowired
	private UserInfoMapper userInfoMapper;

	public String login(UserInfo userInfo) throws Exception {
		//ResponseEntity responseEntity = new ResponseEntity();
		//校验账号密码
		//生成token
		Long expireTime = System.currentTimeMillis() + vaildTime * 60 * 1000;

		JSONObject json = new JSONObject();
		json.put("expireTime", expireTime);
		json.put("userId", userInfo.getId());
		String token = generateToken(json);
		//更新数据库中的token

		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		map.put("id", userInfo.getId());
		map.put("expireTime", expireTime);
		userInfoMapper.update(map);

		return token;
	}

	public String generateToken(JSONObject userInfo) throws Exception {

		Long expireTime = System.currentTimeMillis() + vaildTime * 60 * 1000;

		String tokenId = TokenUtil.createJWT(encryptKey, userInfo, expireTime);

		return tokenId;
	}

	public Long authorize(UserInfo user) {
		//check if username and password is matched or if token has expired
		//if has not expired token, return token
		Map<String, Object> map = new HashMap<>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		UserInfo userInfo = userInfoMapper.getUserInfo(map);
		if (userInfo != null) {
			return userInfo.getId();
		}
		return null;
	}

	public Integer authorizeToken(String token, StringBuilder msg) throws Exception {
		//check if token has expired
		//TODO: sql judge expired through expiredTime
		Claims claims = TokenUtil.parseJWT(token, encryptKey);
		if (claims.containsKey("userId")){
			Integer userId = (Integer) claims.get("userId");
			Long expireTime = (Long) claims.getOrDefault("expireTime", 0);
			if (expireTime > System.currentTimeMillis()) {
				return userId;
			}
			msg.append("Authorize fail, the token has expire, please login again");
			return null;
		}
		msg.append("Authorize fail, the token is unvaild");
		return null;
	}

	public boolean logout(String token) throws Exception {
		//set the token expired
		//maybe not need
		Claims claims = TokenUtil.parseJWT(token, encryptKey);
		if (claims.containsKey("userId")){
			Integer userId = (Integer) claims.get("userId");
			//Long expireTime = System.currentTimeMillis();
			Map<String, Object> map = new HashMap<>();
			map.put("id", userId);
			map.put("token", null);
			userInfoMapper.update(map);
			return true;
		}
		return false;
	}
}
