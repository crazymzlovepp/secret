package com.secret.mapper;

import com.secret.pojo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserVoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserVo record);

    UserVo selectByPrimaryKey(String userId);

    List<UserVo> selectAll();

    int updateByPrimaryKey(UserVo record);
    //根据用户名查询用户信息
    UserVo selectUserByUserName(String userName);
    //根据用户名及密码查询用户信息
    UserVo selectUserByuserNameAndPassword(@Param("userName") String userName, @Param("password")String password);
}