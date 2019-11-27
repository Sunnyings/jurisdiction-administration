package com.example.demo.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.controller.Result;
import com.example.demo.model.SysResourcesInfo;
import com.example.demo.model.SysRoleInfo;
import com.example.demo.service.role.RoleService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String  authRoleIndex(HttpServletResponse response) {
		response.addHeader("x-frame-options","SAMEORIGIN");
		return "/role/index";
	}
	  @RequestMapping(value = "/list", method = RequestMethod.GET)
		@ResponseBody
		public Result<SysRoleInfo>  list(HttpServletResponse response,SysRoleInfo role) {
			PageInfo<SysRoleInfo> pageInfo=roleService.page(role);
			Result<SysRoleInfo> result = new Result<SysRoleInfo>(pageInfo.getList(),(int)pageInfo.getTotal());
			return result;
		}
	  @RequestMapping(value = "/add", method = RequestMethod.GET)
	  @ResponseBody 
	  public Result<SysRoleInfo> add(HttpServletResponse response,SysRoleInfo info) { 
		  int code=roleService.add(info);
		  if (code>0) {
			return new Result<SysRoleInfo>(0, "添加成功");
		}
		  return  new Result<SysRoleInfo>(1, "添加失败");
	}
	  @RequestMapping(value = "/give", method = RequestMethod.GET)
	  @ResponseBody 
	  public Result<SysResourcesInfo> give(HttpServletResponse response, String resourcesInfos,String roleUuid) { 
		  List<String> list = JSONArray.parseArray(resourcesInfos, String.class);
		  Integer codeInteger=roleService.give(list,roleUuid);
		 return  new Result<SysResourcesInfo>(codeInteger, "添加成功");
	}
	  
	  @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
	  @ResponseBody 
	  public Result<SysRoleInfo>  getRoleList(){
		 Result<SysRoleInfo> result = new Result<SysRoleInfo>(roleService.getRoleList());
		  return result;
	  }
}
