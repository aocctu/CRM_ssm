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

import com.examsys.po.ConsultRecord;
import com.examsys.po.Custom;
import com.examsys.po.CustomInfo;
import com.examsys.po.Department;
import com.examsys.po.Employee;
import com.examsys.po.JobInfo;
import com.examsys.service.CustomInfoService;
import com.examsys.service.CustomService;
import com.examsys.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 销售客户跟踪信息控制层
 * @author edu
 *
 */
@Controller
@RequestMapping("/customInfo")
public class CustomInfoController {

	// 日志
	private Logger log = Logger.getLogger(this.getClass());
	
	//注入业务层
	@Resource
	private CustomInfoService customInfoService;
	@Resource
	private CustomService customService;  // 客户
	@Resource
	private EmployeeService employeeService; // 员工
	
	
	
	/**
	 * 初始化客户下拉框
	 * @return
	 */
	@RequestMapping("/customDatas")
	public @ResponseBody List<Custom> customDatas(){
		log.info("获取客户下拉框数据");
		 List<Custom> customsList = null;
		 try {
			 customsList = customService.getList();
		} catch (Exception e) {
			log.error("获取下拉框数据失败",e);
		}
		 return customsList; // 返回存放数据的集合，最终级springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 初始化员工下拉框
	 * @return
	 */
	@RequestMapping("/employeeDatas")
	public @ResponseBody List<Employee> employeeDatas(){
		log.info("获取部门下拉框数据");
		 List<Employee> employeesList = null;
		 try {
			 employeesList = employeeService.getList();
		} catch (Exception e) {
			log.error("获取下拉框数据失败",e);
		}
		 return employeesList; // 返回存放数据的集合，最终级springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 保存添加
	 * @param admin
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(CustomInfo customInfo,
			@RequestParam(name="customid",required=true) Integer customid,
			@RequestParam(name="followManid",required=true) Integer followManid){
		log.info("接收到页面添加的数据："+customInfo+",客户编号："+customid+",员工编号："+followManid);//把customInfo放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			Custom custom = customService.get(customid);
			Employee employee = employeeService.get(followManid);
			customInfo.setCustom(custom);
			customInfo.setEmployee(employee);
			//通过用户名去获取管理员
			boolean flag = customInfoService.add(customInfo);
			
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
	public @ResponseBody CustomInfo update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的系统功能数据供前端修改");//把id放到日志
		CustomInfo customInfo=null;
		try {
			//通过id去获取系统功能信息
			customInfo=customInfoService.get(id);//通过id拿到功能对象赋给rolesSettings变量
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return customInfo;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 保存修改
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(CustomInfo customInfo,
			//上面这个是父级编号，获取父级编号信息时，直接把parent_id放进去
			@RequestParam(name="customid",required=false) Integer customid,
			@RequestParam(name="followManid",required=false) Integer followManid){
		log.info("接收到页面添加的数据："+customInfo+",客户编号："+customid+",员工编号："+followManid);//把consultRecord放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			CustomInfo oldcustomInfo=customInfoService.get(customInfo.getId());//通过id找到对应的这一条系统功能信息
			//找到之后，把名字放进去给它
			
			oldcustomInfo.setStatu(customInfo.getStatu());
			oldcustomInfo.setStartDate(customInfo.getStartDate());
			oldcustomInfo.setLastFollowDate(customInfo.getLastFollowDate());
			oldcustomInfo.setPlanDate(customInfo.getPlanDate());
			oldcustomInfo.setMark(customInfo.getMark());
			
			
			
			if(customid!=null){
				Custom custom  = customService.get(customid);
				oldcustomInfo.setCustom(custom);
			}
			if(followManid!=null){
				Employee employee = employeeService.get(followManid);
				oldcustomInfo.setEmployee(employee);
			}
			
			
			//调用修改的方法，修改原先的信息
			boolean flag = customInfoService.update(oldcustomInfo);
			
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
		log.info("开始删除编号为"+id+"的功能");//把id放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			//通过id去删除数据
			boolean flag = customInfoService.delete(id);
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	

	/**
	 * 销售客户跟踪信息页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入重置密码页面");
		ModelAndView mv = new ModelAndView(); //创建视图模型
		mv.setViewName("customInfo/CustomInfoManager"); // 设置显示页面
		return mv;
	}
	
	
	/**
	 * 销售客户跟踪信息表格数据
	 * @param resetpass
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(CustomInfo customInfo,
			// 当前页
			@RequestParam(value="page",required=false)int page,
			// 每页显示数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("开始获取表格数据");
		Map jsonDatas = new HashMap(); //待返回的json集合数据
		List<CustomInfo> customInfoList = new ArrayList<CustomInfo>(); // 待返回系统的数据
		try {
			// 分页处理
			PageHelper.startPage(page, rows);
			customInfoList = customInfoService.getList(customInfo);
			// 获取记录总条数
			PageInfo<CustomInfo> pageInfo = new PageInfo<CustomInfo>(customInfoList);
			jsonDatas.put("total", pageInfo.getTotal()); // 总数
			jsonDatas.put("rows", customInfoList);
		} catch (Exception e) {
			log.error("获取数据失败",e);
		}
		
		return jsonDatas; // 返回存放数据集合，最终给springmvc转换为json数据输出给浏览器 
		
	}
	
	
	
}
