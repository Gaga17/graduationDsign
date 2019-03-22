package study.cjj.eloan.base.mapper;

import java.util.List;

import study.cjj.eloan.base.domain.SystemDictionaryItem;


public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);
}