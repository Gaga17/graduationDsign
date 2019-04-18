package study.cjj.eloan.base.service;

import java.util.List;

import study.cjj.eloan.base.domain.UserFile;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.UserFileQueryObject;

public interface IUserFileService {

	List<UserFile> selectUserFileList(Long applierId, boolean noType);

	void applyUserFile(String relativeFile);

	void updateFileTypes(Long[] id, Long[] fileType);

	PageResult getUserFileBy(UserFileQueryObject qo);

	void audit(int state, String remark, Long id, int score);
	
}
