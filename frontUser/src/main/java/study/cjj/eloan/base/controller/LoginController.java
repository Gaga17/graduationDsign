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
import study.cjj.eloan.base.util.UserContext;

@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private ILoginInfoService loginInfoService;
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ResultJSON login(String username,String password,HttpServletRequest request){
		ResultJSON result = new ResultJSON();
		try {
			LoginInfo currentUser = loginInfoService.login(username, password,LoginInfo.USERTYPE_USER,request.getRemoteAddr());
			if(currentUser==null){
				throw new LogicException("用户名或密码错误!",103);
			}
			result.setSuccess(true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	
	/**
	 * 注销/登出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(){
		UserContext.setLoginInfo(null);
		return "redirect:main.do";
	}

	

}
