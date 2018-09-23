package com.secret.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.secret.common.utils.JsonData;
import com.secret.pojo.MenuQueryVo;
import com.secret.pojo.MenuVo;
import com.secret.service.LoginService;
import com.secret.service.MenuService;
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
	MenuService menuService;
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
	public Map<String,Object> selectStageNameListByParam(String menuParentId,String menuName,String pageSize,String pageNumber){
		Map<String,Object> returnMap = new HashMap<>();
		try {
			//封装查询参数
			returnMap.put("currentPage", !StringUtils.isEmpty(pageNumber)?(Integer.valueOf(pageNumber)/Integer.valueOf(pageSize))+1:1);
			returnMap.put("pageSize",!StringUtils.isEmpty(pageSize)?Integer.valueOf(pageSize):20);
			returnMap.put("menuName",menuName);
			returnMap.put("menuParentId",menuParentId);
			returnMap = menuService.selectMenuListByParam(returnMap);
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
				MenuVo menuVo = JSON.parseObject(vo,MenuVo.class);
				if(StringUtils.isEmpty(menuVo.getMenuId())){//新增
					menuVo.setMenuId(UUID.randomUUID().toString().replaceAll("-",""));
					menuVo.setMenuFlag("Y");
					menuVo.setCreateDate(new Date());
					menuService.insertMenuVo(menuVo);
				}else{//编辑
					menuVo.setUpdateDate(new Date());
					menuService.updateMenuVo(menuVo);
				}
				jsonData.setStatus(true);
			}
		} catch (Exception e) {
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
			int returnType = menuService.deleteMenu(!StringUtils.isEmpty(ids)?ids.split(","):null);
			if(returnType == 0){
				jsonData.setStatus(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}
}
