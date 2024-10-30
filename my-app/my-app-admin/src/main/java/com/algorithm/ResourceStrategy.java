package com.algorithm;/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.dto.VolunteerInfoDTO;
import com.po.ArrangePO;
import com.po.CallHelpPO;
import com.vo.ArrangeVO;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceStrategy extends Strategy {

    @Override
    List<ArrangeVO> arrange(ArrangePO arrangePO) {
        //需要用来排班的两个通用对象
        List<VolunteerInfoDTO> availableVolunteers = arrangeMapper.getAvailableVolunteers(arrangePO);
        List<CallHelpPO> allWaitingTask = callHelpMapper.getAllWaitingTask();
        //新添加的排班
        List<Long> cidList = new ArrayList<>();
        //要返回的dataList
        List<ArrangeVO> dataList = null;
        VolunteerInfoDTO volunteer;
        CallHelpPO callHelp;
        //代表当前志愿者已被分配一个任务，如果放在循环里面就会失效：!callHelp.getState().equals("已分配")不生效，
        // 因为每一次判断之前都重新get(i)那就刷新，这就证明了每次get(i)都是把值赋给一个新的堆对象并返回，而不是引用同一个堆对象
        boolean notArranged = true;
        for (int i = 0; i < availableVolunteers.size(); i++, notArranged = true) {
            //一个可以被安排志愿者的信息集合
            volunteer = availableVolunteers.get(i);
            for (int j = 0; j < allWaitingTask.size() && notArranged; j++) {
                //一条可以被安排的求助表单
                callHelp = allWaitingTask.get(j);

                //////////////////////////////////////////////////////Resource////////////////////////////////////////////////////////////////////
                //资源吻合
                boolean enoughFlag = true;
                int[] call_resource = callHelp.getResourceArr();
                int[] volunteer_resource = volunteer.getResourceArr();
                //判断资源是否吻合
                for (int k = 0; k < call_resource.length; k++) {
                    if (volunteer_resource[k] < call_resource[k]) {
                        enoughFlag = false;
                        break;
                    }
                }
                //如果资源吻合并且还没有被分配过，就可以继续遍历
                if (!callHelp.getState().equals("已分配")
                        && enoughFlag
                )
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                {
                    //
                    callHelp.setState("已分配");
                    callHelp.setVolunteerId(volunteer.getVolunteerId());
                    callHelpService.updateById(callHelp);
                    //退出内层循环，就是这个志愿者已经被安排过了,他不用继续匹配下一个求助表单
                    notArranged = false;
                    //记录一下修改过的
                    cidList.add(callHelp.getCid());
                }
            }
        }
        //调用一下接口，筛选一下新增加的
        if (cidList.size() > 0) {
            String preStr = cidList.toString();
            dataList = organizationMapper.findAddArrangeByStr(preStr.substring(1, preStr.length() - 1));
        }
        return dataList;
    }
}
