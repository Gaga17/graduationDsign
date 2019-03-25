package study.cjj.eloan.base.service;

import study.cjj.eloan.base.domain.MyMailMessage;

public interface IMailService {

	/**
	 * 发送邮件
	 * @param mail
	 */
	public void sendMail(MyMailMessage mail);

}
