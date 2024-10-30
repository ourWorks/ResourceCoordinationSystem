package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.po.CallHelpPO;
import com.po.VolunteerPO;
import com.service.CallHelpService;
import com.mapper.CallHelpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author hero
* @description 针对表【call_help】的数据库操作Service实现
* @createDate 2022-03-13 18:59:29
*/
@Service
public class CallHelpServiceImpl extends ServiceImpl<CallHelpMapper, CallHelpPO>
    implements CallHelpService{

    @Autowired
    CallHelpMapper callHelpMapper;

    @Override
    public List<CallHelpPO> getWaitingOrArrangedTask(CallHelpPO callHelpPO) {
        return callHelpMapper.getWaitingOrArrangedTask(callHelpPO);
    }

    @Override
    public List<CallHelpPO> myVolunteerTask(VolunteerPO volunteerPO) {
        return callHelpMapper.myVolunteerTask(volunteerPO);
    }

    @Override
    public int getDaiJiuYuan() {
        return callHelpMapper.getDaiJiuYuan();
    }

    @Override
    public int getYiFenPei() {
        return callHelpMapper.getYiFenPei();
    }

    @Override
    public int getJiuYuanZhong() {
        return callHelpMapper.getJiuYuanZhong();
    }

    @Override
    public int getYiWanCheng() {
        return callHelpMapper.getYiWanCheng();
    }

//    @Override
//    public String getClientIp(HttpServletRequest request) {
//
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("X-Real-IP");
//            //LOGGER.error("X-Real-IP:"+ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("http_client_ip");
////            LOGGER.error("http_client_ip:"+ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
////            LOGGER.error("getRemoteAddr:"+ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
////            LOGGER.error("Proxy-Client-IP:"+ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
////            LOGGER.error("WL-Proxy-Client-IP:"+ip);
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
////            LOGGER.error("HTTP_X_FORWARDED_FOR:"+ip);
//        }
//        // 如果是多级代理，那么取第一个ip为客户ip
//        if (ip != null && ip.indexOf(",") != -1) {
//            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
////            LOGGER.error("ip:"+ip);
//        }
//
//        return ip;
//    }
}




