package com.bosssoft.trainee.nontax.service;

import com.bosssoft.trainee.nontax.entity.dto.ResourceDTO;
import com.bosssoft.trainee.nontax.entity.po.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author DELL
* @description 针对表【basic_resource(资源)】的数据库操作Service
* @createDate 2022-06-20 21:21:24
*/
public interface ResourceService extends IService<Resource> {
    /**
     * 获取所有资源信息
     * @param map
     * @return
     */
    List<ResourceDTO> listResourceByMap(Map<String, Object> map);

    /**
     * 查看资源是否已经存在
     * @param resourceUrl
     * @return
     */
    boolean hasThisResource(String resourceUrl);

    /**
     * 保存资源信息
     * @param map
     * @return
     */
    boolean saveResource(Map<String, Object> map);

    /**
     * 修改资源信息
     * @param map
     * @return
     */
    boolean updateResource(Map<String, Object> map);
}
