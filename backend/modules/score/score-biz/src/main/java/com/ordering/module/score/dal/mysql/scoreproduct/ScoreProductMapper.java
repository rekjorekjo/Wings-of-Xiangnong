package com.ordering.module.score.dal.mysql.scoreproduct;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ordering.framework.mybatis.core.mapper.BaseMapperX;
import com.ordering.module.score.dal.dataobject.scoreproduct.ScoreProductDO;
import org.apache.ibatis.annotations.Mapper;
import com.ordering.module.score.controller.admin.scoreproduct.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 积分产品 Mapper
 *
 * @author project team
 */
@Mapper
public interface ScoreProductMapper extends BaseMapperX<ScoreProductDO> {

    default PageResult<ScoreProductDO> selectPage(ScoreProductPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ScoreProductDO>()
                .eqIfPresent(ScoreProductDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ScoreProductDO::getImage, reqVO.getImage())
                .eqIfPresent(ScoreProductDO::getImages, reqVO.getImages())
                .eqIfPresent(ScoreProductDO::getDesc, reqVO.getDesc())
                .eqIfPresent(ScoreProductDO::getScore, reqVO.getScore())
                .eqIfPresent(ScoreProductDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ScoreProductDO::getStock, reqVO.getStock())
                .eqIfPresent(ScoreProductDO::getSales, reqVO.getSales())
                .betweenIfPresent(ScoreProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ScoreProductDO::getId));
    }

    default List<ScoreProductDO> selectList(ScoreProductExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ScoreProductDO>()
                .eqIfPresent(ScoreProductDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ScoreProductDO::getImage, reqVO.getImage())
                .eqIfPresent(ScoreProductDO::getImages, reqVO.getImages())
                .eqIfPresent(ScoreProductDO::getDesc, reqVO.getDesc())
                .eqIfPresent(ScoreProductDO::getScore, reqVO.getScore())
                .eqIfPresent(ScoreProductDO::getWeigh, reqVO.getWeigh())
                .eqIfPresent(ScoreProductDO::getStock, reqVO.getStock())
                .eqIfPresent(ScoreProductDO::getSales, reqVO.getSales())
                .betweenIfPresent(ScoreProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ScoreProductDO::getId));
    }


    /**
     * 普通商品 减库存 加销量
     * @param num
     * @param productId
     * @return
     */
    @Update("update app_score_product set stock=stock-#{num}, sales=sales+#{num}" +
            " where id=#{productId} and stock >= #{num}")
    int decStockIncSales(@Param("num") Integer num, @Param("productId") Long productId);

}
