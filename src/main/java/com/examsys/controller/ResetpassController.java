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

import com.examsys.po.Resetpass;
import com.examsys.service.ResetpassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 重置密码控制层
 * @author edu
 *
 */
@Controller
@RequestMapping("/resetpass")
public class ResetpassController {

	// 日志
	private Logger log = Logger.getLogger(this.getClass());
	// 注入业务层
	@Resource
	private ResetpassService resetpassService;
	
	
	
	
	
	/**
	 * 添加
	 * @param resetpass
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(Resetpass resetpass){
		log.info("接收添加的数据" + resetpass);
		Map jsonDatas = new HashMap();
		jsonDatas.put("statu", 0);  // 设置状态为0 ，表示失败
		
		try {
			// 调用业务逻辑层保存数据
			boolean flag = resetpassService.add(resetpass);
			
			if(flag){
				jsonDatas.put("statu", 1);  // 设置状态为1，表示成功
			}
		} catch (Exception e) {
			log.error("添加失败",e);
		}
		
		return jsonDatas; // 返回json数据集合，最终给springmvc转换为json数据输出给浏览器
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
			boolean flag = resetpassService.delete(id);
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
		mv.setViewName("resetpass/ResetpassManager"); // 设置显示页面
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
	public @ResponseBody Map selectDatas(Resetpass resetpass,
			// 当前页
			@RequestParam(value="page",required=false)int page,
			// 每页显示数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("开始获取表格数据");
		Map jsonDatas = new HashMap(); //待返回的json集合数据
		List<Resetpass> resetpassesList = new ArrayList<Resetpass>(); // 待返回系统的数据
		
		try {
			// 分页处理
			PageHelper.startPage(page, rows);
			resetpassesList = resetpassService.getList(resetpass);
			// 获取记录总条数
			PageInfo<Resetpass> pageInfo = new PageInfo<Resetpass>(resetpassesList);
			jsonDatas.put("total", pageInfo.getTotal()); // 总数
			jsonDatas.put("rows", resetpassesList);
		} catch (Exception e) {
			log.error("获取数据失败",e);
		}
		
		return jsonDatas; // 返回存放数据集合，最终给springmvc转换为json数据输出给浏览器 
		
	}
	
	
}
