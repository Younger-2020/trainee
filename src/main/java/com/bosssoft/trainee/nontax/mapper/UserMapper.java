package com.bosssoft.trainee.nontax.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bosssoft.trainee.nontax.entity.dto.UserDTO;
import com.bosssoft.trainee.nontax.entity.po.Role;
import com.bosssoft.trainee.nontax.entity.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author DELL
* @description 针对表【basic_user(用户)】的数据库操作Mapper
* @createDate 2022-06-20 21:21:24
* @Entity com.bosssoft.trainee.nontax.entity.po.User
*/

@Repository
public interface UserMapper extends BaseMapper<User> {
    public List<Role> getRolesById(@Param("id") Long id);

    public UserDTO login(@Param("username") String userName, @Param("password") String password);
}




