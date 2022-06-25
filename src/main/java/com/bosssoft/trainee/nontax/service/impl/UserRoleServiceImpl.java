package com.bosssoft.trainee.nontax.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.nontax.entity.po.UserRole;
import com.bosssoft.trainee.nontax.service.UserRoleService;
import com.bosssoft.trainee.nontax.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【basic_user_role(用户角色关系表)】的数据库操作Service实现
* @createDate 2022-06-20 21:21:24
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




