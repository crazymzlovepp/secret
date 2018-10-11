package com.secret.common.utils;

import com.alibaba.druid.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @param :
 * @author :     mym
 * @version :    V1.0
 * @date :       2018/09/24 16:11
 * @describe :   日期格式化的各种转化方法
 * @return :
 */
public class DateFormatterUtils {
    private static final SimpleDateFormat yyyy= new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat yyyy_MM = new SimpleDateFormat("yyyy-MM");
    private static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat HH_mm_sss = new SimpleDateFormat("HH:mm:ss");
    /* *
    * @author :     mym
    * @date Date :  2018/9/24 16:16
    * @version :    V1.0
    * @describe :   得到yyyy-MM-dd的日期串
    * @param :      
    * @return :     
    */
    public static String getYyyyMmDdStr(Date date){
        if(date == null){
            return "";
        }else{
            return yyyy_MM_dd.format(date);
        }
    }
    /* *
    * @author :     mym
    * @date Date :  2018/9/24 16:17
    * @version :    V1.0
    * @describe :   得到yyyy-MM-dd的日期
    * @param :      
    * @return :     
    */
    public static Date getYyyyMmDdDate(String  str){
        if(StringUtils.isEmpty(str)){
            return null;
        }else{
            try {
                return yyyy_MM_dd.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    /* *
     * @author :     mym
     * @date Date :  2018/9/24 16:16
     * @version :    V1.0
     * @describe :   得到yyyy-MM-dd HH:mm:ss的日期串
     * @param :
     * @return :
     */
    public static String getYyyyMmDdHhMmSsStr(Date date){
        if(date == null){
            return "";
        }else{
            return yyyy_MM_dd_HH_mm_ss.format(date);
        }
    }
    /* *
     * @author :     mym
     * @date Date :  2018/9/24 16:17
     * @version :    V1.0
     * @describe :   得到yyyy-MM-dd HH:mm:ss的日期
     * @param :
     * @return :
     */
    public static Date getYyyyMmDdHhMmSsDate(String  str){
        if(StringUtils.isEmpty(str)){
            return null;
        }else{
            try {
                return yyyy_MM_dd_HH_mm_ss.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    /* *
     * @author :     mym
     * @date Date :  2018/9/24 16:16
     * @version :    V1.0
     * @describe :   得到HH:mm:ss的日期串
     * @param :
     * @return :
     */
    public static String getHhMmSsStr(Date date){
        if(date == null){
            return "";
        }else{
            return HH_mm_sss.format(date);
        }
    }
    /* *
     * @author :     mym
     * @date Date :  2018/9/24 16:17
     * @version :    V1.0
     * @describe :   得到HH:mm:ss的日期
     * @param :
     * @return :
     */
    public static Date getHhMmSsDate(String  str){
        if(StringUtils.isEmpty(str)){
            return null;
        }else{
            try {
                return HH_mm_sss.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    /* *
     * @author :     mym
     * @date Date :  2018/10/11 21:36
     * @version :    V1.0
     * @describe :   得到yyyy-MM的日期串
     * @param :
     * @return :
     */
    public static String getYyyyMmStr(Date date){
        if(date == null){
            return "";
        }else{
            return yyyy_MM.format(date);
        }
    }
    /* *
     * @author :     mym
     * @date Date :  2018/10/11 21:35
     * @version :    V1.0
     * @describe :   得到yyyy-MM的日期
     * @param :
     * @return :
     */
    public static Date getYyyyMmDate(String  str){
        if(StringUtils.isEmpty(str)){
            return null;
        }else{
            try {
                return yyyy_MM.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
    /* *
     * @author :     mym
     * @date Date :  2018/10/11 21:42
     * @version :    V1.0
     * @describe :   得到yyyy的日期串
     * @param :
     * @return :
     */
    public static String getYyyyStr(Date date){
        if(date == null){
            return "";
        }else{
            return yyyy.format(date);
        }
    }
    /* *
     * @author :     mym
     * @date Date :  2018/10/11 21:42
     * @version :    V1.0
     * @describe :   得到yyyy的日期
     * @param :
     * @return :
     */
    public static Date getYyyyDate(String  str){
        if(StringUtils.isEmpty(str)){
            return null;
        }else{
            try {
                return yyyy.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
