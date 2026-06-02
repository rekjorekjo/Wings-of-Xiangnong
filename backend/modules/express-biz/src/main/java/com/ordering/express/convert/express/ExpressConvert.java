package com.ordering.express.convert.express;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.express.controller.admin.express.vo.ExpressCreateReqVO;
import com.ordering.express.controller.admin.express.vo.ExpressExcelVO;
import com.ordering.express.controller.admin.express.vo.ExpressRespVO;
import com.ordering.express.controller.admin.express.vo.ExpressUpdateReqVO;
import com.ordering.express.dal.dataobject.express.ExpressDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 快递公司 Convert
 *
 * @author project team
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
