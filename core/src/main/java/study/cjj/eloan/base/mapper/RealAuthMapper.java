package study.cjj.eloan.base.mapper;

import java.util.List;
import study.cjj.eloan.base.domain.RealAuth;
import study.cjj.eloan.base.query.RealAuthQueryObject;

public interface RealAuthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RealAuth record);

    RealAuth selectByPrimaryKey(Long id);

    List<RealAuth> selectAll();

    int updateByPrimaryKey(RealAuth record);

	int queryForCount(RealAuthQueryObject qo);

	List<RealAuth> query(RealAuthQueryObject qo);

}