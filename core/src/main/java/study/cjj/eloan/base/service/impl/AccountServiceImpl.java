package study.cjj.eloan.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.domain.Account;
import study.cjj.eloan.base.mapper.AccountMapper;
import study.cjj.eloan.base.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private AccountMapper accountMapper;

	public Account getAccount(Long id) {
		Account account = accountMapper.selectByPrimaryKey(id);
		return account;
	}

}
