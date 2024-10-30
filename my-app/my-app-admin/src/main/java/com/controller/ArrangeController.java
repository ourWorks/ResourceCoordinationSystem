package com.controller;


/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.algorithm.ArrangeTask;
import com.common.http.HttpResult;
import com.po.ArrangePO;
import com.po.OrganizationPO;
import com.po.VolunteerPO;
import com.service.ArrangeService;
import com.service.OrganizationService;
import com.vo.ArrangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ArrangeController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    ArrangeTask arrangeTask;

    //排班模块：按照传入的组织号来查找所有志愿者的姓名、手机号，检索志愿者对应本组织的救援表单（根据volunteerId或【id和orgId】）
    @PostMapping("/showArrange")
    public HttpResult showArrange(@RequestBody OrganizationPO organizationPO) {
        //查看信息，返回List<ArrangeVO>
        List<ArrangeVO> dataList = organizationService.getArrangeData(organizationPO);
        return HttpResult.ok(dataList);
    }

    //排班模块：，领导查看所有“待求助”状态表单的资源需求
    @PostMapping("/beginArrange")
    public HttpResult beginArrange(@RequestBody ArrangePO arrangePO) {
//        arrangeTask.setTaskType("time",arrangePO);
        arrangeTask.setTaskType(arrangePO);
        List<ArrangeVO> arrangeVOS = arrangeTask.getStrategyResult();
        //把新增的返回去
        return HttpResult.ok(arrangeVOS);
    }
    //排班模块：，领导根据物资状态选择排班策略，将根据组织内志愿者的物资现状进行推荐，更改表单状态为“被分配”
    //选择某种资源进行排班的推荐，策略会先按照这种资源的数量来进行【简单的贪心策略排班】
    @PostMapping("/markVolunteerTask")
    public HttpResult markVolunteerTask(@RequestBody VolunteerPO volunteerPO) {
        return HttpResult.ok();
    }

}
