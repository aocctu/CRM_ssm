package com.examsys.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.examsys.po.Admin;
import com.examsys.po.AdminRoles;
import com.examsys.service.AdminRolesService;
import com.examsys.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 管理员控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	//log4j日志对象
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private AdminService adminService;//业务逻辑层对象
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private AdminRolesService adminRolesService;//业务逻辑层对象
 	
	/**
	 * 初始化角色数据
	 * @return
	 */
	@RequestMapping("/rolesDatas")
	public @ResponseBody List<AdminRoles> rolesDatas(){
		log.info("获取角色下拉框数据");
		List<AdminRoles> adminRolesList=null;
		try {
			adminRolesList = adminRolesService.getList();
		
		} catch (Exception e) {
			log.error("获取系统功能父级下拉框数据失败",e);
		}
		return adminRolesList;//返回存放数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 保存添加
	 * @param admin
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(Admin admin,
			@RequestParam(name="role_id",required=true) Integer role_id){
		log.info("接收到页面添加的数据："+admin+",角色编号："+role_id);//把admin放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			AdminRoles adminRoles = adminRolesService.get(role_id);
			admin.setAdminRoles(adminRoles);
			admin.setCreate_date(new Date(System.currentTimeMillis())); //创建时间
			//通过用户名去获取管理员
			boolean flag = adminService.add(admin);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		
		} catch (Exception e) {
			log.error("添加失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 初始化修改
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Admin update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的管理员数据供前端修改");//把id放到日志
		Admin admin = null;
		try {
			//通过id去获取管理员
			admin = adminService.get(id);
			
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return admin;
	}
	
	/**
	 * 保存修改
	 * @param admin
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(Admin admin,
			@RequestParam(name="roles_id",required=true) Integer roles_id){
		
		log.info("接收到页面修改的数据："+admin+",角色编号："+roles_id);//把admin放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		
		try {
			//通过编号获取管理员
			Admin oldAdmin = adminService.get(admin.getId());
			
			//填上修改过的数据
			oldAdmin.setUser_name(admin.getUser_name());
			oldAdmin.setUser_pass(admin.getUser_pass());
			oldAdmin.setPhone(admin.getPhone());
			oldAdmin.setStatus(admin.getStatus());
			oldAdmin.setRemark(admin.getRemark());
			
			if(roles_id!=null){
				AdminRoles adminRoles = adminRolesService.get(roles_id);
				oldAdmin.setAdminRoles(adminRoles);
			}else{
				oldAdmin.setAdminRoles(null);
			}
			
			//调用业务逻辑层保存修改的管理员
			boolean flag = adminService.update(oldAdmin);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		
		} catch (Exception e) {
			log.error("修改失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 删除管理员
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam(name="id",required=true) Integer id){
		log.info("开始删除编号为"+id+"的管理员");//把id放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		
		try {
			//通过编号删除管理员
			boolean flag = adminService.delete(id);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
			
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 管理员管理
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入管理员管理页面");
		ModelAndView mv = new ModelAndView();//创建视图模型对象
		mv.setViewName("admin/AdminManager");//设置显示页面
		return mv;//返回视图模型对象
	}
	
	/**
	 * 管理员管理表格数据
	 * @param admin 查询条件
	 * @param page 页码
	 * @param rows 每一页行数
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(Admin admin,
			//page当前是第几页
			@RequestParam(value="page",required=false)int page,
			//rows每页几条数据
			@RequestParam(value="rows",required=false)int rows){
		
		log.info("开始获取管理员管理表格数据");
		Map jsonDatas=new HashMap();//待返回json集合数据;
		List<Admin> adminList=new ArrayList<Admin>();//待返回系统功能数据;
		try {
			//分页处理
			PageHelper.startPage(page, rows);
			adminList = adminService.getList(admin);
			//取记录总条数
			PageInfo<Admin> pageInfo = new PageInfo<Admin>(adminList);
			jsonDatas.put("total", pageInfo.getTotal());//总数
			jsonDatas.put("rows", adminList);//前端需要的行数据
		} catch (Exception e) {
			log.error("获取管理员管理表格数据失败",e);
		}
		return jsonDatas;//返回存放数据的集合,最终给springmvc转换为json数据输出给浏览器
		
	}
}
