package com.bosssoft.trainee.nontax.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResourceVO implements Serializable {
    /**
     * 资源ID
     */
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
     * 后端接口地址读音组件的 path属性
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
}
