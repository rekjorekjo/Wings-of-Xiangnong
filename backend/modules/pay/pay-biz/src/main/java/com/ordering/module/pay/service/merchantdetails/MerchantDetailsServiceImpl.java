package com.ordering.module.pay.service.merchantdetails;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ordering.module.pay.controller.admin.merchantdetails.vo.*;
import com.ordering.module.pay.dal.dataobject.merchantdetails.MerchantDetailsDO;
import com.ordering.framework.common.pojo.PageResult;

import com.ordering.module.pay.convert.merchantdetails.MerchantDetailsConvert;
import com.ordering.module.pay.dal.mysql.merchantdetails.MerchantDetailsMapper;

import static com.ordering.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ordering.module.pay.enums.ErrorCodeConstants.*;

/**
 * 支付服务商配置 Service 实现类
 *
 * @author project team
 */
@Service
@Validated
public class MerchantDetailsServiceImpl implements MerchantDetailsService {

    @Resource
    private MerchantDetailsMapper merchantDetailsMapper;

    @Override
    public String createMerchantDetails(MerchantDetailsCreateReqVO createReqVO) {
        // 插入
        MerchantDetailsDO merchantDetails = MerchantDetailsConvert.INSTANCE.convert(createReqVO);
        merchantDetailsMapper.insert(merchantDetails);
        // 返回
        return merchantDetails.getDetailsId();
    }

    @Override
    public void updateMerchantDetails(MerchantDetailsUpdateReqVO updateReqVO) {
        // 校验存在
        validateMerchantDetailsExists(updateReqVO.getDetailsId());
        // 更新
        MerchantDetailsDO updateObj = MerchantDetailsConvert.INSTANCE.convert(updateReqVO);
        merchantDetailsMapper.updateById(updateObj);
    }

    @Override
    public void deleteMerchantDetails(String id) {
        // 校验存在
        validateMerchantDetailsExists(id);
        // 删除
        merchantDetailsMapper.deleteById(id);
    }

    private void validateMerchantDetailsExists(String id) {
        if (merchantDetailsMapper.selectById(id) == null) {
            throw exception(MERCHANT_DETAILS_NOT_EXISTS);
        }
    }

    @Override
    public MerchantDetailsDO getMerchantDetails(String id) {
        return merchantDetailsMapper.selectById(id);
    }

    @Override
    public List<MerchantDetailsDO> getMerchantDetailsList(Collection<String> ids) {
        return merchantDetailsMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MerchantDetailsDO> getMerchantDetailsPage(MerchantDetailsPageReqVO pageReqVO) {
        return merchantDetailsMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MerchantDetailsDO> getMerchantDetailsList(MerchantDetailsExportReqVO exportReqVO) {
        return merchantDetailsMapper.selectList(exportReqVO);
    }

}
