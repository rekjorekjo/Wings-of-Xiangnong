package com.wings.module.message.dal.mysql.wechattemplate;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;
import com.wings.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.module.message.dal.dataobject.wechattemplate.WechatTemplateDO;
import org.apache.ibatis.annotations.Mapper;
import com.wings.module.message.controller.admin.wechattemplate.vo.*;

/**
 * 微信模板 Mapper
 *
 * @author wings
 */
@Mapper
public interface WechatTemplateMapper extends BaseMapperX<WechatTemplateDO> {

    default PageResult<WechatTemplateDO> selectPage(WechatTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WechatTemplateDO>()
                .eqIfPresent(WechatTemplateDO::getTempkey, reqVO.getTempkey())
                .likeIfPresent(WechatTemplateDO::getName, reqVO.getName())
                .eqIfPresent(WechatTemplateDO::getContent, reqVO.getContent())
                .eqIfPresent(WechatTemplateDO::getTempid, reqVO.getTempid())
                .betweenIfPresent(WechatTemplateDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WechatTemplateDO::getStatus, reqVO.getStatus())
                .eqIfPresent(WechatTemplateDO::getType, reqVO.getType())
                .orderByDesc(WechatTemplateDO::getId));
    }

    default List<WechatTemplateDO> selectList(WechatTemplateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WechatTemplateDO>()
                .eqIfPresent(WechatTemplateDO::getTempkey, reqVO.getTempkey())
                .likeIfPresent(WechatTemplateDO::getName, reqVO.getName())
                .eqIfPresent(WechatTemplateDO::getContent, reqVO.getContent())
                .eqIfPresent(WechatTemplateDO::getTempid, reqVO.getTempid())
                .betweenIfPresent(WechatTemplateDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WechatTemplateDO::getStatus, reqVO.getStatus())
                .eqIfPresent(WechatTemplateDO::getType, reqVO.getType())
                .orderByDesc(WechatTemplateDO::getId));
    }

}
