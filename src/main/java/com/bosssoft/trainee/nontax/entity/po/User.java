package com.bosssoft.trainee.nontax.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @TableName basic_user
 */
@TableName(value ="basic_user")
@Data
public class User implements Serializable {
    /**
     * 用户ID
     */
    @TableId
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

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人
     */
    private Long updatedBy;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 状态位 1 正常用户 0 注销用户 2 锁定用户
     */
    @TableLogic(value = "1",delval = "0")
    private Integer status;

    /**
     * 版本
     */
    @Version
    private Long version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
