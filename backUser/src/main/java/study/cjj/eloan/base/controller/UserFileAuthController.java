package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.UserFileQueryObject;
import study.cjj.eloan.base.service.IUserFileService;
import study.cjj.eloan.base.util.ResultJSON;

@Controller
public class UserFileAuthController extends BaseController{
	
	@Autowired
	private IUserFileService userFileService;

	/**
	 * 用户风控材料审核列表
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("userFileAuth")
	public String list(@ModelAttribute("qo")UserFileQueryObject qo, Model model) {
		PageResult pageResult = this.userFileService.getUserFileBy(qo);
		model.addAttribute("pageResult", pageResult);
		return "userFile/list";
	}
	
	/**
	 * 用户风控材料审核
	 * @param state
	 * @param remark
	 * @param id
	 * @param score
	 * @return
	 */
	@RequestMapping("userFile_audit")
	@ResponseBody
	public ResultJSON audit(int state, String remark, Long id, int score) {
		this.userFileService.audit(state, remark, id, score);
		return new ResultJSON(true);
	}


}
