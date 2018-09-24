package com.secret.service;

import com.secret.pojo.StageNameVo;

import java.util.Map;

/* *
* @author :     mym
* @date Date :  2018/9/24 12:38
* @version :    V1.0
* @describe :   艺名管理接口
* @param :      
* @return :     
*/
public interface StageNameService {
	//艺名多条件查询
	Map<String, Object> selectStageNameListByParam(Map<String, Object> returnMap);
	//新增艺名
	void insertStageNameVo(StageNameVo stageNameVo);
	//更新
	void updateStageNameVo(StageNameVo stageNameVo);
	//删除艺名
	int deleteStageName(String[] ids);
}
