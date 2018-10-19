package com.secret.mapper;

import com.secret.pojo.ArticleVo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ArticleVoMapper {
    int deleteByPrimaryKey(@Param("articleId") String articleId, @Param("userId") String userId);

    int insert(ArticleVo articleVo);

    ArticleVo selectByPrimaryKey(@Param("articleId") String articleId, @Param("userId") String userId);

    List<ArticleVo> selectAll();

    int updateByPrimaryKey(ArticleVo articleVo);
    //多条件查询秘密数据
    List<ArticleVo> selectArticleVoListByParam(Map<String, Object> paramMap);
    //更新文章的点赞量、踩量、浏览量
    void updateReleaseArticle(ArticleVo articleVo);
    //根据文章id查询文章举报人数
    int selectReportNum(String articleId);
    //拉黑或者更新文章举报人数
    void reportOrUpdateArticle(ArticleVo articleVo);
    //更新秘密主体信息
    void updateArticleVo(ArticleVo articleVo);
    //删除秘密 逻辑删除
    void deleteReleaseArticlePage(@Param("articleId") String articleId);
}