package com.mapper;

import com.po.CitizenPO;
import com.po.OrganizationPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.po.VolunteerPO;
import com.vo.ArrangeVO;
import com.vo.CallHelpVO;
import com.vo.VolunteerVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* @author hero
* @description 针对表【organization】的数据库操作Mapper
* @createDate 2022-03-16 15:09:50
* @Entity com.po.OrganizationPO
*/
@Component
public interface OrganizationMapper extends BaseMapper<OrganizationPO> {

    //展示已经安排过的列表
    List<ArrangeVO> findArrangeList(OrganizationPO organizationPO);
    List<ArrangeVO> findAddArrangeByStr(String cidStr);

//    List<ArrangeVO> findAddArrangeList(List<Long> cids); 这样写失效了

}




