package com.bosssoft.trainee.nontax.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 角色
 * @TableName basic_sub_system
 */
@TableName(value ="basic_sub_system")
@Data
public class SubSystem implements Serializable {
    /**
     * 子系统ID
     */
    @TableId
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
     * 状态位 0 不可用 可用
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
