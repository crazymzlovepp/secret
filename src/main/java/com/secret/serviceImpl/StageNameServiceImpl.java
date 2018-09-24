package com.secret.serviceImpl;

import com.secret.common.utils.UUIDUtils;
import com.secret.mapper.StageNameVoMapper;
import com.secret.pojo.StageNameVo;
import com.secret.service.StageNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StageNameServiceImpl implements StageNameService {
	@Autowired
	StageNameVoMapper stageNameVoMapper;
	//艺名多条件查询
	@Override
	public Map<String, Object> selectStageNameListByParam(Map<String, Object> returnMap) {
		try{
			int total = stageNameVoMapper.selectStageNameCountByParam(returnMap);
			List<StageNameVo> rows = stageNameVoMapper.selectStageNameListByParam(returnMap);
			returnMap.put("total",total);
			returnMap.put("rows",rows);
		}catch(Exception e){
			returnMap.put("total",0);
			returnMap.put("rows",null);
			e.printStackTrace();
		}
		return returnMap;
	}
	//新增
	@Override
	public void insertStageNameVo(StageNameVo stageNameVo) {
		String stageName = stageNameVo.getStageName();
		if(stageName.contains("/")){
			String[] nameArr = stageName.split("/");
			for (String name:nameArr) {
				stageNameVo.setStageNameId(UUIDUtils.getUUID());
				stageNameVo.setCreateDate(new Date());
				stageNameVo.setStageName(name);
				stageNameVoMapper.insert(stageNameVo);
			}
		}else{
			stageNameVo.setStageNameId(UUIDUtils.getUUID());
			stageNameVo.setCreateDate(new Date());
			stageNameVoMapper.insert(stageNameVo);
		}
	}
	//更新
	@Override
	public void updateStageNameVo(StageNameVo stageNameVo) {
		stageNameVo.setUpdateDate(new Date());
		stageNameVoMapper.updateStageNameVo(stageNameVo);
	}
	//删除艺名
	@Override
	public int deleteStageName(String[] ids) {
		int returnType = 0;
		try{
			stageNameVoMapper.deleteStageName(ids);
		}catch (Exception e){
			returnType =1;
			e.printStackTrace();
		}
		return returnType;
	}
}
