package com.example.demo.controller.sys;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.Result;
import com.example.demo.model.SysResourcesInfo;
import com.example.demo.service.auth.AuthorityService;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/auth")
public class AuthorityController {

	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String  index(HttpServletResponse response) {
		response.addHeader("x-frame-options","SAMEORIGIN");
		return "auth/index";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Result<SysResourcesInfo>  list(HttpServletResponse response,SysResourcesInfo info) {
		PageInfo<SysResourcesInfo> pageInfo=authorityService.page(info);
		Result<SysResourcesInfo> result = new Result<SysResourcesInfo>(pageInfo.getList(),(int)pageInfo.getTotal());
		return result;
	}
	
	@RequestMapping(value = "/getResouList", method = RequestMethod.GET)
	@ResponseBody
	public Result<SysResourcesInfo>  getResouList(HttpServletResponse response,String roleUuid) {
		PageInfo<SysResourcesInfo> pageInfo=authorityService.getResouList(roleUuid);
		Result<SysResourcesInfo> result = new Result<SysResourcesInfo>(pageInfo.getList(),(int)pageInfo.getTotal());
		return result;
	}
	  @RequestMapping(value = "/add", method = RequestMethod.GET)
	  @ResponseBody 
	  public Result<SysResourcesInfo> add(HttpServletResponse response,SysResourcesInfo info) { 
		  int code=authorityService.add(info);
		  if (code>0) {
			return new Result<SysResourcesInfo>(0, "添加成功");
		}
		  return  new Result<SysResourcesInfo>(0, "添加失败");
	 }
	  
	  @RequestMapping(value = "/del", method = RequestMethod.GET)
	  @ResponseBody 
	  public Result<SysResourcesInfo> del(HttpServletResponse response,SysResourcesInfo info) {
		  int code=authorityService.del(info);
		  if (code==0) {
			return new Result<SysResourcesInfo>(0, "添加成功");
		   }
		  return  new Result<SysResourcesInfo>(1, "添加失败");
		  }
}
