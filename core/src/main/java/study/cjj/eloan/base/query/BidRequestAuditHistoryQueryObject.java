package study.cjj.eloan.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidRequestAuditHistoryQueryObject extends BaseAuditQueryObject{
	
	private int auditType = -1;
	
	private Long bidRequestId;
	
}
