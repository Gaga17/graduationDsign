package study.cjj.eloan.base.query;

import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 视频认证审核查询
 * @author cjj
 * @date 2019年4月2日
 */
@Getter
@Setter
public class VedioAuthQueryObject extends BaseAuditQueryObject{

	private String keyWord;


	public String getKeyWord(){
		return StringUtils.hasLength(keyWord)?keyWord:null;
	}


}
