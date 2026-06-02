package com.ordering.module.score.convert.scoreproduct;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.module.score.controller.app.product.vo.AppScoreProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.module.score.controller.admin.scoreproduct.vo.*;
import com.ordering.module.score.dal.dataobject.scoreproduct.ScoreProductDO;

/**
 * 积分产品 Convert
 *
 * @author project team
 */
@Mapper
public interface ScoreProductConvert {

    ScoreProductConvert INSTANCE = Mappers.getMapper(ScoreProductConvert.class);

    ScoreProductDO convert(ScoreProductCreateReqVO bean);

    ScoreProductDO convert(ScoreProductUpdateReqVO bean);

    ScoreProductRespVO convert(ScoreProductDO bean);

    AppScoreProductVO convert01(ScoreProductDO bean);

    List<ScoreProductRespVO> convertList(List<ScoreProductDO> list);

    List<AppScoreProductVO> convertList01(List<ScoreProductDO> list);

    PageResult<ScoreProductRespVO> convertPage(PageResult<ScoreProductDO> page);

    List<ScoreProductExcelVO> convertList02(List<ScoreProductDO> list);

}
