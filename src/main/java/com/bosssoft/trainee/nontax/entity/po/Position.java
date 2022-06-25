package com.bosssoft.trainee.nontax.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName basic_position
 */
@TableName(value ="basic_position")
@Data
public class Position implements Serializable {
    /**
     * 职位ID
     */
    @TableId
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
     * 状态
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
