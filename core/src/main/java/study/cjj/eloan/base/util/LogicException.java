package study.cjj.eloan.base.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 逻辑异常类
 * @author cjj
 * @date 2019年3月18日
 */
@Getter
@Setter
public class LogicException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 异常错误码,类似于http响应的状态码，比如404代表（页面没有找到）	101代表用户名不存在，102代表密码错误；
	 */
	private Integer errCode;
	
	public LogicException(String message) {
		super(message);
	}
	

	public LogicException(String message,Integer errCode) {
		super(message);
		this.errCode = errCode;
	}


}
