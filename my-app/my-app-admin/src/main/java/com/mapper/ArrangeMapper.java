package com.mapper;

import com.dto.VolunteerInfoDTO;
import com.po.ArrangePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.po.OrganizationPO;
import com.vo.OrgResourceVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* @author hero
* @description 针对表【arrange】的数据库操作Mapper
* @createDate 2022-03-16 15:09:50
* @Entity com.po.ArrangePO
*/

//为了在algorithm。Strategy中注而加的，不知道为什么ArrangeServiceImpl中不用加
@Component

public interface ArrangeMapper extends BaseMapper<ArrangePO> {
    List<OrgResourceVO> showOrgResource(OrganizationPO organizationPO);

    List<VolunteerInfoDTO> getAvailableVolunteers(ArrangePO arrangePO);
}




