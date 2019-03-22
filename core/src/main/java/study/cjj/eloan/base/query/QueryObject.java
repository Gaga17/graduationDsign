package study.cjj.eloan.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
	
	private int pageSize = 10;		//每页的数据量
	private int currentPage = 1;	//当前页

	public int getStart() {
		return (currentPage - 1) * pageSize;
	}

}
