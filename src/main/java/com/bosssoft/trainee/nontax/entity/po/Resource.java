package com.bosssoft.trainee.nontax.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资源
 * @TableName basic_resource
 */
@TableName(value ="basic_resource")
@Data
public class Resource implements Serializable {
    /**
     * 资源ID
     */
    @TableId
    private Long id;

    /**
     * 资源名称  注意这个也可以是界面上的按钮
     */
    private String name;

    /**
     * 子系统ID，预留
     */
    private Long subSystemId;

    /**
     * 父亲节点
     */
    private Long parentId;

    /**
     * URL 这个决定加载什么组件 对应import组件
     */
    private String url;

    /**
     * 后端接口地址组件的 path属性
     */
    private String api;

    /**
     * 资源类型  这个是菜单还是按钮 0 菜单 1 按钮
     */
    private Integer resourceType;

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
