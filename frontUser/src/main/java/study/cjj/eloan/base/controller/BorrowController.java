package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import study.cjj.eloan.base.constant.BidConst;
import study.cjj.eloan.base.domain.Account;
import study.cjj.eloan.base.domain.BidRequest;
import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.service.IAccountService;
import study.cjj.eloan.base.service.IBidRequestService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.util.UserContext;

@Controller
public class BorrowController extends BaseController {

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IBidRequestService bidRequestService;

	/**
	 * 借款介绍页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/borrow")
	public String borrowIndex(Model model) {
		LoginInfo current = UserContext.getLoginInfo();
		if (current == null) {
			return "redirect:borrow.html";
		}
		UserInfo ui = this.userInfoService.getUserInfo(current.getId());
		Account account = this.accountService.getAccount(current.getId());
		model.addAttribute("userInfo", ui);
		model.addAttribute("account", account);
		model.addAttribute("creditBorrowScore", BidConst.CREDIT_BORROW_SCORE);
		return "borrow";
	}

	/**
	 * 显示申请借款页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/borrowInfo")
	public String borrowInfo(Model model) {
		LoginInfo current = UserContext.getLoginInfo();
		UserInfo ui = this.userInfoService.getUserInfo(current.getId());
		if (ui.getHasBidRequestInProcess()) {
			return "borrow_apply_result";
		} else {
			Account account = this.accountService.getAccount(current.getId());
			model.addAttribute("account", account);
			model.addAttribute("minBidRequestAmount",BidConst.SMALLEST_BIDREQUEST_AMOUNT);		//最小借款金额
			model.addAttribute("minBidAmount",BidConst.SMALLEST_BID_AMOUNT);	///最小投标金额
			return "borrow_apply";
		}
	}
	
	
	/**
	 * 提交借款申请
	 * @param bidRequest
	 * @return
	 */
	@RequestMapping("/borrow_apply")
	public String borrowApply(BidRequest bidRequest){
		//首先再次检查该用户是否有发标的权限
		LoginInfo current=UserContext.getLoginInfo();
		boolean canPublish=this.bidRequestService.canPublish(current);
		if(!canPublish){
			return "redirect:borrow.do";
		}
		//提交申请
		this.bidRequestService.applyBidRequest(bidRequest);
		return "redirect:borrowInfo.do";
	}


}
