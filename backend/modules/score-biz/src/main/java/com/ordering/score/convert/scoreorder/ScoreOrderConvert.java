package com.ordering.score.convert.scoreorder;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.score.controller.app.order.vo.AppScoreOrderVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.score.controller.admin.scoreorder.vo.*;
import com.ordering.score.dal.dataobject.scoreorder.ScoreOrderDO;

/**
 * 积分商城订单 Convert
 *
 * @author project team
 */
@Mapper
public interface ScoreOrderConvert {

    ScoreOrderConvert INSTANCE = Mappers.getMapper(ScoreOrderConvert.class);

    ScoreOrderDO convert(ScoreOrderCreateReqVO bean);

    ScoreOrderDO convert(ScoreOrderUpdateReqVO bean);

    ScoreOrderRespVO convert(ScoreOrderDO bean);

    AppScoreOrderVO convert01(ScoreOrderDO bean);

    List<ScoreOrderRespVO> convertList(List<ScoreOrderDO> list);

    List<AppScoreOrderVO> convertList01(List<ScoreOrderDO> list);

    PageResult<ScoreOrderRespVO> convertPage(PageResult<ScoreOrderDO> page);

    List<ScoreOrderExcelVO> convertList02(List<ScoreOrderDO> list);

}
