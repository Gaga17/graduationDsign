package study.cjj.eloan.base.mapper;

import java.util.List;

import study.cjj.eloan.base.domain.SystemDictionary;
import study.cjj.eloan.base.query.SystemDictionaryQueryObject;

public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    int updateByPrimaryKey(SystemDictionary record);

	int queryForCount(SystemDictionaryQueryObject qo);

	List<SystemDictionary> query(SystemDictionaryQueryObject qo);
	
}