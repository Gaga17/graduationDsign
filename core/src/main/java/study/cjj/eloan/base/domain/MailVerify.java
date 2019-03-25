package study.cjj.eloan.base.domain;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import study.cjj.eloan.base.util.DateUtil;

@Getter
@Setter
public class MailVerify extends BaseDomain {

	private static final long serialVersionUID = 1L;

	private Long userinfoId;
	private Date deadline;
	private String randomCode;
	private String email;

	public static MailVerify create(Long userinfoId, String email, int validateDay) {
		MailVerify mv = new MailVerify();
		mv.setRandomCode(UUID.randomUUID().toString());
		mv.setUserinfoId(userinfoId);
		mv.setEmail(email);
		mv.setDeadline(DateUtil.addDay(new Date(), validateDay));
		return mv;
	}

}
