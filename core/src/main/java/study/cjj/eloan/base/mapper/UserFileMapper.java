package study.cjj.eloan.base.mapper;

import java.util.List;
import study.cjj.eloan.base.domain.UserFile;

public interface UserFileMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserFile record);

    UserFile selectByPrimaryKey(Long id);

    List<UserFile> selectAll();

    int updateByPrimaryKey(UserFile record);

	List<UserFile> selectUserFileList(Long applierId, boolean noType);
}