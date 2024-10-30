package com.service;

import com.po.OrganizationPO;
import com.po.VolunteerPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vo.VolunteerVO;

import java.util.List;

/**
* @author hero
* @description 针对表【volunteer】的数据库操作Service
* @createDate 2022-03-21 23:53:29
*/
public interface VolunteerService extends IService<VolunteerPO> {

    List<VolunteerVO> findOrgVolunteers(OrganizationPO organizationPO);

    boolean markDeleteState(VolunteerPO volunteerPO);
    //获取志愿者人数
    int getVolunteerNum();
}
