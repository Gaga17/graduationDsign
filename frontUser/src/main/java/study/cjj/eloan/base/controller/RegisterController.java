package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.service.ILoginInfoService;
import study.cjj.eloan.base.util.ResultJSON;



@Controller
public class RegisterController extends BaseController{
	
	@Autowired
	private ILoginInfoService loginInfoService;
	
	
	@RequestMapping("/register")
	@ResponseBody
	public ResultJSON register(String username,String password){
		ResultJSON result = new ResultJSON();
		try {
			loginInfoService.register(username, password);
			result.setSuccess(true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/checkUsername")
	@ResponseBody
	public String checkUsername(String username){
		if(loginInfoService.checkUsername(username,LoginInfo.STATE_NORMAL)){
			return "true";
		}else{
			return "false";
		}
	}
	

}
