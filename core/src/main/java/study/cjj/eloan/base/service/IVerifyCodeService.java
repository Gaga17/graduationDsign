package study.cjj.eloan.base.service;

public interface IVerifyCodeService {

	public void sendVerifyCode(String phoneNumber);

	public boolean checkVerifyCode(String phoneNumber, String verifyCode);
	
}
