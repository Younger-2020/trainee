package com.bosssoft.trainee.nontax.controller;

import com.bosssoft.trainee.nontax.entity.dto.UserDTO;
import com.bosssoft.trainee.nontax.service.UserService;
import com.bosssoft.trainee.nontax.util.JWT.JwtHelper;
import com.bosssoft.trainee.nontax.util.Login.LoginForm;
import com.bosssoft.trainee.nontax.util.Result;
import com.bosssoft.trainee.nontax.util.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class SystemController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    /**
     * 根据登录表单返回结果
     * POST /user/login
     *
     * @param loginForm 登录表单
     * @return 统一返回结果状态信息类
     */
    @RequestMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        logger.info("用户登录：用户名：{} 密码：{}", loginForm.getUsername(), loginForm.getPassword());
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        UserDTO userDTO = userService.login(loginForm);
        if (null != userDTO) {
            List<String> roles = userService.getAllRolesById(userDTO.getId());
            String token = JwtHelper.createToken(userDTO.getId(), userDTO.getName(), roles);
            stringObjectHashMap.put("token", token);
            return Result.build(stringObjectHashMap, ResultCodeEnum.LOGIN_SUCCESS);
        } else {
            return Result.build(ResultCodeEnum.LOGIN_ERROR);
        }
    }

    /**
     * 根据token获取用户信息
     * GET /user/info
     * @param token
     * @return
     */
    @RequestMapping("/info")
    public Result getInfo(@RequestParam("token") String token) {
        boolean Expired = JwtHelper.isExpiration(token);
        if (Expired) {
            return Result.fail().message("token已过期！");
        } else {
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("roles", JwtHelper.getRoles(token));
            stringObjectHashMap.put("name", JwtHelper.getUserName(token));
            return Result.build(stringObjectHashMap, ResultCodeEnum.SUCCESS);
        }
    }
}
