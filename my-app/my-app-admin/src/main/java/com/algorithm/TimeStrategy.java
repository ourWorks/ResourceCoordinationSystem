package com.algorithm;

/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.dto.VolunteerInfoDTO;
import com.po.ArrangePO;
import com.po.CallHelpPO;
import com.vo.ArrangeVO;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//如果这里不加Service,那么下面的arrangeMapper.getAvailableVolunteers(organizationPO);
//arrangeMapper就是NonPointerException
@Component
public class TimeStrategy extends Strategy {

    @Override
    List<ArrangeVO> arrange(ArrangePO arrangePO) {
        //新添加的排班cidList
        List<Long> cidList = new ArrayList<>();
        //要返回的dataList
        List<ArrangeVO> dataList = null;
        List<VolunteerInfoDTO> availableVolunteers = arrangeMapper.getAvailableVolunteers(arrangePO);
        List<CallHelpPO> allWaitingTask = callHelpMapper.getAllWaitingTask();
        VolunteerInfoDTO volunteer;
        CallHelpPO callHelp;
        //代表当前志愿者已被分配一个任务，如果放在循环里面就会失效：!callHelp.getState().equals("已分配")不生效，
        boolean notArranged = true;
        for (int i = 0; i < availableVolunteers.size(); i++, notArranged = true) {
            //一个可以被安排志愿者的信息集合
            volunteer = availableVolunteers.get(i);
            for (int j = 0; j < allWaitingTask.size() && notArranged; j++) {
                //一条可以被安排的求助表单
                callHelp = allWaitingTask.get(j);

                //////////////////////////////////////////////////////TIme////////////////////////////////////////////////////////////////////
                //不比较日期，只比较每一天的时间段
                //A.compare(B),A的时间比B的时间早就返回0，否则返回1,==>需要结果为真，就把大的放在前面：如果startTime和中间的都是00:00就会有bug,现在多加一个或判断
                LocalTime small = volunteer.getStartTime().toLocalTime();
                LocalTime middle = callHelp.getDeadline().toLocalTime();
                LocalTime big = volunteer.getEndTime().toLocalTime();
                if (!callHelp.getState().equals("已分配")
                        && (middle.compareTo(small) == 1 || middle.equals(small))
                        && big.compareTo(middle) == 1
                )
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                {
                    //开始安排
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
