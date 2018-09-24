package com.secret.mapper;

import com.secret.pojo.StageNameVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StageNameVoMapper {
    int insert(StageNameVo record);

    List<StageNameVo> selectAll();
    //多条件查询总数
    int selectStageNameCountByParam(Map<String, Object> returnMap);
    //多条件查询数据集合
    List<StageNameVo> selectStageNameListByParam(Map<String, Object> returnMap);
    //更新艺名
    void updateStageNameVo(StageNameVo stageNameVo);
    //删除艺名
    void deleteStageName(@Param("ids") String[] ids);
}