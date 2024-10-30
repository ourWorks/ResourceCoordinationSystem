package com.controller;

/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.http.HttpResult;
import com.mapper.VolunteerMapper;
import com.po.LeaderPO;
import com.po.OrganizationPO;
import com.service.LeaderService;
import com.service.OrganizationService;
import com.service.VolunteerService;
import com.vo.VolunteerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class OrgnationController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    LeaderService leaderService;

    @Autowired
    VolunteerService volunteerService;

    @Autowired
    VolunteerMapper volunteerMapper;


    //一、创建新组织：如果名称、地址、经纬度都一样，就证明是相同的，恢复旧的；如果不一样，就创建新的
    @PostMapping("/addOrg")
    public HttpResult addOrg(HttpServletRequest request,@RequestBody OrganizationPO organizationPO) {
        //曾经存在该组织，只是标记成删除了
        QueryWrapper<OrganizationPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("orgName", organizationPO.getOrgName());
        OrganizationPO old_organizationPO = organizationService.getOne(queryWrapper);
        if (old_organizationPO != null) {
            ////功能1：返回报错信息
            return HttpResult.error("该组织名已被占用，请输入新的组织名或联系管理员");
        } else {
            //一、插入新的组织
            organizationService.save(organizationPO);
            //二、获取组织包括组织号orgId在内的详细信息【组织的名称也是唯一的】
            OrganizationPO new_organizationPO = organizationService.getOne(queryWrapper);
            //三、在领导表中将创建者变成领导
            //发送请求头中获取市民id
            Long id = Long.parseLong(request.getHeader("id"));
            leaderService.save(LeaderPO.builder().id(id).orgId(new_organizationPO.getOrgId()).build());
            return HttpResult.ok(new_organizationPO);
        }
    }

    //二、获取组织信息
    @PostMapping("/orgInfo")
    public HttpResult orgInfo(@RequestBody OrganizationPO organizationPO) {
        //
        if (organizationPO.getOrgId() != null) {
            ////功能1：返回报错信息
            OrganizationPO oneOrg = organizationService.getById(organizationPO.getOrgId());
            return HttpResult.ok(oneOrg);
        } else {
            return HttpResult.error("找不到这个组织");
        }
    }

    //三、修改组织信息
    @PostMapping("/updateOrg")
    public HttpResult updateOrg(@RequestBody OrganizationPO organizationPO) {
        //
        if (organizationPO.getOrgId() != null) {
            ////功能1：返回报错信息
            organizationService.updateById(organizationPO);
            return HttpResult.ok(organizationPO);
        } else {
            return HttpResult.error("发生错误");
        }
    }

    //二、删除组织（要和退出组织区分）
    @PostMapping("/delOrg")
    public HttpResult delOrg(@RequestBody OrganizationPO organizationPO) {
        //标记删除状态
        organizationPO.setDeleteState(1);
        organizationService.updateById(organizationPO);
        //返回操作结果
        return HttpResult.ok("删除成功");
    }

}
