package study.cjj.eloan.base.mapper;

import java.util.List;

import study.cjj.eloan.base.domain.MailVerify;

public interface MailVerifyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MailVerify record);

    MailVerify selectByPrimaryKey(Long id);

    List<MailVerify> selectAll();

    int updateByPrimaryKey(MailVerify record);

	MailVerify selectByCheckCode(String checkCode);
}