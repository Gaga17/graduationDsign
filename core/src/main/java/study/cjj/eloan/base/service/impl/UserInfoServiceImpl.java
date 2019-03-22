package study.cjj.eloan.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.mapper.UserInfoMapper;
import study.cjj.eloan.base.service.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	
	public UserInfo getUserInfo(Long id) {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
		return userInfo;
	}

}
