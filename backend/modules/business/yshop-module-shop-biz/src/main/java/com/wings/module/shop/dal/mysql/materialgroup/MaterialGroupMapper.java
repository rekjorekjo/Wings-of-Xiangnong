package com.wings.module.shop.dal.mysql.materialgroup;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;
import com.wings.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.module.shop.dal.dataobject.materialgroup.MaterialGroupDO;
import org.apache.ibatis.annotations.Mapper;
import com.wings.module.shop.controller.admin.materialgroup.vo.*;

/**
 * 素材分组 Mapper
 *
 * @author wings
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
