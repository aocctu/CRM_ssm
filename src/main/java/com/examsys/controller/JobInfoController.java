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

import com.examsys.po.Department;
import com.examsys.po.JobInfo;
import com.examsys.service.DepartmentService;
import com.examsys.service.JobInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 职位控制层
 * @author edu
 *
 */
@Controller
@RequestMapping("/jobInfo")
public class JobInfoController {
	
	// log4j 日志对象
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private JobInfoService jobInfoService; // 注入业务逻辑层
	
	@Resource
	private DepartmentService departmentService; 
	
	
	
	
	
	/**
	 * 初始化部门下拉框
	 * @return
	 */
	@RequestMapping("/departmentDatas")
	public @ResponseBody List<Department> departmentDatas(){
		log.info("获取部门下拉框数据");
		 List<Department> departmentsList = null;
		 try {
			departmentsList = departmentService.getList();
		} catch (Exception e) {
			log.error("获取下拉框数据失败",e);
		}
		 return departmentsList; // 返回存放数据的集合，最终级springmvc转换为json数据输出给浏览器
	}
	
	
	/**
	 * 保存添加
	 * @param admin
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(JobInfo jobInfo,
			@RequestParam(name="departmentid",required=true) Integer departmentid){
		log.info("接收到页面添加的数据："+jobInfo+",角色编号："+departmentid);//把admin放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			Department department = departmentService.get(departmentid);
			jobInfo.setDepartment(department);
			//通过用户名去获取管理员
			boolean flag = jobInfoService.add(jobInfo);
			
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
	public @ResponseBody JobInfo update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的系统功能数据供前端修改");//把id放到日志
		JobInfo jobInfo=null;
		try {
			//通过id去获取系统功能信息
			jobInfo=jobInfoService.get(id);//通过id拿到功能对象赋给rolesSettings变量
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return jobInfo;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 保存修改
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(JobInfo jobInfo,
			//上面这个是父级编号，获取父级编号信息时，直接把parent_id放进去
			@RequestParam(name="departmentid",required=false) Integer departmentid){
		log.info("接收到页面添加的数据："+jobInfo+",部门编号："+departmentid);//把consultRecord放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			JobInfo oldjobInfo=jobInfoService.get(jobInfo.getId());//通过id找到对应的这一条系统功能信息
			//找到之后，把名字放进去给它
			
			oldjobInfo.setJob(jobInfo.getJob());
			
			
			if(departmentid!=null){
				Department department  = departmentService.get(departmentid);
				oldjobInfo.setDepartment(department);
			}
			
			
			//调用修改的方法，修改原先的信息
			boolean flag = jobInfoService.update(oldjobInfo);
			
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
			boolean flag = jobInfoService.delete(id);
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 职位管理页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入职位管理页面");
		ModelAndView mv = new ModelAndView(); // 创建视图模型对象
		mv.setViewName("jobInfo/JobInfoManager"); //设置显示页面
		return mv; //返回视图模型对象
	}
	
	/**
	 * 职位管理表格数据
	 * @param jobInfo 查询条件
	 * @param page 页码
	 * @param rows 第一页行数
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(JobInfo jobInfo ,
			// page当前页
			@RequestParam(value="page",required=false)int page,
			// rows每页显示的数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("开始获取职位管理表格数据");
		Map jsonDatas = new HashMap(); //待返回json集合数据 
		List<JobInfo> jobInfosList = new ArrayList<JobInfo>(); //待返回系统 功能数据 
		try {
			// 分布处理
			PageHelper.startPage(page,rows);
			jobInfosList = jobInfoService.getList(jobInfo);
			// 取记录总条数
			PageInfo<JobInfo> pageInfo = new PageInfo<JobInfo>(jobInfosList);
			jsonDatas.put("total", pageInfo.getTotal()); //总数
			jsonDatas.put("rows", jobInfosList); //前端需要的行数据
		} catch (Exception e) {
			log.error("获取职位表格失败",e);
		}
		
		return jsonDatas; //返回存放数据的集合,最终给springmvc转换为json数据输出给浏览器
	}

}
