package study.cjj.eloan.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.cjj.eloan.base.domain.RealAuth;
import study.cjj.eloan.base.domain.SystemDictionaryItem;
import study.cjj.eloan.base.domain.UserFile;
import study.cjj.eloan.base.domain.UserInfo;
import study.cjj.eloan.base.mapper.UserFileMapper;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.UserFileQueryObject;
import study.cjj.eloan.base.service.IUserFileService;
import study.cjj.eloan.base.service.IUserInfoService;
import study.cjj.eloan.base.util.UserContext;

@Service
public class UserFileServiceImpl implements IUserFileService{

	@Autowired
	private UserFileMapper userFileMapper;
	
	@Autowired
	private IUserInfoService userInfoService;

	@Override
	public List<UserFile> selectUserFileList(Long applierId, boolean noType) {
		return this.userFileMapper.selectUserFileList(applierId, noType);
	}

	@Override
	public void applyUserFile(String relativeFile) {
		UserFile userFile = new UserFile();
		userFile.setFile(relativeFile);
		userFile.setState(RealAuth.STATE_APPLY);
		userFile.setApplyTime(new Date());
		userFile.setApplier(UserContext.getLoginInfo());
		this.userFileMapper.insert(userFile);

	}

	@Override
	public void updateFileTypes(Long[] id, Long[] fileType) {
		for(int i=0;i<id.length;i++){
			Long ufid=id[i];
			UserFile uf=this.userFileMapper.selectByPrimaryKey(ufid);
			if(uf!=null){
				SystemDictionaryItem item=new SystemDictionaryItem();
				item.setId(fileType[i]);
				uf.setFileType(item);
				this.userFileMapper.updateByPrimaryKey(uf);
			}
		}

	}

	@Override
	public PageResult getUserFileBy(UserFileQueryObject qo) {
		Integer total = this.userFileMapper.queryForCount(qo);
		if (total > 0) {
			List<UserFile> ps = this.userFileMapper.query(qo);
			return new PageResult(ps,total,qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void audit(int state, String remark, Long id, int score) {
		UserFile ra = userFileMapper.selectByPrimaryKey(id);
		if (ra != null && ra.getState() == UserFile.STATE_APPLY) {
			ra.setState(state);
			ra.setRemark(remark);
			ra.setScore(score);
			ra.setAuditTime(new Date());
			ra.setAuditor(UserContext.getLoginInfo());
			if (state == RealAuth.STATE_PASS) {
				UserInfo applyUser = this.userInfoService.getUserInfo(ra.getApplier().getId());
				applyUser.setAuthScore(applyUser.getAuthScore() + score);
				this.userInfoService.update(applyUser);
			}
			this.userFileMapper.updateByPrimaryKey(ra);
		}

	}

}
