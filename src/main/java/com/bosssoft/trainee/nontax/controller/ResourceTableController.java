package com.bosssoft.trainee.nontax.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.trainee.nontax.entity.dto.ResourceDTO;
import com.bosssoft.trainee.nontax.entity.vo.ResourceVO;
import com.bosssoft.trainee.nontax.service.ResourceService;
import com.bosssoft.trainee.nontax.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resources-table")
public class ResourceTableController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ResourceService resourceService;

    /**
     * 根据前端传递的参数列出所有资源
     *
     * @param map
     * @return
     */
    @GetMapping("/list")
    public Result listAllRoles(@RequestParam Map<String, Object> map) {
        logger.info("子系统查询：{}", map);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<ResourceDTO> resourceDTOS = resourceService.listResourceByMap(map);
        List<ResourceVO> resourceVOS = new ArrayList<>();
        resourceDTOS.forEach(x -> {
            ResourceVO resourceVO = new ResourceVO();
            BeanUtil.copyProperties(x, resourceVO);
            resourceVOS.add(resourceVO);
        });
        stringObjectHashMap.put("items", resourceVOS);
        stringObjectHashMap.put("total", resourceVOS.size());
        return Result.ok(stringObjectHashMap);
    }

    /**
     * 创建资源
     *
     * @param map
     * @return
     */
    @PostMapping("/create")
    public Result createNewResource(@RequestBody Map<String, Object> map) {
        logger.info("资源创建：{}", map);
        String resourcesUrl = (String) map.get("resource_url");
        boolean exists = resourceService.hasThisResource(resourcesUrl);
        if (exists) {
            return Result.fail().message("资源已存在");
        }
        boolean success = resourceService.saveResource(map);
        if (success) {
            return Result.ok().message("创建资源成功");
        } else {
            return Result.fail().message("创建资源失败");
        }
    }

    /**
     * 修改资源信息
     *
     * @param map
     * @return
     */
    @PostMapping("/update")
    public Result updateResource(@RequestBody Map<String, Object> map) {
        logger.info("资源修改：{}", map);
        boolean success = resourceService.updateResource(map);
        if (success) {
            return Result.ok().message("资源信息修改成功");
        } else {
            return Result.fail().message("资源信息修改失败");
        }
    }

    /**
     * 删除子系统
     *
     * @param map
     * @return
     */
    @PostMapping("/delete")
    public Result deleteResource(@RequestBody Map<String, Object> map) {
        logger.info("资源删除：{}", map);
        boolean success = resourceService.removeById(Long.valueOf((String) map.get("id")));
        if (success) {
            return Result.ok().message("资源信息删除成功");
        } else {
            return Result.fail().message("资源信息删除失败");
        }
    }
}
