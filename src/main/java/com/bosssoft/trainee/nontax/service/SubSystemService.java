package com.bosssoft.trainee.nontax.service;

import com.bosssoft.trainee.nontax.entity.dto.SubSystemDTO;
import com.bosssoft.trainee.nontax.entity.po.SubSystem;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
* @author DELL
* @description 针对表【basic_sub_system(角色)】的数据库操作Service
* @createDate 2022-06-20 21:21:24
*/
public interface SubSystemService extends IService<SubSystem> {
    /**
     * 获取所有子系统信息
     * @param map
     * @return
     */
    List<SubSystemDTO> listSubsysByMap(Map<String, Object> map);

    /**
     * 判断子系统表中是否已经存在子系统
     * @param SubsysName
     * @return
     */
    boolean hasThisSubsys(String SubsysName);

    /**
     * 通过前端传递过来的参数保存子系统
     * @param map
     * @return
     */
    boolean saveSubsys(Map<String, Object> map);

    /**
     * 通过前端传递过来的参数修改子系统
     * @param map
     * @return
     */
    boolean updateSubsys(Map<String, Object> map);
}
