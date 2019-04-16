package study.cjj.eloan.base.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

/**
 * 视频认证审核,视频认证不需要申请,后台直接联系用户并审核就可以了
 * @author cjj
 * @date 2019年4月2日
 */
@Getter
@Setter
@Alias("VedioAuth")
public class VedioAuth extends BaseAuditDomain{

	private static final long serialVersionUID = 1L; 

}
