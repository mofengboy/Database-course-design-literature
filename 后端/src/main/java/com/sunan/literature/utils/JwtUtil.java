package com.sunan.literature.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    /**
     * 过期时间为为一天
     */
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

    /**
     * token私钥
     */
    private static final String TOKEN_SECRET = "joijsdfjls313sadfasdf135";

    /**
     * 生成签名,15分钟后过期
     *
     * @param username
     * @param userId
     * @return
     */
    public static String sign(String username, String userId) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //私钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userID生成签名
        return JWT.create().withHeader(header).withClaim("loginName", username)
                .withClaim("userId", userId).withExpiresAt(date).sign(algorithm);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     * @throws IllegalArgumentException
     * @throws JWTVerificationException
     */
    public static DecodedJWT verity(String token) throws IllegalArgumentException, JWTVerificationException {
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }

    /**
     * 根据Token获取user_id
     *
     * @param token
     * @return
     * @throws IllegalArgumentException
     * @throws JWTVerificationException
     */
    public static Integer getUserId(String token) throws IllegalArgumentException, JWTVerificationException {
        Map<String, Claim> claims = verity(token).getClaims();
        Claim user_id_claim = claims.get("userId");
        return Integer.valueOf(user_id_claim.asString());
    }

}
