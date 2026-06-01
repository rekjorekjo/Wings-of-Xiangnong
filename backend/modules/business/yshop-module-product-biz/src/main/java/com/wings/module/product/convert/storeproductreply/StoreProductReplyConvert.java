package com.wings.module.product.convert.storeproductreply;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.wings.module.product.controller.admin.storeproductreply.vo.*;
import com.wings.module.product.dal.dataobject.storeproductreply.StoreProductReplyDO;

/**
 * 评论 Convert
 *
 * @author wings
 */
@Mapper
public interface StoreProductReplyConvert {

    StoreProductReplyConvert INSTANCE = Mappers.getMapper(StoreProductReplyConvert.class);


    StoreProductReplyDO convert(StoreProductReplyUpdateReqVO bean);

    StoreProductReplyRespVO convert(StoreProductReplyDO bean);

    List<StoreProductReplyRespVO> convertList(List<StoreProductReplyDO> list);

    PageResult<StoreProductReplyRespVO> convertPage(PageResult<StoreProductReplyDO> page);


}
