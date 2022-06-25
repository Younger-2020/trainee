package com.bosssoft.trainee.nontax.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubSystemDTO implements Serializable {
    /**
     * 子系统ID
     */
    private Long id;

    /**
     * 子系统名
     */
    private String name;

    /**
     * 子系统图标
     */
    private String imageUrl;

    /**
     * 链接
     */
    private String link;

    /**
     * 备注
     */
    private String remark;
}
