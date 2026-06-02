package com.ordering.module.shop.convert.materialgroup;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.module.shop.controller.admin.materialgroup.vo.*;
import com.ordering.module.shop.dal.dataobject.materialgroup.MaterialGroupDO;

/**
 * 素材分组 Convert
 *
 * @author project team
 */
@Mapper
public interface MaterialGroupConvert {

    MaterialGroupConvert INSTANCE = Mappers.getMapper(MaterialGroupConvert.class);

    MaterialGroupDO convert(MaterialGroupCreateReqVO bean);

    MaterialGroupDO convert(MaterialGroupUpdateReqVO bean);

    MaterialGroupRespVO convert(MaterialGroupDO bean);

    List<MaterialGroupRespVO> convertList(List<MaterialGroupDO> list);

    PageResult<MaterialGroupRespVO> convertPage(PageResult<MaterialGroupDO> page);

    List<MaterialGroupExcelVO> convertList02(List<MaterialGroupDO> list);

}
