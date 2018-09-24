package com.secret.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.secret.common.utils.JsonData;
import com.secret.pojo.MenuQueryVo;
import com.secret.pojo.MenuVo;
import com.secret.pojo.StageNameVo;
import com.secret.service.LoginService;
import com.secret.service.StageNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/* *
* @author :     mym
* @date Date :  2018/9/23 22:24
* @version :    V1.0
* @describe :   艺名管理
* @param :      
* @return :     
*/
@Controller
@RequestMapping("/stageName")
public class StageNameController {
	@Autowired
	LoginService loginService;
	@Autowired
	StageNameService stageNameService;
	/**
	 * 
	 * @author 		mym
	 * @date   		2018年7月31日下午3:19:31
	 * @describe	
	 * @return		String
	 *
	 */
	@RequestMapping("/toStageNamePage")
	public ModelAndView goToLoginPage(ModelAndView model){
		try{
			//查找所有菜单数据集合
			List<MenuQueryVo> menuQueryVoList = loginService.selectAllMenuList();
			model.addObject("menuQueryVoList",menuQueryVoList);
			model.addObject("userName","admin");
			model.addObject("menuName","菜单管理");
			model.setViewName("theBackGround/actionPage/stageName");
		}catch(Exception e){
			e.printStackTrace();
		}
		return model;
	}
	/* *
	* @author :     mym
	* @date Date :  2018/9/23 22:29
	* @version :    V1.0
	* @describe :   艺名多条件查询
	* @param :      
	* @return :     
	*/
	@RequestMapping("/selectStageNameListByParam")
	@ResponseBody
	public Map<String,Object> selectStageNameListByParam(String stageName,String pageSize,String pageNumber){
		Map<String,Object> returnMap = new HashMap<>();
		try {
			//封装查询参数
			returnMap.put("currentPage", !StringUtils.isEmpty(pageNumber)?(Integer.valueOf(pageNumber)/Integer.valueOf(pageSize))+1:1);
			returnMap.put("pageSize",!StringUtils.isEmpty(pageSize)?Integer.valueOf(pageSize):20);
			returnMap.put("stageName",stageName);
			returnMap = stageNameService.selectStageNameListByParam(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}
	/* *
	* @author :     mym
	* @date Date :  2018/9/23 22:29
	* @version :    V1.0
	* @describe :   新增或者编辑艺名
	* @param :      
	* @return :     
	*/
	@RequestMapping("/addOrUpdateStageName")
	@ResponseBody
	public JsonData addOrUpdateStageName(String vo){
		JsonData jsonData = new JsonData();
		try {
			if(!StringUtils.isEmpty(vo)){
				StageNameVo stageNameVo = JSON.parseObject(vo,StageNameVo.class);
				if(!StringUtils.isEmpty(stageNameVo.getStageName())){//艺名为空 不允许新增
					if(StringUtils.isEmpty(stageNameVo.getStageNameId())){//新增
						stageNameService.insertStageNameVo(stageNameVo);
					}else{//编辑
						stageNameService.updateStageNameVo(stageNameVo);
					}
					jsonData.setStatus(true);
				}else{
					jsonData.setMsg("艺名为空，不允许操作！");
				}
			}
		} catch (Exception e) {
			jsonData.setMsg("操作异常，请重试！");
			e.printStackTrace();
		}
		return jsonData;
	}
	/* *
	* @author :     mym
	* @date Date :  2018/9/23 22:30
	* @version :    V1.0
	* @describe :   删除艺名
	* @param :      
	* @return :     
	*/
	@RequestMapping("/deleteStageName")
	@ResponseBody
	public JsonData deleteStageName(String ids){
		JsonData jsonData= new JsonData();
		try {
			int returnType = stageNameService.deleteStageName(!StringUtils.isEmpty(ids)?ids.split(","):null);
			if(returnType == 0){
				jsonData.setStatus(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}
}
