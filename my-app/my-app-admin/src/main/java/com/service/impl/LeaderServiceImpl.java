package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mapper.CitizenMapper;
import com.po.CitizenPO;
import com.po.LeaderPO;
import com.service.LeaderService;
import com.mapper.LeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hero
* @description 针对表【leader】的数据库操作Service实现
* @createDate 2022-03-13 18:59:29
*/
@Service
public class LeaderServiceImpl extends ServiceImpl<LeaderMapper, LeaderPO>
    implements LeaderService{


    @Autowired
    private LeaderMapper leaderMapper;

    @Override
    public List<CitizenPO> getOrgLeaders(Long orgId) {
        return leaderMapper.getOrgLeaders(orgId);
    }

    @Override
    public int getLeaderNum() {
        return leaderMapper.getLeaderNum();
    }
}




