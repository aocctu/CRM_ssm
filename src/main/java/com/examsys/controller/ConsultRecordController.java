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
import com.examsys.po.Department;
import com.examsys.po.Employee;
import com.examsys.po.JobInfo;
import com.examsys.service.ConsultRecordService;
import com.examsys.service.CustomService;
import com.examsys.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 咨询师跟单记录控制层
 * @author edu
 *
 */
@Controller
@RequestMapping("/consultRecord")
public class ConsultRecordController {

	// 日志
	private Logger log = Logger.getLogger(this.getClass());
	
	// 注入业务层
	@Resource
	private ConsultRecordService consultRecordService;
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
	public @ResponseBody Map addSave(ConsultRecord consultRecord,
			@RequestParam(name="customid",required=true) Integer customid,
			@RequestParam(name="consultManid",required=true) Integer consultManid){
		log.info("接收到页面添加的数据："+consultRecord+",客户编号："+customid+",员工编号："+consultManid);//把consultRecord放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			Custom custom = customService.get(customid);
			Employee employee = employeeService.get(consultManid);
			consultRecord.setCustom(custom);
			consultRecord.setEmployee(employee);
			//通过用户名去获取管理员
			boolean flag = consultRecordService.add(consultRecord);
			
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
	public @ResponseBody ConsultRecord update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的系统功能数据供前端修改");//把id放到日志
		ConsultRecord consultRecord=null;
		try {
			//通过id去获取系统功能信息
			consultRecord=consultRecordService.get(id);//通过id拿到功能对象赋给rolesSettings变量
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return consultRecord;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 保存修改
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(ConsultRecord consultRecord,
			//上面这个是父级编号，获取父级编号信息时，直接把parent_id放进去
			@RequestParam(name="customid",required=false) Integer customid,
			@RequestParam(name="consultManid",required=false) Integer consultManid){
		log.info("接收到页面添加的数据："+consultRecord+",客户编号："+customid+",员工编号："+consultManid);//把consultRecord放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			ConsultRecord oldconsultRecord=consultRecordService.get(consultRecord.getId());//通过id找到对应的这一条系统功能信息
			//找到之后，把名字放进去给它
			
			oldconsultRecord.setConsultStatu(consultRecord.getConsultStatu());
			oldconsultRecord.setConsultDate(consultRecord.getConsultDate());
			oldconsultRecord.setResult(consultRecord.getResult());
			
			if(customid!=null){
				Custom custom  = customService.get(customid);
				oldconsultRecord.setCustom(custom);
			}
			if(consultManid!=null){
				Employee employee = employeeService.get(consultManid);
				oldconsultRecord.setEmployee(employee);
			}
			
			
			//调用修改的方法，修改原先的信息
			boolean flag = consultRecordService.update(oldconsultRecord);
			
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
			boolean flag = consultRecordService.delete(id);
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	

	/**
	 * 页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入重置密码页面");
		ModelAndView mv = new ModelAndView(); //创建视图模型
		mv.setViewName("consultRecord/ConsultRecordManager"); // 设置显示页面
		return mv;
	}
	
	
	/**
	 * 重置密码表格数据
	 * @param resetpass
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(ConsultRecord consultRecord,
			// 当前页
			@RequestParam(value="page",required=false)int page,
			// 每页显示数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("开始获取表格数据");
		Map jsonDatas = new HashMap(); //待返回的json集合数据
		List<ConsultRecord> consultRecordList = new ArrayList<ConsultRecord>(); // 待返回系统的数据
		
		try {
			// 分页处理
			PageHelper.startPage(page, rows);
			consultRecordList = consultRecordService.getList(consultRecord);
			// 获取记录总条数
			PageInfo<ConsultRecord> pageInfo = new PageInfo<ConsultRecord>(consultRecordList);
			jsonDatas.put("total", pageInfo.getTotal()); // 总数
			jsonDatas.put("rows", consultRecordList);
		} catch (Exception e) {
			log.error("获取数据失败",e);
		}
		
		return jsonDatas; // 返回存放数据集合，最终给springmvc转换为json数据输出给浏览器 
		
	}
	
	
	
}
