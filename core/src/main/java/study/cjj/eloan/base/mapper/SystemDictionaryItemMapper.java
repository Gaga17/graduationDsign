package study.cjj.eloan.base.mapper;

import java.util.List;

import study.cjj.eloan.base.domain.SystemDictionaryItem;
import study.cjj.eloan.base.query.SystemDictionaryQueryObject;


public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);

	int queryForCount(SystemDictionaryQueryObject qo);

	List<SystemDictionaryItem> query(SystemDictionaryQueryObject qo);

	List<SystemDictionaryItem> listItemByParentSn(String sn);
}