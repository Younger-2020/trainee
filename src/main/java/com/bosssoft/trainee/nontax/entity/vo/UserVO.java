package com.bosssoft.trainee.nontax.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String profilePicture;

    /**
     * 性别 约定0为男 1为女
     */
    private Integer sex;

    /**
     * 电话
     */
    private String tel;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long departmentId;

    /**
     * 职位ID  注意这个不等同于角色实际就是岗位
     */
    private Long positionId;

    /**
     * 公司ID
     */
    private Long companyId;
}
