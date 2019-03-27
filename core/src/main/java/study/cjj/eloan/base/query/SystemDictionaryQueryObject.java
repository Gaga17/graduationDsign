package study.cjj.eloan.base.query;

import org.apache.commons.lang.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemDictionaryQueryObject extends QueryObject{
	
	private String keywords;
	
	private String parentId;
	
	public String getKeywords() {
		return StringUtils.isNotBlank(keywords) ? keywords : null;
	}


}
