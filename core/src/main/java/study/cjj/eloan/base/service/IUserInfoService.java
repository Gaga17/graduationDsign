package study.cjj.eloan.base.service;

import study.cjj.eloan.base.domain.UserInfo;

public interface IUserInfoService {

	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public UserInfo getUserInfo(Long id);

	/**
	 * 绑定手机号
	 * @param phoneNumber	手机号
	 * @param verifyCode	验证码
	 */
	public void bindPhone(String phoneNumber, String verifyCode);

	/**
	 * 发送绑定邮件
	 * @param email
	 */
	public void sendBindEmail(String email);

	/**
	 * 绑定邮件验证
	 * @param checkCode
	 */
	public void bindMailVerify(String checkCode);

	/**
	 * 更新用户基本信息
	 * @param userinfo
	 */
	public void updateBasicInfo(UserInfo userinfo);
	

}
