package com.bosssoft.trainee.nontax.util.JWT;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtHelper {
    private static long tokenExpiration = 86400000L;
    private static String tokenSignKey = "123456";

    //生成token字符串
    public static String createToken(Long userId, String userName, List<String> roles) {
        return Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("userName", userName)
                .claim("roles", roles)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    //从token字符串获取userid
    public static Long getUserId(String token) {
        if (StrUtil.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get("userId");
        return userId.longValue();
    }

    //从token字符串获取userName

    public static String getUserName(String token) {
        if (StrUtil.isEmpty(token)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("userName");
    }

    //从token字符串获取用户角色信息
    public static List<String> getRoles(String token) {
        if (StrUtil.isEmpty(token)) return new ArrayList<String>() {
        };
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (List<String>) (claims.get("roles"));
    }

    //判断token是否有效
    public static boolean isExpiration(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
        } catch (Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }


    /**
     * 刷新Token
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            refreshedToken = JwtHelper.createToken(getUserId(token), getUserName(token), getRoles(token));
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }
}
