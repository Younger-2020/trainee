package com.bosssoft.trainee.nontax.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionDTO implements Serializable {
    /**
     * 职位ID
     */
    private Long id;

    /**
     * 职位名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 公司id
     */
    private Long companyId;
}
