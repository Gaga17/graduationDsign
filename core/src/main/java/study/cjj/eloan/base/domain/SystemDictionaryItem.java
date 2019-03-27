package study.cjj.eloan.base.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据字典明细
 * @author cjj
 * @date 2019年3月19日
 */
@Getter
@Setter
@Alias("SystemDictionaryItem")
public class SystemDictionaryItem extends BaseDomain{

	private static final long serialVersionUID = 1L;

	private Long parentId; // 系统目录
	private String title; // 名称
	private String tvalue; // 值
	private Integer sequence; // 序列(排序)
	private String intro; // 说明

	public String getJsonString() {
		Map<String, Object> m = new HashMap<>();
		m.put("id", id);
		m.put("parentId", parentId);
		m.put("title", title);
		m.put("sequence", sequence);
		m.put("intro", intro);
		return JSONObject.toJSONString(m);
	}

}
