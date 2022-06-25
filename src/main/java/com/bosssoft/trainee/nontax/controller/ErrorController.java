package com.bosssoft.trainee.nontax.controller;

import com.bosssoft.trainee.nontax.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("err")
public class ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public Result<Object> error() {
        int a = 1 / 0;
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("发生异常后");
        return Result.ok();
    }
}
