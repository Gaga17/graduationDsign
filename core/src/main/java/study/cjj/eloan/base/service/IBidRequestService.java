package study.cjj.eloan.base.service;

import study.cjj.eloan.base.domain.BidRequest;
import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.query.BidRequestQueryObject;
import study.cjj.eloan.base.query.PageResult;

public interface IBidRequestService {

	public boolean canPublish(LoginInfo current);

	public void applyBidRequest(BidRequest bidRequest);

	/**
	 * 获取正在申请的标
	 * @param queryObject
	 * @return
	 */
	public PageResult queryBidRequest(BidRequestQueryObject queryObject);

	public BidRequest getBidRequest(Long id);

	public void publishAudit(BidRequest bidRequest, int state, String remark);

}
