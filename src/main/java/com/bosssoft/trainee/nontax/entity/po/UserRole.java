package com.bosssoft.trainee.nontax.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户角色关系表
 * @TableName basic_user_role
 */
@TableName(value ="basic_user_role")
@Data
public class UserRole implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改人id
     */
    private Long updatedBy;

    /**
     * 修改时间
     */
    private Date updatedTime;

    /**
     * 数据状态
     */
    @TableLogic(value = "1",delval = "0")
    private Integer status;

    /**
     * 数据版本
     */
    @Version
    private Long version;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
