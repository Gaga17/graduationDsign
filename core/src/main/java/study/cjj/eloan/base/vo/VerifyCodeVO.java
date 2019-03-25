package study.cjj.eloan.base.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装了验证码,手机号,发送时间
 * @author cjj
 * @date 2019年3月22日
 */
@Getter
@Setter
public class VerifyCodeVO {

	private String phoneNumber;
	private String code;
	private Date sendTime;
	
	public VerifyCodeVO(String phoneNumber, String code, Date sendTime) {
		this.phoneNumber = phoneNumber;
		this.code = code;
		this.sendTime = sendTime;
	}
	public VerifyCodeVO() {
	}
}
