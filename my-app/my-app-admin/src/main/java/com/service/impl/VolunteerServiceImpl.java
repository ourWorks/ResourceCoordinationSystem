package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mapper.CitizenMapper;
import com.po.OrganizationPO;
import com.po.VolunteerPO;
import com.service.VolunteerService;
import com.mapper.VolunteerMapper;
import com.vo.VolunteerVO;
import com.vo.VolunteerVOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hero
* @description 针对表【volunteer】的数据库操作Service实现
* @createDate 2022-03-21 23:53:29
*/
@Service
public class VolunteerServiceImpl extends ServiceImpl<VolunteerMapper, VolunteerPO>
    implements VolunteerService{

    @Autowired
    VolunteerMapper volunteerMapper;

    @Override
    public List<VolunteerVO> findOrgVolunteers(OrganizationPO organizationPO) {
        return volunteerMapper.findOrgVolunteers(organizationPO);
    }

    @Override
    public boolean markDeleteState(VolunteerPO volunteerPO) {
        return volunteerMapper.markDeleteState(volunteerPO);
    }

    @Override
    public int getVolunteerNum() {
        return volunteerMapper.getVolunteerNum();
    }
}




