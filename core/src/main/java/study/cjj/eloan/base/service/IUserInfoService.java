package study.cjj.eloan.base.service;

import study.cjj.eloan.base.domain.UserInfo;

public interface IUserInfoService {

	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public UserInfo getUserInfo(Long id);
	

}