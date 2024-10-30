package com.service;

import com.po.CitizenPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hero
* @description 针对表【citizen】的数据库操作Service
* @createDate 2022-03-13 18:59:29
*/
public interface CitizenService extends IService<CitizenPO> {

    CitizenPO registered(CitizenPO citizenPO);
    CitizenPO verify(CitizenPO citizenPO);
    int getCitizenNum();
}
