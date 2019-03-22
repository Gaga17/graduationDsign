package study.cjj.eloan.base.service;

import study.cjj.eloan.base.domain.Account;

public interface IAccountService {

	/**
	 * 获取账户信息
	 * @param id
	 * @return
	 */
	public Account getAccount(Long id);

}
