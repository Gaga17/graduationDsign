package study.cjj.eloan.base.service;

import java.util.List;

import study.cjj.eloan.base.domain.SystemDictionary;
import study.cjj.eloan.base.domain.SystemDictionaryItem;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.SystemDictionaryQueryObject;

public interface ISystemDictionaryService {

	/**
	 * 系统字典分页查询
	 * @param qo
	 * @return
	 */
	public PageResult queryDic(SystemDictionaryQueryObject qo);

	/**
	 * 系统字典更新或保存
	 * @param dic
	 */
	public void saveOrUpdateDic(SystemDictionary dic);

	/**
	 * 系统字典明细分页查询
	 * @param qo
	 * @return
	 */
	public PageResult queryDicItem(SystemDictionaryQueryObject qo);

	/**
	 * 系统字典删除
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 查找所有系统字典
	 * @return
	 */
	public List<SystemDictionary> listAllDics();

	/**
	 * 系统字典明细更新
	 * @param item
	 */
	public void saveOrUpdateItem(SystemDictionaryItem item);

	/**
	 * 系统字典明细修改
	 * @param id
	 */
	public void deleteItem(Long id);

	/**
	 * 根据sn查看对应字典的所有明细
	 * @param sn
	 * @return
	 */
	public List<SystemDictionaryItem> listItemByParentSn(String sn);
	
}
