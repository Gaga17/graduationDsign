package study.cjj.eloan.base.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

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

	public String getJsonString(){
		Map<String,Object> json = new HashMap<>();
		json.put("id", id);
		json.put("applier", getApplier().getUsername());
		json.put("fileType", fileType.getTitle());
		json.put("image", file);
		return JSONObject.toJSONString(json);
	}
}
