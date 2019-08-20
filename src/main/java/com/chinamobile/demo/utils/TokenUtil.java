package com.chinamobile.demo.utils;


import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

/**
 * JWTUtils工具类，生成jwt和解析jwt
 * JSON WEB TOKEN 结构组成：
 * (1)Header(头部)：包含加密算法，通常直接使用 HMAC SHA256
 * (2)Payload(负载)：存放有效信息，比如消息体、签发者、过期时间、签发时间等
 * (3)Signature(签名)：由header(base64后的)+payload(base64后的)+secret(秘钥)三部分组合，然后通过head中声明的算法进行加密
 * @author hetengjiao@chinamobile.com
 * @date 2019/8/20
 *
 */

public class TokenUtil {

	private static final String KEY_DECODE_ALGORITHM = "AES";
	private static final String RSA_ALGORITHM = "RSA";
	private static final String DEFAULT_USER_KEY = "userId";
	private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; //默认HS256

	/*
	 * @Desccription create token
	 */
	public static String createJWT(String key, JSONObject userInfo, long expireTime) throws Exception{
		if(signatureAlgorithm == null){
			throw new Exception("identity_token_jwt_0001::this algorithm is not supported");
		}
		return createJWT(signatureAlgorithm, key, userInfo, expireTime);
	}

	/*
	 * @Desccription create token
	 */
	public static String createJWT(SignatureAlgorithm signatureAlgorithm, String key,
	                               JSONObject paramMap, long expireTime) throws Exception{

		String token = null;
		Key secretKey = null;

		try{
			secretKey = getCreateKey(signatureAlgorithm, key);
		}catch(RuntimeException sex){
			throw sex;
		}catch(Exception ex){
			throw new Exception("identity_token_jwt_0003::get secret key failed");
		}

		Date expDate = new Date(expireTime);

		Date nowDate = new Date();
		String jwtId = CommonUtil.getUUID();
		JwtBuilder builder = Jwts.builder().setId(jwtId)
				.setIssuedAt(nowDate)
				.setSubject(paramMap.getString("userName"))
				.setExpiration(expDate)
				.signWith(signatureAlgorithm, secretKey);
		try{
			if(paramMap != null){
				paramMap.remove(DEFAULT_USER_KEY);
				if(paramMap.size() > 0){
					builder.setClaims(paramMap);
				}
			}
		}catch(Exception ex){
			throw new Exception("identity_token_jwt_0004::get user info faied");
		}

		try{
			token = builder.compact();
		}catch(Exception ex){
			throw new Exception("identity_token_jwt_0005::generate token failed");
		}


		return token;
	}


	/*
	 * @Desccription 根据算法获取加密的key
	 */
	private static Key getCreateKey(SignatureAlgorithm signatureAlgorithm, String key) throws Exception {

		Key result = null;
		if(SignatureAlgorithm.RS256.equals(signatureAlgorithm) ||
				SignatureAlgorithm.RS384.equals(signatureAlgorithm) ||
				SignatureAlgorithm.RS512.equals(signatureAlgorithm)){
			result = generalRSPrivateKey(key);
		}else if(SignatureAlgorithm.HS256.equals(signatureAlgorithm) ||
				SignatureAlgorithm.HS384.equals(signatureAlgorithm) ||
				SignatureAlgorithm.HS512.equals(signatureAlgorithm)){
			result = generalHSKey(key);
		}else{
			throw new Exception("identity_token_jwt_0008::this algorithm is not supported");
		}

		return result;

	}

	/*
	 * @Desccription
	 */
	private static Key generalHSKey(String key) throws Exception{
		byte[] encodedKey = new Base64().decode(key);
		Key secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, KEY_DECODE_ALGORITHM);
		return secretKey;
	}

	/*
	 * @Desccription 获取秘钥
	 */
	private static Key generalRSPrivateKey(String key) throws Exception {
		byte[] publicKeyBytes = new Base64().decode(key);
		byte[] publicKeyRealBytes = new Base64().decode(publicKeyBytes);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyRealBytes);
		KeyFactory keyFac = KeyFactory.getInstance(RSA_ALGORITHM);

		return keyFac.generatePublic(keySpec);
	}

	/**
	 * 解密jwt，获取实体
	 * @param tokenId
	 */
	public static Claims parseJWT(String tokenId, SignatureAlgorithm signatureAlgorithm,
	                              String key) throws Exception {
		try{
			Key secretKey = getCreateKey(signatureAlgorithm, key);
			Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(tokenId).getBody();
			return claims;
		}catch(RuntimeException sex){
			throw sex;
		}catch(Exception ex){
			throw new Exception("identity_token_jwt_0003::get secret key failed");
		}
	}

}
