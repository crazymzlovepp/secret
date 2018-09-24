package com.secret.serviceImpl;

import com.secret.common.utils.UUIDUtils;
import com.secret.mapper.ArticleVoMapper;
import com.secret.mapper.UserVoMapper;
import com.secret.pojo.ArticleVo;
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
}
