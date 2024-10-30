package com.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.po.LoginLogPO;
import com.service.LoginLogService;
import com.mapper.LoginLogMapper;
import org.springframework.stereotype.Service;

/**
* @author hero
* @description 针对表【login_log】的数据库操作Service实现
* @createDate 2022-03-13 18:59:29
*/
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLogPO>
    implements LoginLogService{

}




