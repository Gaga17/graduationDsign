package study.cjj.eloan.base.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import study.cjj.eloan.base.util.SystemDictionaryUtil;

public class AddGlobalUtilsInterceptor  extends HandlerInterceptorAdapter{

	@Autowired
	private ApplicationContext ctx;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Map<String, Object> utils = new HashMap<String, Object>();
		Assert.notNull(ctx);
		utils.put("_SDUtil", ctx.getBean(SystemDictionaryUtil.class));
		if (modelAndView != null)
			modelAndView.addObject("_GUtils", utils);
		super.postHandle(request, response, handler, modelAndView);
	}

}
