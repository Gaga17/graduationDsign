package study.cjj.eloan.base.service;

import study.cjj.eloan.base.domain.BidRequest;
import study.cjj.eloan.base.domain.LoginInfo;

public interface IBidRequestService {

	public boolean canPublish(LoginInfo current);

	public void applyBidRequest(BidRequest bidRequest);

}
