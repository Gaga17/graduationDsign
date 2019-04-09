package study.cjj.eloan.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.domain.VedioAuth;
import study.cjj.eloan.base.mapper.VedioAuthMapper;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.VedioAuthQueryObject;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.service.IVedioAuthService;
import study.cjj.eloan.base.util.BitStatesUtils;
import study.cjj.eloan.base.util.UserContext;

@Service
public class VedioAuthServiceImpl implements IVedioAuthService {

	@Autowired
	private VedioAuthMapper vedioAuthMapper;
	
	@Autowired
	private IUserInfoService userInfoService;

	@Override
	public VedioAuth getVedioAuth(Long vedioAuthenticationId) {
		return vedioAuthMapper.selectByPrimaryKey(vedioAuthenticationId);
	}

	@Override
	public PageResult getVedioAuthBy(VedioAuthQueryObject queryObject) {
		Integer total = this.vedioAuthMapper.getVedioAuthTotalCount(queryObject);
		if (total > 0) {
			List<VedioAuth> ps = this.vedioAuthMapper.getVedioAuthBy(queryObject);
			return new PageResult(ps, total, queryObject.getCurrentPage(), queryObject.getPageSize());
		}
		return PageResult.empty(queryObject.getPageSize());
	}

	@Override
	public void audit(int state, String remark, Long loginInfoValue) {
		if (loginInfoValue != null) {
			UserInfo ui = this.userInfoService.getUserInfo(loginInfoValue);
			if (ui != null && !ui.getIsVedioAuth()) {
				LoginInfo li = new LoginInfo();
				li.setId(ui.getId());
				
				VedioAuth va = new VedioAuth();
				va.setApplier(li);
				va.setApplyTime(new Date());
				va.setAuditor(UserContext.getLoginInfo());
				va.setAuditTime(new Date());
				va.setState(state);
				va.setRemark(remark);
				
				if(state==VedioAuth.STATE_PASS){
					ui.addState(BitStatesUtils.OP_VEDIO_AUTH);
					this.userInfoService.update(ui);
				}
				this.vedioAuthMapper.insert(va);
			}
		}

	}

}
