package com.ordering.module.member.dal.mysql.user;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.mybatis.core.mapper.BaseMapperX;
import com.ordering.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ordering.module.member.controller.admin.user.vo.UserExportReqVO;
import com.ordering.module.member.controller.admin.user.vo.UserPageReqVO;
import com.ordering.module.member.dal.dataobject.user.MemberUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员 User Mapper
 *
 * @author project team
 */
@Mapper
public interface MemberUserMapper extends BaseMapperX<MemberUserDO> {

    default MemberUserDO selectByMobile(String mobile) {
        return selectOne(MemberUserDO::getMobile, mobile);
    }

    default List<MemberUserDO> selectListByNicknameLike(String nickname) {
        return selectList(new LambdaQueryWrapperX<MemberUserDO>()
                .likeIfPresent(MemberUserDO::getNickname, nickname));
    }

    default PageResult<MemberUserDO> selectPage(UserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberUserDO>()
                .likeIfPresent(MemberUserDO::getLoginType, reqVO.getLoginType())
                .likeIfPresent(MemberUserDO::getUsername, reqVO.getUsername())
                .likeIfPresent(MemberUserDO::getRealName, reqVO.getRealName())
                .likeIfPresent(MemberUserDO::getNickname, reqVO.getNickname())
                .likeIfPresent(MemberUserDO::getMobile, reqVO.getPhone())
                .betweenIfPresent(MemberUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberUserDO::getId));
    }

    default List<MemberUserDO> selectList(UserExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MemberUserDO>()
                .likeIfPresent(MemberUserDO::getUsername, reqVO.getUsername())
                .likeIfPresent(MemberUserDO::getRealName, reqVO.getRealName())
                .likeIfPresent(MemberUserDO::getNickname, reqVO.getNickname())
                .eqIfPresent(MemberUserDO::getMobile, reqVO.getPhone())
                .betweenIfPresent(MemberUserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberUserDO::getId));
    }

    @Update("update app_user set now_money=now_money-#{payPrice}" +
            " where id=#{uid}")
    int decPrice(@Param("payPrice") BigDecimal payPrice, @Param("uid") Long uid);

    @Update("update app_user set integral=integral-#{score}" +
            " where id=#{uid}")
    int decScore(@Param("score") Integer score, @Param("uid") Long uid);

    @Update("update app_user set pay_count=pay_count+1" +
            " where id=#{uid}")
    int incPayCount(@Param("uid") Long uid);

    @Update("update app_user set now_money=now_money+#{price}" +
            " where id=#{uid}")
    int incMoney(@Param("uid") Long uid,@Param("price") BigDecimal price);

}
