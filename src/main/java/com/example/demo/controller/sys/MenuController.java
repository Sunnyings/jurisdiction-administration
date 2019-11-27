package com.example.demo.controller.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.controller.Result;
import com.example.demo.model.SysMenuInfo;
import com.example.demo.model.SysRoleInfo;
import com.example.demo.service.menu.MenuService;
import com.example.demo.service.sys.SysMenuInfoService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping(value = "menu")
public class MenuController {

	@Autowired
	MenuService menuService;
	
	@Autowired
	private SysMenuInfoService sysMenuInfoService;
	
	@RequestMapping(value = "index",method = RequestMethod.GET)
	public String index() {
		return "/menu/index";
	}
	
	@RequestMapping(value = "list",method = RequestMethod.GET)
	@ResponseBody
	public Result<SysMenuInfo> list(SysMenuInfo info) {
		PageInfo<SysMenuInfo> pageInfo=menuService.page(info);
		Result<SysMenuInfo> result = new Result<SysMenuInfo>(pageInfo.getList(),(int)pageInfo.getTotal());
		return result;
	}
	
	@RequestMapping(value = "getAllTopMenu",method = RequestMethod.GET)
	@ResponseBody
	public Result<SysMenuInfo> getAllTopMenu() {
		List<SysMenuInfo> list=sysMenuInfoService.getAllTopMenu();
		Result<SysMenuInfo> result = new Result<SysMenuInfo>(list);
		return result;
	}
	
	@RequestMapping(value = "add",method = RequestMethod.GET)
	@ResponseBody
	public Result<SysMenuInfo> add(SysMenuInfo info) {
		 int code=menuService.add(info);
		  if (code==0) {
			return new Result<SysMenuInfo>(0, "添加成功");
		}
		  return  new Result<SysMenuInfo>(1, "添加失败");
	}
}
