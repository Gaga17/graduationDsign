package study.cjj.eloan.base.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import study.cjj.eloan.base.constant.BidConst;

/**
 * 投标对象
 * @author cjj
 * @date 2019年4月19日
 */
@Getter
@Setter
public class Bid extends BaseDomain{

	private static final long serialVersionUID = -7190421832579160878L;

	private BigDecimal actualRate = BidConst.ZERO; // 实际年利率(应该是等同于标的的利率)
	private BigDecimal availableAmount = BidConst.ZERO; // 投标有效金额(就是投标金额)

	private Long bidRequestId; // 来自于哪个借款标
	private String bidRequestTitle;//标的title
	private LoginInfo bidUser; // 投标人id(loginInfo)
	private Date bidTime;//投标时间
	
	private int bidRequestState;

}
