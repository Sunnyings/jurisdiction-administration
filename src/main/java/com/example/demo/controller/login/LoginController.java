package com.example.demo.controller.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.BaseController;
import com.example.demo.controller.Result;
import com.example.demo.manage.Constants;
import com.example.demo.model.SessionUserBean;
import com.example.demo.model.SysMenuInfo;
import com.example.demo.service.sys.SysMenuInfoService;
import com.example.demo.util.SessionUtil;
import com.google.code.kaptcha.Producer;
@Controller
public class LoginController extends BaseController {
	@Autowired
	private SysMenuInfoService sysMenuInfoService;
	
	@Autowired 
	Producer captchaProducer;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletResponse response) {
        return "login";
    }
	
	@RequestMapping("main")
	public void main(HttpServletRequest request) {
		
	}
	@RequestMapping("/")
	public String  index(HttpServletRequest request,Model mode) {
		List<SysMenuInfo> topMenus;
		List<SysMenuInfo> childMenus;
		if (isDeveloper()) {//判断是否是开发者
			topMenus = sysMenuInfoService.getAllTopMenu();//获取一级菜单并从小到大排序
			childMenus = sysMenuInfoService.getAllChildMenu();//获取二级菜单并从小到大排序
		} else {
			SessionUserBean user = SessionUtil.getSessionUser(request);
			topMenus = sysMenuInfoService.getTopMenu(user.getId());
			childMenus = sysMenuInfoService.getChildMenu(user.getId());
		}
		Map<String, Object> menus = new HashMap<String, Object>();
		menus.put("tops", topMenus);
		menus.put("chidren", childMenus);
		mode.addAttribute("result", new Result<SysMenuInfo>(menus));
		
		return "index";//返回数据到index界面
	}
	@RequestMapping("/error_msg")
	public String  error(HttpServletRequest request,Model mode) {
		mode.addAttribute("error_msg", "登陆失败，密码错误");
		return "login";//返回数据到index界面
	}
	@RequestMapping("/checkinEerror")
	public String  checkinEerror(HttpServletRequest request,Model mode) {
		mode.addAttribute("checkinEerror_msg", "登陆失败，验证码错误");
		return "login";//返回数据到index界面
	}
	
	@RequestMapping(value = "/login_code", method = RequestMethod.GET)
    public void getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String capText = captchaProducer.createText();  
        request.getSession().setAttribute(Constants.LOGIN_CODE_SESSION_KEY, capText);
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg"); 
        
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(captchaProducer.createImage(capText), "jpg", out);  
        try { 
            out.flush();  
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
