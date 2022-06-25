package com.bosssoft.trainee.nontax.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.trainee.nontax.entity.dto.SubSystemDTO;
import com.bosssoft.trainee.nontax.entity.vo.SubSystemVO;
import com.bosssoft.trainee.nontax.service.SubSystemService;
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
@RequestMapping("/subsys-table")
public class SubsysTableController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SubSystemService subSystemService;

    /**
     * 根据前端传递的参数列出所有子系统
     *
     * @param map
     * @return
     */
    @GetMapping("/list")
    public Result listAllSubsys(@RequestParam Map<String, Object> map) {
        logger.info("子系统查询："+map.toString());
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<SubSystemDTO> subSystemDTOS = subSystemService.listSubsysByMap(map);
        List<SubSystemVO> subSystemVOS = new ArrayList<>();
        subSystemDTOS.forEach(x -> {
            SubSystemVO subSystemVO = new SubSystemVO();
            BeanUtil.copyProperties(x, subSystemVO);
            subSystemVOS.add(subSystemVO);
        });
        stringObjectHashMap.put("items", subSystemVOS);
        stringObjectHashMap.put("total", subSystemVOS.size());
        return Result.ok(stringObjectHashMap);
    }

    /**
     * 创建子系统
     *
     * @param map
     * @return
     */
    @PostMapping("/create")
    public Result createNewSubsys(@RequestBody Map<String, Object> map) {
        String subsysName = (String) map.get("subsys_name");
        Boolean exists = subSystemService.hasThisSubsys(subsysName);
        if (exists) {
            return Result.fail().message("子系统已存在");
        }
        Boolean success = subSystemService.saveSubsys(map);
        if (success) {
            return Result.ok().message("创建子系统成功");
        } else {
            return Result.fail().message("创建子系统失败");
        }
    }

    /**
     * 修改子系统信息
     *
     * @param map
     * @return
     */
    @PostMapping("/update")
    public Result updateSubsys(@RequestBody Map<String, Object> map) {
        logger.info("子系统修改：" + map.toString());
        Boolean success = subSystemService.updateSubsys(map);
        if (success) {
            return Result.ok().message("子系统信息修改成功");
        } else {
            return Result.fail().message("子系统信息修改失败");
        }
    }

    /**
     * 删除子系统
     * @param map
     * @return
     */
    @PostMapping("/delete")
    public Result deleteSubsys(@RequestBody Map<String, Object> map) {
        logger.info("子系统删除：" + map.toString());
        Boolean success = subSystemService.removeById(Long.valueOf((String) map.get("id")));
        if (success) {
            return Result.ok().message("子系统信息删除成功");
        } else {
            return Result.fail().message("子系统信息删除失败");
        }
    }
}
