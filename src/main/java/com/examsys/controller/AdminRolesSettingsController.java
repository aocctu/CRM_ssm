package com.examsys.controller;

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

import com.examsys.po.AdminRolesSettings;
import com.examsys.service.AdminRolesSettingsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 系统功能管理控制层
 *
 */
@Controller
@RequestMapping("/adminRolesSettings")
public class AdminRolesSettingsController {

	//log4j日志对象
	private Logger log=Logger.getLogger(this.getClass());
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private AdminRolesSettingsService adminRolesSettingsService;
	
	/**
	 * 保存添加
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(AdminRolesSettings adminRolesSettings,
			//上面这个是父级编号，获取父级编号信息时，直接把parent_id放进去
			@RequestParam(name="parent_id",required=false) Integer parent_id
			){
		                                                             
		log.info("接收到页面添加的数据："+adminRolesSettings+",父级编号："+parent_id);//把adminRolesSettings放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			AdminRolesSettings pRolesSettings=adminRolesSettingsService.get(parent_id);//获取父级编号，并用rolesSettings接收
			adminRolesSettings.setAdminRolesSettings(pRolesSettings);//把接收了父级编号的rolesSettings放到自己的adminRolesSettings里面去
			
			boolean flag = adminRolesSettingsService.add(adminRolesSettings);//然后把adminRolesSettings添加进去
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
	 * @param id 待修改的功能的编号
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody AdminRolesSettings update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的系统功能数据供前端修改");//把id放到日志
		AdminRolesSettings rolesSettings=null;
		try {
			//通过id去获取系统功能信息
			rolesSettings=adminRolesSettingsService.get(id);//通过id拿到功能对象赋给rolesSettings变量
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return rolesSettings;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 保存修改
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(AdminRolesSettings adminRolesSettings,
			//上面这个是父级编号，获取父级编号信息时，直接把parent_id放进去
			@RequestParam(name="parent_id",required=false) Integer parent_id
			){
		log.info("接收到页面修改的数据："+adminRolesSettings+",父级编号："+parent_id);//把adminRolesSettings放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			AdminRolesSettings oldrolesSettings=adminRolesSettingsService.get(adminRolesSettings.getId());//通过id找到对应的这一条系统功能信息
			//找到之后，把名字放进去给它
			oldrolesSettings.setName(adminRolesSettings.getName());
			oldrolesSettings.setUrl(adminRolesSettings.getUrl());
			oldrolesSettings.setPorder(adminRolesSettings.getPorder());
			oldrolesSettings.setCode(adminRolesSettings.getCode());
			
			if(parent_id!=null){
				AdminRolesSettings pRolesSettings=adminRolesSettingsService.get(parent_id);//获取父级编号，并用rolesSettings接收
				oldrolesSettings.setAdminRolesSettings(pRolesSettings);//设置关联，把接收了的父级编号对应的pRolesSettings放到自己的adminRolesSettings属性里面去
			}else{
				oldrolesSettings.setAdminRolesSettings(null);
			}
			//调用修改的方法，修改原先的信息
			boolean flag = adminRolesSettingsService.update(oldrolesSettings);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		
		} catch (Exception e) {
			log.error("修改失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 删除功能
	 * @param id待删除的功能的编号
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam(name="id",required=true) Integer id){
		log.info("开始删除编号为"+id+"的系统功能");//把id放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			//通过id去删除数据
			boolean flag = adminRolesSettingsService.delete(id);
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 系统功能管理页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入系统功能管理页面");
		ModelAndView mv=new ModelAndView();//创建视图模型对象
		mv.setViewName("adminRolesSettings/AdminRolesSettingsManager");//设置显示页面
		return mv;//返回视图模型对象
		
	}
	
	/**
	 * 系统功能父级下拉框数据
	 * @return
	 */
	@RequestMapping("/parentDatas")
	public @ResponseBody List<AdminRolesSettings> parentDatas(){
		log.info("获取系统功能父级下拉框数据");
		List<AdminRolesSettings> adminRolesrolesSettingsList=new ArrayList<AdminRolesSettings>();//待返回集合数据
		try {
			adminRolesrolesSettingsList = adminRolesSettingsService.getList();//获取所有系统功能
		} catch (Exception e) {
			log.error("获取系统功能父级下拉框数据失败",e);
		}
		return adminRolesrolesSettingsList;//返回存放数据的集合,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 系统功能管理表格数据
	 * @param adminRolesSettings 查询条件
	 * @param page 页码
	 * @param rows 每一页行数
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(AdminRolesSettings adminRolesSettings,
			//page当前是第几页
			@RequestParam(value="page",required=false)int page,
			//rows每页几条数据
			@RequestParam(value="rows",required=false)int rows){
		
		log.info("开始获取系统功能管理表格数据");
		Map jsonDatas=new HashMap();//待返回json集合数据;
		List<AdminRolesSettings> adminRolesrolesSettingsList=new ArrayList<AdminRolesSettings>();//待返回系统功能数据;
		try {
			//分页处理
			PageHelper.startPage(page, rows);
			adminRolesrolesSettingsList = adminRolesSettingsService.getList(adminRolesSettings);
			//取记录总条数
			PageInfo<AdminRolesSettings> pageInfo = new PageInfo<AdminRolesSettings>(adminRolesrolesSettingsList);
			jsonDatas.put("total", pageInfo.getTotal());//总数
			jsonDatas.put("rows", adminRolesrolesSettingsList);//前端需要的行数据
		} catch (Exception e) {
			log.error("获取系统功能管理表格数据失败",e);
		}
		return jsonDatas;//返回存放数据的集合,最终给springmvc转换为json数据输出给浏览器
		
	}
}

