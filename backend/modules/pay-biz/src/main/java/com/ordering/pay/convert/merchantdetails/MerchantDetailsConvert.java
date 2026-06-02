package com.ordering.pay.convert.merchantdetails;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.pay.controller.admin.merchantdetails.vo.*;
import com.ordering.pay.dal.dataobject.merchantdetails.MerchantDetailsDO;

/**
 * 支付服务商配置 Convert
 *
 * @author project team
 */
@Mapper
public interface MerchantDetailsConvert {

    MerchantDetailsConvert INSTANCE = Mappers.getMapper(MerchantDetailsConvert.class);

    MerchantDetailsDO convert(MerchantDetailsCreateReqVO bean);

    MerchantDetailsDO convert(MerchantDetailsUpdateReqVO bean);

    MerchantDetailsRespVO convert(MerchantDetailsDO bean);

    List<MerchantDetailsRespVO> convertList(List<MerchantDetailsDO> list);

    PageResult<MerchantDetailsRespVO> convertPage(PageResult<MerchantDetailsDO> page);

    List<MerchantDetailsExcelVO> convertList02(List<MerchantDetailsDO> list);

}
