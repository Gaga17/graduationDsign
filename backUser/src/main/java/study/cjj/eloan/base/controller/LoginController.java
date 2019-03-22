package study.cjj.eloan.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.service.ILoginInfoService;
import study.cjj.eloan.base.util.LogicException;
import study.cjj.eloan.base.util.ResultJSON;

/**
 * 平台系统用户登录
 * @author cjj
 * @date 2019年3月20日
 */
@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private ILoginInfoService loginService;
	
	@RequestMapping("/login")
	@ResponseBody
	public ResultJSON login(String username, String password,HttpServletRequest request) {
		ResultJSON json = new ResultJSON();
		try {
			LoginInfo loginInfo = loginService.login(username, password, LoginInfo.USERTYPE_MANAGER,request.getRemoteAddr());
			if (loginInfo == null) {
				throw new LogicException("用户名或密码错误", 103);
			}
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
		}
		return json;
	}

}
