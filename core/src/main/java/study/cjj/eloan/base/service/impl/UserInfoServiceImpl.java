package study.cjj.eloan.base.service.impl;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.domain.LoginInfo;
import study.cjj.eloan.base.domain.MailVerify;
import study.cjj.eloan.base.domain.MyMailMessage;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.mapper.MailVerifyMapper;
import study.cjj.eloan.base.mapper.UserInfoMapper;
import study.cjj.eloan.base.service.IMailService;
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
	
	@Autowired
	private IMailService mailService;
	
	@Autowired
	private MailVerifyMapper mailVerifyMapper;

	@Value("${mail.verifyUrl}")
	private String verifyUrl;
	
	
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


	public void sendBindEmail(String email) {
		LoginInfo current = UserContext.getLoginInfo();
		UserInfo ui = this.userInfoMapper.selectByPrimaryKey(current.getId());
		if (!ui.getIsBindEmail()) {
			MyMailMessage mail = new MyMailMessage();
			mail.setTitle("绑定邮箱验证邮件");
			mail.setToAddress(email);

			MailVerify mv = MailVerify.create(current.getId(), email,3);
			StringBuilder html = new StringBuilder(100).append("<html><body><h1>请点击连接：<a href='").append(verifyUrl).append("?checkCode=")
					.append(mv.getRandomCode()).append("'>点击这里</a>，完成邮箱绑定，有效期至")
					.append(DateFormat.getDateInstance().format(mv.getDeadline())).append("！</h1></body></html>");
			mail.setContent(html.toString());
			//=====调戏========
//			mail.setTitle("每日一笑");
//			mail.setContent("送你一份有屎以来，粪量最重的礼物，你一定会大吃一斤，还要多多饱含，如觉粪量不够还请自便！");
			//=============
			this.mailVerifyMapper.insert(mv);
			this.mailService.sendMail(mail);
		
		
		}

	}
	
	@Override
	public void bindMailVerify(String checkCode) {
		if (StringUtils.isNotBlank(checkCode)) {
			MailVerify mv = this.mailVerifyMapper.selectByCheckCode(checkCode);
			if (mv != null) {
				Date now = new Date();
				if (now.before(mv.getDeadline())) {
					UserInfo ui = this.userInfoMapper.selectByPrimaryKey(mv.getUserinfoId());
					if (!ui.getIsBindEmail()) {
						ui.addState(BitStatesUtils.OP_BIND_EMAIL);
						ui.setEmail(mv.getEmail());
						this.update(ui);
                    return ;
					}
				}
			}
		}
		throw new LogicException("验证码无效！");
	}


	@Override
	public void updateBasicInfo(UserInfo userinfo) {
		Long id = UserContext.getLoginInfo().getId();
		UserInfo old = this.userInfoMapper.selectByPrimaryKey(id);

		old.setEducationBackground(userinfo.getEducationBackground());
		old.setHouseCondition(userinfo.getHouseCondition());
		old.setIncomeGrade(userinfo.getIncomeGrade());
		old.setKidCount(userinfo.getKidCount());
		old.setMarriage(userinfo.getMarriage());

		old.addState(BitStatesUtils.OP_BASIC_INFO);
		this.update(old);
		
	}



}
