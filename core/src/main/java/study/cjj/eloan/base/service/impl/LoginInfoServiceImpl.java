package study.cjj.eloan.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.constant.BidConst;
import study.cjj.eloan.base.domain.Account;
import study.cjj.eloan.base.domain.IpLog;
import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.mapper.AccountMapper;
import study.cjj.eloan.base.mapper.IpLogMapper;
import study.cjj.eloan.base.mapper.LoginInfoMapper;
import study.cjj.eloan.base.mapper.UserInfoMapper;
import study.cjj.eloan.base.service.ILoginInfoService;
import study.cjj.eloan.base.util.LogicException;
import study.cjj.eloan.base.util.MD5;
import study.cjj.eloan.base.util.UserContext;

@Service
public class LoginInfoServiceImpl implements ILoginInfoService {

	@Autowired
	private LoginInfoMapper loginInfoMapper;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private IpLogMapper ipLogMapper;

	public void register(String username, String password) {
		int count = loginInfoMapper.selectCountByUserName(username,LoginInfo.USERTYPE_USER);
		if (count <= 0) {
			LoginInfo loginInfo = new LoginInfo();
			loginInfo.setUsername(username);
			loginInfo.setPassword(MD5.encode(password));
			loginInfo.setUserType(LoginInfo.USERTYPE_USER);
			loginInfoMapper.insert(loginInfo);
			
			//创建账户信息
			Account account  = Account.empty(loginInfo.getId(),MD5.encode(password));
			accountMapper.insert(account);
			//创建用户信息
			UserInfo userInfo = UserInfo.empty(loginInfo.getId());	
			userInfoMapper.insert(userInfo);
		}else{
			throw new LogicException("用户名已存在!");
		}
	}

	public boolean checkUsername(String username,Integer userType) {
		int selectCountByUserName = loginInfoMapper.selectCountByUserName(username,userType);
		if(selectCountByUserName<=0){
			return true;
		}else{
			return false;
		}
	}

	public LoginInfo login(String username, String password,Integer userType,String ip) {
		IpLog ipLog = new IpLog(ip,IpLog.LOGIN_FAIL,username,null,userType);
		ipLog.setLoginTime(new Date());
		password = MD5.encode(password);
		LoginInfo loginInfo = loginInfoMapper.getLoginInfoByUserNameAndPassword(username,password,userType);
		if(loginInfo!=null){
			UserContext.setLoginInfo(loginInfo);
			ipLog.setLoginState(IpLog.LOGIN_SUCCESS);
			ipLog.setUsername(loginInfo.getUsername());
			ipLog.setLoginInfoId(loginInfo.getId());
		}
		ipLogMapper.insert(ipLog);
		return loginInfo;
	}

	public boolean hasAdminUser() {
		int count = this.loginInfoMapper.selectUserTypeCount(LoginInfo.USERTYPE_MANAGER);
		return count > 0;

	}

	public void createDefaultAdmin() {
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setUsername(BidConst.DEFALUT_ADMIN);
		loginInfo.setPassword(MD5.encode(BidConst.DEFALUT_ADMIN_PASSWORD));
		loginInfo.setState(LoginInfo.STATE_NORMAL);
		loginInfo.setUserType(LoginInfo.USERTYPE_MANAGER);
		loginInfo.setAdmin(true);
		this.loginInfoMapper.insert(loginInfo);
	}

	public Date getLastLoginTime(String username,Integer userType) {
		return loginInfoMapper.getLastLoginTime(username,userType);
	}

	@Override
	public List<Map<String, String>> autoComplateList(String name) {
		
		return this.loginInfoMapper.autoComplateList(name);
	}

}
