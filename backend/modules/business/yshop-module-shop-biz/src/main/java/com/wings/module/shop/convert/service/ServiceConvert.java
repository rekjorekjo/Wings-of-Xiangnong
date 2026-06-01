package com.wings.module.shop.convert.service;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;

import com.wings.module.shop.controller.app.service.vo.AppServiceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.wings.module.shop.controller.admin.service.vo.*;
import com.wings.module.shop.dal.dataobject.service.ServiceDO;

/**
 * 我的服务 Convert
 *
 * @author wings
 */
@Mapper
public interface ServiceConvert {

    ServiceConvert INSTANCE = Mappers.getMapper(ServiceConvert.class);

    ServiceDO convert(ServiceCreateReqVO bean);

    ServiceDO convert(ServiceUpdateReqVO bean);

    ServiceRespVO convert(ServiceDO bean);

    List<ServiceRespVO> convertList(List<ServiceDO> list);

    List<AppServiceVO> convertList03(List<ServiceDO> list);

    AppServiceVO convert03(ServiceDO bean);

    PageResult<ServiceRespVO> convertPage(PageResult<ServiceDO> page);

    List<ServiceExcelVO> convertList02(List<ServiceDO> list);

}
