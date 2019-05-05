package com.examsys.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.examsys.po.Employee;
import com.examsys.service.CustomService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 客户基础信息控制层
 * @author edu
 *
 */
@Controller
@RequestMapping("/custom")
public class CustomController {

	// 日志
	private Logger log = Logger.getLogger(this.getClass());
	
	// 注入业务层
	@Resource
	private CustomService customService;
	
	
	
	/**
	 * 添加
	 * @param custom
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(Custom custom){
		
		log.info("页面添加的数据"+ custom);
		Map jsonDatas = new HashMap(); // 存放json数据的集合
		
		jsonDatas.put("status", 0); // 默认状态为0，表示操作失败
		try {
			
			custom.setCreateDate(new Date(System.currentTimeMillis())); //创建时间
			// 调用业务逻辑层的方法保存custom对象
			boolean flag = customService.add(custom);
			
			if(flag){
				jsonDatas.put("status", 1);  // 设置状态为1，表示操作成功
			}
		} catch (Exception e) {
			log.error("添加失败",e);
		}
		
		
		return jsonDatas;  // 返回存放json数据的集合，最终给springmvc转换为json数据输出给浏览器
	}
	
	
	/**
	 * 初始化修改
	 * @param id 待修改的功能的编号
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Custom update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的系统功能数据供前端修改");//把id放到日志
		Custom custom=null;
		try {
			//通过id去获取系统功能信息
			custom=customService.get(id);//通过id拿到功能对象赋给rolesSettings变量
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return custom;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 保存修改
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(Custom custom){
		log.info("接收到页面添加的数据："+custom);//把custom放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			Custom oldCustom=customService.get(custom.getId());//通过id找到对应的这一条系统功能信息
			//找到之后，把名字放进去给它
			
			oldCustom.setName(custom.getName());
			oldCustom.setEducation(custom.getEducation());
			oldCustom.setPhoneNo(custom.getPhoneNo());
			oldCustom.setQq(custom.getQq());
			oldCustom.setEmail(custom.getEmail());
			oldCustom.setCustomStatu(custom.getCustomStatu());
			/*oldCustom.setCreateDate(custom.getCreateDate());*/
			oldCustom.setInviteName(custom.getInviteName());
			
			
			//调用修改的方法，修改原先的信息
			boolean flag = customService.update(oldCustom);
			
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
			boolean flag = customService.delete(id);
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}

	/**
	 * 客户基础信息页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入重置密码页面");
		ModelAndView mv = new ModelAndView(); //创建视图模型
		mv.setViewName("custom/CustomManager"); // 设置显示页面
		return mv;
	}
	
	
	/**
	 * 客户基础信息表格数据
	 * @param resetpass
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(Custom custom,
			// 当前页
			@RequestParam(value="page",required=false)int page,
			// 每页显示数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("开始获取表格数据");
		Map jsonDatas = new HashMap(); //待返回的json集合数据
		List<Custom> customList = new ArrayList<Custom>(); // 待返回系统的数据
		
		try {
			// 分页处理
			PageHelper.startPage(page, rows);
			customList = customService.getList(custom);
			// 获取记录总条数
			PageInfo<Custom> pageInfo = new PageInfo<Custom>(customList);
			jsonDatas.put("total", pageInfo.getTotal()); // 总数
			jsonDatas.put("rows", customList);
		} catch (Exception e) {
			log.error("获取数据失败",e);
		}
		
		return jsonDatas; // 返回存放数据集合，最终给springmvc转换为json数据输出给浏览器 
		
	}
	
	
	
	
}
