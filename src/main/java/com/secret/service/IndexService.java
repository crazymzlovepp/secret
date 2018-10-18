package com.secret.service;

import com.secret.pojo.ArticleVo;
import com.secret.pojo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * @param :
 * @author :     mym
 * @version :    V1.0
 * @date :       2018/09/23 18:06
 * @describe :   TODO
 * @return :
 */
public interface IndexService {
    //根据用户名查询用户是否存在
    UserVo selectUserByUserName(String userName);
    //新增用户
    void insertUserVo(UserVo userVo);
    //根据用户名及密码查询用户信息
    UserVo selectUserByuserNameAndPassword(String userName, String password);
    //随机取一个艺名
    String setlectStageName();
    //新增秘密
    void insertArticleVo(ArticleVo articleVo);
    //多条件分页查询秘密
    List<ArticleVo> selectArticleVoListByParam(Map<String, Object> paramMap);
    //更新文章的点赞量、踩量、浏览量
    void updateReleaseArticle(ArticleVo articleVo);
    //根据文章id查询文章举报人数
    int selectReportNum(String articleId);
    //拉黑或者更新文章举报人数
    void reportOrUpdateArticle(ArticleVo articleVo);
}
