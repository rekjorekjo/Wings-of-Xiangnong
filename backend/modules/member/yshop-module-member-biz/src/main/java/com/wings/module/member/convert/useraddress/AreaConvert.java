package com.wings.module.member.convert.useraddress;

import com.wings.framework.ip.core.Area;
import com.wings.module.member.controller.app.address.vo.AreaNodeRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AreaConvert {

    AreaConvert INSTANCE = Mappers.getMapper(AreaConvert.class);

    List<AreaNodeRespVO> convertList(List<Area> list);

}
