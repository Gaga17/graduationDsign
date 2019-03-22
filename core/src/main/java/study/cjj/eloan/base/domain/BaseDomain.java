package study.cjj.eloan.base.domain;

import java.io.Serializable;

/**
 * 
 * @author cjj
 * @date 2019年3月8日
 */
public class BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
