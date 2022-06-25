package com.bosssoft.trainee.nontax.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName basic_department
 */
@TableName(value ="basic_department")
@Data
public class Department implements Serializable {
    /**
     * 部门ID
     */
    @TableId
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
