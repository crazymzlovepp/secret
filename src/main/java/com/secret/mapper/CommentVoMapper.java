package com.secret.mapper;

import com.secret.pojo.CommentVo;
import java.util.List;

public interface CommentVoMapper {
    int deleteByPrimaryKey(String articleId);

    int insert(CommentVo commentVo);

    CommentVo selectByPrimaryKey(String articleId);

    List<CommentVo> selectAll();

    int updateByPrimaryKey(CommentVo commentVo);
}