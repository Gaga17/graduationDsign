package study.cjj.eloan.base.domain;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("RealAuth")
public class RealAuth extends BaseAuditDomain{

	private static final long serialVersionUID = 1L;
	
	public static final int SEX_MALE = 0; //性别男
	public static final int SEX_FEMALE = 1;//性别女

	private String realName; //真实姓名
	private Integer sex = 0;
	private String birthDate; //生日
	private String idNumber = ""; //身份证号码
	private String address = ""; //身份证地址	
	private String image1;	//身份证正面照
	private String image2;	//身份证背面照	
	public String getSexDisplay(){
		return sex==0?"男":"女";
	}
	
	public String getJsonString() {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("id", id);
		m.put("realName", realName);
		m.put("sex", this.getSexDisplay());
		m.put("birthDate", birthDate);
		m.put("idNumber", idNumber);
		m.put("address", address);
		m.put("image1", image1);
		m.put("image2", image2);
		return JSONObject.toJSONString(m);
	}



}
