package study.cjj.eloan.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.VedioAuthQueryObject;
import study.cjj.eloan.base.service.ILoginInfoService;
import study.cjj.eloan.base.service.IVedioAuthService;
import study.cjj.eloan.base.util.ResultJSON;

/**
 * 后台完成视频认证审核
 * @author cjj
 * @date 2019年4月2日
 */
@Controller
public class VedioAuthController extends BaseController {
	
	@Autowired
	private IVedioAuthService vedioAuthService;
	
	@Autowired
	private ILoginInfoService loginInfoService;
	
	/**
	 * 视频认证列表
	 * @param qo
	 * @param model
	 * @param state
	 * @return
	 */
	@RequestMapping("vedioAuth")
	public String list(@ModelAttribute("qo")VedioAuthQueryObject qo, Model model,Integer state) {
		if (qo != null && state != null) {
			qo.setStatus(state);
		}
		PageResult pageResult = this.vedioAuthService.getVedioAuthBy(qo);
		model.addAttribute("pageResult", pageResult);
		return "vedioauth/list";
	}

	/**
	 * 视频认证审核
	 * @param state
	 * @param remark
	 * @param loginInfoValue
	 * @return
	 */
	@RequestMapping("vedioAuth_audit")
	@ResponseBody
	public ResultJSON audit(int state, String remark, Long loginInfoValue) {
		this.vedioAuthService.audit(state, remark, loginInfoValue);
		return new ResultJSON(true);
	}
	
	/**
	 * 实名认证,自动补全
	 * @param key
	 * @return
	 */
	@RequestMapping("vedioAuth_autocomplate")
	@ResponseBody
	public List<Map<String,String>> autcomplate(String key) {
		return this.loginInfoService.autoComplateList(key);
	}


}
