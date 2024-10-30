package com.mapper;

import com.po.CitizenPO;
import com.po.LeaderPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author hero
* @description 针对表【leader】的数据库操作Mapper
* @createDate 2022-03-16 15:09:50
* @Entity com.po.LeaderPO
*/
public interface LeaderMapper extends BaseMapper<LeaderPO> {

    List<CitizenPO> getOrgLeaders(Long orgId);

    int getLeaderNum();
}




