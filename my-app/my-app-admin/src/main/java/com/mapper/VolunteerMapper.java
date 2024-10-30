package com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.po.OrganizationPO;
import com.po.VolunteerPO;
import com.vo.VolunteerVO;

import java.util.List;

/**
* @author hero
* @description 针对表【volunteer】的数据库操作Mapper
* @createDate 2022-03-21 23:53:29
* @Entity com.po.Volunteer
*/
public interface VolunteerMapper extends BaseMapper<VolunteerPO> {
    List<VolunteerVO> findOrgVolunteers(OrganizationPO organizationPO);
    boolean markDeleteState(VolunteerPO volunteerPO);
    int getVolunteerNum();
}




