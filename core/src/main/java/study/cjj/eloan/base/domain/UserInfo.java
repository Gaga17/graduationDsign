package study.cjj.eloan.base.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import study.cjj.eloan.base.util.BitStatesUtils;

/**
 * 前台用户信息
 * 
 * @author cjj
 * @date 2019年3月19日
 */
@Getter
@Setter
@Alias("UserInfo")
public class UserInfo extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private int version;// 版本号(用作乐观锁)
	private Long bitState = 0L; // 位状态,看有没开通会员,身份验证等.Long能表示64种位状态;
	private String realName; // 对应实名认证中的真实姓名
	private String idNumber; // 对应实名认证中的身份证号
	private String phoneNumber = ""; // 手机号

	// ====================== 会员基本资料 ===================
	private SystemDictionaryItem incomeGrade; // 月收入
	private SystemDictionaryItem marriage; // 婚姻情况
	private SystemDictionaryItem kidCount; // 子女情况
	private SystemDictionaryItem educationBackground; // 学历
	private SystemDictionaryItem houseCondition; // 住房条件

	public static UserInfo empty(Long id) {
		UserInfo empty = new UserInfo();
		empty.setId(id);
		return empty;

	}

	// 判断是否已经绑定了手机
	public boolean getIsBindPhone() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_PHONE);
	}

	// 判断是否已经绑定看了银行卡
	public boolean getIsBindBank() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_HAS_BIND_BANK);
	}

	// 判断是否已经绑定了邮箱
	public boolean getIsBindEmail() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_EMAIL);
	}

	// 添加绑定的状态码
	public void addState(Long state) {
		bitState = BitStatesUtils.addState(this.bitState, state);
	}

	// 移除状态码
	public void removeState(Long state) {
		bitState = BitStatesUtils.removeState(this.bitState, state);
	}

	// 判断用户是否已经填写了基本资料
	public boolean getIsBasicInfo() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BASIC_INFO);
	}

	// 判断用户是否已经实名认证
	public boolean getIsRealAuth() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_REAL_AUTH);
	}

	// 判断用户是否已经视频认证
	public boolean getIsVedioAuth() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_VEDIO_AUTH);
	}

	// 判断用户是否已经有一个借款在审核流程中
	public boolean getHasBidRequestInProcess() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
	}

	// 判断用户是否已经有一个提现在审核流程中
	public boolean getHasWithdrawInProcess() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_HAS_WITHDRAW_PROCESS);
	}

}
