package study.cjj.eloan.base.service;

import study.cjj.eloan.base.domain.VedioAuth;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.VedioAuthQueryObject;

public interface IVedioAuthService {

	/**
	 * 获取视频认证
	 * @param vedioAuthenticationId
	 * @return
	 */
	public VedioAuth getVedioAuth(Long vedioAuthenticationId);

	/**
	 * 视频认证分页查询
	 * @param queryObject
	 * @return
	 */
	public PageResult getVedioAuthBy(VedioAuthQueryObject queryObject);

	/**
	 * 视频认证审核
	 * @param state
	 * @param remark
	 * @param loginInfoValue
	 */
	public void audit(int state, String remark, Long loginInfoValue);

}
