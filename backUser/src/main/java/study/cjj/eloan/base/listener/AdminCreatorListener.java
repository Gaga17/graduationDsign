package study.cjj.eloan.base.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import study.cjj.eloan.base.service.ILoginInfoService;

@Component
public class AdminCreatorListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private ILoginInfoService loginInfoService;
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(!loginInfoService.hasAdminUser()){
			loginInfoService.createDefaultAdmin();
		}

	}

}
