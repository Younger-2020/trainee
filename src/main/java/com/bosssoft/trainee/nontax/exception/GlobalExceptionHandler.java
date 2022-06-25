package com.bosssoft.trainee.nontax.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bosssoft.trainee.nontax.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler  {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Throwable e) {
        e.printStackTrace();
        // 记录日志
        logger.error(e.getMessage());
        HashMap<String, Object> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("exception",e.getMessage());
        stringStringHashMap.put("url", request.getRequestURL());
        return Result.fail(stringStringHashMap);
    }
}
