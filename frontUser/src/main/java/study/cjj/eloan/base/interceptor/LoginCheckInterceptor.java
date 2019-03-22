package study.cjj.eloan.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import study.cjj.eloan.base.annotation.RequireLogin;
import study.cjj.eloan.base.util.UserContext;

/**
 * 登录检查拦截器
 * @author cjj
 * @date 2019年3月22日
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (handler instanceof HandlerMethod) {
			//获得被拦截的方法
			HandlerMethod hm = (HandlerMethod) handler;
			RequireLogin rl = hm.getMethodAnnotation(RequireLogin.class);
			if (rl != null) {
				if (request.getSession().getAttribute(UserContext.LOGININFO_IN_SESSION) == null) {
					response.sendRedirect("/login.html");
					return false;
				}
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	
}
