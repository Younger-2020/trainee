package com.bosssoft.trainee.nontax.mapper;

import com.bosssoft.trainee.nontax.entity.po.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author DELL
* @description 针对表【basic_role(角色)】的数据库操作Mapper
* @createDate 2022-06-20 21:21:24
* @Entity com.bosssoft.trainee.nontax.entity.po.Role
*/
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    Integer countByNameAndCompanyId(@Param("roleName") String roleName, @Param("companyId") Long companyId);
}




