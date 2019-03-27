package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cjj.eloan.base.constant.DicSnConst;
import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.service.ISystemDictionaryService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.util.ResultJSON;
import study.cjj.eloan.base.util.UserContext;

@Controller
public class BasicInfoController extends BaseController{

	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	/**
	 * 用户基本信息
	 * @param model
	 * @return
	 */
	@RequestMapping("basicInfo")
	public String basicInfoIndex(Model model) {
		LoginInfo current = UserContext.getLoginInfo();
		if (current != null) {
			UserInfo ui=this.userInfoService.getUserInfo(current.getId());
			model.addAttribute("userInfo",ui);
			
			model.addAttribute("ebgSelects", systemDictionaryService.listItemByParentSn(DicSnConst.SN_EDUCATION_BACKGROUND));
			model.addAttribute("igSelects", systemDictionaryService.listItemByParentSn(DicSnConst.SN_INCOME_GRADE));
			model.addAttribute("magSelects", systemDictionaryService.listItemByParentSn(DicSnConst.SN_MARRIAGE));
			model.addAttribute("kcSelects", systemDictionaryService.listItemByParentSn(DicSnConst.SN_KID_COUNT));
			model.addAttribute("hcSelects", systemDictionaryService.listItemByParentSn(DicSnConst.SN_HOUSE_CONDITION));
		}
		return "userInfo";
	}
	
	/**
	 * 用户基本信息修改
	 * @param userinfo
	 * @return
	 */
	@RequestMapping("basicInfo_save")
	@ResponseBody
	public ResultJSON basicInfoSave(UserInfo userinfo){
		ResultJSON json = new ResultJSON();
		try{
			json.setSuccess(true);
			this.userInfoService.updateBasicInfo(userinfo);
		}catch(Exception e){
			e.printStackTrace();
			json.setMsg("保存失败!");
		}
		return json;
	}


	
}
