package com.examsys.controller;

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

import com.examsys.po.ExpressCompany;
import com.examsys.service.ExpressCompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 快递公司控制层
 * @author edu
 *
 */
@Controller
@RequestMapping("/expressCompany")
public class ExpressCompanyController {

	// log4j日志对象
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private ExpressCompanyService expressCompanyService; // 业务逻辑层对象
	
	
	/**
	 * 添加快递公司
	 * @param ExpressCompany
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(ExpressCompany ExpressCompany){
		log.info("接收到页面添加的数据："+ ExpressCompany);
		
		Map jsonDatas = new HashMap(); // 存放json数据的集合
		jsonDatas.put("status", 0); // 默认状态为0，表示操作失败
		try {
			// 调用业务逻辑层的添加方法保存
			boolean flag = expressCompanyService.add(ExpressCompany);
			
			if(flag){
				jsonDatas.put("status", 1); // 设置状态为1 ， 表示操作成功
			}
		} catch (Exception e) {
			log.error("添加失败",e);
		}
		
		return jsonDatas;  // 返回存放json数据的集合，最终给springmvc转换成json数据输出给浏览器
	}
	
	
	/**
	 * 初始化修改
	 * @param id 待修改的功能的编号
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody ExpressCompany update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的系统功能数据供前端修改");//把id放到日志
		ExpressCompany ExpressCompany=null;
		try {
			//通过id去获取系统功能信息
			ExpressCompany=expressCompanyService.get(id);//通过id拿到功能对象赋给rolesSettings变量
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return ExpressCompany;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 保存修改
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(ExpressCompany ExpressCompany){
		log.info("接收到页面修改的数据："+ ExpressCompany);//把ExpressCompany放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			ExpressCompany oldExpressCompany=expressCompanyService.get(ExpressCompany.getId());//通过id找到对应的这一条系统功能信息
			//找到之后，把名字放进去给它
			
			oldExpressCompany.setExp_company(ExpressCompany.getExp_company());
			
			
			//调用修改的方法，修改原先的信息
			boolean flag = expressCompanyService.update(oldExpressCompany);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		
		} catch (Exception e) {
			log.error("修改失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	
	
	
	/**
	 * 删除快递公司
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam(name="id",required=true)Integer id){
		log.info("开始删除编号为："+id+"的快递公司");
		
		Map jsonDatas = new HashMap(); // 存放json数据的集合
		jsonDatas.put("status", 0);  // 默认状态为0，表示操作失败
		
		try {
			// 通过id去删除数据
			boolean flag = expressCompanyService.delete(id);
			
			if(flag){
				jsonDatas.put("status", 1); // 设置状态1，表示操作成功
			}
		} catch (Exception e) {
			log.error("删除失败",e);
		}
		return jsonDatas; // 返回存放json数据的集合，最终给springmvc转换成json数据输出给浏览器
	}
	
	
	/**
	 * 快递公司管理
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入快递公司管理页面");
		ModelAndView mv = new ModelAndView(); //创建视图模型对象
		mv.setViewName("expressCompany/ExpressCompanyManager"); // 设置显示 页面
		return mv; // 返回视图模型对象
	}
	
	/**
	 * 快递公司管理的数据
	 * @param ExpressCompany
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(ExpressCompany ExpressCompany ,
			// page当前第几页
			@RequestParam(value="page",required=false)int page,
			// rows每页几条数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("开始获取快递公司管理表格数据");
		Map jsonDatas = new HashMap();
		try {
			// 分布处理
			PageHelper.startPage(page, rows);
			List<ExpressCompany> ExpressCompanysList = expressCompanyService.getList(ExpressCompany);
			PageInfo<ExpressCompany> pageInfo = new PageInfo<ExpressCompany>(ExpressCompanysList);
			jsonDatas.put("total", pageInfo.getTotal()); //总数
			jsonDatas.put("rows", ExpressCompanysList); //前端而要的需要的行数据
	
		} catch (Exception e) {
			log.error("获取快递公司管理表格数据失败",e);
		}
		
		return jsonDatas; //返回存放数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
			
}