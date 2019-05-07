package com.examsys.controller;



import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.examsys.po.Employee;
import com.examsys.po.Express;
import com.examsys.po.ExpressCompany;
import com.examsys.service.ExpressCompanyService;
import com.examsys.service.ExpressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 快递接收管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/expressReceive")
public class ExpressReceiveController {

	//日志
	private Logger log = Logger.getLogger(this.getClass());
	
	//注入业务层
	@Resource
	private ExpressService expressService;
	
	@Resource
	private ExpressCompanyService expressCompanyService;
	
	/**
	 * 初始化快递公司下拉框
	 * @return
	 */
	@RequestMapping("/expressCompanyDatas")
	public @ResponseBody List<ExpressCompany> expressCompanyDatas(){
		log.info("获取快递公司下拉框数据");
		List<ExpressCompany> expressCompanyList = null;
		try {
			expressCompanyList = expressCompanyService.getList();
		} catch (Exception e) {
			log.error("获取下拉框数据失败",e);
		}
		
		return expressCompanyList;
	}
	
	
	/**
	 * 初始化修改
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Express update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为：" + id + "的系统功能数据供前端修改");//把id放到日志
		Express express = null;
		
		try {
			express = expressService.get(id);
		} catch (Exception e) {
			log.error("初始化修改失败",e);
		}
		
		return express;
	}
	
	/**
	 * 保存修改
	 * @param express
	 * @param exp_company_id
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public @ResponseBody Map updateSave(Express express){
		log.info("接收到要修改的数据为："+express);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		try {
			Express oldExpress = expressService.get(express.getId());
			oldExpress.setExp_status("2");

			boolean flag = expressService.update(oldExpress);
			if(flag){
				jsonDatas.put("status", 1);
			}
		} catch (Exception e) {
			log.error("修改失败",e);
		}
		return jsonDatas;
	}
	
	
	/**
	 * 快递管理基础信息页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入快递管理页面");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("expressReceive/ExpressReceiveManager");
		return mv;
	}

	/**
	 * 快递录入管理表格数据
	 * @param express
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(Express express,
			//page当前是第几页
			@RequestParam(value="page",required=false)int page,
			//rows每页几条数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("开始获取快递录入管理表数据");
		Map jsonDatas = new HashMap();
		List<Express> expList = new ArrayList<Express>();
		try {
			//分页处理
			PageHelper.startPage(page, rows);
			expList = expressService.getList(express);
			//取记录总条数
			PageInfo<Express> pageInfo = new PageInfo<Express>(expList);
			jsonDatas.put("total", pageInfo.getTotal()); //总数
			jsonDatas.put("rows", expList); //前端需要的行数据
			
		} catch (Exception e) {
			log.error("获取快递录入管理表格数据失败",e);
		}
		return jsonDatas; //返回存入数据的集合，最终给springmvc转换为json数据输出给浏览器
	}
	
}
