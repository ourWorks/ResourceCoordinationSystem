package com.mapper;

import com.po.CitizenPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author hero
* @description 针对表【citizen】的数据库操作Mapper
* @createDate 2022-03-16 15:09:50
* @Entity com.po.CitizenPO
*/
public interface CitizenMapper extends BaseMapper<CitizenPO> {
    CitizenPO selectOneByPhoneOrEmailOrIdCard(CitizenPO citizenPO);
    CitizenPO verify(CitizenPO citizenPO);
    int getCitizenNum();
}




