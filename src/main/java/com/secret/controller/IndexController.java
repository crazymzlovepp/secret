package com.secret.controller;

import com.alibaba.fastjson.JSON;
import com.secret.common.utils.DateFormatterUtils;
import com.secret.common.utils.IpInfoUtils;
import com.secret.common.utils.JsonData;
import com.secret.common.utils.UUIDUtils;
import com.secret.pojo.ArticleVo;
import com.secret.pojo.ReportVo;
import com.secret.pojo.UserVo;
import com.secret.pojo.VisitUserInfoVo;
import com.secret.service.IndexService;
import com.secret.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author      mym
 * @date        2018/9/14 0014 18:22
 * @describe    进入秘密网首页
 * @version     V1.0
 * @param       进入秘密网首页
 * @return      
*/
@Controller
@RequestMapping("/index")
public class IndexController {
    //举报文章人数警告值
    private static final int REPORT_WARN_NUM = 20;
    @Autowired
    IndexService indexService;
    @Autowired
    LoginService loginService;
	/**
	 * 
	 * @author 		mym
	 * @date   		2018年7月31日下午3:19:31
	 * @describe	
	 * @return		String
	 *
	 */
	@RequestMapping("/index")
	public ModelAndView goToIndex(ModelAndView model,HttpServletRequest request,String userName){
	    Map<String,Object> paramMap = new HashMap<>();
        String userId = "";
		try {
            // 获取cookie信息
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (int i = 0; i < cookies.length; i++) {
                    if(cookies[i].getName() != null && cookies[i].getName().equals("userId")){
                        userId = cookies[i].getValue();
                    }
                }
            }
            if(StringUtils.isEmpty(userId)){
                model.addObject("loginType",null);//访问首页没登录时将登录按钮显示否则隐藏
            }else{
                model.addObject("loginType","logged");
                model.addObject("loginUserId",userId);
            }
            //初始化进入首页  默认查询24小时最热的秘密
            String start = DateFormatterUtils.getYyyyMmDdStr(new Date());
            paramMap.put("startDate",start+" 00:00:00");
            paramMap.put("endDate",start+" 23:59:59");
            paramMap.put("currentPage",1);
            paramMap.put("pageSize",15);
            List<ArticleVo> articleVoList = indexService.selectArticleVoListByParam(paramMap);
            model.addObject("articleVoList",articleVoList);
			model.setViewName("index/index");
			//记录当前访客ip信息 每个访客每天只记录一次
            //登录成功后将当前登录IP等信息存起来
            //先根据当前年月日以及ip查询是否存在当日访问记录，不存在则执行新增
            VisitUserInfoVo vo = new VisitUserInfoVo();
            vo.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            vo.setIp(IpInfoUtils.getVisitIp(request));
            vo.setIpAddress(IpInfoUtils.getIpAddress(vo.getIp()));
            vo.setHostName(IpInfoUtils.getHostName(request));
            vo.setSource("1");
            vo.setUsername(StringUtils.isEmpty(userId) ? "访客" : userId);
            vo.setVisitDate(new Date());
            int num = loginService.selectVisitInfoByVo(vo);
            if(num == 0){
                loginService.insertVisitUserInfoVo(vo);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
    /**
     * @author      mym
     * @date        2018/9/19 0019 14:10
     * @describe    [model, id, request, response]
     * @version     V1.0
     * @param       [model, id, request, response]
     * @return      org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping("/loginOrRegister")
    public ModelAndView loginOrRegister(ModelAndView model, String id,HttpServletRequest request,HttpServletResponse response){
        try {
            if(!StringUtils.isEmpty(id) && id.equals("loginOrRegister_id")){//登录/注册
                model.addObject("sourceType","login");
                model.setViewName("index/loginOrRegister");
            }else if(!StringUtils.isEmpty(id) && id.equals("layout_id")){//退出登录
                // 删除cookie信息
                Cookie[] cookies = request.getCookies();
                if(cookies != null){
                    for (int i = 0; i < cookies.length; i++) {
                        if(cookies[i].getName() != null && cookies[i].getName().equals("userId")){
                            cookies[i].setMaxAge(0);
                            response.addCookie(cookies[i]);
                        }
                    }
                }
                response.sendRedirect(request.getContextPath()+"/index/index.html");
                model.setViewName("index/index");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    /**
     * @author      mym
     * @date        2018/9/18 0018 9:45
     * @describe    登录
     * @version     V1.0
     * @param       [request, response]
     * @return      void
    */
    @RequestMapping("/login")
    public ModelAndView login(ModelAndView model,HttpServletRequest request, HttpServletResponse response){
        try {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String sevenLoginSelf = request.getParameter("sevenLoginSelf");
            if(!StringUtils.isEmpty(userName) &&!StringUtils.isEmpty(password)){
                UserVo userVo = indexService.selectUserByuserNameAndPassword(userName,password);
                if(userVo != null){
                    //request.getSession().setAttribute("userId",userVo.getUserId());
                    Cookie userId_Cookie = new Cookie("userId",userVo.getUserId());
                    if(!StringUtils.isEmpty(sevenLoginSelf) && sevenLoginSelf.equals("on")){//7天免密
                        userId_Cookie.setMaxAge(60*60*24*7);
                    }
                    response.addCookie(userId_Cookie);
                    response.sendRedirect(request.getContextPath()+"/index/index.html");
                    model.setViewName("index/index");
                }else{
                    model.addObject("errorInfo","用户名或密码错误！");
                    model.addObject("sourceType","login");
                    model.setViewName("index/loginOrRegister");
                }
            }else{
                model.addObject("errorInfo","用户名或密码不能为空！");
                model.addObject("sourceType","login");
                model.setViewName("index/loginOrRegister");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    /* *
    * @author :     mym
    * @date Date :  2018/9/23 17:53
    * @version :    V1.0
    * @describe :   注册
    * @param :      
    * @return :     
    */
    @RequestMapping("/register")
    public ModelAndView register(ModelAndView model, UserVo userVo,HttpServletRequest request, HttpServletResponse response){
        try {
            if(!StringUtils.isEmpty(userVo.getUserName()) && !StringUtils.isEmpty(userVo.getPassword())
                    && !StringUtils.isEmpty(userVo.getBirthPlace()) && !StringUtils.isEmpty(userVo.getHobby())){
                UserVo checkUserVo  = indexService.selectUserByUserName(userVo.getUserName());
                if(checkUserVo != null){
                    model.addObject("errorInfo","用户名已存在，请重新输入！");
                    model.addObject("sourceType","register");
                    model.setViewName("index/loginOrRegister");
                }else if(!StringUtils.isEmpty(userVo.getPassword()) && !StringUtils.isEmpty(userVo.getPasswordConfirm())
                && !userVo.getPassword().equals(userVo.getPasswordConfirm())){
                    model.addObject("errorInfo","两次输入的密码不一致，请重新输入！");
                    model.addObject("sourceType","register");
                    model.setViewName("index/loginOrRegister");
                }else{
                    userVo.setRegisterIp(IpInfoUtils.getVisitIp(request));
                    String[] ipAddressArr = IpInfoUtils.getIpAddress(IpInfoUtils.getVisitIp(request)).split("/");
                    for (int i=0;i<ipAddressArr.length;i++) {
                        if(i == 0){
                            userVo.setRegisterCountry(!StringUtils.isEmpty(ipAddressArr[0])?ipAddressArr[0]:"");
                        }else if(i ==1){
                            userVo.setRegisterProvince(!StringUtils.isEmpty(ipAddressArr[1])?ipAddressArr[0]:"");
                        }else{
                            userVo.setRegisterCity(!StringUtils.isEmpty(ipAddressArr[2])?ipAddressArr[0]:"");
                        }
                    }
                    indexService.insertUserVo(userVo);
                    //request.getSession().setAttribute("userId",userVo.getUserId());
                    response.sendRedirect(request.getContextPath()+"/index/loginOrRegister.html?id=loginOrRegister_id");
                    model.setViewName("index/loginOrRegister");
                }
            }else{
                model.addObject("errorInfo","必填项有空！");
                model.setViewName("index/loginOrRegister");
            }

        } catch (Exception e) {
            model.addObject("errorInfo","新增用户失败，请稍后再试！");
            model.setViewName("index/loginOrRegister");
            e.printStackTrace();
        }
        return model;
    }
    /* *
    * @author :     mym
    * @date Date :  2018/9/24 15:25
    * @version :    V1.0
    * @describe :   跳转到发布秘密页
    * @param :      
    * @return :     
    */
    @RequestMapping("/toReleaseArticlePage")
    public ModelAndView toReleaseArticlePage(ModelAndView model,HttpServletRequest request){
        String userId = "";
        try {
            // 获取cookie信息
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (int i = 0; i < cookies.length; i++) {
                    if(cookies[i].getName() != null && cookies[i].getName().equals("userId")){
                        userId = cookies[i].getValue();
                    }
                }
            }
            if(StringUtils.isEmpty(userId)){
                model.addObject("loginType",null);//访问首页没登录时将登录按钮显示否则隐藏
            }else{
                model.addObject("loginType","logged");
                model.addObject("loginUserId",userId);
            }
            model.setViewName("index/releaseArticlePage");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    /* *
    * @author :     mym
    * @date Date :  2018/9/24 15:30
    * @version :    V1.0
    * @describe :   发布或者更新秘密
    * @param :
    * @return :
    */
    @RequestMapping("/addOrUpdateReleaseArticle")
    @ResponseBody
    public JsonData addOrUpdateReleaseArticle(String vo,HttpServletRequest request){
        JsonData jsonData = new JsonData();
        String userId = "";
        try {
            // 获取cookie信息
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (int i = 0; i < cookies.length; i++) {
                    if(cookies[i].getName() != null && cookies[i].getName().equals("userId")){
                        userId = cookies[i].getValue();
                    }
                }
            }
            if(!StringUtils.isEmpty(vo)) {
                ArticleVo articleVo = JSON.parseObject(vo, ArticleVo.class);
                if(StringUtils.isEmpty(articleVo.getArticleId())){//新增秘密
                    articleVo.setArticleId(UUIDUtils.getUUID());
                    articleVo.setArticleUsername(indexService.setlectStageName());
                    articleVo.setBrowse(0L);
                    articleVo.setCreateDate(new Date());
                    articleVo.setCreateIp(IpInfoUtils.getVisitIp(request));
                    articleVo.setDeleteTag("N");
                    articleVo.setReportNum(0);
                    articleVo.setReportTag("N");
                    articleVo.setUpdateDate(null);
                    articleVo.setUpdateIp("");
                    if(!StringUtils.isEmpty(userId)){
                        articleVo.setUserId(userId);
                        indexService.insertArticleVo(articleVo);
                        jsonData.setMsg("秘密分享成功！");
                        jsonData.setStatus(true);
                    }else{
                        jsonData.setMsg("发布秘密失败，请稍后重试！");
                    }
                }else{//修改
                    articleVo.setUpdateDate(new Date());
                    articleVo.setUpdateIp(IpInfoUtils.getVisitIp(request));
                    if(!StringUtils.isEmpty(userId)){
                        articleVo.setUserId(userId);
                        indexService.updateArticleVo(articleVo);
                        jsonData.setMsg("秘密修改成功！");
                        jsonData.setStatus(true);
                    }else{
                        jsonData.setMsg("修改秘密失败，请稍后重试！");
                    }
                }
            }
        } catch (Exception e) {
            jsonData.setMsg("发布秘密失败，请稍后重试！");
            e.printStackTrace();
        }
        return jsonData;
    }
    /* *
    * @author :     mym
    * @date Date :  2018/10/11 21:09
    * @version :    V1.0
    * @describe :   根据选择的秘密类型查询对应的秘密数据
    * @param :      
    * @return :     
    */
    @RequestMapping("/selectArticleByParam")
    @ResponseBody
    public JsonData selectArticleByParam(String type,String param,HttpServletRequest request){
        JsonData jsonData = new JsonData();
        Map<String,Object> paramMap = new HashMap<>();
        //当前系统时间
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        //起始、截止时间
        String start =  "";
        String end =  "";
        try{
            paramMap.put("currentPage",1);
            paramMap.put("pageSize",15);
            paramMap.put("param",param);
            //根据type类型来加载对应的数据
            if(!StringUtils.isEmpty(type)){
                if(type.equals("24_hours_id")){//24小时最热
                    start = DateFormatterUtils.getYyyyMmDdStr(nowDate);
                    end = start;
                }if(type.equals("7_days_id")){//7天最热
                    calendar.add(Calendar.DAY_OF_YEAR,-6);
                    start = DateFormatterUtils.getYyyyMmDdStr(calendar.getTime());
                    end = DateFormatterUtils.getYyyyMmDdStr(nowDate);
                }else if(type.equals("the_month_id")){//当月最热
                    start = DateFormatterUtils.getYyyyMmStr(nowDate)+"-01";
                    end = DateFormatterUtils.getYyyyMmDdStr(nowDate);
                }else if(type.equals("three_month_id")){//三月最热
                    calendar.add(Calendar.MONTH,-3);
                    start = DateFormatterUtils.getYyyyMmDdStr(calendar.getTime());
                    end = DateFormatterUtils.getYyyyMmDdStr(nowDate);
                }else if(type.equals("the_year_id")){//当年最热
                    start = DateFormatterUtils.getYyyyStr(nowDate)+"-01-01";
                    end = DateFormatterUtils.getYyyyMmDdStr(nowDate);
                }else if(type.equals("myself_id")){//自己的
                    // 获取cookie信息
                    Cookie[] cookies = request.getCookies();
                    if(cookies != null){
                        for (int i = 0; i < cookies.length; i++) {
                            if(cookies[i].getName() != null && cookies[i].getName().equals("userId")){
                                paramMap.put("userId",cookies[i].getValue());
                         }
                        }
                     }
                }/*else if(type.equals("search_id")){//检索
                }*/
                if(!StringUtils.isEmpty(start)){
                    paramMap.put("startDate",start+" 00:00:00");
                }
                if(!StringUtils.isEmpty(end)){
                    paramMap.put("endDate",end+" 23:59:59");
                }
            }else{
                start = DateFormatterUtils.getYyyyMmDdStr(nowDate);
                paramMap.put("startDate",start+" 00:00:00");
                paramMap.put("endDate",start+" 23:59:59");
            }
            List<ArticleVo> articleVoList = indexService.selectArticleVoListByParam(paramMap);
            jsonData.setObject(articleVoList);
            jsonData.setStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonData;
    }
    /**
     * @author      mym
     * @date        2018/10/16 0016 16:01
     * @describe    更新文章的点赞量、踩量、浏览量
     * @version     V1.0
     * @param       [articleId, zanNum, caiNum, type]
     * @return      com.secret.common.utils.JsonData
    */
    @RequestMapping("/updateReleaseArticle")
    @ResponseBody
    public JsonData updateReleaseArticle(String articleId,String zanNum,String caiNum,String type){
        JsonData jsonData = new JsonData();
        ArticleVo articleVo = new ArticleVo();
        Long browse = 0L;
        try {
            if(!StringUtils.isEmpty(articleId)){
                articleVo.setArticleId(articleId);
                if(!StringUtils.isEmpty(zanNum)){
                    articleVo.setZanNum(new Long(zanNum));
                    browse = new Long(zanNum);
                }
                if(!StringUtils.isEmpty(caiNum)){
                    articleVo.setCaiNum(new Long(caiNum));
                    browse = browse + new Long(caiNum);
                }
                articleVo.setBrowse(browse);
                indexService.updateReleaseArticle(articleVo);
            }
            if(!StringUtils.isEmpty(type)){
                if(type.equals("zan")){
                    jsonData.setStatus(true);
                    jsonData.setMsg("这么用力，给你32个赞！");
                }else{
                    jsonData.setStatus(true);
                    jsonData.setMsg("大地都被你踩的震动了！");
                }
            }
        } catch (Exception e) {
            if(!StringUtils.isEmpty(type)){
                if(type.equals("zan")){
                    jsonData.setMsg("用力太小，点赞失败咯，补充点能量再来一次吧！");
                }else{
                    jsonData.setMsg("脚太滑，没踩上，用劲儿再来一次吧！");
                }
            }else{
                jsonData.setMsg("数据被外星人劫持了，稍后再来吧！");
            }
            e.printStackTrace();
        }
        return jsonData;
    }
    /**
     * @author      mym
     * @date        2018/10/18 0018 16:48
     * @describe    举报文章
     * @version     V1.0
     * @param       [articleId, userId]
     * @return      com.secret.common.utils.JsonData
    */
    @RequestMapping("/toReportArticle")
    @ResponseBody
    public JsonData toReportArticle(String articleId,String userId){
        JsonData jsonData = new JsonData();
        ArticleVo articleVo = new ArticleVo();
        try {
            articleVo.setArticleId(articleId);
            articleVo.setUserId(userId);
            //查询当前文章是否被当前用户举报过，没个账号只能举报一次
            ReportVo reportVo = indexService.setlectReportVoByUserIdAndArticleId(articleVo);
            if(reportVo == null){//第一次举报
                //先将举报信息存入举报表中
                ReportVo newReportVo = new ReportVo();
                newReportVo.setReportId(UUIDUtils.getUUID());
                newReportVo.setArticleId(articleId);
                newReportVo.setReportUserId(userId);
                newReportVo.setReportDate(new Date());
                indexService.insertReportVo(newReportVo);

                //根据文章id查询文章举报人数
                int reportNum = indexService.selectReportNum(articleId);
                if(REPORT_WARN_NUM <= reportNum){//超过警告值，将当前文章拉黑处理
                    articleVo.setReportNum(reportNum + 1);
                    articleVo.setReportTag("Y");
                }else{
                    articleVo.setReportNum(reportNum + 1);
                    articleVo.setReportTag("N");//本身就是N 但是因为调用同一更新接口，所以这里也赋一下值
                }
                indexService.reportOrUpdateArticle(articleVo);
                jsonData.setStatus(true);
                jsonData.setMsg("已收到您的举报，稍后我们会处理！");
            }else{
                jsonData.setStatus(true);
                jsonData.setMsg("您已经举报过了，消消气，我们会尽快处理！");
            }
        } catch (Exception e) {
            jsonData.setMsg("坏人把网抢走了，稍后再试吧！");
            e.printStackTrace();
        }
        return jsonData;
    }
    /**
     * @author      mym
     * @date        2018/10/19 0019 14:50
     * @describe    编辑秘密
     * @version     V1.0
     * @param       [model, request]
     * @return      org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping("/toEditReleaseArticlePage")
    public ModelAndView toEditReleaseArticlePage(ModelAndView model,HttpServletRequest request){
        String userId = "";
        String articleId = request.getParameter("articleId");
        try {
            // 获取cookie信息
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for (int i = 0; i < cookies.length; i++) {
                    if(cookies[i].getName() != null && cookies[i].getName().equals("userId")){
                        userId = cookies[i].getValue();
                    }
                }
            }
            if(StringUtils.isEmpty(userId)){
                model.addObject("loginType",null);//访问首页没登录时将登录按钮显示否则隐藏
            }else{
                model.addObject("loginType","logged");
                model.addObject("loginUserId",userId);
            }
            //根据当前用户及秘密id查询秘密详情数据
            ArticleVo articleVo = indexService.selectArticleVoByUserIdAndArticleId(articleId,userId);
            model.addObject("articleVo",articleVo);
            model.setViewName("index/editArticlePage");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
    /**
     * @author      mym
     * @date        2018/10/19 0019 15:22
     * @describe    删除秘密
     * @version     V1.0
     * @param       [articleId, zanNum, caiNum, type]
     * @return      com.secret.common.utils.JsonData
    */
    @RequestMapping("/deleteReleaseArticlePage")
    @ResponseBody
    public JsonData deleteReleaseArticlePage(String articleId){
        JsonData jsonData = new JsonData();
        try {
            if(!StringUtils.isEmpty(articleId)){
                indexService.deleteReleaseArticlePage(articleId);
                jsonData.setMsg("成功移除你的小烦恼啦！");
            }
        } catch (Exception e) {
            jsonData.setMsg("删除失败了，可能不想你丢弃它，稍后再试吧！");
            e.printStackTrace();
        }
        return jsonData;
    }
}
