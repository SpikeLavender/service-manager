package com.chinamobile.demo.service;


import com.chinamobile.demo.entities.IdentityUser;
import com.chinamobile.demo.entities.ResponseEntity;
import com.chinamobile.demo.entities.Token;
import com.chinamobile.demo.entities.UserInfo;
import com.chinamobile.demo.utils.CommonUtil;
import com.chinamobile.demo.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



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

	public ResponseEntity login(UserInfo userInfo) throws Exception {
		//ResponseEntity responseEntity = new ResponseEntity();
		//校验账号密码
		boolean res = authorize(userInfo);
		if (res) {
			//生成token
			IdentityUser user = new IdentityUser(userInfo.getUserName());
			Token token = generateToken(user);
//			responseEntity = ResponseEntity.ok();
//			responseEntity.setData(CommonUtil.objectToJson(token));
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
		token.setId(tokenId);
		token.setExpireTime(expireTime);

		return token;
	}

	public boolean authorize(UserInfo user) {
		//check if username and password is matched or if token has expired
		//TODO
		return true;
	}
}
