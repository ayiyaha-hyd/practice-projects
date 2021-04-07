package com.hyd.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * token管理类
 */
@Component
public class TokenManager {
    /**
     * token有效时长
     */
    private final static long TOKEN_VALID_TIME = 24*60*60*1000;

    private final static String TOKEN_SIGN_KEY = "123456";

    /**
     * 根据用户名生成token
     */
    public String createToken(String username) {
        return Jwts.builder().setSubject(username)//设置主体
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALID_TIME))//设置过期时间
                .signWith(SignatureAlgorithm.ES512, TOKEN_SIGN_KEY).compressWith(CompressionCodecs.GZIP).compact();
    }

    /**
     * 根据token字符串得到用户信息
     */
    public String getUserInfoFromToken(String token){
        return Jwts.parser().setSigningKey(TOKEN_SIGN_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * 删除token
     * @param token
     */
    public void removeToken(String token){}

}
