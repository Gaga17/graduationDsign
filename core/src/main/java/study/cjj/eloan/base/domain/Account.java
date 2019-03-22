package study.cjj.eloan.base.domain;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.cjj.eloan.base.constant.BidConst;

/**
 * 用户的账户信息
 * @author cjj
 * @date 2019年3月19日
 */
@Alias("Account")
@Getter
@Setter
public class Account extends BaseDomain{

	private static final long serialVersionUID = 1L;
	
	private int version;								  //乐观锁版本号
	private String tradePassword;                         //交易密码,默认是初始登录密码
	private BigDecimal usableAmount = BidConst.ZERO;      //可用余额
	private BigDecimal freezedAmount = BidConst.ZERO;	  //冻结金额
	private BigDecimal unReceiveInterest=BidConst.ZERO;	  //账户待收利息
	private BigDecimal unReceivePrincipal=BidConst.ZERO;  //账户待收本金
	private BigDecimal unReturnAmount=BidConst.ZERO;	  //账户待还金额
	private BigDecimal remainBorrowLimit=BidConst.ZERO;	  //账户剩余授信额度
	private BigDecimal borrowLimitAmount; 				  //授信额度
	
	public static Account empty(Long id,String password) {
		Account empty = new Account();
		empty.setId(id);
		empty.setTradePassword(password);	
		empty.setRemainBorrowLimit(BidConst.BORROW_LIMIT);
		empty.setBorrowLimitAmount(BidConst.BORROW_LIMIT);
		return empty;
	}
	
	public BigDecimal getTotalAmount(){
		return usableAmount.add(this.freezedAmount).add(this.unReceivePrincipal);
	}


}
