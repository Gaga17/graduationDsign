package study.cjj.eloan.base.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 包装json结果
 * @author cjj
 * @date 2019年3月18日
 */
@Getter
@Setter
public class ResultJSON {
	
	private boolean success = false;
	
	private Object msg;
	
	private Integer errorCode;

	public ResultJSON() {
	}

	public ResultJSON(boolean success){
		this.success = success;
	}
	
	public ResultJSON(boolean success, Object msg) {
		this.success = success;
		this.msg = msg;
	}
	
}
