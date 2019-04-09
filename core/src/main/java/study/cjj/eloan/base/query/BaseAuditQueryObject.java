package study.cjj.eloan.base.query;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础审核查询对象
 * @author cjj
 * @date 2019年4月2日
 */
@Getter
@Setter
public class BaseAuditQueryObject extends QueryObject {

	private int status = -1;
	private Date beginDate;// 审核时间
	private Date endDate;

	public Date getEndDate() {
		if (endDate != null) {
			Date ed = DateUtils.addSeconds(DateUtils.addDays(endDate, 1), -1);
			return ed;
		}
		return null;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}