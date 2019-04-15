package study.cjj.eloan.base.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("UserFile")
@Getter
@Setter
public class UserFile extends BaseAuditDomain{

	private static final long serialVersionUID = 1L;
	private String file;	//文件名称
	private SystemDictionaryItem fileType;	//文件类别
	private int score;		//此文件对应信用得分

}
