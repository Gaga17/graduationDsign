package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import study.cjj.eloan.base.annotation.RequireLogin;
import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.service.IAccountService;
import study.cjj.eloan.base.service.ILoginInfoService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.util.UserContext;

@Controller
public class PersonalController extends BaseController{

	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private ILoginInfoService loginInfoService;

	@RequireLogin
	@RequestMapping("/personal")
	public String personal(Model model) {
		LoginInfo current = UserContext.getLoginInfo();
		model.addAttribute("account", accountService.getAccount(current.getId()));
		model.addAttribute("userInfo", userInfoService.getUserInfo(current.getId()));
		model.addAttribute("lastloginTime",loginInfoService.getLastLoginTime(current.getUsername(),current.getUserType()));
		return "personal";
	}

}
