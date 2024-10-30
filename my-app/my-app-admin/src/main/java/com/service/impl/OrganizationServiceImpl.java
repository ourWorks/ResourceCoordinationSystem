package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.po.CitizenPO;
import com.po.OrganizationPO;
import com.po.VolunteerPO;
import com.service.OrganizationService;
import com.mapper.OrganizationMapper;
import com.vo.ArrangeVO;
import com.vo.VolunteerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author hero
* @description 针对表【organization】的数据库操作Service实现
* @createDate 2022-03-14 00:32:11
*/
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, OrganizationPO>
    implements OrganizationService{

    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public List<ArrangeVO> getArrangeData(OrganizationPO organizationPO) {
        //寻找组织OrgId寻找志愿者
        List<ArrangeVO> arrangeList = organizationMapper.findArrangeList(organizationPO);
        return arrangeList;
    }
}




