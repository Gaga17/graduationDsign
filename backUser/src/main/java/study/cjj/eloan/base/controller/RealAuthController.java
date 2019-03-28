package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cjj.eloan.base.query.RealAuthQueryObject;
import study.cjj.eloan.base.service.IRealAuthService;
import study.cjj.eloan.base.util.ResultJSON;

@Controller
public class RealAuthController extends BaseController {

	@Autowired
	private IRealAuthService realAuthService;
	
	/**
	 * 实名认证列表
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("/realAuth")
	public String list(@ModelAttribute("qo")RealAuthQueryObject qo,Model model){
		model.addAttribute("pageResult",this.realAuthService.query(qo));
		return "realAuth/list";
	}
	
	/**
	 * 实名认证审核
	 * @param id
	 * @param remark
	 * @param state
	 * @return
	 */
	@RequestMapping("/realAuth_audit")
	@ResponseBody
	public ResultJSON audit(Long id, String remark, int state) {
		ResultJSON json = new ResultJSON();
		try {
			this.realAuthService.audit(id, remark, state);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg(e.getMessage());
		}
		return json;
	}


}
