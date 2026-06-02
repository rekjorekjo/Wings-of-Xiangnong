package com.ordering.module.shop.dal.mysql.materialgroup;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ordering.framework.mybatis.core.mapper.BaseMapperX;
import com.ordering.module.shop.dal.dataobject.materialgroup.MaterialGroupDO;
import org.apache.ibatis.annotations.Mapper;
import com.ordering.module.shop.controller.admin.materialgroup.vo.*;

/**
 * 素材分组 Mapper
 *
 * @author project team
 */
@Mapper
public interface MaterialGroupMapper extends BaseMapperX<MaterialGroupDO> {

    default PageResult<MaterialGroupDO> selectPage(MaterialGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaterialGroupDO>()
                .betweenIfPresent(MaterialGroupDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(MaterialGroupDO::getName, reqVO.getName())
                .orderByDesc(MaterialGroupDO::getId));
    }

    default List<MaterialGroupDO> selectList(MaterialGroupExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MaterialGroupDO>()
                .betweenIfPresent(MaterialGroupDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(MaterialGroupDO::getName, reqVO.getName())
                .orderByDesc(MaterialGroupDO::getId));
    }

}
