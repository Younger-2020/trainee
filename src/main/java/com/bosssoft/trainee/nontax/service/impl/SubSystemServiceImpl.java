package com.bosssoft.trainee.nontax.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.nontax.entity.dto.SubSystemDTO;
import com.bosssoft.trainee.nontax.entity.po.SubSystem;
import com.bosssoft.trainee.nontax.service.SubSystemService;
import com.bosssoft.trainee.nontax.mapper.SubSystemMapper;
import com.bosssoft.trainee.nontax.util.SortHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author DELL
 * @description 针对表【basic_sub_system(角色)】的数据库操作Service实现
 * @createDate 2022-06-20 21:21:24
 */
@Service
public class SubSystemServiceImpl extends ServiceImpl<SubSystemMapper, SubSystem> implements SubSystemService {

    @Autowired
    private SubSystemMapper subsystemMapper;

    @Override
    public List<SubSystemDTO> listSubsysByMap(Map<String, Object> map) {
        // 从map中获取各项参数
        Integer pageNo = Integer.valueOf((String) map.get("page"));
        Integer pageSize = Integer.valueOf((String) map.get("limit"));
        String sort = (String) map.get("sort");
        String subsysName = (String) map.get("subsys_name");
        String subsysRemark = (String) map.get("subsys_remark");
        // 创建page对象、wrapper对象用于查询
        Page page = new Page(pageNo, pageSize);
        page.setOrders(SortHelper.getOrderItem(sort));
        QueryWrapper<SubSystem> subSystemDTOQueryWrapper = new QueryWrapper<>();
        subSystemDTOQueryWrapper.like(subsysName != null, "name", subsysName)
                .like(subsysRemark != null, "remark", subsysRemark);
        Page selectPage = subsystemMapper.selectPage(page, subSystemDTOQueryWrapper);
        List<SubSystem> records = selectPage.getRecords();
        // 将获取到的PO转为DTO返回
        ArrayList<SubSystemDTO> subSystemDTOS = new ArrayList<>();
        records.forEach(x -> {
            SubSystemDTO subSystemDTO = new SubSystemDTO();
            BeanUtil.copyProperties(x, subSystemDTO);
            subSystemDTOS.add(subSystemDTO);
        });
        return subSystemDTOS;
    }

    @Override
    public boolean hasThisSubsys(String subsysName) {
        QueryWrapper<SubSystem> subSystemQueryWrapper = new QueryWrapper<>();
        subSystemQueryWrapper.eq("name", subsysName);
        SubSystem subSystem = subsystemMapper.selectOne(subSystemQueryWrapper);
        return subSystem != null;
    }

    @Override
    public boolean saveSubsys(Map<String, Object> map) {
        SubSystem subSystem = new SubSystem();
        subSystem.setName((String) map.get("subsys_name"));
        subSystem.setImageUrl((String) map.get("subsys_image_url"));
        subSystem.setRemark((String) map.get("subsys_remark"));
        subSystem.setLink((String) map.get("subsys_link"));
        subSystem.setStatus(1);
        int insert = subsystemMapper.insert(subSystem);
        return insert > 0;
    }

    @Override
    public boolean updateSubsys(Map<String, Object> map) {
        SubSystem subSystem = new SubSystem();
        subSystem.setId(Long.valueOf((String) map.get("id")));
        subSystem.setName((String) map.get("subsys_name"));
        subSystem.setImageUrl((String) map.get("subsys_image_url"));
        subSystem.setRemark((String) map.get("subsys_remark"));
        subSystem.setLink((String) map.get("subsys_link"));
        int update = subsystemMapper.updateById(subSystem);
        return update > 0;
    }
}




