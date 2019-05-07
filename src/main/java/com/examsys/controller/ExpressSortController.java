package com.examsys.controller;



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

import com.examsys.po.Express;
import com.examsys.po.ExpressSort;
import com.examsys.service.ExpressSortService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 快递分捡
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/expressSort")
public class ExpressSortController {

	//日志
	private Logger log = Logger.getLogger(this.getClass());
	
	//注入业务层
	@Resource
	private ExpressSortService expressSortService;
	
	
	/**
	 * 保存添加
	 * @param express
	 * @param req
	 * @param exp_company_id
	 * @return
	 */
	@RequestMapping("addSave")
	public @ResponseBody Map addSave(Express express,HttpServletRequest req,
			@RequestParam(name="exp_company_id",required=true) Integer exp_company_id){
		log.info("接收到页面添加的数据："+ express +",快递公司编号："+ exp_company_id);
		Map jsonDatas = new HashMap(); //存放json数据的集合
		jsonDatas.put("status", 0); //默认状态为0，表示操作失败
		try {
			
			
			
		} catch (Exception e) {
			log.error("添加失败",e);
		}
		
		return jsonDatas;
	}
	
	/**
	 * 初始化修改
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody ExpressSort update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为：" + id + "的系统功能数据供前端修改");//把id放到日志
		ExpressSort expressSort = null;
		
		try {
			expressSort = expressSortService.get(id);
		} catch (Exception e) {
			log.error("初始化修改失败",e);
		}
		
		return expressSort;
	}
	
	/**
	 * 保存修改
	 * @param express
	 * @param exp_company_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(Express express,
			@RequestParam(name="exp_company_id",required=true)Integer exp_company_id){
		log.info("接收到要修改的数据为："+express);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		try {
			
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
		mv.setViewName("expressSort/ExpressSortManager");
		
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
	public @ResponseBody Map selectDatas(ExpressSort expressSort,
			//page当前是第几页
			@RequestParam(value="page",required=false)int page,
			//rows每页几条数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("开始获取快递录入管理表数据");
		Map jsonDatas = new HashMap();
		List<ExpressSort> expList = new ArrayList<ExpressSort>();
		try {
			//分页处理
			PageHelper.startPage(page, rows);
			expList = expressSortService.getList(expressSort);
			//取记录总条数
			PageInfo<ExpressSort> pageInfo = new PageInfo<ExpressSort>(expList);
			jsonDatas.put("total", pageInfo.getTotal()); //总数
			jsonDatas.put("rows", expList); //前端需要的行数据
			
		} catch (Exception e) {
			log.error("获取快递录入管理表格数据失败",e);
		}
		return jsonDatas; //返回存入数据的集合，最终给springmvc转换为json数据输出给浏览器
	}
	
}
