package study.cjj.eloan.base.mapper;

import java.util.List;

import study.cjj.eloan.base.domain.RealAuth;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.query.RealAuthQueryObject;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);


}