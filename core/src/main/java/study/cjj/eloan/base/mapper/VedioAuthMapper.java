package study.cjj.eloan.base.mapper;

import java.util.List;

import study.cjj.eloan.base.domain.VedioAuth;
import study.cjj.eloan.base.query.VedioAuthQueryObject;

public interface VedioAuthMapper {
    int deleteByPrimaryKey(Long id);

    int insert(VedioAuth record);

    VedioAuth selectByPrimaryKey(Long id);

    List<VedioAuth> selectAll();

    int updateByPrimaryKey(VedioAuth record);

	List<VedioAuth> getVedioAuthBy(VedioAuthQueryObject queryObject);

	int getVedioAuthTotalCount(VedioAuthQueryObject queryObject);
}