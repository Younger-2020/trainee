package com.bosssoft.trainee.nontax.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.nontax.entity.dto.ResourceDTO;
import com.bosssoft.trainee.nontax.entity.po.Resource;
import com.bosssoft.trainee.nontax.mapper.ResourceMapper;
import com.bosssoft.trainee.nontax.service.ResourceService;
import com.bosssoft.trainee.nontax.util.SortHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @description 针对表【basic_resource(资源)】的数据库操作Service实现
 * @createDate 2022-06-20 21:21:24
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource>
        implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<ResourceDTO> listResourceByMap(Map<String, Object> map) {
        // 从map中获取各项参数
        Integer pageNo = Integer.valueOf((String) map.get("page"));
        Integer pageSize = Integer.valueOf((String) map.get("limit"));
        String sort = (String) map.get("sort");
        String resourceName = (String) map.get("resource_name");
        // 创建page对象、wrapper对象用于查询
        Page page = new Page(pageNo, pageSize);
        page.setOrders(SortHelper.getOrderItem(sort));
        QueryWrapper<Resource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper.like(resourceName != null, "name", resourceName);
        Page selectPage = resourceMapper.selectPage(page, resourceQueryWrapper);
        List<Resource> records = selectPage.getRecords();
        // 将获取到的PO转为DTO返回
        ArrayList<ResourceDTO> resourceDTOS = new ArrayList<>();
        records.forEach(x -> {
            ResourceDTO resourceDTO = new ResourceDTO();
            BeanUtil.copyProperties(x, resourceDTO);
            resourceDTOS.add(resourceDTO);
        });
        return resourceDTOS;
    }

    @Override
    public boolean hasThisResource(String resourceUrl) {
        QueryWrapper<Resource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper.eq("url", resourceUrl);
        Resource resource = resourceMapper.selectOne(resourceQueryWrapper);
        return resource != null;
    }

    @Override
    public boolean saveResource(Map<String, Object> map) {
        Resource resource = new Resource();
        resource.setName((String) map.get("resource_name"));
        resource.setUrl((String) map.get("resource_url"));
        resource.setRemark((String) map.get("resource_remark"));
        String resourceType = (String) map.get("resource_type");
        if (resourceType != null) {
            resource.setResourceType(resourceType.equals("菜单") ? 0 : 1);
        }
        resource.setStatus(1);
        int insert = resourceMapper.insert(resource);
        return insert > 0;
    }

    @Override
    public boolean updateResource(Map<String, Object> map) {
        Resource resource = new Resource();
        Long id = Long.valueOf((String) map.get("id"));
        resource.setId(id);
        resource.setName((String) map.get("resource_name"));
        resource.setUrl((String) map.get("resource_url"));
        resource.setRemark((String) map.get("resource_remark"));
        String resourceType = (String) map.get("resource_type");
        if (resourceType != null) {
            resource.setResourceType(resourceType.equals("菜单") ? 0 : 1);
        }
        int update = resourceMapper.updateById(resource);
        return update > 0;
    }
}




