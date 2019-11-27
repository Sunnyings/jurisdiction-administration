package com.example.demo.controller.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.Result;
import com.example.demo.exception.JurisdictionException;
import com.example.demo.model.BaseModel;
import com.example.demo.model.SessionUserBean;
import com.example.demo.service.sys.SysService;
import com.example.demo.util.SessionUtil;

@Controller
@RequestMapping("/sys")
public class SysController {
	@Autowired
	private SysService sysService;
	
	@RequestMapping(value = "/auth/add", method = RequestMethod.GET)
	public String  add(HttpServletResponse response) {
		response.addHeader("x-frame-options","SAMEORIGIN");
		return "sys/index";
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	@ResponseBody
	public Result<BaseModel> updatePassword(@RequestBody JSONObject params,HttpServletRequest request) throws JurisdictionException{
		SessionUserBean user=SessionUtil.getSessionUser(request);
		Boolean success=sysService.updatePassword(user.getId(), params.getString("oldPass"), params.getString("newPass"));
		return new Result<BaseModel>(success?0:1,success?"修改密码成功!":"修改密码失败!");
	}
	
	
	
	
}
