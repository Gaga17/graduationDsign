package study.cjj.eloan.base.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import study.cjj.eloan.base.domain.RealAuth;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.service.IRealAuthService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.util.UploadUtil;
import study.cjj.eloan.base.util.UserContext;

@Controller
public class RealAuthController extends BaseController{
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IRealAuthService realAuthService;
	
	@Autowired
	private ServletContext servletContext ;

	/**
	 * 实名认证页面
	 * @param model
	 * @return
	 */
	@RequestMapping("realAuth")
	public String realAuthIndex(Model model){
		UserInfo current=this.userInfoService.getUserInfo(UserContext.getLoginInfo().getId());
		if(current.getIsRealAuth()){
			RealAuth realAuth=this.realAuthService.getRealAuth(current.getRealAuthenticationId());
			model.addAttribute("realAuth",realAuth);
			return "realAuth_result";
		}else if(current.getRealAuthenticationId()!=null){
			model.addAttribute("auditing", true);
			return "realAuth_result";
		}else{
			return "realAuth";
		}
	}
	
	/**
	 * 实名认证-图片上传
	 * @param file
	 * @param response
	 */
	@RequestMapping("realAuth_upload")
	@ResponseBody
	public void realAuthUpload(MultipartFile file,HttpServletResponse response){
		String basePath=servletContext.getRealPath("/upload");
		String fileName=UploadUtil.upload(file,basePath);
		String relativeFile="/upload/"+fileName;
		try {
			response.getWriter().write(relativeFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 实名认证信息保存
	 * @param realAuth
	 * @return
	 */
	@RequestMapping("realAuth_save")
	public String realAuthSave(RealAuth realAuth){
		this.realAuthService.applyRealAuth(realAuth);
		return "redirect:realAuth.do";
	}


}
