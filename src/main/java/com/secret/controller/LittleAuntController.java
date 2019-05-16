package com.secret.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @param :
 * @author :     mym
 * @version :    V1.0
 * @date :       2019/05/16 14:44
 * @describe :   TODO
 * @return :
 */
@Controller
@RequestMapping("/littleAunt")
public class LittleAuntController {
    @RequestMapping("/index")
    @ResponseBody
    public List<HashMap<String,Object>> littleAuntIndex(String now){
        List<HashMap<String,Object>> returnList = new ArrayList();
        Date nowDate = null;
        int totalDay;//当前月总天数
        String day = "";//当前天数
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            if(now != null && now != ""){
                nowDate = sdf.parse(now);
            }else{
                nowDate = new Date();
            }
            int today = getCurrentToday(new Date());//当前年月日
            totalDay = getCurrentMonthDay(nowDate);
            for(int i = 1; i<=totalDay;i++){
                HashMap<String,Object> dayMap = new HashMap<>();
                day = (i<10 ? "0-"+i : i+"");
                dayMap.put("ymd",now+"-"+day);
                day = "";
                dayMap.put("date",i);
                dayMap.put("love",false);
                dayMap.put("danger",false);
                dayMap.put("monthly",false);
                dayMap.put("nomonthly",false);
                dayMap.put("ovulation",false);
                if(i == today && now.equals(sdf.format(new Date()))){
                    dayMap.put("today",true);
                }else{
                    dayMap.put("today",false);
                }
                returnList.add(dayMap);
            }
        }catch(Exception e){
         e.printStackTrace();
        }
           return returnList;
    }
    /* *
    * @author :     mym
    * @date Date :  2019/5/16 15:05
    * @version :    V1.0
    * @describe :   得到当前月总天数
    * @param :
    * @return :
    */
    public static int getCurrentMonthDay(Date date)
        {
         Calendar c = Calendar.getInstance();
         c.setTime(date);
         c.set(Calendar.DATE, 1);
         c.roll(Calendar.DATE, -1);
         int maxDate = c.get(Calendar.DATE);
         return maxDate;
        }
    /* *
    * @author :     mym
    * @date Date :  2019/5/16 15:05
    * @version :    V1.0
    * @describe :   拿到今天在当前月中的具体日期
    * @param :
    * @return :
    */
    public static int getCurrentToday(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int maxDate = c.get(Calendar.DATE);
        return maxDate;
    }
}
