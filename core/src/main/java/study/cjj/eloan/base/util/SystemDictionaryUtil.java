package study.cjj.eloan.base.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import study.cjj.eloan.base.domain.SystemDictionaryItem;
import study.cjj.eloan.base.service.ISystemDictionaryService;

/**
 * 系统字典工具类
 * @author cjj
 * @date 2019年3月28日
 */
@Component
public class SystemDictionaryUtil {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	/**
	 * 加载某个sn字典下的明细
	 * @param sn
	 * @return
	 */
	public List<SystemDictionaryItem> loadItems(String sn) {
		return systemDictionaryService.listItemByParentSn(sn);
	}

}
