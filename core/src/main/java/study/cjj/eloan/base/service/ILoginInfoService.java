package study.cjj.eloan.base.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import study.cjj.eloan.base.domain.LoginInfo;

public interface ILoginInfoService {
	
	/**
	 * 前台用户注册
	 * @param username	用户名
	 * @param password	密码
	 */
	public void register(String username,String password);

	/**
	 * 检查用户名是否存在
	 * @param username
	 * @param userType
	 * @return
	 */
	public boolean checkUsername(String username,Integer userType);

	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param userType
	 * @param ip
	 * @return
	 */
	public LoginInfo login(String username, String password,Integer userType,String ip);

	/**
	 * 看数据库是否有管理员
	 * @return
	 */
	public boolean hasAdminUser();

	/**
	 * 创建默认管理员
	 */
	public void createDefaultAdmin();

	/**
	 * 获取用户上一次登录时间,倒数第二次
	 * @param username
	 * @param userType
	 * @return
	 */
	public Date getLastLoginTime(String username,Integer userType);

	/**
	 * 视频认证自动补全
	 * @param key
	 * @return
	 */
	public List<Map<String, String>> autoComplateList(String key);
}
