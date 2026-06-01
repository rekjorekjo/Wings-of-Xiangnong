package com.wings.module.product.service.storeproductreply;

import cn.hutool.core.util.StrUtil;
import com.wings.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.wings.module.product.controller.app.product.vo.AppStoreProductReplyQueryVo;
import com.wings.module.product.dal.dataobject.storeproduct.StoreProductDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.wings.module.product.controller.admin.storeproductreply.vo.*;
import com.wings.module.product.dal.dataobject.storeproductreply.StoreProductReplyDO;
import com.wings.framework.common.pojo.PageResult;

import com.wings.module.product.convert.storeproductreply.StoreProductReplyConvert;
import com.wings.module.product.dal.mysql.storeproductreply.StoreProductReplyMapper;

import static com.wings.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.wings.module.product.enums.ErrorCodeConstants.*;

/**
 * 评论 Service 实现类
 *
 * @author wings
 */
@Service
@Validated
public class StoreProductReplyServiceImpl implements StoreProductReplyService {

    @Resource
    private StoreProductReplyMapper storeProductReplyMapper;


    @Override
    public void updateStoreProductReply(StoreProductReplyUpdateReqVO updateReqVO) {
        // 校验存在
        validateStoreProductReplyExists(updateReqVO.getId());
        // 更新
        StoreProductReplyDO updateObj = StoreProductReplyConvert.INSTANCE.convert(updateReqVO);
        storeProductReplyMapper.updateById(updateObj);
    }

    @Override
    public void deleteStoreProductReply(Long id) {
        // 校验存在
        validateStoreProductReplyExists(id);
        // 删除
        storeProductReplyMapper.deleteById(id);
    }

    private void validateStoreProductReplyExists(Long id) {
        if (storeProductReplyMapper.selectById(id) == null) {
            throw exception(STORE_PRODUCT_REPLY_NOT_EXISTS);
        }
    }

    @Override
    public StoreProductReplyDO getStoreProductReply(Long id) {
        return storeProductReplyMapper.selectById(id);
    }

    @Override
    public PageResult<AppStoreProductReplyQueryVo> getStoreProductReplyPage(StoreProductReplyPageReqVO pageReqVO) {
                Page<StoreProductReplyDO> pageModel = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        List<AppStoreProductReplyQueryVo> list = storeProductReplyMapper
                .allReplyList(pageModel,pageReqVO.getNickname());
        return new PageResult<>(list, storeProductReplyMapper.allReplyListCount(pageReqVO.getNickname()));
    }


}
