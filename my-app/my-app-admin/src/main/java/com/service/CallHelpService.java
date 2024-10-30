package com.service;

import com.po.CallHelpPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.po.VolunteerPO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author hero
* @description 针对表【call_help】的数据库操作Service
* @createDate 2022-03-13 18:59:29
*/
public interface CallHelpService extends IService<CallHelpPO> {

    List<CallHelpPO> getWaitingOrArrangedTask(CallHelpPO callHelpPO);

    List<CallHelpPO> myVolunteerTask(VolunteerPO volunteerPO);

    int getDaiJiuYuan();
    int getYiFenPei();
    int getJiuYuanZhong();
    int getYiWanCheng();
}
