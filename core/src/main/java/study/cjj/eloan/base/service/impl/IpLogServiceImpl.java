package study.cjj.eloan.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.domain.IpLog;
import study.cjj.eloan.base.mapper.IpLogMapper;
import study.cjj.eloan.base.query.IpLogQueryObject;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.service.IIpLogService;

@Service
public class IpLogServiceImpl implements IIpLogService {

	@Autowired
	private IpLogMapper ipLogMapper;

	@Override
	public PageResult query(IpLogQueryObject qo) {
		int totalCount = ipLogMapper.queryForCount(qo);
		if (totalCount > 0) {
			List<IpLog> list = this.ipLogMapper.query(qo);
			return new PageResult(list, totalCount,qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

}
