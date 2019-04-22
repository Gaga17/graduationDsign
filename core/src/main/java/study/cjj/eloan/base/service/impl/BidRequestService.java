package study.cjj.eloan.base.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.constant.BidConst;
import study.cjj.eloan.base.domain.Account;
import study.cjj.eloan.base.domain.BaseAuditDomain;
import study.cjj.eloan.base.domain.BidRequest;
import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.mapper.BidRequestAuditHistory;
import study.cjj.eloan.base.mapper.BidRequestAuditHistoryMapper;
import study.cjj.eloan.base.mapper.BidRequestMapper;
import study.cjj.eloan.base.query.BidRequestQueryObject;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.service.IAccountService;
import study.cjj.eloan.base.service.IBidRequestService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.util.BitStatesUtils;
import study.cjj.eloan.base.util.CalculatetUtil;
import study.cjj.eloan.base.util.UserContext;

@Service
public class BidRequestService implements IBidRequestService {

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private BidRequestMapper bidRequestMapper;
	
	@Autowired
	private BidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;

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
		if (bidRequest.getBidRequestAmount().compareTo(account.getRemainBorrowLimit()) <= 0) {
			// 设置发标人
			bidRequest.setCreateUser(UserContext.getLoginInfo());
			// 设置标的类型
			bidRequest.setBidRequestType(BidConst.BIDREQUEST_TYPE_NORMAL);
			// 设置标的状态
			bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
			// 计算标应付的利息
			bidRequest.setTotalRewardAmount(CalculatetUtil.calTotalInterest(bidRequest.getReturnType(),
					bidRequest.getBidRequestAmount(), bidRequest.getCurrentRate(), bidRequest.getMonthes2Return()));
			// 发布标
			this.bidRequestMapper.insert(bidRequest);
			// 设置当前用户有正在申请中的借款
			UserInfo userInfo = this.userInfoService.getUserInfo(UserContext.getLoginInfo().getId());
			userInfo.addState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
			userInfoService.update(userInfo);
		}

	}

	@Override
	public PageResult queryBidRequest(BidRequestQueryObject queryObject) {
		Long total = this.bidRequestMapper.getBidRequestTotalCount(queryObject);
		if (total > 0) {
			List<BidRequest> ps = this.bidRequestMapper.getBidRequestBy(queryObject);
			return new PageResult(ps, total.intValue(), queryObject.getCurrentPage(), queryObject.getPageSize());
		}
		return PageResult.empty(queryObject.getPageSize());

	}

	@Override
	public BidRequest getBidRequest(Long id) {
		return bidRequestMapper.selectByPrimaryKey(id);
	}

	@Override
	public void publishAudit(BidRequest bidRequest, int state, String remark) {
		// 设置审核历史对象
		BidRequestAuditHistory history = createBidRequestAuditHistory(bidRequest, state, remark,
				BidRequestAuditHistory.PUBLISH_AUDIT);
		if (state == BaseAuditDomain.STATE_REJECT) {
			// 如果审核拒绝，处理标相关内容
			bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_REFUSE);
		} else {
			// 审核通过，发标
			bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_BIDDING);
			bidRequest.setPublishTime(new Date());
			bidRequest.setDisableDate(DateUtils.addDays(new Date(), bidRequest.getDisableDays()));
			bidRequest.setNote(remark);
		}
		// 保存审核历史对象
		this.bidRequestAuditHistoryMapper.insert(history);
		// 修改标的对象
		this.update(bidRequest);

	}

	/**
	 * 创建标的审核历史对象
	 * @param bidRequest
	 * @param state
	 * @param remark
	 * @param auditType
	 * @return
	 */

	private BidRequestAuditHistory createBidRequestAuditHistory(BidRequest bidRequest, int state, String remark,
			int auditType) {
		BidRequestAuditHistory history = new BidRequestAuditHistory();
		history.setApplier(bidRequest.getCreateUser());
		history.setAuditor(UserContext.getLoginInfo());
		history.setAuditTime(new Date());
		history.setAuditType(auditType);
		history.setBidRequestId(bidRequest.getId());
		history.setRemark(remark);
		history.setState(state);
		return history;

	}

	public void update(BidRequest bidRequest) {
		int ret=this.bidRequestMapper.updateByPrimaryKey(bidRequest);
		if(ret<=0){
			throw new RuntimeException("标的修改乐观锁失败："+bidRequest.getId());
		}

	}

}
