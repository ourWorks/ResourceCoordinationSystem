package com.controller;

/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.http.HttpResult;
import com.po.CallHelpPO;
import com.po.OrganizationPO;
import com.po.VolunteerPO;
import com.service.CallHelpService;
import com.service.OrganizationService;
import com.service.VolunteerService;
import com.vo.VolunteerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class VolunteerController {


    @Autowired
    VolunteerService volunteerService;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    CallHelpService callHelpService;


    //一、根据VolunteerPO的id来查询本市民是否是志愿者
    @PostMapping("/isVolunteer")
    public HttpResult isVolunteer(HttpServletRequest request) {
        QueryWrapper<VolunteerPO> queryWrapper = new QueryWrapper<>();
        //根据id来查询某个市民是否是志愿者
        String id = request.getHeader("id");
        queryWrapper.eq("id",id);
        VolunteerPO oneVolunteer = volunteerService.getOne(queryWrapper);
        if(oneVolunteer==null){
            //这里写ok是为了不要让前端自动提示信息
//            return HttpResult.error(401,"登录过时");
            return HttpResult.ok("这个市民没有作为志愿者加入任何组织");
        }
        return HttpResult.ok(oneVolunteer);
    }

    //新增组织或修改组织信息
    //二、作为志愿者加入组织，这里可以实现一个市民作为志愿者加入多个组织，但是根据业务，只实现一个市民加入一个组织:
    //这里的id不从request取了，额外再传入id和name
    @PostMapping("/addOrUpdateVolunteer")
    public HttpResult addOrUpdateVolunteer(@RequestBody VolunteerPO volunteerPO) {
        QueryWrapper<VolunteerPO> queryWrapper = new QueryWrapper<>();
        //曾经加入过该组织，只是后来退出了
        queryWrapper.eq("orgId",volunteerPO.getOrgId());
        queryWrapper.eq("id",volunteerPO.getId());
        //旧时加入过该组织的志愿者记录信息，就恢复该志愿者身份
        VolunteerPO old_volunteerPO = volunteerService.getOne(queryWrapper);
        //要加入的组织
        OrganizationPO toOrg = organizationService.getById(volunteerPO.getOrgId());
        if (old_volunteerPO != null) {
            //功能1：更改旧的删除状态，功能二：修改志愿者信息
            volunteerPO.setDeleteState(0);
            volunteerPO.setVolunteerId(old_volunteerPO.getVolunteerId());
            //volunteer调用这个方法，ById它的id是orgId
            volunteerService.updateById(volunteerPO);
            return HttpResult.ok(volunteerPO);
        }
        //如果没有这个组织。就返回提示
        else if(toOrg==null){
            return HttpResult.error("组织号输入错误！");
        }
        else{
            //插入新的志愿者信息
            volunteerService.save(volunteerPO);
            return HttpResult.ok(toOrg);
        }
    }

    //三、查询本组织的所有志愿者
    @PostMapping("/getOrgVolunteers")
    public HttpResult getOrgVolunteers(@RequestBody OrganizationPO organizationPO) {
        List<VolunteerVO> orgVolunteers = volunteerService.findOrgVolunteers(organizationPO);
        return HttpResult.ok(orgVolunteers);
    }

    //四、作为志愿者退出组织（要和删除组织区分）
    @PostMapping("/quitOrgAsVolunteer")
    public HttpResult quitOrgAsVolunteer(@RequestBody VolunteerPO volunteerPO) {
        boolean flag = volunteerService.markDeleteState(volunteerPO);
        return HttpResult.ok(flag);
    }


    //志愿者模块-选取救助：系统内所有”待救援“、“被分配” && "未删除"的表单
    @PostMapping("/getWaitingOrArrangedTask")
    public HttpResult AllHelpTasks(@RequestBody CallHelpPO callHelpPO) {
        List<CallHelpPO> helpList = callHelpService.getWaitingOrArrangedTask(callHelpPO);
        return HttpResult.ok(helpList);
    }

    //志愿者模块-求助列表：某个志愿者选取/接受某个志愿任务，更改状态为“救援中”
    @PostMapping("/beginVolunteerTask")
    public HttpResult beginVolunteerTask(@RequestBody CallHelpPO callHelpPO) {
        CallHelpPO one_call = callHelpService.getById(callHelpPO.getCid());
        one_call.setState("救援中");
        one_call.setVolunteerId(callHelpPO.getVolunteerId());
        callHelpService.updateById(one_call);
        return HttpResult.ok(one_call);
    }

    //志愿者模块-我的任务：获取某个志愿者的求助表单信息：“被分配”、“救助中”、“已完成” && "未删除"的表单
    @PostMapping("/myVolunteerTask")
    public HttpResult myVolunteerTask(@RequestBody VolunteerPO volunteerPO) {
        List<CallHelpPO> helpList = callHelpService.myVolunteerTask(volunteerPO);
        return HttpResult.ok(helpList);
    }

    //志愿者模块-我的任务：更新状态
    @PostMapping("/changeHelpState")
    public HttpResult changeHelpState(@RequestBody CallHelpPO callHelpPO) {
        CallHelpPO one_call = callHelpService.getById(callHelpPO.getCid());
        one_call.setState(callHelpPO.getState());
        if(callHelpPO.getState().equals("待救援")){
            one_call.setVolunteerId(null);
        }
        callHelpService.updateById(one_call);
        return HttpResult.ok(one_call);
    }

}
