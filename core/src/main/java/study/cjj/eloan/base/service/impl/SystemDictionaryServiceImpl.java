package study.cjj.eloan.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.domain.SystemDictionary;
import study.cjj.eloan.base.domain.SystemDictionaryItem;
import study.cjj.eloan.base.mapper.SystemDictionaryItemMapper;
import study.cjj.eloan.base.mapper.SystemDictionaryMapper;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.SystemDictionaryQueryObject;
import study.cjj.eloan.base.service.ISystemDictionaryService;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;

	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItemMapper;

	@Override
	public PageResult queryDic(SystemDictionaryQueryObject qo) {
		int totalCount = systemDictionaryMapper.queryForCount(qo);
		if (totalCount > 0) {
			List<SystemDictionary> list = this.systemDictionaryMapper.query(qo);
			return new PageResult(list, totalCount, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void saveOrUpdateDic(SystemDictionary dic) {
		if (dic.getId() != null) {
			systemDictionaryMapper.updateByPrimaryKey(dic);
		} else {
			systemDictionaryMapper.insert(dic);
		}
	}

	@Override
	public PageResult queryDicItem(SystemDictionaryQueryObject qo) {
		int totalCount = systemDictionaryItemMapper.queryForCount(qo);
		if (totalCount > 0) {
			List<SystemDictionaryItem> list = this.systemDictionaryItemMapper.query(qo);
			return new PageResult(list, totalCount, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void delete(Long id) {
		systemDictionaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SystemDictionary> listAllDics() {
		return systemDictionaryMapper.selectAll();
	}

	@Override
	public void saveOrUpdateItem(SystemDictionaryItem item) {
		if(item.getId()==null){
			systemDictionaryItemMapper.insert(item);
		}else{
			systemDictionaryItemMapper.updateByPrimaryKey(item);
		}
	}

	@Override
	public void deleteItem(Long id) {
		systemDictionaryItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<SystemDictionaryItem> listItemByParentSn(String sn) {
		return systemDictionaryItemMapper.listItemByParentSn(sn);
	}


}
