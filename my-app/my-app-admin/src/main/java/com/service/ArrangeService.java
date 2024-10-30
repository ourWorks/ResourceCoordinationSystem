package com.service;

import com.dto.VolunteerInfoDTO;
import com.po.ArrangePO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.po.OrganizationPO;
import com.vo.ArrangeVO;
import com.vo.OrgResourceVO;
import java.util.List;

/**
* @author hero
* @description 针对表【arrange】的数据库操作Service
* @createDate 2022-03-13 18:59:29
*/
public interface ArrangeService extends IService<ArrangePO> {
    List<OrgResourceVO> showOrgResource(OrganizationPO organizationPO);
    List<VolunteerInfoDTO> getAvailableVolunteers(ArrangePO arrangePO);
//    List<ArrangeVO> arrangeByTime(OrganizationPO organizationPO);
}
