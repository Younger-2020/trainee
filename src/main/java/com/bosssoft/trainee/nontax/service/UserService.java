package com.bosssoft.trainee.nontax.service;

import com.bosssoft.trainee.nontax.entity.dto.UserDTO;
import com.bosssoft.trainee.nontax.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bosssoft.trainee.nontax.util.login.LoginForm;

import java.util.List;
import java.util.Map;

/**
* @author DELL
* @description 针对表【basic_user(用户)】的数据库操作Service
* @createDate 2022-06-20 21:21:24
*/
public interface UserService extends IService<User> {
    /**
     * 根据登录表单返回用户DTO对象
     * @param loginForm 登录表单
     * @return 用户的DTO对象
     */
    UserDTO login(LoginForm loginForm);

    /**
     * 根据用户id获取该用户的所有角色
     * @param id 用户id
     * @return 字符串数组，包含该用户的所有角色名
     */
    List<String> getAllRolesById(Long id);

    /**
     * 列出所有的用户
     * @param map
     * @return
     */
    List<UserDTO> listUserByMap(Map<String, Object> map);

    /**
     * 判断用户表中是否已经存在该用户
     * @param userName
     * @return
     */
    Boolean hasThisUser(String userName);

    /**
     * 保存用户信息
     * @param map
     * @return
     */
    Boolean saveUser(Map<String, Object> map);

    /**
     * 修改用户信息
     * @param map
     * @return
     */
    Boolean updateUser(Map<String, Object> map);
}
