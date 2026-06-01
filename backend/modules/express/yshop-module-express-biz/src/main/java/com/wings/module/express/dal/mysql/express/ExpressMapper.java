package com.wings.module.express.dal.mysql.express;

import com.wings.framework.common.pojo.PageResult;
import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.wings.module.express.controller.admin.express.vo.ExpressExportReqVO;
import com.wings.module.express.controller.admin.express.vo.ExpressPageReqVO;
import com.wings.module.express.dal.dataobject.express.ExpressDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 快递公司 Mapper
 *
 * @author wings
 */
@Mapper
public interface ExpressMapper extends BaseMapperX<ExpressDO> {

    default PageResult<ExpressDO> selectPage(ExpressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExpressDO>()
                .eqIfPresent(ExpressDO::getCode, reqVO.getCode())
                .likeIfPresent(ExpressDO::getName, reqVO.getName())
                .orderByDesc(ExpressDO::getId));
    }

    default List<ExpressDO> selectList(ExpressExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ExpressDO>()
                .eqIfPresent(ExpressDO::getCode, reqVO.getCode())
                .likeIfPresent(ExpressDO::getName, reqVO.getName())
                .orderByDesc(ExpressDO::getId));
    }

}
