package com.wings.module.pay.dal.mysql.merchantdetails;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;
import com.wings.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.module.pay.dal.dataobject.merchantdetails.MerchantDetailsDO;
import org.apache.ibatis.annotations.Mapper;
import com.wings.module.pay.controller.admin.merchantdetails.vo.*;

/**
 * 支付服务商配置 Mapper
 *
 * @author wings
 */
@Mapper
public interface MerchantDetailsMapper extends BaseMapperX<MerchantDetailsDO> {

    default PageResult<MerchantDetailsDO> selectPage(MerchantDetailsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MerchantDetailsDO>()
                .eqIfPresent(MerchantDetailsDO::getPayType, reqVO.getPayType())
                .eqIfPresent(MerchantDetailsDO::getAppid, reqVO.getAppid())
                .eqIfPresent(MerchantDetailsDO::getMchId, reqVO.getMchId())
                .eqIfPresent(MerchantDetailsDO::getCertStoreType, reqVO.getCertStoreType())
                .eqIfPresent(MerchantDetailsDO::getKeyPrivate, reqVO.getKeyPrivate())
                .eqIfPresent(MerchantDetailsDO::getKeyPublic, reqVO.getKeyPublic())
                .eqIfPresent(MerchantDetailsDO::getKeyCert, reqVO.getKeyCert())
                .eqIfPresent(MerchantDetailsDO::getKeyCertPwd, reqVO.getKeyCertPwd())
                .eqIfPresent(MerchantDetailsDO::getNotifyUrl, reqVO.getNotifyUrl())
                .eqIfPresent(MerchantDetailsDO::getReturnUrl, reqVO.getReturnUrl())
                .eqIfPresent(MerchantDetailsDO::getSignType, reqVO.getSignType())
                .eqIfPresent(MerchantDetailsDO::getSeller, reqVO.getSeller())
                .eqIfPresent(MerchantDetailsDO::getSubAppId, reqVO.getSubAppId())
                .eqIfPresent(MerchantDetailsDO::getSubMchId, reqVO.getSubMchId())
                .eqIfPresent(MerchantDetailsDO::getInputCharset, reqVO.getInputCharset())
                .eqIfPresent(MerchantDetailsDO::getIsTest, reqVO.getIsTest())
                .orderByDesc(MerchantDetailsDO::getDetailsId));
    }

    default List<MerchantDetailsDO> selectList(MerchantDetailsExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MerchantDetailsDO>()
                .eqIfPresent(MerchantDetailsDO::getPayType, reqVO.getPayType())
                .eqIfPresent(MerchantDetailsDO::getAppid, reqVO.getAppid())
                .eqIfPresent(MerchantDetailsDO::getMchId, reqVO.getMchId())
                .eqIfPresent(MerchantDetailsDO::getCertStoreType, reqVO.getCertStoreType())
                .eqIfPresent(MerchantDetailsDO::getKeyPrivate, reqVO.getKeyPrivate())
                .eqIfPresent(MerchantDetailsDO::getKeyPublic, reqVO.getKeyPublic())
                .eqIfPresent(MerchantDetailsDO::getKeyCert, reqVO.getKeyCert())
                .eqIfPresent(MerchantDetailsDO::getKeyCertPwd, reqVO.getKeyCertPwd())
                .eqIfPresent(MerchantDetailsDO::getNotifyUrl, reqVO.getNotifyUrl())
                .eqIfPresent(MerchantDetailsDO::getReturnUrl, reqVO.getReturnUrl())
                .eqIfPresent(MerchantDetailsDO::getSignType, reqVO.getSignType())
                .eqIfPresent(MerchantDetailsDO::getSeller, reqVO.getSeller())
                .eqIfPresent(MerchantDetailsDO::getSubAppId, reqVO.getSubAppId())
                .eqIfPresent(MerchantDetailsDO::getSubMchId, reqVO.getSubMchId())
                .eqIfPresent(MerchantDetailsDO::getInputCharset, reqVO.getInputCharset())
                .eqIfPresent(MerchantDetailsDO::getIsTest, reqVO.getIsTest())
                .orderByDesc(MerchantDetailsDO::getDetailsId));
    }

}
