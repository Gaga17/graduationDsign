package study.cjj.eloan.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BidRequestQueryObject extends BaseAuditQueryObject{
	
	private int bidRequestState = -1;//标的的状态
	private Long creatorId;//标的申请人
	private int[] states;//按照多种状态查询标的

}
