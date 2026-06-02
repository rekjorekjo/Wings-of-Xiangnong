package com.ordering.module.member.convert.useraddress;

import com.ordering.framework.ip.core.Area;
import com.ordering.module.member.controller.app.address.vo.AreaNodeRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AreaConvert {

    AreaConvert INSTANCE = Mappers.getMapper(AreaConvert.class);

    List<AreaNodeRespVO> convertList(List<Area> list);

}
