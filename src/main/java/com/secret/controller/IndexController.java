package com.secret.controller;

import com.alibaba.fastjson.JSON;
import com.secret.common.utils.DateFormatterUtils;
import com.secret.common.utils.IpInfoUtils;
import com.secret.common.utils.JsonData;
import com.secret.common.utils.UUIDUtils;
import com.secret.pojo.ArticleVo;
import com.secret.pojo.UserVo;
import com.secret.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    IndexService indexService;
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
		try {
            Object userId = request.getSession().getAttribute("userId");
            if(userId==null){
                model.addObject("loginType",null);//访问首页没登录时将登录按钮显示否则隐藏
            }else{
                model.addObject("loginType","logged");
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
            if(!StringUtils.isEmpty(id) && id.equals("login_id")){//登录
                model.setViewName("index/login");
            }else if(!StringUtils.isEmpty(id) && id.equals("register_id")){//注册
                model.setViewName("index/register");
            }else if(!StringUtils.isEmpty(id) && id.equals("layout_id")){//退出登录
                request.getSession().removeAttribute("userId");
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
            if(!StringUtils.isEmpty(userName) &&!StringUtils.isEmpty(password)){
                UserVo userVo = indexService.selectUserByuserNameAndPassword(userName,password);
                if(userVo != null){
                    request.getSession().setAttribute("userId",userVo.getUserId());
                    response.sendRedirect(request.getContextPath()+"/index/index.html");
                    model.setViewName("index/index");
                }else{
                    model.addObject("errorInfo","用户名或密码错误！");
                    model.setViewName("index/login");
                }
            }else{
                model.addObject("errorInfo","用户名或密码不能为空！");
                model.setViewName("index/login");
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
                    &&!StringUtils.isEmpty(userVo.getBirthPlace()) && !StringUtils.isEmpty(userVo.getHobby())){
                UserVo checkUserVo  = indexService.selectUserByUserName(userVo.getUserName());
                if(checkUserVo != null){
                    model.addObject("errorInfo","用户名已存在，请重新输入！");
                    model.setViewName("index/register");
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
                    request.getSession().setAttribute("userId",userVo.getUserId());
                    response.sendRedirect(request.getContextPath()+"/index/index.html");
                    model.setViewName("index/index");
                }
            }else{
                model.addObject("errorInfo","必填项有空！");
                model.setViewName("index/register");
            }

        } catch (Exception e) {
            model.addObject("errorInfo","新增用户失败，请稍后再试！");
            model.setViewName("index/register");
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
        try {
            Object username = request.getSession().getAttribute("userName");
            if(username==null){
                model.addObject("loginType",null);//访问首页没登录时将登录按钮显示否则隐藏
            }else{
                model.addObject("loginType","logged");
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
    * @describe :   发布秘密
    * @param :
    * @return :
    */
    @RequestMapping("/addReleaseArticle")
    @ResponseBody
    public JsonData addReleaseArticle(String vo,HttpServletRequest request){
        JsonData jsonData = new JsonData();
        try{
            Object userId = request.getSession().getAttribute("userId");
            if(!StringUtils.isEmpty(vo)) {
                ArticleVo articleVo = JSON.parseObject(vo, ArticleVo.class);
                articleVo.setArticleId(UUIDUtils.getUUID());
                if(userId !=null ){
                    articleVo.setUserId(userId.toString());
                }
                articleVo.setArticleUsername(indexService.setlectStageName());
                articleVo.setBrowse(0L);
                articleVo.setCreateDate(new Date());
                articleVo.setCreateIp(IpInfoUtils.getVisitIp(request));
                articleVo.setDeleteTag("N");
                articleVo.setReportNum(0);
                articleVo.setReportTag("N");
                articleVo.setUpdateDate(null);
                articleVo.setUpdateIp("");
                indexService.insertArticleVo(articleVo);
                jsonData.setMsg("秘密分享成功！");
                jsonData.setStatus(true);
            }
        } catch (Exception e) {
            jsonData.setMsg("发布秘密失败，请稍后重试！");
            e.printStackTrace();
        }
        return jsonData;
    }
}
