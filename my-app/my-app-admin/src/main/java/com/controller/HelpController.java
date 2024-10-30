package com.controller;

import com.common.util.RequestUtil;
import com.common.http.HttpResult;
import com.po.CallHelpPO;
import com.service.CallHelpService;
import com.util.JwtUtils;
import com.vo.CallHelpVO;
import com.vo.CallHelpVOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class HelpController {


    @Autowired
    CallHelpService callHelpService;

    @Autowired
    private JwtUtils jwtUtils;

    //如果不加（“/call”）貌似会出错
    @PostMapping("/call")
    public HttpResult call(@RequestBody CallHelpPO callHelpPO, HttpServletRequest request) {
        log.info("进入call请求里");
        System.out.println(callHelpPO.toString());
        //获取客户端的IP地址
        RequestUtil requestUtil = new RequestUtil();
        String ip = requestUtil.getRequestIp(request);
        callHelpPO.setIp(ip);
        //获取现在的时间
        callHelpPO.setCallTime(LocalDateTime.now());
        //取出id
//        String token = request.getHeader("authorization");
//        if (jwtUtils.isTokenExpired(token)) {
//            Long id = Long.parseLong(jwtUtils.parseJwt(token).getId());
//            callHelpPO.setId(id);
//        } else {
//            return HttpResult.error(505, "token超时");
//        }
        Long id = Long.parseLong(request.getHeader("id"));
        callHelpPO.setId(id);
        //插入申请
        callHelpService.save(callHelpPO);
        log.info("保存后的步骤");
        return HttpResult.ok("成功发出求助申请");
    }

    //根据市民id获取他的所有求助列表
    @PostMapping("/call/helpList")
    public HttpResult helpList(HttpServletRequest request) {
        //构造的HashMap是将请求人的id放进去，并根据id来查询
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", request.getHeader("id"));
        paramMap.put("deleteState", 0);
        List<CallHelpPO> callHelpPOList = callHelpService.listByMap(paramMap);
        //转换为视图的VO
        List<CallHelpVO> callHelpVOList = new ArrayList<>();
        for (CallHelpPO callHelpPO : callHelpPOList) {
            CallHelpVO callHelpVO = CallHelpVOMapper.INSTANCE.po2Vo(callHelpPO);
            callHelpVOList.add(callHelpVO);
        }
        log.info(callHelpPOList.toString());
        log.info("测试！！！");
        log.info(callHelpVOList.toString());
        return HttpResult.ok(callHelpVOList);
    }

    //根据cid获取某个求助单
    @PostMapping("/call/editHelp")
    public HttpResult editHelp(@RequestBody CallHelpPO callHelpPO) {
        //构造的HashMap是将请求人的id放进去，并根据id来查询
        callHelpPO = callHelpService.getById(callHelpPO.getCid());
        CallHelpVO callHelpVO = CallHelpVOMapper.INSTANCE.po2Vo(callHelpPO);
        log.info(callHelpPO.toString());
        log.info("测试！！！");
        log.info(callHelpVO.toString());
        return HttpResult.ok(callHelpVO);
    }

    //根据cid更新某个求助单
    @PostMapping("/call/updateHelp")
    public HttpResult updateHelp(@RequestBody CallHelpPO callHelpPO) {
        //根据id来更新
        boolean updateFlag = callHelpService.updateById(callHelpPO);
        return HttpResult.ok(updateFlag);
    }

    //根据cid删除某个求助单
    @PostMapping("/call/delHelp")
    public HttpResult delHelp(@RequestBody CallHelpPO callHelpPO) {
        //根据id来”删除“
        callHelpPO.setDeleteState(1);
        boolean deleteFlag = callHelpService.updateById(callHelpPO);
        return HttpResult.ok(deleteFlag);
    }
}
