package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import study.cjj.eloan.base.constant.BidConst;
import study.cjj.eloan.base.domain.Account;
import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.service.IAccountService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.util.UserContext;

@Controller
public class BorrowController extends BaseController{
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IAccountService accountService;

	/**
	 * 借款介绍页面
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

}
