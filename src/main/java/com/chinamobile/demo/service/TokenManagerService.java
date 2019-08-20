package com.chinamobile.demo.service;


import com.chinamobile.demo.entities.IdentityUser;
import com.chinamobile.demo.entities.ResponseEntity;
import com.chinamobile.demo.entities.Token;
import com.chinamobile.demo.entities.UserInfo;
import com.chinamobile.demo.mapper.UserInfoMapper;
import com.chinamobile.demo.utils.CommonUtil;
import com.chinamobile.demo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


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
		boolean res = authorize(userInfo);
		if (res) {
			//生成token
			IdentityUser user = new IdentityUser(userInfo.getUserName());
			Token token = generateToken(user);
			//更新数据库中的tokenId
			userInfo.setTokenId(token.getTokenId());
			userInfo.setExpireTime(new Date(token.getExpireTime()));
			userInfoMapper.update(userInfo);
			return new ResponseEntity("200", "get token success", CommonUtil.objectToJson(token));
		} else {
			return new ResponseEntity("40001", "user name or password error");
		}
	}

	public Token generateToken(IdentityUser user) throws Exception {


		Long expireTime = System.currentTimeMillis() + vaildTime * 60 * 1000;
		String tokenId = TokenUtil.createJWT(encryptKey, CommonUtil.objectToJson(user), expireTime);

		Token token = new Token();
		token.setUserName(user.getUserName());
		token.setTokenId(tokenId);
		token.setExpireTime(expireTime);

		return token;
	}

	public boolean authorize(UserInfo user) {
		//check if username and password is matched or if token has expired
		List<UserInfo> userInfos = userInfoMapper.getUserInfo(user);
		if (!userInfos.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean authorizeToken(String tokenId) {
		//check if token has expired
		//TODO: sql judge expired through expiredTime
		List<UserInfo> userInfos = userInfoMapper.getUserInfo(new UserInfo(tokenId));
		if (!userInfos.isEmpty()) {
			return true;
		}
		return false;
	}
}
