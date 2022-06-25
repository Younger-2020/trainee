package com.bosssoft.trainee.nontax.util;

import com.bosssoft.trainee.nontax.util.JWT.JwtHelper;

import javax.servlet.http.HttpServletRequest;

public class AuthContextHolder {

    private AuthContextHolder(){}

    //从请求头token获取userid
    public static Long getUserIdToken(HttpServletRequest request) {
        //从请求头token
        String token = request.getHeader("token");
        //调用工具类
        return JwtHelper.getUserId(token);
    }

    //从请求头token获取name
    public static String getUserName(HttpServletRequest request) {
        //从header获取token
        String token = request.getHeader("token");
        //jwt从token获取username
        return JwtHelper.getUserName(token);
    }
}
