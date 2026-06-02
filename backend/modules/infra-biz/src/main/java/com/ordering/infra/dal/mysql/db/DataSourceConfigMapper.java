package com.ordering.infra.dal.mysql.db;

import com.ordering.framework.mybatis.core.mapper.BaseMapperX;
import com.ordering.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 * @author project team
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
