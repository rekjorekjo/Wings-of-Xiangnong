package com.ordering.shop.convert.material;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.shop.controller.admin.material.vo.*;
import com.ordering.shop.dal.dataobject.material.MaterialDO;

/**
 * 素材库 Convert
 *
 * @author project team
 */
@Mapper
public interface MaterialConvert {

    MaterialConvert INSTANCE = Mappers.getMapper(MaterialConvert.class);

    MaterialDO convert(MaterialCreateReqVO bean);

    MaterialDO convert(MaterialUpdateReqVO bean);

    MaterialRespVO convert(MaterialDO bean);

    List<MaterialRespVO> convertList(List<MaterialDO> list);

    PageResult<MaterialRespVO> convertPage(PageResult<MaterialDO> page);

    List<MaterialExcelVO> convertList02(List<MaterialDO> list);

}
