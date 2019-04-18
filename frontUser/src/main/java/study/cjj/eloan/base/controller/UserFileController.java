package study.cjj.eloan.base.controller;

import java.io.Writer;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import study.cjj.eloan.base.domain.UserFile;
import study.cjj.eloan.base.service.ISystemDictionaryService;
import study.cjj.eloan.base.service.IUserFileService;
import study.cjj.eloan.base.util.UploadUtil;
import study.cjj.eloan.base.util.UserContext;

@Controller
public class UserFileController extends BaseController {

	@Autowired
	private IUserFileService userFileService;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	/**
	 * 用户风控文件
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("userFile")
	public String userFileList(Model model, HttpServletRequest request) {
		List<UserFile> noTypeUserFiles = this.userFileService.selectUserFileList(UserContext.getLoginInfo().getId(),
				true);
		if (noTypeUserFiles != null && noTypeUserFiles.size() <= 0) {
			noTypeUserFiles = this.userFileService.selectUserFileList(UserContext.getLoginInfo().getId(), false);
			model.addAttribute("sessionId", request.getSession().getId());
			model.addAttribute("userFiles", noTypeUserFiles);
			return "userFiles";
		} else {
			model.addAttribute("fileTypes", this.systemDictionaryService.listItemByParentSn("fileType"));
			model.addAttribute("userFiles", noTypeUserFiles);
			return "userFiles_commit";
		}
	}

	/**
	 * 风控文件上传
	 * 
	 * @param file
	 * @param out
	 */
	@RequestMapping("userFile_upload")
	@ResponseBody
	public void userFileUpload(MultipartFile file, Writer out) {
		String basePath = servletContext.getRealPath("/upload");
		String fileName = UploadUtil.upload(file, basePath);
		String relativeFile = "/upload/" + fileName;
		this.userFileService.applyUserFile(relativeFile);
	}

	/**
	 * 设置风控文件类别
	 * 
	 * @param id
	 * @param fileType
	 * @return
	 */
	@RequestMapping("userFile_selectType")
	public String userFileTypeSelect(Long[] id, Long[] fileType) {
		if (id != null && fileType != null && id.length == fileType.length) {
			this.userFileService.updateFileTypes(id, fileType);
		}
		return "redirect:userFile.do";
	}

}
