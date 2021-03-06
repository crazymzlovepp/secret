package com.secret.serviceImpl;

import com.secret.common.utils.UUIDUtils;
import com.secret.mapper.ArticleVoMapper;
import com.secret.mapper.ReportVoMapper;
import com.secret.mapper.UserVoMapper;
import com.secret.pojo.ArticleVo;
import com.secret.pojo.ReportVo;
import com.secret.pojo.UserVo;
import com.secret.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    UserVoMapper userVoMapper;
    @Autowired
    ArticleVoMapper articleVoMapper;
    @Autowired
    ReportVoMapper reportVoMapper;
    //根据用户名查询用户是否存在
    @Override
    public UserVo selectUserByUserName(String userName) {
        return userVoMapper.selectUserByUserName(userName);
    }
    //新增用户
    @Override
    public void insertUserVo(UserVo userVo) {
        userVo.setUserId(UUIDUtils.getUUID());
        userVo.setPassword(UUIDUtils.getEncryptioPassword(userVo.getPassword()));
        userVo.seteMail("");
        userVo.setPhone("");
        userVo.setRemarks("");
        userVo.setRegisterDate(new Date());
        userVoMapper.insert(userVo);
    }
    //根据用户名及密码查询用户信息
    @Override
    public UserVo selectUserByuserNameAndPassword(String userName, String password) {
        password = UUIDUtils.getEncryptioPassword(password);
        return userVoMapper.selectUserByuserNameAndPassword(userName,password);
    }
    //随机取一个艺名
    @Override
    public String setlectStageName() {
        String returnStr = "";
        try{
            returnStr = userVoMapper.setlectStageNameRandom();
        }catch(Exception e){
           e.printStackTrace();
        }
        return returnStr;
    }

    @Override
    public void insertArticleVo(ArticleVo articleVo) {
        articleVoMapper.insert(articleVo);
    }
    //多条件查询秘密数据
    @Override
    public List<ArticleVo> selectArticleVoListByParam(Map<String, Object> paramMap) {
        List<ArticleVo> returnArticleVoList = new ArrayList<>();
        try{
            returnArticleVoList = articleVoMapper.selectArticleVoListByParam(paramMap);
        }catch(Exception e){
           e.printStackTrace();
        }
        return returnArticleVoList;
    }
    //更新文章的点赞量、踩量、浏览量
    @Override
    public void updateReleaseArticle(ArticleVo articleVo) {
        articleVoMapper.updateReleaseArticle(articleVo);
    }
    //根据文章id查询文章举报人数
    @Override
    public int selectReportNum(String articleId) {
        return articleVoMapper.selectReportNum(articleId);
    }
    //拉黑或者更新文章举报人数
    @Override
    public void reportOrUpdateArticle(ArticleVo articleVo) {
        articleVoMapper.reportOrUpdateArticle(articleVo);
    }
    //查询当前文章是否被当前用户举报过，没个账号只能举报一次
    @Override
    public ReportVo setlectReportVoByUserIdAndArticleId(ArticleVo articleVo) {
        return reportVoMapper.setlectReportVoByUserIdAndArticleId(articleVo);
    }
    //新增举报信息
    @Override
    public void insertReportVo(ReportVo newReportVo) {
        reportVoMapper.insert(newReportVo);
    }

    @Override
    public ArticleVo selectArticleVoByUserIdAndArticleId(String articleId, String userId) {
        return articleVoMapper.selectByPrimaryKey(articleId,userId);
    }
    //更新秘密主体
    @Override
    public void updateArticleVo(ArticleVo articleVo) {
        articleVoMapper.updateArticleVo(articleVo);
    }
    //删除秘密 逻辑删除
    @Override
    public void deleteReleaseArticlePage(String articleId) {
        articleVoMapper.deleteReleaseArticlePage(articleId);
    }
}
