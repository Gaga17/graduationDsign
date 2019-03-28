package study.cjj.eloan.base.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseAuditDomain extends BaseDomain{

	private static final long serialVersionUID = 1L;
	
	public static final int STATE_APPLY = 0; //申请状态
	public static final int STATE_PASS = 1; //审核通过
	public static final int STATE_REJECT = 2;//审核拒绝

	private String remark; //审核时的备注信息
	private LoginInfo applier; //申请人
	private LoginInfo auditor; //审核操作员
	private Date auditTime; //审核操作时间
	private Date applyTime = new Date();//申请时间
	private int state = STATE_APPLY; //状态

	public String getStateDisplay() {
		switch (state) {
		case STATE_APPLY:
			return "申请状态";
		case STATE_PASS:
			return "审核通过";
		case STATE_REJECT:
			return "审核拒绝";
		default:
			return "错误状态";
		}
	}


	
}
