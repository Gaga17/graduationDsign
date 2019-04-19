package study.cjj.eloan.base.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import study.cjj.eloan.base.constant.BidConst;

/**
 * 借款标
 * @author cjj
 * @date 2019年4月19日
 */
@Getter
@Setter
public class BidRequest extends BaseDomain{

	private static final long serialVersionUID = -9148437073259797244L;

	private int version;// 版本
	private int returnType = BidConst.RETURN_TYPE_MONTH_INTEREST_PRINCIPAL; // 还款方式,按月分期
	private int bidRequestType = BidConst.BIDREQUEST_TYPE_NORMAL; // 标的类型
	private int bidRequestState = BidConst.BIDREQUEST_STATE_PUBLISH_PENDING; // 这个标的状态

	private BigDecimal bidRequestAmount = BidConst.ZERO; // 借款金额
	private BigDecimal currentRate = BidConst.ZERO; // 借款利率
	private BigDecimal minBidAmount = BidConst.SMALLEST_BID_AMOUNT;// 最小投标
	private int monthes2Return = 1; // 借款期限(月份数1~12)
	private int bidCount = 0; // 已有投标数量
	private BigDecimal totalRewardAmount = BidConst.ZERO; // 总报酬金额
	private BigDecimal currentSum = BidConst.ZERO; // 当前已经借到多少钱
	private String title = ""; // 借款标题
	private String description = ""; // 借款描述
	private String note = ""; // 风控评审意见
	private Date disableDate = new Date(); // 招标到期时间 (页面数据是招标天数，无效日期=招标天数+当前日期)
	private int disableDays = 0; // 标的有效天数
	private LoginInfo createUser; // 借款人
	private List<Bid> bids = new ArrayList<Bid>(); // 这个借款已经有的标(已经收到的投标)
	private Date applyTime;// 申请时间
	private Date publishTime;// 发布时间

}
