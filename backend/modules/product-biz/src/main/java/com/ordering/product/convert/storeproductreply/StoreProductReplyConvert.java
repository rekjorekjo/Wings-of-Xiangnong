package com.ordering.product.convert.storeproductreply;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.product.controller.admin.storeproductreply.vo.*;
import com.ordering.product.dal.dataobject.storeproductreply.StoreProductReplyDO;

/**
 * 评论 Convert
 *
 * @author project team
 */
@Mapper
public interface StoreProductReplyConvert {

    StoreProductReplyConvert INSTANCE = Mappers.getMapper(StoreProductReplyConvert.class);


    StoreProductReplyDO convert(StoreProductReplyUpdateReqVO bean);

    StoreProductReplyRespVO convert(StoreProductReplyDO bean);

    List<StoreProductReplyRespVO> convertList(List<StoreProductReplyDO> list);

    PageResult<StoreProductReplyRespVO> convertPage(PageResult<StoreProductReplyDO> page);


}
