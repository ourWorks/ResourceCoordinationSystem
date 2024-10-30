package com.service;

import com.po.CitizenPO;
import com.po.LeaderPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hero
* @description 针对表【leader】的数据库操作Service
* @createDate 2022-03-13 18:59:29
*/
public interface LeaderService extends IService<LeaderPO> {

    List<CitizenPO> getOrgLeaders(Long orgId);

    int getLeaderNum();
}
