package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import study.cjj.eloan.base.domain.BidRequest;
import study.cjj.eloan.base.domain.UserFile;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.query.UserFileQueryObject;
import study.cjj.eloan.base.service.IBidRequestService;
import study.cjj.eloan.base.service.IRealAuthService;
import study.cjj.eloan.base.service.IUserFileService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.service.impl.RealAuthServiceImpl;

@Controller
public class BorrowInfoController extends BaseController {

	
	@Autowired
	private IBidRequestService bidRequestService;
	
	@Autowired
	private IUserInfoService userInfoService;
	
	@Autowired
	private IUserFileService userFileService;
	
	@Autowired
	private IRealAuthService realAuthService;
	
	/**
	 * 查看标的明细
	 */
	@RequestMapping("/borrow_info")
	public String borrowInfo(Long id, Model model) {
		BidRequest bidRequest = this.bidRequestService.getBidRequest(id);
		//获得当前的借款人
		UserInfo userinfo = this.userInfoService.getUserInfo(bidRequest.getCreateUser().getId());
		model.addAttribute("bidRequest",bidRequest );
		model.addAttribute("userInfo",userinfo);
		//查询当前标的 历史审核对象
		model.addAttribute("audits", this.bidRequestService.listAuditHistorayByBidRequest(bidRequest.getId()));
		//用户的实名认证信息
		model.addAttribute("realAuth", this.realAuthService.getRealAuth(userinfo.getRealAuthenticationId()));
		
		//借款人所有的相关的风控材料(已经审核通过)
		UserFileQueryObject  qo = new UserFileQueryObject() ;
		qo.setStatus(UserFile.STATE_PASS);
		qo.setApplierId(userinfo.getId()); 
		qo.setPageSize(-1);
		model.addAttribute("userFiles", this.userFileService.getUserFileBy(qo));
		return "bidrequest/borrow_info";
	}

}
