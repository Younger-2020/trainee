package com.bosssoft.trainee.nontax.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.nontax.entity.po.Department;
import com.bosssoft.trainee.nontax.service.DepartmentService;
import com.bosssoft.trainee.nontax.mapper.DepartmentMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【basic_department】的数据库操作Service实现
* @createDate 2022-06-20 21:21:15
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

}




