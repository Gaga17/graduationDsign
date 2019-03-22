package study.cjj.eloan.base.service;

import study.cjj.eloan.base.query.IpLogQueryObject;
import study.cjj.eloan.base.query.PageResult;

public interface IIpLogService {

	/**
	 * 分页查询
	 * @param qo
	 * @return
	 */
	public PageResult query(IpLogQueryObject qo);
}
