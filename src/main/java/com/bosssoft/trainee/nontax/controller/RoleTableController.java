package com.bosssoft.trainee.nontax.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bosssoft.trainee.nontax.entity.dto.RoleDTO;
import com.bosssoft.trainee.nontax.entity.vo.RoleVO;
import com.bosssoft.trainee.nontax.service.RoleService;
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
@RequestMapping("/role-table")
public class RoleTableController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public Result listAllRoles(@RequestParam Map<String, Object> map) {
        logger.info("角色查询：{}",map);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        List<RoleDTO> roleDTOS = roleService.listRolePageByCondition(map);
        List<RoleVO> roleVOS = new ArrayList<>();
        roleDTOS.forEach(x -> {
            RoleVO roleVO = new RoleVO();
            BeanUtil.copyProperties(x, roleVO);
            roleVOS.add(roleVO);
        });
        stringObjectHashMap.put("items", roleVOS);
        stringObjectHashMap.put("total", roleVOS.size());
        return Result.ok(stringObjectHashMap);
    }

    /**
     * 创建用户
     * @param map 用于接收客户端发送的数据
     * @return
     */
    @PostMapping("/create")
    public Result createNewRole(@RequestBody Map<String, Object> map) {
        String roleName = (String) map.get("role_name");
        String companyName = (String) map.get("role_company");
        boolean b = roleService.hasThisRole(companyName, roleName);
        if(b){
            return Result.fail().message("角色名已存在！");
        }
        String roleRemark = (String) map.getOrDefault("role_remark",null);
        logger.info("添加角色:角色名：{}，公司名称：{}，角色备注：{}",roleName,companyName,roleRemark);
        boolean success = roleService.saveRole(companyName, roleName, roleRemark);
        if (success) {
            return Result.ok().message("保存成功");
        } else {
            return Result.fail().message("角色创建失败");
        }
    }

    /**
     * 修改角色信息
     * @param map 用于接收客户端发送的数据
     * @return
     */
    @PostMapping("/update")
    public Result updateRole(@RequestBody Map<String, Object> map) {
        logger.info("修改角色:{}",map);
        boolean success = roleService.updateRole(map);
        if (success) {
            return Result.ok().message("修改成功");
        }else{
            return Result.fail().message("修改失败");
        }
    }

    /**
     * 删除角色信息
     * @param map
     * @return
     */
    @PostMapping("/delete")
    public Result deleteRole(@RequestBody Map<String, Object> map) {
        Long roleId = Long.valueOf((String) map.get("id"));
        logger.info("删除角色：角色id：{}",roleId);
        boolean success = roleService.removeById(roleId);
        if (success) {
            return Result.ok().message("删除成功");
        } else {
            return Result.fail().message("删除失败");
        }
    }
}
