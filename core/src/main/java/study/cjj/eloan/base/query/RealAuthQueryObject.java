package study.cjj.eloan.base.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import study.cjj.eloan.base.util.DateUtil;

@Getter
@Setter
public class RealAuthQueryObject extends QueryObject{

	private int status = -1;
	private Date beginDate;
	private Date endDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBeginDate(Date date) {
		this.beginDate = date;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setEndDate(Date date) {
		this.endDate = date;
	}

	public Date getEndDate() {
		if (endDate != null)
			return DateUtil.endOfDay(endDate);
		return null;
	}

}
