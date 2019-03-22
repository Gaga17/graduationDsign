package study.cjj.eloan.base.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
/**
 * @author cjj
 * @date 2019年3月8日
 */
@Alias("LoginInfo")
@Getter
@Setter
public class LoginInfo extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	public final static int USERTYPE_USER = 1; // 平台用户
	public final static int USERTYPE_MANAGER = 0; // 后台操作员
	
	public final static int STATE_NORMAL = 0; // 普通用户状态
	public final static int STATE_LOCK = 1; // 用户锁定状态
	public final static int STATE_LOGOFF = -1; // 用户注销状态

	private String username; // 用户名
	private String password; // 密码
	private Integer userType;	//用户类型
	private Integer state = STATE_NORMAL;	//用户状态
	private boolean admin;	//是否管理员
}
