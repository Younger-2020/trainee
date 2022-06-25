package com.bosssoft.trainee.nontax.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.nontax.entity.dto.RoleDTO;
import com.bosssoft.trainee.nontax.entity.po.Role;
import com.bosssoft.trainee.nontax.mapper.CompanyMapper;
import com.bosssoft.trainee.nontax.service.RoleService;
import com.bosssoft.trainee.nontax.mapper.RoleMapper;
import com.bosssoft.trainee.nontax.util.SortHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @description 针对表【basic_role(角色)】的数据库操作Service实现
 * @createDate 2022-06-20 21:21:24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<RoleDTO> listRolePageByCondition(Map<String, Object> map) {
        logger.info("传递过来的参数:{}",map);
        Integer pageNo = Integer.valueOf((String) map.get("page"));
        Integer pageSize = Integer.valueOf((String) map.get("limit"));
        String sort = (String) map.get("sort");
        String roleName = (String) map.get("role_name");
        Page page = new Page(pageNo, pageSize);
        page.setOrders(SortHelper.getOrderItem(sort));
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.like(null != roleName, "name", roleName);
        Page selectPage = roleMapper.selectPage(page, roleQueryWrapper);
        List<Role> pageRecords = selectPage.getRecords();
        ArrayList<RoleDTO> roleDTOS = new ArrayList<>();
        pageRecords.forEach(x -> {
            RoleDTO roleDTO = new RoleDTO();
            BeanUtil.copyProperties(x, roleDTO);
            roleDTOS.add(roleDTO);
        });
        return roleDTOS;
    }

    @Override
    public boolean hasThisRole(String companyName, String roleName) {
        Long id = companyMapper.selectIdByName(companyName);
        Integer integer = roleMapper.countByNameAndCompanyId(roleName, id);
        return integer > 0;
    }

    @Override
    public boolean saveRole(String companyName, String roleName, String roleRemark) {
        Long id = companyMapper.selectIdByName(companyName);
        Role role = new Role();
        role.setName(roleName);
        role.setCompanyId(id);
        role.setRemark(roleRemark);
        role.setStatus(1);
        int insert = roleMapper.insert(role);
        return insert > 0;
    }

    @Override
    public boolean updateRole(Map<String, Object> map) {

        Long roleId = Long.valueOf((String) map.get("id"));
        String roleName = (String) map.get("role_name");
        String companyName = (String) map.get("role_company");
        String roleRemark = (String) map.get("role_remark");
        Long companyId = companyMapper.selectIdByName(companyName);
        Role role = new Role();
        role.setId(roleId);
        role.setName(roleName);
        role.setCompanyId(companyId);
        role.setRemark(roleRemark);
        int i = roleMapper.updateById(role);
        return i > 0;
    }
}




