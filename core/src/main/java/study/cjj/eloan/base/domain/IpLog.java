package study.cjj.eloan.base.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
@Alias("IpLog")
@Getter
@Setter
public class IpLog extends BaseDomain{

	private static final long serialVersionUID = 1L;

	public static final int LOGIN_SUCCESS = 1; //登陆成功
	public static final int LOGIN_FAIL = 0; //登陆失败

	private String ip; //ip地址
	private int loginState = LOGIN_SUCCESS; //登陆情况(成功/失败)
	private String username = ""; //登录用户名
	private Long loginInfoId; //登陆用户id
	private int loginType; //登录用户类型(等同于LoginInfo中的userType)
	private Date loginTime=new Date();	//登录的时间

	public IpLog() {
	}

	public IpLog(String ip, int loginState, String username, Long loginInfoId, int loginType) {
		this.ip = ip;
		this.loginState = loginState;
		this.username = username;
		this.loginInfoId = loginInfoId;
		this.loginType = loginType;
	}

	public String getStateDisplay(){
		return loginState == LOGIN_SUCCESS ? "登陆成功" : "登陆失败" ;
	}
	
	public String getUserTypeDisplay(){
		return loginType == LoginInfo.USERTYPE_USER ? "前台用户" : "后台管理员" ;
	}
	
}
