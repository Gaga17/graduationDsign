package study.cjj.eloan.base.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典
 * @author cjj
 * @date 2019年3月19日
 */
@Getter
@Setter
@Alias("SystemDictionary")
public class SystemDictionary extends BaseDomain{

	private static final long serialVersionUID = 1L;

	private String sn;			//编码
	private String title;		//名称
	private String intro;		//简介
	
}
