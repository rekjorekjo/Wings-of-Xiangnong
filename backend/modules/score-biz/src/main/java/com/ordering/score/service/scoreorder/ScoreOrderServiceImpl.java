package com.ordering.score.service.scoreorder;

import com.ordering.framework.common.enums.ShopCommonEnum;
import com.ordering.member.controller.admin.user.vo.UserRespVO;
import com.ordering.member.convert.user.UserConvert;
import com.ordering.member.dal.dataobject.user.MemberUserDO;
import com.ordering.member.dal.mysql.user.MemberUserMapper;
import com.ordering.score.convert.scoreproduct.ScoreProductConvert;
import com.ordering.score.dal.dataobject.scoreproduct.ScoreProductDO;
import com.ordering.score.dal.mysql.scoreproduct.ScoreProductMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ordering.score.controller.admin.scoreorder.vo.*;
import com.ordering.score.dal.dataobject.scoreorder.ScoreOrderDO;
import com.ordering.framework.common.pojo.PageResult;

import com.ordering.score.convert.scoreorder.ScoreOrderConvert;
import com.ordering.score.dal.mysql.scoreorder.ScoreOrderMapper;

import static com.ordering.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ordering.score.enums.ErrorCodeConstants.*;

/**
 * 积分商城订单 Service 实现类
 *
 * @author project team
 */
@Service
@Validated
public class ScoreOrderServiceImpl implements ScoreOrderService {

    @Resource
    private ScoreOrderMapper orderMapper;
    @Resource
    private ScoreProductMapper scoreProductMapper;
    @Resource
    private MemberUserMapper memberUserMapper;

    @Override
    public Long createOrder(ScoreOrderCreateReqVO createReqVO) {
        // 插入
        ScoreOrderDO order = ScoreOrderConvert.INSTANCE.convert(createReqVO);
        orderMapper.insert(order);
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(ScoreOrderUpdateReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        ScoreOrderDO updateObj = ScoreOrderConvert.INSTANCE.convert(updateReqVO);
        updateObj.setHaveDelivered(ShopCommonEnum.DEFAULT_1.getValue());
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    @Override
    public void takeOrder(Long id) {
        // 校验存在
        ScoreOrderDO scoreOrderDO = orderMapper.selectById(id);
        if (scoreOrderDO == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
        scoreOrderDO.setHaveReceived(ShopCommonEnum.DEFAULT_1.getValue());
        orderMapper.updateById(scoreOrderDO);

    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public ScoreOrderRespVO getOrder(Long id) {
        ScoreOrderDO scoreOrderDO = orderMapper.selectById(id);
        ScoreOrderRespVO scoreOrderRespVO = ScoreOrderConvert.INSTANCE.convert(scoreOrderDO);
        ScoreProductDO scoreProductDO = scoreProductMapper.selectById(scoreOrderRespVO.getProductId());
        scoreOrderRespVO.setScoreProductRespVO(ScoreProductConvert.INSTANCE.convert(scoreProductDO));
        MemberUserDO memberUserDO = memberUserMapper.selectById(scoreOrderRespVO.getUid());
        UserRespVO  userRespVO = UserConvert.INSTANCE.convert4(memberUserDO);
        scoreOrderRespVO.setUserRespVO(userRespVO);
        return scoreOrderRespVO;
    }

    @Override
    public List<ScoreOrderDO> getOrderList(Collection<Long> ids) {
        return orderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ScoreOrderRespVO> getOrderPage(ScoreOrderPageReqVO pageReqVO) {
        PageResult<ScoreOrderDO> pageResult = orderMapper.selectPage(pageReqVO);
        PageResult<ScoreOrderRespVO> scoreOrderRespVOPageResult = ScoreOrderConvert.INSTANCE.convertPage(pageResult);
        for (ScoreOrderRespVO scoreOrderRespVO : scoreOrderRespVOPageResult.getList()) {
            ScoreProductDO scoreProductDO = scoreProductMapper.selectById(scoreOrderRespVO.getProductId());
            scoreOrderRespVO.setScoreProductRespVO(ScoreProductConvert.INSTANCE.convert(scoreProductDO));
            MemberUserDO memberUserDO = memberUserMapper.selectById(scoreOrderRespVO.getUid());
            UserRespVO  userRespVO = UserConvert.INSTANCE.convert4(memberUserDO);
            scoreOrderRespVO.setUserRespVO(userRespVO);
        }
        return scoreOrderRespVOPageResult;
    }

    @Override
    public List<ScoreOrderDO> getOrderList(ScoreOrderExportReqVO exportReqVO) {
        return orderMapper.selectList(exportReqVO);
    }

}
