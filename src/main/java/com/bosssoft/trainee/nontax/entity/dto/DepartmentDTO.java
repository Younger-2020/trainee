package com.bosssoft.trainee.nontax.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepartmentDTO implements Serializable {
    /**
     * 部门ID
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门描述
     */
    private String descript;

    /**
     * 公司id
     */
    private Long companyId;
}
