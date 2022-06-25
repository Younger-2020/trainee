package com.bosssoft.trainee.nontax.entity.po;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.bosssoft.trainee.nontax.entity.dto.RoleDTO;
import lombok.Data;

/**
 * 角色
 * @TableName basic_role
 */
@TableName(value ="basic_role")
@Data
public class Role implements Serializable {
    /**
     * 角色ID
     */
    @TableId
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
     * 状态位
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
