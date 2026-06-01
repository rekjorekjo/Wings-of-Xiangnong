package com.wings.module.infra.dal.mysql.db;

import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.module.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 * @author wings
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
