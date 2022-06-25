package com.bosssoft.trainee.nontax.entity.dto;

import com.bosssoft.trainee.nontax.entity.po.Resource;
import com.bosssoft.trainee.nontax.entity.po.Role;
import com.bosssoft.trainee.nontax.entity.vo.RoleVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleDTO implements Serializable {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 公司ID
     */
    private Long companyId;

    /**
     * 该角色锁拥有的资源列表
     */
    private List<ResourceDTO> resourceList;
}
