package com.dto;

/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.po.CitizenPO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//这个Mapper千万不能导错成org.apache.ibatis.annotations.Mapper

@Mapper
public interface CitizenDTOMapper {
    CitizenDTOMapper INSTANCE = Mappers.getMapper(CitizenDTOMapper.class);
    //    @Mappings({
//            @Mapping(source = "name", target = "testStr")
//    })
    CitizenDTO po2dto(CitizenPO citizenPO);
    CitizenPO dto2po(CitizenDTO citizenDTO);
}
