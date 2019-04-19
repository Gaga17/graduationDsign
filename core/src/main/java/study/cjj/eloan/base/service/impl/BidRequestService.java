package study.cjj.eloan.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.constant.BidConst;
import study.cjj.eloan.base.domain.Account;
import study.cjj.eloan.base.domain.BidRequest;
import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.mapper.BidRequestMapper;
import study.cjj.eloan.base.service.IAccountService;
import study.cjj.eloan.base.service.IBidRequestService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.util.BitStatesUtils;
import study.cjj.eloan.base.util.CalculatetUtil;
import study.cjj.eloan.base.util.UserContext;

@Service
public class BidRequestService implements IBidRequestService{
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private BidRequestMapper bidRequestMapper;

	@Override
	public boolean canPublish(LoginInfo loginInfo) {
		UserInfo ui = this.userInfoService.getUserInfo(loginInfo.getId());
		Account account = this.accountService.getAccount(loginInfo.getId());
		return !ui.getHasBidRequestInProcess()// 没有申请中的借款
				&& account.getBorrowLimitAmount().compareTo(BidConst.SMALLEST_BIDREQUEST_AMOUNT) >= 0// 至少还可借500
				&& ui.getIsBasicInfo()// 填写了基本信息
				&& ui.getIsRealAuth()// 完成实名认证
				&& ui.getIsVedioAuth();// 完成了视频认证
	}

	@Override
	public void applyBidRequest(BidRequest bidRequest) {
		Account account = this.accountService.getAccount(UserContext.getLoginInfo().getId());
		if(bidRequest.getBidRequestAmount().compareTo(account.getRemainBorrowLimit()) <= 0) {
			// 设置发标人
			bidRequest.setCreateUser(UserContext.getLoginInfo());
			// 设置标的类型
			bidRequest.setBidRequestType(BidConst.BIDREQUEST_TYPE_NORMAL);
			// 设置标的状态			
			bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
			// 计算标应付的利息			
			bidRequest.setTotalRewardAmount(CalculatetUtil.calTotalInterest(bidRequest.getReturnType(), bidRequest.getBidRequestAmount(),
			bidRequest.getCurrentRate(), bidRequest.getMonthes2Return()));
			// 发布标
			this.bidRequestMapper.insert(bidRequest);
			// 设置当前用户有正在申请中的借款
			UserInfo userInfo = this.userInfoService.getUserInfo(UserContext.getLoginInfo().getId());
			userInfo.addState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
			userInfoService.update(userInfo);
		}

	}

}
