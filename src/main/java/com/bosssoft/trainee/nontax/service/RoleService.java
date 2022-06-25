package com.bosssoft.trainee.nontax.service;

import com.bosssoft.trainee.nontax.entity.dto.RoleDTO;
import com.bosssoft.trainee.nontax.entity.po.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
* @author DELL
* @description 针对表【basic_role(角色)】的数据库操作Service
* @createDate 2022-06-20 21:21:24
*/
public interface RoleService extends IService<Role> {
    /**
     * 用于返回用户分页对象
     * @param map 传递过来的参数
     * @return 角色DTO列表
     */
    public List<RoleDTO> listRolePageByCondition(Map<String, Object> map);

    /**
     * 判断role表中同样公司下是否存在相同的角色名
     * @param companyName
     * @param roleName
     * @return
     */
    boolean hasThisRole(String companyName, String roleName);

    /**
     * 根据前端传递过来的参数持久化到数据库
     * @param companyName
     * @param roleName
     * @param roleRemark
     * @return
     */
    boolean saveRole(String companyName, String roleName, String roleRemark);

    /**
     * 根据前端传递过来的参数修改对应的角色信息
     * @param map 前端传递过来的参数
     * @return
     */
    boolean updateRole(Map<String, Object> map);
}
