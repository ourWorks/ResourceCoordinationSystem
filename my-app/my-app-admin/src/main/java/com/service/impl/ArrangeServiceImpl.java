package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dto.VolunteerInfoDTO;
import com.mapper.CallHelpMapper;
import com.mapper.OrganizationMapper;
import com.mapper.VolunteerMapper;
import com.po.ArrangePO;
import com.po.CallHelpPO;
import com.po.OrganizationPO;
import com.po.VolunteerPO;
import com.service.ArrangeService;
import com.mapper.ArrangeMapper;
import com.service.CallHelpService;
import com.service.VolunteerService;
import com.vo.ArrangeVO;
import com.vo.OrgResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hero
 * @description 针对表【arrange】的数据库操作Service实现
 * @createDate 2022-03-13 18:59:29
 */
@Service
public class ArrangeServiceImpl extends ServiceImpl<ArrangeMapper, ArrangePO>
        implements ArrangeService {

    @Autowired
    ArrangeMapper arrangeMapper;

    @Autowired
    CallHelpMapper callHelpMapper;

    @Autowired
    OrganizationMapper organizationMapper;

    @Autowired
    CallHelpService callHelpService;

    @Override
    public List<OrgResourceVO> showOrgResource(OrganizationPO organizationPO) {
        return arrangeMapper.showOrgResource(organizationPO);
    }

    @Override
    public List<VolunteerInfoDTO> getAvailableVolunteers(ArrangePO arrangePO) {
        List<VolunteerInfoDTO> availableVolunteers = arrangeMapper.getAvailableVolunteers(arrangePO);
        return availableVolunteers;
    }

//    //根据时间来进行安排
//    @Override
//    public List<ArrangeVO> arrangeByTime(OrganizationPO organizationPO) {
//
//        //返回一下新添加的
//        List<Long> cidList = new ArrayList<>();
//        //要返回的dataList
//        List<ArrangeVO> dataList=null;
//
//        List<VolunteerInfoDTO> availableVolunteers = arrangeMapper.getAvailableVolunteers(organizationPO);
//        List<CallHelpPO> allWaitingTask = callHelpMapper.getAllWaitingTask();
//        VolunteerInfoDTO volunteer;
//        CallHelpPO callHelp;
//        //代表当前志愿者已被分配一个任务，如果放在循环里面就会失效：!callHelp.getState().equals("已分配")不生效，
//        // 因为每一次判断之前都重新get(i)那就刷新，这就证明了每次get(i)都是把值赋给一个新的堆对象并返回，而不是引用同一个堆对象
//        boolean notArranged = true;
//        for (int i = 0; i < availableVolunteers.size(); i++, notArranged = true) {
//            //一个可以被安排志愿者的信息集合
//            volunteer = availableVolunteers.get(i);
//            for (int j = 0; j < allWaitingTask.size() && notArranged; j++) {
//                //一条可以被安排的求助表单
//                callHelp = allWaitingTask.get(j);
//                //不比较日期，只比较每一天的时间段
//                //A.compare(B),A的时间比B的时间早就返回0，否则返回1,==>需要结果为真，就把大的放在前面：如果startTime和中间的都是00:00就会有bug,现在多加一个或判断
//                LocalTime small = volunteer.getStartTime().toLocalTime();
//                LocalTime middle = callHelp.getDeadline().toLocalTime();
//                LocalTime big = volunteer.getEndTime().toLocalTime();
//                if (!callHelp.getState().equals("已分配")
//                        && (middle.compareTo(small) == 1 || middle.equals(small))
//                        && big.compareTo(middle) == 1
//                ) {
////                    System.out.println("do something");
////                    System.out.println("==================");
////                    System.out.println("开始时间为"+volunteer.getStartTime());
////                    System.out.println("call_help截止时间为"+callHelp.getDeadline());
////                    System.out.println("结束时间为"+volunteer.getEndTime());
////                    System.out.println("==================");
//                    //开始安排
//                    callHelp.setState("已分配");
//                    callHelp.setVolunteerId(volunteer.getVolunteerId());
//                    callHelpService.updateById(callHelp);
//                    //退出内层循环，就是这个志愿者已经被安排过了,他不用继续匹配下一个求助表单
//                    notArranged = false;
//                    //记录一下修改过的
//                    cidList.add(callHelp.getCid());
//                }
//            }
//        }
//
//        //调用一下接口，筛选一下新增加的
////        List<ArrangeVO> dataList = organizationMapper.findAddArrangeList(cidList);  //这个本身就有奇怪的错误
//        if (cidList.size() > 0) {
//            String preStr = cidList.toString();
//            dataList = organizationMapper.findAddArrangeByStr(preStr.substring(1, preStr.length() - 1));
//        }
//        return dataList;
//    }

}




