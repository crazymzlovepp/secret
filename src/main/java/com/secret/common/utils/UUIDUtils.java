package com.secret.common.utils;

import org.thymeleaf.util.StringUtils;
import sun.misc.BASE64Encoder;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * @param :
 * @author :     mym
 * @version :    V1.0
 * @date :       2018/09/23 18:17
 * @describe :   TODO
 * @return :
 */
public class UUIDUtils{
    private static final String secret_key = "secret_32_key_password";
    //获取32为主键
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    //获取加密后的密码
    public static String getEncryptioPassword(String password){
        String returnStr = "";
        try{
            if(StringUtils.isEmpty(password)){
                return returnStr;
            }else{
                password = password+secret_key;
                MessageDigest md5=MessageDigest.getInstance("MD5");
                BASE64Encoder base64en = new BASE64Encoder();
                //加密后的字符串   24位
                returnStr=base64en.encode(md5.digest(password.getBytes("utf-8")));
            }
        }catch(Exception e){
           e.printStackTrace();
        }
        return returnStr;
    }
}
