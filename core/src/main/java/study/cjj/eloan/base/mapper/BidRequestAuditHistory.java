package study.cjj.eloan.base.mapper;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import study.cjj.eloan.base.domain.BaseAuditDomain;

/**
 * 标的审核记录
 * 
 * @author cjj
 * @date 2019年4月22日
 */
@Getter
@Setter
@Alias("BidRequestAuditHistory")
public class BidRequestAuditHistory extends BaseAuditDomain {

	private static final long serialVersionUID = 1L;

	public static final int PUBLISH_AUDIT = 1;// 发标审核
	public static final int FULL_AUDIT1 = 2;// 满标一审
	public static final int FULL_AUDIT2 = 3;// 满标二审

	private Long bidRequestId;
	private int auditType;

	public String getAuditTypeDisplay() {
		switch (auditType) {
		case PUBLISH_AUDIT:
			return "发标审核";
		case FULL_AUDIT1:
			return "满标一审";
		case FULL_AUDIT2:
			return "满标二审";
		default:
			return "错误状态！";
		}
	}

}
