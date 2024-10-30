package com.vo;

import com.po.CitizenPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//这个Mapper千万不能导错成org.apache.ibatis.annotations.Mapper

@Mapper
public interface CitizenVOMapper {

    CitizenVOMapper INSTANCE = Mappers.getMapper(CitizenVOMapper.class);
//    @Mappings({
//            @Mapping(source = "name", target = "testStr")
//    })
    CitizenVO po2Vo(CitizenPO citizenPO);
    CitizenPO vo2Po(CitizenVO citizenVO);
}
