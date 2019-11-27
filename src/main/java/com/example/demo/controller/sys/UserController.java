package com.example.demo.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.Result;
import com.example.demo.model.SysUserInfo;
import com.example.demo.service.user.UserService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	@RequestMapping("index")
	public String index() {
		return "/user/index";
	}
	@RequestMapping("list")
	@ResponseBody
	public Result<SysUserInfo> list(SysUserInfo info) {
		PageInfo<SysUserInfo> pageInfo=userService.page(info);
		Result<SysUserInfo> result = new Result<SysUserInfo>(pageInfo.getList(),(int)pageInfo.getTotal());
		return result;
	}
	
	@RequestMapping(value = "addUser", method = RequestMethod.POST)
	@ResponseBody
	public Result<SysUserInfo> addUser(@RequestBody JSONObject params,HttpServletRequest request) {
		int code=userService.addUser(params);
		if(code==0) {
			return new Result<SysUserInfo>(0, "添加成功");
		}else {
			return new Result<SysUserInfo>(1, "添加失败，已存在该用户");
		}
	}
	@RequestMapping(value = "del", method = RequestMethod.GET)
	@ResponseBody
	public Result<SysUserInfo> del(SysUserInfo info) {
		int code=userService.del(info);
		if(code==0) {
			return new Result<SysUserInfo>(0, "删除成功");
		}else {
			return new Result<SysUserInfo>(1, "删除失败");
		}
	}
}
