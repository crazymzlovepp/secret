package com.secret.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView goToindex(ModelAndView model, String userName){
		try {
			model.setViewName("index/index");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
    /**
     * @author      mym
     * @date        2018/9/17 0017 16:18
     * @describe    登录或者注册
     * @version     V1.0
     * @param       [model, userName]
     * @return      org.springframework.web.servlet.ModelAndView
    */
    @RequestMapping("/loginOrRegister")
    public ModelAndView loginOrRegister(ModelAndView model, String id){
        try {
            if(!StringUtils.isEmpty(id) && id.equals("login_id")){
                model.setViewName("index/login");
            }else if(!StringUtils.isEmpty(id) && id.equals("register_id")){
                model.setViewName("index/register");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
