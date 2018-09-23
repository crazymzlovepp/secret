package com.secret.serviceImpl;

import com.secret.common.utils.UUIDUtils;
import com.secret.mapper.UserVoMapper;
import com.secret.pojo.UserVo;
import com.secret.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
}
