package com.wings.module.shop.dal.mysql.material;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;
import com.wings.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.module.shop.dal.dataobject.material.MaterialDO;
import org.apache.ibatis.annotations.Mapper;
import com.wings.module.shop.controller.admin.material.vo.*;

/**
 * 素材库 Mapper
 *
 * @author wings
 */
@Mapper
public interface MaterialMapper extends BaseMapperX<MaterialDO> {

    default PageResult<MaterialDO> selectPage(MaterialPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MaterialDO>()
                .betweenIfPresent(MaterialDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MaterialDO::getType, reqVO.getType())
                .eqIfPresent(MaterialDO::getGroupId, reqVO.getGroupId())
                .likeIfPresent(MaterialDO::getName, reqVO.getName())
                .eqIfPresent(MaterialDO::getUrl, reqVO.getUrl())
                .orderByDesc(MaterialDO::getId));
    }

    default List<MaterialDO> selectList(MaterialExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MaterialDO>()
                .betweenIfPresent(MaterialDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MaterialDO::getType, reqVO.getType())
                .eqIfPresent(MaterialDO::getGroupId, reqVO.getGroupId())
                .likeIfPresent(MaterialDO::getName, reqVO.getName())
                .eqIfPresent(MaterialDO::getUrl, reqVO.getUrl())
                .orderByDesc(MaterialDO::getId));
    }

}
