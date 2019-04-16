package study.cjj.eloan.base.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import study.cjj.eloan.base.domain.LoginInfo;

public interface LoginInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LoginInfo record);

    LoginInfo selectByPrimaryKey(Long id);

    List<LoginInfo> selectAll();

    int updateByPrimaryKey(LoginInfo record);

	int selectCountByUserName(@Param("username")String username,@Param("userType")Integer userType);

	LoginInfo getLoginInfoByUserNameAndPassword(@Param("username")String username, @Param("password")String password,@Param("userType")Integer userType);

	int selectUserTypeCount(int usertypeManager);

	Date getLastLoginTime(@Param("username")String username,@Param("userType")Integer userType);

	List<Map<String, String>> autoComplateList(String name);
}