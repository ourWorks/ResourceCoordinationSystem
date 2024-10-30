package com.service;

import com.po.OrganizationPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.vo.ArrangeVO;

import java.util.List;

/**
* @author hero
* @description 针对表【organization】的数据库操作Service
* @createDate 2022-03-14 00:32:11
*/
public interface OrganizationService extends IService<OrganizationPO> {

    List<ArrangeVO> getArrangeData(OrganizationPO organizationPO);
}
