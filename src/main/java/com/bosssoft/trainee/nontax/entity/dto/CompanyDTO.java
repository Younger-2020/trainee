package com.bosssoft.trainee.nontax.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyDTO implements Serializable {
    /**
     * 公司ID
     */
    private Long id;

    /**
     * 公司名
     */
    private String name;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;
}
