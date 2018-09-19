package com.secret.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		try {
            Object username = request.getSession().getAttribute("userName");
            if(username==null){
                model.addObject("loginType",null);//访问首页没登录时将登录按钮显示否则隐藏
            }else{
                model.addObject("loginType","logged");
            }
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
                request.getSession().removeAttribute("userName");
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
            //伪代码
            if(!StringUtils.isEmpty(userName) && userName.equals("admin")
            &&!StringUtils.isEmpty(password) && password.equals("admin")){
                request.getSession().setAttribute("userName",userName);
                response.sendRedirect(request.getContextPath()+"/index/index.html");
                model.setViewName("index/index");
            }else{
                model.addObject("errorInfo","用户名或密码错误！");
                model.setViewName("index/login");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
