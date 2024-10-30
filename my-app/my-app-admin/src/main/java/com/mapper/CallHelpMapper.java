package com.mapper;

import com.po.CallHelpPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.po.OrganizationPO;
import com.po.VolunteerPO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* @author hero
* @description 针对表【call_help】的数据库操作Mapper
* @createDate 2022-03-16 15:09:50
* @Entity com.po.CallHelpPO
*/
@Component
public interface CallHelpMapper extends BaseMapper<CallHelpPO> {

    List<CallHelpPO> getWaitingOrArrangedTask(CallHelpPO callHelpPO);
    List<CallHelpPO> myVolunteerTask(VolunteerPO volunteerPO);
    List<CallHelpPO> getAllWaitingTask();

    //获取待救援、已分配、救援中、已完成四种状态
    int getDaiJiuYuan();
    int getYiFenPei();
    int getJiuYuanZhong();
    int getYiWanCheng();
}




