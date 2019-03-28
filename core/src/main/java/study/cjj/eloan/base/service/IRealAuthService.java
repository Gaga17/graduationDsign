package study.cjj.eloan.base.service;

import study.cjj.eloan.base.domain.RealAuth;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.RealAuthQueryObject;

public interface IRealAuthService {

	/**
	 * 根据ID获取实名认证信息
	 * @param realAuthenticationId
	 * @return
	 */
	public RealAuth getRealAuth(Long realAuthenticationId);

	/**
	 * 实名认证申请
	 * @param realAuth
	 */
	public void applyRealAuth(RealAuth realAuth);

	/**
	 * 实名认证信息分页查询
	 * @param qo
	 * @return
	 */
	public PageResult query(RealAuthQueryObject qo);

	/**
	 * 实名认证审核
	 * @param id
	 * @param remark
	 * @param state
	 */
	public void audit(Long id, String remark, int state);

}
