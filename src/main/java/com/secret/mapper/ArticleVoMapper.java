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
}