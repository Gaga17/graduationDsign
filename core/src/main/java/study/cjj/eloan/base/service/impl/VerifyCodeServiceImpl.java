package study.cjj.eloan.base.service.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.service.IVerifyCodeService;
import study.cjj.eloan.base.util.DateUtil;
import study.cjj.eloan.base.util.LogicException;
import study.cjj.eloan.base.util.StringUtil;
import study.cjj.eloan.base.util.UserContext;
import study.cjj.eloan.base.vo.VerifyCodeVO;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService{

	@Value("${sms.username}")
	private String username;
	@Value("${sms.apikey}")
	private String apikey;
	@Value("${sms.url}")
	private String url;
	
	public void sendVerifyCode(String phoneNumber) {
		if (this.checkCanSendVerifyCode()) {
			//================
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(url);

			client.getParams().setContentCharset("GBK");
			method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=GBK");
			String mobile_code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
			String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");
			NameValuePair[] data = { // 提交短信
					new NameValuePair("account", username), // 查看用户名
															// 登录用户中心->验证码通知短信>产品总览->API接口信息->APIID
					new NameValuePair("password", StringUtil.MD5Encode(apikey)), // 查看密码
															// 登录用户中心->验证码通知短信>产品总览->API接口信息->APIKEY
					// new NameValuePair("password",
					// util.StringUtil.MD5Encode("密码")),
					new NameValuePair("mobile", phoneNumber), new NameValuePair("content", content), };
			method.setRequestBody(data);
			try {
				client.executeMethod(method);
				String SubmitResult = method.getResponseBodyAsString();
				// System.out.println(SubmitResult);
				Document doc = DocumentHelper.parseText(SubmitResult);
				Element root = doc.getRootElement();
				String code = root.elementText("code");
				String msg = root.elementText("msg");
				String smsid = root.elementText("smsid");
				System.out.println(code);
				System.out.println(msg);
				System.out.println(smsid);
				if ("2".equals(code)) {
					System.out.println("短信提交成功");
					VerifyCodeVO verifyCodeVO = new VerifyCodeVO(phoneNumber,mobile_code,new Date());
					UserContext.setVerifyCodeVO(verifyCodeVO);
				}else{
					throw new LogicException("请输入正确手机号!");
				}
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			//=======================
		}
	}
	
	private boolean checkCanSendVerifyCode() {
		Date now = new Date();
		VerifyCodeVO vc = UserContext.getVerifyCodeVO();
		if (vc == null) {
			return true;
		} else {
			Date lastTime = vc.getSendTime();
			if (DateUtil.getBetweenSecond(lastTime, now) >= 120) {
				return true;
			}
		}
		return false;
	}

	
	public boolean checkVerifyCode(String phoneNumber, String code) {
		VerifyCodeVO vc = UserContext.getVerifyCodeVO();
		if (vc != null) {
			if (vc.getPhoneNumber().equals(phoneNumber) && // 判断电话号码是否相同
					vc.getCode().equals(code) && // 判断验证码是否相同
					DateUtil.getBetweenSecond(vc.getSendTime(), new Date()) <= 60 * 60 * 3) {// 判断在3分钟之内
				return true;
			}
		}
		return false;
	}




}
