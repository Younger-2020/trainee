package com.bosssoft.trainee.nontax.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.trainee.nontax.entity.dto.UserDTO;
import com.bosssoft.trainee.nontax.entity.vo.UserVO;
import com.bosssoft.trainee.nontax.service.UserService;
import com.bosssoft.trainee.nontax.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-management")
public class UserManagementController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;

    /**
     * 根据前端传递的参数列出所有用户
     *
     * @param map
     * @return
     */
    @GetMapping("/list")
    public Result listAllUsers(@RequestParam Map<String, Object> map) {
        logger.info("用户查询：" + map.toString());
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<UserDTO> userDTOS = userService.listUserByMap(map);
        List<UserVO> userVOS = new ArrayList<>();
        userDTOS.forEach(x -> {
            UserVO userVO = new UserVO();
            BeanUtil.copyProperties(x, userVO);
            userVOS.add(userVO);
        });
        stringObjectHashMap.put("items", userVOS);
        stringObjectHashMap.put("total", userVOS.size());
        return Result.ok(stringObjectHashMap);
    }

    /**
     * 创建用户
     *
     * @param map
     * @return
     */
    @PostMapping("/create")
    public Result createNewUser(@RequestBody Map<String, Object> map) {
        logger.info("用户创建：" + map.toString());
        String userName = (String) map.get("user_name");
        Boolean exists = userService.hasThisUser(userName);
        if (exists) {
            return Result.fail().message("用户已存在");
        }
        Boolean success = userService.saveUser(map);
        if (success) {
            return Result.ok().message("创建用户成功");
        } else {
            return Result.fail().message("创建用户失败");
        }
    }

    /**
     * 修改用户信息
     *
     * @param map
     * @return
     */
    @PostMapping("/update")
    public Result updateUser(@RequestBody Map<String, Object> map) {
        logger.info("用户修改：" + map.toString());
        Boolean success = userService.updateUser(map);
        if (success) {
            return Result.ok().message("用户信息修改成功");
        } else {
            return Result.fail().message("用户信息修改失败");
        }
    }

    /**
     * 删除用户
     *
     * @param map
     * @return
     */
    @PostMapping("/delete")
    public Result deleteUser(@RequestBody Map<String, Object> map) {
        logger.info("资源删除：" + map.toString());
        Boolean success = userService.removeById(Long.valueOf((String) map.get("id")));
        if (success) {
            return Result.ok().message("用户删除成功");
        } else {
            return Result.fail().message("用户删除失败");
        }
    }

}
