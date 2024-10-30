package com.controller;


/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.common.http.HttpResult;
import com.po.CallHelpPO;
import com.po.OrganizationPO;
import com.po.VolunteerPO;
import com.service.*;
import com.vo.OrgResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShowController {

    @Autowired
    ArrangeService arrangeService;
    @Autowired
    CitizenService citizenService;
    @Autowired
    VolunteerService volunteerService;
    @Autowired
    LeaderService leaderService;

    @Autowired
    CallHelpService callHelpService;

    //查看所有"待救助"资源需求
    @PostMapping("/getMembersNum")
    public HttpResult getMembersNum() {
        //构造的HashMap是将请求人的id放进去，并根据id来查询
        int citizenNum = citizenService.getCitizenNum();
        int volunteerNum = volunteerService.getVolunteerNum();
        int leaderNum = leaderService.getLeaderNum();

        Map map = new HashMap();
        map.put("citizenNum", citizenNum);
        map.put("volunteerNum", volunteerNum);
        map.put("leaderNum", leaderNum);

        int JiuYuanZhong = callHelpService.getJiuYuanZhong();
        int YiFenPei = callHelpService.getYiFenPei();
        int YiWanCheng = callHelpService.getYiWanCheng();
        int DaiJiuYuan = callHelpService.getDaiJiuYuan();
        map.put("JiuYuanZhong", JiuYuanZhong);
        map.put("YiFenPei", YiFenPei);
        map.put("YiWanCheng", YiWanCheng);
        map.put("DaiJiuYuan", DaiJiuYuan);
        //进行排计算
        return HttpResult.ok(map);
    }

    //查看所有"待救助"资源需求
    @PostMapping("/getTotalDemandNum")
    public HttpResult getTotalDemandNum() {
        //构造的HashMap是将请求人的id放进去，并根据id来查询
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("deleteState", 0);
        paramMap.put("state", "待救援");
        List<CallHelpPO> callHelpPOList = callHelpService.listByMap(paramMap);
        //初始化
        String[] strArr = {"口罩", "绷带", "担架", "降压药", "抗过敏药", "饮用水", "充饥食品", "帐篷", "车位", "船位"};
        Map<String, Object> resultMap = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            resultMap.put(strArr[i], 0);
        }
        //进行统计
        for (int i = 0; i < callHelpPOList.size(); i++) {
            String DRStr = callHelpPOList.get(i).getDailyResource();
            String MRStr = callHelpPOList.get(i).getMedicalResource();
            int peopleNum = callHelpPOList.get(i).getPeopleNum();
            //如果不是空就拆分字符串并进行统计
            if (!DRStr.equals("---")) {
                String[] dailyArr = DRStr.split(",");
                for (int j = 0; j < dailyArr.length; j++) {
                    resultMap.put(dailyArr[j], (int) resultMap.get(dailyArr[j]) + peopleNum);
                }
            }
            if (!MRStr.equals("---")) {
                String[] medicalArr = MRStr.split(",");
                for (int j = 0; j < medicalArr.length; j++) {
                    resultMap.put(medicalArr[j], (int) resultMap.get(medicalArr[j]) + peopleNum);
                }
            }
        }
        int[] finalResult = new int[10];
        for (int i = 0; i < finalResult.length; i++) {
            finalResult[i] = (int) resultMap.get(strArr[i]);
        }
        //进行排计算
        return HttpResult.ok(finalResult);
    }

    //查看所有"本组织的"资源供应
    @PostMapping("/getOrgResourceNum")
    public HttpResult getOrgResourceNum(@RequestBody OrganizationPO organizationPO) {
        //测试一下，手动赋值
        List<OrgResourceVO> sourceList = arrangeService.showOrgResource(organizationPO);
        //初始化
        String[] strArr = {"口罩", "绷带", "担架", "降压药", "抗过敏药", "饮用水", "充饥食品", "帐篷", "车位", "船位"};
        Map<String, Object> resultMap = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            resultMap.put(strArr[i], 0);
        }
        //进行统计
        for (int i = 0; i < sourceList.size(); i++) {
            resultMap.put("口罩", (int) resultMap.get("口罩") + (int) sourceList.get(i).getMask());
            resultMap.put("绷带", (int) resultMap.get("绷带") + (int) sourceList.get(i).getBandage());
            resultMap.put("担架", (int) resultMap.get("担架") + (int) sourceList.get(i).getStretcher());
            resultMap.put("降压药", (int) resultMap.get("降压药") + (int) sourceList.get(i).getDepressor());
            resultMap.put("抗过敏药", (int) resultMap.get("抗过敏药") + (int) sourceList.get(i).getAntihistamine());
            ////
            resultMap.put("饮用水", (int) resultMap.get("饮用水") + (int) sourceList.get(i).getWater());
            resultMap.put("充饥食品", (int) resultMap.get("充饥食品") + (int) sourceList.get(i).getFood());
            resultMap.put("帐篷", (int) resultMap.get("帐篷") + (int) sourceList.get(i).getTent());
            resultMap.put("车位", (int) resultMap.get("车位") + (int) sourceList.get(i).getCar());
            resultMap.put("船位", (int) resultMap.get("船位") + (int) sourceList.get(i).getBoat());
        }
        int[] finalResult = new int[10];
        for (int i = 0; i < finalResult.length; i++) {
            finalResult[i] = (int) resultMap.get(strArr[i]);
        }
        //进行排计算
        return HttpResult.ok(finalResult);
    }


}
