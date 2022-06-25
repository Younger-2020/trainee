package com.bosssoft.trainee.nontax.mapper;

import com.bosssoft.trainee.nontax.entity.po.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
* @author DELL
* @description 针对表【basic_company】的数据库操作Mapper
* @createDate 2022-06-20 21:20:59
* @Entity com.bosssoft.trainee.nontax.entity.po.Company
*/
@Repository
public interface CompanyMapper extends BaseMapper<Company> {
    Long selectIdByName(@Param("companyName") String companyName);
}




