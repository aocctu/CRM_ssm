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

import com.examsys.po.ExpressSort;
import com.examsys.service.ExpressSortService;
import com.examsys.util.SessionNullException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 主/整机仓存汇总控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/gather")
public class GatherController {

	//日志
	private Logger log = Logger.getLogger(this.getClass());
	
	//注入业务层
	@Resource
	private ExpressSortService expressSortService;
	
	SessionNullException sne = new SessionNullException();
	
	
	/**
	 * 主/整机仓存汇总基础信息页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){

		log.info("进入主/整机仓存汇总管理页面");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gather/GatherManager");
		
		return mv;
	}

	/**
	 * 主/整机仓存汇总表格数据
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
		log.info("开始获取主/整机仓存汇总表数据");
		Map jsonDatas = new HashMap();
		List<ExpressSort> expList = new ArrayList<ExpressSort>();
		try {
			sne.ex();
			//分页处理
			PageHelper.startPage(page, rows);
			expList = expressSortService.getList3(expressSort);
			//取记录总条数
			PageInfo<ExpressSort> pageInfo = new PageInfo<ExpressSort>(expList);
			jsonDatas.put("total", pageInfo.getTotal()); //总数
			jsonDatas.put("rows", expList); //前端需要的行数据
			
		} catch (Exception e) {
			log.error("获取主/整机仓存汇总表格数据失败",e);
		}
		return jsonDatas; //返回存入数据的集合，最终给springmvc转换为json数据输出给浏览器
	}
	
}
