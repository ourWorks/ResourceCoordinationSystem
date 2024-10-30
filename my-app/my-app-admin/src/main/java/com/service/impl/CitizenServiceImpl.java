package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.po.CitizenPO;
import com.service.CitizenService;
import com.mapper.CitizenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author hero
* @description 针对表【citizen】的数据库操作Service实现
* @createDate 2022-03-13 18:59:29
*/
@Service
public class CitizenServiceImpl extends ServiceImpl<CitizenMapper, CitizenPO>
    implements CitizenService{

    @Autowired
    CitizenMapper citizenMapper;

    /*
     * 查看是否存在对应身份证号、电话号码或邮箱，如果有就说明注册过:将业务写在service层
     * */
    @Override
    public CitizenPO registered(CitizenPO citizenPO) {
        return citizenMapper.selectOneByPhoneOrEmailOrIdCard(citizenPO);
    }

    @Override
    public CitizenPO verify(CitizenPO citizenPO) {
        citizenPO.setEmail(citizenPO.getPhone());
        citizenPO.setIdCard(citizenPO.getPhone());
        //如果手机 或 邮箱 或 身份证号任意一个存在，在此前提下密码正确，那么就返回对象，否则返回null
        return citizenMapper.verify(citizenPO);
    }

    @Override
    public int getCitizenNum() {
        return citizenMapper.getCitizenNum();
    }


}




