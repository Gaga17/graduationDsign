package study.cjj.eloan.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import study.cjj.eloan.base.domain.SystemDictionary;
import study.cjj.eloan.base.domain.SystemDictionaryItem;
import study.cjj.eloan.base.query.PageResult;
import study.cjj.eloan.base.query.SystemDictionaryQueryObject;
import study.cjj.eloan.base.service.ISystemDictionaryService;
import study.cjj.eloan.base.util.ResultJSON;

@Controller
public class SystemDictionaryController extends BaseController {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;

	/**
	 * 系统字典列表
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("systemDictionary_list")
	public String list(@ModelAttribute("qo")SystemDictionaryQueryObject qo, Model model) {
		PageResult pageResult = this.systemDictionaryService.queryDic(qo);
		model.addAttribute("pageResult", pageResult);
		return "systemdic/systemDictionary_list";
	}

	/**
	 * 系统字典更新
	 * @param dictionary
	 * @return
	 */
	@RequestMapping("systemDictionary_update")
	@ResponseBody
	public ResultJSON update(SystemDictionary dictionary) {
		ResultJSON json = new ResultJSON();
		try{
			this.systemDictionaryService.saveOrUpdateDic(dictionary);
			json.setSuccess(true);
		}catch(Exception e){
			json.setMsg("系统错误,保存失败!");
		}
		return json;
	}
	
	/**
	 * 系统字典删除
	 * @param dictionary
	 * @return
	 */
	@RequestMapping("systemDictionary_delete")
	@ResponseBody
	public ResultJSON delete(Long id) {
		ResultJSON json = new ResultJSON();
		try{
			this.systemDictionaryService.delete(id);
			json.setSuccess(true);
		}catch(Exception e){
			json.setMsg("系统错误,删除失败!");
		}
		return json;
	}
	
	/**
	 * 系统字典明细列表
	 * @param queryObject
	 * @param model
	 * @return
	 */
	@RequestMapping("systemDictionaryItem_list")
	public String listItem(@ModelAttribute("qo")SystemDictionaryQueryObject queryObject, Model model) {
		PageResult pageResult = this.systemDictionaryService
				.queryDicItem(queryObject);
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("systemDictionaryGroups",
				this.systemDictionaryService.listAllDics());
		return "systemDic/systemDictionaryItem_list";
	}

	/**
	 * 系统字典明细更新
	 * @param item
	 * @param model
	 * @return
	 */
	@RequestMapping("systemDictionaryItem_update")
	@ResponseBody
	public ResultJSON updateItem(SystemDictionaryItem item,Model model) {
		ResultJSON json = new ResultJSON();
		try {
			this.systemDictionaryService.saveOrUpdateItem(item);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("保存失败!");
		}
		return json;
	}
	
	
	/**
	 * 系统字典明细删除
	 * @param dictionary
	 * @return
	 */
	@RequestMapping("systemDictionaryItem_delete")
	@ResponseBody
	public ResultJSON deleteItem(Long id) {
		ResultJSON json = new ResultJSON();
		try{
			this.systemDictionaryService.deleteItem(id);
			json.setSuccess(true);
		}catch(Exception e){
			json.setMsg("系统错误,删除失败!");
		}
		return json;
	}

	

}
