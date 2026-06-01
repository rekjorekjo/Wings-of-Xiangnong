package com.wings.module.system.dal.mysql.mail;

import com.wings.framework.common.pojo.PageResult;
import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.wings.framework.mybatis.core.query.QueryWrapperX;
import com.wings.module.system.controller.admin.mail.vo.account.MailAccountPageReqVO;
import com.wings.module.system.dal.dataobject.mail.MailAccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailAccountMapper extends BaseMapperX<MailAccountDO> {

    default PageResult<MailAccountDO> selectPage(MailAccountPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<MailAccountDO>()
                .likeIfPresent(MailAccountDO::getMail, pageReqVO.getMail())
                .likeIfPresent(MailAccountDO::getUsername , pageReqVO.getUsername()));
    }

}
