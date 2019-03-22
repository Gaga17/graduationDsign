package study.cjj.eloan.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.mapper.UserInfoMapper;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.service.IVerifyCodeService;
import study.cjj.eloan.base.util.BitStatesUtils;
import study.cjj.eloan.base.util.LogicException;
import study.cjj.eloan.base.util.UserContext;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private IVerifyCodeService verifyCodeService;
	
	
	public UserInfo getUserInfo(Long id) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
		return userInfo;
	}


	public void bindPhone(String phoneNumber, String verifyCode) {
		if (this.verifyCodeService.checkVerifyCode(phoneNumber, verifyCode)) {
			LoginInfo current = UserContext.getLoginInfo();
			UserInfo ui = this.userInfoMapper.selectByPrimaryKey(current.getId());
			if (!ui.getIsBindPhone()) {
				ui.addState(BitStatesUtils.OP_BIND_PHONE);
				ui.setPhoneNumber(phoneNumber);
				this.update(ui);
			}
		} else {
			throw new LogicException("验证码错误或者过期!");
		}
	}
	
	private void update(UserInfo ui) {
		int ret = this.userInfoMapper.updateByPrimaryKey(ui);
		if (ret == 0) {
			System.out.println("userinfo对象更新失败");
			throw new LogicException("系统正忙，请稍后再试!如果多次看到该提示，请联系平台管理员！");
		}
	}


}
