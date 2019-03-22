package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cjj.eloan.base.annotation.RequireLogin;
import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.service.IAccountService;
import study.cjj.eloan.base.service.ILoginInfoService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.service.IVerifyCodeService;
import study.cjj.eloan.base.util.LogicException;
import study.cjj.eloan.base.util.ResultJSON;
import study.cjj.eloan.base.util.UserContext;

@Controller
public class PersonalController extends BaseController{

	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private ILoginInfoService loginInfoService;
	@Autowired
	private IVerifyCodeService verifyCodeSeivice;

	/**
	 * 个人中心,账户信息
	 * @param model
	 * @return
	 */
	@RequireLogin
	@RequestMapping("/personal")
	public String personal(Model model) {
		LoginInfo current = UserContext.getLoginInfo();
		model.addAttribute("account", accountService.getAccount(current.getId()));
		model.addAttribute("userInfo", userInfoService.getUserInfo(current.getId()));
		model.addAttribute("lastloginTime",loginInfoService.getLastLoginTime(current.getUsername(),current.getUserType()));
		return "personal";
	}

	
	/**
	 * 发送手机验证码
	 * @param model
	 * @return
	 */
	@RequestMapping("/sendVerifyCode")
	@ResponseBody
	public String sendVerifyCode(Model model,String phoneNumber) {
		LoginInfo current = UserContext.getLoginInfo();
		System.out.println("发送手机验证码");
		verifyCodeSeivice.sendVerifyCode(phoneNumber);
		return "true";
	}
	
	/**
	 * 绑定手机号
	 * @param phoneNumber
	 * @param verifyCode
	 * @param model
	 * @return
	 */
	@RequestMapping("/bindPhone")
	@ResponseBody
	public ResultJSON bindPhone(String phoneNumber,String verifyCode, Model model) {
		ResultJSON json = new ResultJSON();
		json.setSuccess(true);
		try {
			this.userInfoService.bindPhone(phoneNumber,verifyCode);
			json.setSuccess(true);
		} catch (LogicException le) {
			json.setSuccess(false);
			json.setMsg(le.getMessage());
		}
		return json;
	}


	
}
