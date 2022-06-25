package com.bosssoft.trainee.nontax.mapper;

import com.bosssoft.trainee.nontax.entity.po.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author DELL
* @description 针对表【basic_user_role(用户角色关系表)】的数据库操作Mapper
* @createDate 2022-06-20 21:21:24
* @Entity com.bosssoft.trainee.nontax.entity.po.UserRole
*/

@Repository
public interface UserRoleMapper extends BaseMapper<UserRole> {

}




