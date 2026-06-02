package com.ordering.module.system.dal.mysql.mail;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.mybatis.core.mapper.BaseMapperX;
import com.ordering.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ordering.framework.mybatis.core.query.QueryWrapperX;
import com.ordering.module.system.controller.admin.mail.vo.account.MailAccountPageReqVO;
import com.ordering.module.system.dal.dataobject.mail.MailAccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailAccountMapper extends BaseMapperX<MailAccountDO> {

    default PageResult<MailAccountDO> selectPage(MailAccountPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<MailAccountDO>()
                .likeIfPresent(MailAccountDO::getMail, pageReqVO.getMail())
                .likeIfPresent(MailAccountDO::getUsername , pageReqVO.getUsername()));
    }

}
