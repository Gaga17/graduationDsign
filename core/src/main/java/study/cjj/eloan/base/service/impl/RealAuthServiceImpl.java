package study.cjj.eloan.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.domain.RealAuth;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.mapper.RealAuthMapper;
import study.cjj.eloan.base.mapper.UserInfoMapper;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.RealAuthQueryObject;
import study.cjj.eloan.base.service.IRealAuthService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.util.BitStatesUtils;
import study.cjj.eloan.base.util.LogicException;
import study.cjj.eloan.base.util.UserContext;

@Service
public class RealAuthServiceImpl implements IRealAuthService {

	@Autowired
	private RealAuthMapper realAuthMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private IUserInfoService userInfoService;
	

	@Override
	public RealAuth getRealAuth(Long id) {
		return this.realAuthMapper.selectByPrimaryKey(id);
	}

	@Override
	public void applyRealAuth(RealAuth realAuth) {
		realAuth.setState(RealAuth.STATE_APPLY);
		realAuth.setApplyTime(new Date());
		realAuth.setApplier(UserContext.getLoginInfo());
		this.realAuthMapper.insert(realAuth);
		UserInfo current = userInfoMapper.selectByPrimaryKey(UserContext.getLoginInfo().getId());
		current.setRealAuthenticationId(realAuth.getId());
		this.userInfoMapper.updateByPrimaryKey(current);
	}

	@Override
	public PageResult query(RealAuthQueryObject qo) {
		int totalCount = realAuthMapper.queryForCount(qo);
		if (totalCount > 0) {
			List<RealAuth> list = this.realAuthMapper.query(qo);
			return new PageResult(list,totalCount,qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());

	}

	@Override
	public void audit(Long id, String remark, int state) {
		RealAuth realAuth=this.realAuthMapper.selectByPrimaryKey(id);
		if(realAuth!=null){
			if(realAuth.getState()==RealAuth.STATE_APPLY){
				realAuth.setRemark(remark);
				realAuth.setAuditor(UserContext.getLoginInfo());
				realAuth.setAuditTime(new Date());
				realAuth.setState(state);
				
				UserInfo userinfo=this.userInfoService.getUserInfo(realAuth.getApplier().getId());
				if(state==RealAuth.STATE_REJECT){
					userinfo.setRealAuthenticationId(null);
				}else{
					userinfo.addState(BitStatesUtils.OP_REAL_AUTH);
				}
				this.realAuthMapper.updateByPrimaryKey(realAuth);
				this.userInfoService.update(userinfo);
				return ;
			}
		}
		throw new LogicException("数据错误!");

	}


}
