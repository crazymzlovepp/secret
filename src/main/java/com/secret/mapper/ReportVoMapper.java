package com.secret.mapper;

import com.secret.pojo.ArticleVo;
import com.secret.pojo.ReportVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportVoMapper {
    int deleteByPrimaryKey(@Param("articleId") String articleId, @Param("reportUserId") String reportUserId);

    int insert(ReportVo record);

    ReportVo selectByPrimaryKey(@Param("articleId") String articleId, @Param("reportUserId") String reportUserId);

    List<ReportVo> selectAll();

    int updateByPrimaryKey(ReportVo record);
    //查询当前文章是否被当前用户举报过，没个账号只能举报一次
    ReportVo setlectReportVoByUserIdAndArticleId(ArticleVo articleVo);
}