package com.secret.mapper;

import com.secret.pojo.ArticleVo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleVoMapper {
    int deleteByPrimaryKey(@Param("articleId") String articleId, @Param("userId") String userId);

    int insert(ArticleVo articleVo);

    ArticleVo selectByPrimaryKey(@Param("articleId") String articleId, @Param("userId") String userId);

    List<ArticleVo> selectAll();

    int updateByPrimaryKey(ArticleVo articleVo);
}