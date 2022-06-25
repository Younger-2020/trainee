package com.bosssoft.trainee.nontax.controller;

import com.bosssoft.trainee.nontax.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/log")
public class LoggingController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/write")
    public Result writeLog(@RequestParam Map<String,Object> map){
        map.forEach((x,y)->{
            System.out.println(y.getClass());
        });
        // 级别由低到高 trace<debug<info<warn<error
        logger.trace("这是一个trace日志");
        logger.debug("这是一个debug日志");
        logger.info("这是一个info日志");
        logger.warn("这是一个warn日志");
        logger.error("这是一个error日志");
        return Result.ok("write log success");
    }
}
