package com.secret.service;

import com.secret.pojo.UserVo;

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
}
