package com.vo;

import com.po.CallHelpPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//这个Mapper千万不能导错成org.apache.ibatis.annotations.Mapper

@Mapper
public interface CallHelpVOMapper {

    CallHelpVOMapper INSTANCE = Mappers.getMapper(CallHelpVOMapper.class);
//    @Mappings({
//            @Mapping(source = "name", target = "testStr")
//    })
    CallHelpVO po2Vo(CallHelpPO callHelpPO);
    CallHelpPO vo2Po(CallHelpVO callHelpVO);
}
