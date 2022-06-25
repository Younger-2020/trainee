package com.bosssoft.trainee.nontax.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bosssoft.trainee.nontax.entity.po.Company;
import com.bosssoft.trainee.nontax.service.CompanyService;
import com.bosssoft.trainee.nontax.mapper.CompanyMapper;
import org.springframework.stereotype.Service;

/**
* @author DELL
* @description 针对表【basic_company】的数据库操作Service实现
* @createDate 2022-06-20 21:20:59
*/
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company>
    implements CompanyService{

}




