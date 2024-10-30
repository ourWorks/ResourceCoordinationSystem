package com.controller;/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.http.HttpResult;
import com.po.CitizenPO;
import com.po.LeaderPO;
import com.po.OrganizationPO;
import com.service.CitizenService;
import com.service.LeaderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class LeaderController {


    @Autowired
    LeaderService leaderService;

    @Autowired
    CitizenService citizenService;

    //一、作为领导加入组织，一个市民只管理一个组织，根据市民id和组织id确定
    @PostMapping("/addLeader")
    public HttpResult addLeader(@RequestBody LeaderPO leaderPO) {
        QueryWrapper<LeaderPO> queryWrapper = new QueryWrapper<>();
        //曾经管理过该组织，只是后来退出了
        queryWrapper.eq("id", leaderPO.getId());
        queryWrapper.eq("deleteState", 0);
        //如果这个市民已经是领导，那就返回提示[leader表未删除的领导有，他的orgId不是要加入的orgId就会出错];否则就更新信息，即更改删除状态或者新增
        LeaderPO old_leaderPO = leaderService.getOne(queryWrapper);

        QueryWrapper<CitizenPO> citizenPOQueryWrapper = new QueryWrapper<>();
        citizenPOQueryWrapper.eq("id", leaderPO.getId());
        if(citizenService.getOne(citizenPOQueryWrapper)==null){
            return HttpResult.error("找不到该市民，请重新输入市民号！");
        }
        else if (old_leaderPO != null &&
                old_leaderPO.getOrgId() != leaderPO.getLeaderId()) {
            return HttpResult.error("这位市民已经是某个组织的领导！请检查市民号");
        } else {
            leaderService.saveOrUpdate(leaderPO);
            queryWrapper.eq("orgId", leaderPO.getOrgId());
            LeaderPO new_leaderPO = leaderService.getOne(queryWrapper);
            //返回展示的领导信息
            CitizenPO citizenPO = citizenService.getById(leaderPO.getId());
            citizenPO.setPassword(null);
            citizenPO.setDeleteState(null);
            return HttpResult.ok(citizenPO);
        }
    }

    //二、根据id来查询本市民是否是领导
    @PostMapping("/isLeader")
    public HttpResult isLeader(HttpServletRequest request) {
        QueryWrapper<LeaderPO> queryWrapper = new QueryWrapper<>();
        //根据id来查询某个市民是否是领导
        String id = request.getHeader("id");
        queryWrapper.eq("id", id);
        queryWrapper.eq("deleteState", 0);
        LeaderPO oneLeader;
        try {
            oneLeader = leaderService.getOne(queryWrapper);
        } catch (TooManyResultsException manyResultsException) {
            return HttpResult.error("这个市民加入了多个组织");
        }
        if (oneLeader == null) {
            //这里写ok是为了不要让前端自动提示信息
            return HttpResult.ok("这个市民没有作为领导管理任何组织");
        }
        return HttpResult.ok(oneLeader);
    }


    //三、作为领导退出组织（要和删除组织区分）
    @PostMapping("/quitOrgAsLeader")
    public HttpResult quitOrgAsLeader(@RequestBody LeaderPO leaderPO) {
        //标记删除状态,这里主键是leaderId
        QueryWrapper<LeaderPO> queryWrapper = new QueryWrapper<>();
        //曾经管理过该组织，只是后来退出了
        queryWrapper.eq("orgId", leaderPO.getOrgId());
        queryWrapper.eq("id", leaderPO.getId());
        queryWrapper.eq("deleteState", 0);
        LeaderPO old_leaderPO = leaderService.getOne(queryWrapper);
        old_leaderPO.setDeleteState(1);
        leaderService.updateById(old_leaderPO);
        //返回操作结果
        return HttpResult.ok("删除成功");
    }

    //四、查询orgId查询该组织的所有领导的领导信息，只查leader表里面deleteState=0的
    @PostMapping("/getOrgLeaders")
    public HttpResult getOrgLeaders(@RequestBody OrganizationPO organizationPO) {
        List<CitizenPO> orgLeaders = leaderService.getOrgLeaders(organizationPO.getOrgId());
        //返回操作结果
        return HttpResult.ok(orgLeaders);
    }

}
