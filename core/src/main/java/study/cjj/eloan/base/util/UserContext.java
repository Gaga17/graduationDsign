package study.cjj.eloan.base.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import study.cjj.eloan.base.domain.LoginInfo;

/**
 * 存放用户上下文信息
 * @author cjj
 * @date 2019年3月20日
 */
public class UserContext {
	public static final String LOGININFO_IN_SESSION="loginInfo";

	private static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static LoginInfo getLoginInfo() {
		return (LoginInfo) getRequest().getSession().getAttribute(LOGININFO_IN_SESSION);
	}

	public static void setLoginInfo(LoginInfo loginInfo) {
		getRequest().getSession().setAttribute(LOGININFO_IN_SESSION, loginInfo);
	}

}
