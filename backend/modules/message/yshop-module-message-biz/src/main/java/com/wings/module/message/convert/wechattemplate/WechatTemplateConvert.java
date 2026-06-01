package com.wings.module.message.convert.wechattemplate;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.wings.module.message.controller.admin.wechattemplate.vo.*;
import com.wings.module.message.dal.dataobject.wechattemplate.WechatTemplateDO;

/**
 * 微信模板 Convert
 *
 * @author wings
 */
@Mapper
public interface WechatTemplateConvert {

    WechatTemplateConvert INSTANCE = Mappers.getMapper(WechatTemplateConvert.class);

    WechatTemplateDO convert(WechatTemplateCreateReqVO bean);

    WechatTemplateDO convert(WechatTemplateUpdateReqVO bean);

    WechatTemplateRespVO convert(WechatTemplateDO bean);

    List<WechatTemplateRespVO> convertList(List<WechatTemplateDO> list);

    PageResult<WechatTemplateRespVO> convertPage(PageResult<WechatTemplateDO> page);

    List<WechatTemplateExcelVO> convertList02(List<WechatTemplateDO> list);

}
