package com.algorithm;

/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.mapper.ArrangeMapper;
import com.mapper.CallHelpMapper;
import com.mapper.OrganizationMapper;
import com.po.ArrangePO;
import com.service.CallHelpService;
import com.vo.ArrangeVO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public abstract class Strategy {
    //为了获取求助表单而引入的注入
    @Autowired
    ArrangeMapper arrangeMapper;
    @Autowired
    CallHelpMapper callHelpMapper;
    //为了更新和记录而引入的注入
    @Autowired
    CallHelpService callHelpService;
    @Autowired
    OrganizationMapper organizationMapper;
    //为了更新和记录而引入的注入
    abstract List<ArrangeVO> arrange(ArrangePO arrangePO);
}
