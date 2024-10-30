package com.vo;

/*
 *@program:my-app
 *@author: 苏晓龙
 */

import com.po.CitizenPO;
import com.po.VolunteerPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VolunteerVOMapper {

    VolunteerVOMapper INSTANCE = Mappers.getMapper(VolunteerVOMapper.class);
        @Mappings({
            @Mapping(source = "volunteerPO.id", target = "id")
    })
    VolunteerVO po2Vo(VolunteerPO volunteerPO, CitizenPO citizenPO);
}
