package com.bosssoft.trainee.nontax.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.nontax.entity.dto.ResourceDTO;
import com.bosssoft.trainee.nontax.entity.dto.UserDTO;
import com.bosssoft.trainee.nontax.entity.po.Resource;
import com.bosssoft.trainee.nontax.entity.po.Role;
import com.bosssoft.trainee.nontax.entity.po.User;
import com.bosssoft.trainee.nontax.service.UserService;
import com.bosssoft.trainee.nontax.mapper.UserMapper;
import com.bosssoft.trainee.nontax.util.Login.LoginForm;
import com.bosssoft.trainee.nontax.util.SortHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @description 针对表【basic_user(用户)】的数据库操作Service实现
 * @createDate 2022-06-20 21:21:24
 */
@Service("UserServiceImpl")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO login(LoginForm loginForm) {
        UserDTO login = userMapper.login(loginForm.getUsername(), loginForm.getPassword());
        return login;
    }

    @Override
    public List<String> getAllRolesById(Long id) {
        List<Role> rolesById = userMapper.getRolesById(id);
        List<String> list = new ArrayList<>();
        rolesById.forEach(x -> list.add(x.getName()));
        return list;
    }

    @Override
    public List<UserDTO> listUserByMap(Map<String, Object> map) {
        // 从map中获取各项参数
        Integer pageNo = Integer.valueOf((String) map.get("page"));
        Integer pageSize = Integer.valueOf((String) map.get("limit"));
        String sort = (String) map.get("sort");
        String userName = (String) map.get("user_name");
        String phoneNumber = (String) map.get("phone_number");
        String role = (String) map.get("role");
        // 创建page对象、wrapper对象用于查询
        Page page = new Page(pageNo, pageSize);
        page.setOrders(SortHelper.getOrderItem(sort));
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like(userName != null, "name", userName)
                .like(phoneNumber != null, "tel", phoneNumber);
        Page selectPage = userMapper.selectPage(page, userQueryWrapper);
        List<User> records = selectPage.getRecords();
        // 将获取到的PO转为DTO返回
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        records.forEach(x -> {
            UserDTO userDTO = new UserDTO();
            BeanUtil.copyProperties(x, userDTO);
            userDTOS.add(userDTO);
        });
        return userDTOS;
    }

    @Override
    public Boolean hasThisUser(String userName) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", userName);
        User user = userMapper.selectOne(userQueryWrapper);
        return user != null;
    }

    @Override
    public Boolean saveUser(Map<String, Object> map) {
        User user = new User();
        user.setName((String) map.get("user_name"));
        user.setPassword((String) map.get("password"));
        String sex = (String) map.get("sex");
        user.setSex("男".equals(sex) ? 0 : 1);
        user.setTel((String) map.get("phone_num"));
        user.setStatus(1);
        int insert = userMapper.insert(user);
        return insert > 0;
    }

    @Override
    public Boolean updateUser(Map<String, Object> map) {
        User user = new User();
        user.setId(Long.valueOf((String) map.get("id")));
        user.setName((String) map.get("user_name"));
        user.setPassword((String) map.get("password"));
        String sex = (String) map.get("sex");
        user.setSex("男".equals(sex) ? 0 : 1);
        user.setTel((String) map.get("phone_num"));
        int update = userMapper.updateById(user);
        return update > 0;
    }
}




