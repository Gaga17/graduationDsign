package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.query.IpLogQueryObject;
import study.cjj.eloan.base.service.IIpLogService;
import study.cjj.eloan.base.util.UserContext;

@Controller
public class IpLogController extends BaseController{

	@Autowired
	private IIpLogService ipLogService;

	@RequestMapping("/ipLog")
	public String ipLog(@ModelAttribute("qo")IpLogQueryObject qo,Model model) {
		if(LoginInfo.USERTYPE_MANAGER==UserContext.getLoginInfo().getUserType()){
			qo.setLike(true);
		}
		model.addAttribute("pageResult",this.ipLogService.query(qo));
		return "ipLog/list";
	}

}
