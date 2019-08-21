package com.chinamobile.demo.service;


import com.alibaba.fastjson.JSONObject;
import com.chinamobile.demo.entities.IdentityUser;
import com.chinamobile.demo.entities.ResponseEntity;
import com.chinamobile.demo.entities.Token;
import com.chinamobile.demo.entities.UserInfo;
import com.chinamobile.demo.mapper.UserInfoMapper;
import com.chinamobile.demo.utils.CommonUtil;
import com.chinamobile.demo.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class TokenManagerService {

	@Value("${token.vaildTime}")
	private Long vaildTime;

	@Value("${token.key}")
	private String encryptKey;

	@Autowired
	private UserInfoMapper userInfoMapper;

	public ResponseEntity login(UserInfo userInfo) throws Exception {
		//ResponseEntity responseEntity = new ResponseEntity();
		//校验账号密码
		Long id = authorize(userInfo);
		if (id != null) {
			//生成token
			IdentityUser user = new IdentityUser(userInfo.getUsername());
			Long expireTime = System.currentTimeMillis() + vaildTime * 60 * 1000;

			JSONObject json = new JSONObject();
			json.put("expireTime", expireTime);
			json.put("userId", id);
			String token = generateToken(json);
			//更新数据库中的token

			Map<String, Object> map = new HashMap<>();
			map.put("token", token);
			map.put("id", id);
			map.put("expireTime", new Date(expireTime));
			userInfoMapper.update(map);

			JSONObject resJson = new JSONObject();
			resJson.put("token", token);
			return new ResponseEntity("200", "get token success", resJson);
		} else {
			return new ResponseEntity("40001", "user name or password error");
		}
	}

	public String generateToken(JSONObject userInfo) throws Exception {

		Long expireTime = System.currentTimeMillis() + vaildTime * 60 * 1000;

		String tokenId = TokenUtil.createJWT(encryptKey, userInfo, expireTime);

		return tokenId;
	}

	public Long authorize(UserInfo user) {
		//check if username and password is matched or if token has expired
		Map<String, Object> map = new HashMap<>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		UserInfo userInfo = userInfoMapper.getUserInfo(map);
		if (userInfo != null) {
			return userInfo.getId();
		}
		return null;
	}

	public Long authorizeToken(String token) throws Exception {
		//check if token has expired
		//TODO: sql judge expired through expiredTime
		Claims claims = TokenUtil.parseJWT(token, encryptKey);
		if (claims.containsKey("userId")){
			Long userId = (Long) claims.get("userId");
			return userId;
		}

		return null;
	}
}
