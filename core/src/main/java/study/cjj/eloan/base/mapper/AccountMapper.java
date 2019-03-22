package study.cjj.eloan.base.mapper;

import java.util.List;

import study.cjj.eloan.base.domain.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account account);

    Account selectByPrimaryKey(Long id);

    List<Account> selectAll();

    int updateByPrimaryKey(Account record);
}