package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cjj.eloan.base.constant.BidConst;
import study.cjj.eloan.base.domain.BidRequest;
import study.cjj.eloan.base.query.BidRequestQueryObject;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.service.IBidRequestService;
import study.cjj.eloan.base.util.ResultJSON;

@Controller
public class BidRequestAuditController extends BaseController{
	
	@Autowired
	private IBidRequestService bidRequestService;
	
	
	/**
	 * 发标前审核列表
	 * @param queryObject
	 * @param model
	 * @return
	 */
	@RequestMapping("bidrequest_publishaudit_list")
	public String publishAuditList(BidRequestQueryObject queryObject, Model model) {
		queryObject.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
		PageResult result = this.bidRequestService.queryBidRequest(queryObject);
		model.addAttribute("pageResult", result);
		return "bidrequest/publish_audit";
	}
	
	/**
	 * 发标审核
	 * @param id
	 * @param state
	 * @param remark
	 * @return
	 */
	@RequestMapping("bidrequest_publishaudit")
	@ResponseBody
	public ResultJSON publishAudit(Long id,int state,String remark){
		BidRequest bidRequest=this.bidRequestService.getBidRequest(id);
		if(bidRequest!=null && bidRequest.getBidRequestState()==BidConst.BIDREQUEST_STATE_PUBLISH_PENDING){
			this.bidRequestService.publishAudit(bidRequest,state,remark);
		}
		return new ResultJSON(true);
	}


}
