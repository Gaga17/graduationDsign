package study.cjj.eloan.base.service;

import java.util.List;

import study.cjj.eloan.base.domain.UserFile;

public interface IUserFileService {

	List<UserFile> selectUserFileList(Long applierId, boolean noType);

	void applyUserFile(String relativeFile);

	void updateFileTypes(Long[] id, Long[] fileType);
	
}
