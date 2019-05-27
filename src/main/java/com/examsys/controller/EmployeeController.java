package com.examsys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.examsys.po.Department;
import com.examsys.po.Employee;
import com.examsys.po.JobInfo;
import com.examsys.service.DepartmentService;
import com.examsys.service.EmployeeService;
import com.examsys.service.JobInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 *  员工 控制层
 * @author edu
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	// log4j 日志
	private Logger log = Logger.getLogger(this.getClass());

	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private EmployeeService employeeService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private JobInfoService jobInfoService;
	

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
	 * 初始化部门下拉框
	 * @return
	 */
	@RequestMapping("/jobDatas")
	public @ResponseBody List<JobInfo> jobDatas(){
		log.info("获取部门下拉框数据");
		 List<JobInfo> jobInfoList = null;
		 try {
			 jobInfoList = jobInfoService.getList();
		} catch (Exception e) {
			log.error("获取下拉框数据失败",e);
		}
		 return jobInfoList; // 返回存放数据的集合，最终级springmvc转换为json数据输出给浏览器
	}
	

	/**
	 * 保存添加
	 * @param admin
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(Employee employee,
			@RequestParam(name="departmentid",required=true) Integer departmentid,
			@RequestParam(name="jobInfoid",required=true) Integer jobInfoid){
		log.info("接收到页面添加的数据："+employee+",部门编号："+departmentid+"职位："+jobInfoid);//把employee放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			Department department = departmentService.get(departmentid);
			JobInfo jobInfo = jobInfoService.get(jobInfoid);
						
			employee.setJobInfo(jobInfo);
			employee.setDepartment(department);
			employee.setPass(employee.getPass());
			//通过用户名去获取管理员
			boolean flag = employeeService.add(employee);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		System.out.println(employee.toString());
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
	public @ResponseBody Employee update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的系统功能数据供前端修改");//把id放到日志
		Employee employee=null;
		try {
			//通过id去获取系统功能信息
			employee=employeeService.get(id);//通过id拿到功能对象赋给rolesSettings变量
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return employee;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 保存修改
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(Employee employee,
			//上面这个是父级编号，获取父级编号信息时，直接把parent_id放进去
			@RequestParam(name="departmentid",required=false) Integer departmentid,
			@RequestParam(name="jobInfoid",required=false) Integer jobInfoid
			){
		log.info("接收到页面修改的数据："+employee+",部门编号："+departmentid+"职位："+jobInfoid);
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			Employee oldEmployee=employeeService.get(employee.getId());//通过id找到对应的这一条系统功能信息
			//找到之后，把名字放进去给它
			oldEmployee.setUsername(employee.getUsername());
			oldEmployee.setPass(employee.getPass());
			oldEmployee.setNickname(employee.getNickname());
			oldEmployee.setRealname(employee.getRealname());
			oldEmployee.setPhoneNo(employee.getPhoneNo());
			oldEmployee.setOfficeTel(employee.getOfficeTel());
			oldEmployee.setWorkStatu(employee.getWorkStatu());
			if(jobInfoid!=null){
				JobInfo jobInfo = jobInfoService.get(jobInfoid);
				oldEmployee.setJobInfo(jobInfo);
			}
			if(departmentid!=null){
				Department department = departmentService.get(departmentid);
				oldEmployee.setDepartment(department);
			}
			
			
			//调用修改的方法，修改原先的信息
			boolean flag = employeeService.update(oldEmployee);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		
		} catch (Exception e) {
			log.error("修改失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	

	
	
	/**
	 * 删除系统功能
	 * @param id待删除的功能的编号
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map delete(HttpServletRequest req){
		
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		String ids = req.getParameter("ids");
		String []id=ids.split(",");
		try {
			for(String i : id){
				//通过id去删除数据
				log.info("开始删除编号为"+i+"的用户");//把id放到日志
				employeeService.delete(Integer.valueOf(i).intValue());
			}
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	
	
	/**
	 * 员工管理
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入员工管理页面");
		ModelAndView mv = new ModelAndView(); // 创建视图模型
		mv.setViewName("employee/EmployeeManager"); //设置显示页面
		return mv;
	}
	
	
	/**
	 * 员工管理表格数据
	 * @param employee
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(Employee employee,
		// 当前页
		@RequestParam(value="page",required=false)int page,
		// 每页数据
		@RequestParam(value="rows",required=false)int rows){
		
		log.info("开始获取员工表格数据");
		Map jsonDatas = new HashMap(); // 待返回json集合数据
		List<Employee> employeesList = new ArrayList<Employee>(); // 待返回的员工数据
		
		try {
			// 分页处理
			PageHelper.startPage(page, rows);
			employeesList = employeeService.getList(employee);
			// 取记录总条数
			PageInfo<Employee> pageInfo = new PageInfo<Employee>(employeesList);
			jsonDatas.put("total", pageInfo.getTotal()); //总数
			jsonDatas.put("rows", employeesList); // 前端需要的行数
		} catch (Exception e) {
			log.error("获取员工数据失败",e);
		}
		
		
		return jsonDatas; //返回存放数据的集合,最终给springmvc转换为json数据输出给浏览器
	}

	
}
