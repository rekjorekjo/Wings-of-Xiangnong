package com.wings.module.express.convert.express;

import com.wings.framework.common.pojo.PageResult;
import com.wings.module.express.controller.admin.express.vo.ExpressCreateReqVO;
import com.wings.module.express.controller.admin.express.vo.ExpressExcelVO;
import com.wings.module.express.controller.admin.express.vo.ExpressRespVO;
import com.wings.module.express.controller.admin.express.vo.ExpressUpdateReqVO;
import com.wings.module.express.dal.dataobject.express.ExpressDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 快递公司 Convert
 *
 * @author wings
 */
@Mapper
public interface ExpressConvert {

    ExpressConvert INSTANCE = Mappers.getMapper(ExpressConvert.class);

    ExpressDO convert(ExpressCreateReqVO bean);

    ExpressDO convert(ExpressUpdateReqVO bean);

    ExpressRespVO convert(ExpressDO bean);

    List<ExpressRespVO> convertList(List<ExpressDO> list);

    PageResult<ExpressRespVO> convertPage(PageResult<ExpressDO> page);

    List<ExpressExcelVO> convertList02(List<ExpressDO> list);

}
