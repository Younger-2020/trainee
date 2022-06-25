package com.bosssoft.trainee.nontax.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleVO implements Serializable {
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
     * 该角色所持有的所有资源的资源名
     */
    private List<String> resourceList;
}
