package com.examsys.controller;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.examsys.po.ExpressSort;
import com.examsys.service.ExpressSortService;
import com.examsys.util.SessionNullException;
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
	
	SessionNullException sne = new SessionNullException();
	/**
	 * 保存添加
	 * @param expressSort
	 * @param req
	 * @return
	 */
	@RequestMapping("addSave")
	public @ResponseBody Map addSave(ExpressSort expressSort,HttpServletRequest req){
		sne.ex();
		log.info("接收到页面添加的数据："+ expressSort );
		Map jsonDatas = new HashMap(); //存放json数据的集合
		jsonDatas.put("status", 0); //默认状态为0，表示操作失败
		try {
			Employee employee = (Employee) req.getSession().getAttribute("EMPLOYEE");
			expressSort.setCreate_name(employee.getUsername());
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateNowStr = sdf.format(d);
			expressSort.setCreate_date(dateNowStr);
			
			expressSort.setPosition("分拣录入");

			boolean flag = expressSortService.add(expressSort);
			if(flag){
				jsonDatas.put("status", 1);
			}
			
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
	 * @param expressSort
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(ExpressSort expressSort){
		sne.ex();
		log.info("接收到要修改的数据为："+expressSort);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		try {
			ExpressSort oldexpressSort = expressSortService.get(expressSort.getId());
			oldexpressSort.setExp_num(expressSort.getExp_num());
			oldexpressSort.setModel(expressSort.getModel());
			oldexpressSort.setMaterial_type(expressSort.getMaterial_type());
			oldexpressSort.setQuantity(expressSort.getQuantity());
			oldexpressSort.setFault(expressSort.getFault());
			oldexpressSort.setType(expressSort.getType());
			oldexpressSort.setRemark(expressSort.getRemark());
			oldexpressSort.setColor(expressSort.getColor());
			oldexpressSort.setSn(expressSort.getSn());
			
			boolean flag = expressSortService.update(oldexpressSort);
			if(flag){
				jsonDatas.put("status", 1);
			}
			
		} catch (Exception e) {
			log.error("修改失败",e);
		}
		return jsonDatas;
	}
	
	@RequestMapping("/updateStorage")
	public @ResponseBody Map updateStorage(HttpServletRequest req){
		sne.ex();
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		String ids = req.getParameter("ids");
		String pos = req.getParameter("pos");
		if(pos.contains("入库")||pos.contains("分拣录入")){
			new Exception();
		}else{
			String []id=ids.split(",");
		
			try {
				for(String i : id){
					//通过id去修改数据
					log.info("要修改数据的编号为"+i);//把id放到日志
					ExpressSort oldexpressSort = expressSortService.get(Integer.valueOf(i).intValue());
					oldexpressSort.setPosition("入库");
					boolean flag = expressSortService.update(oldexpressSort);
					if(flag){
						jsonDatas.put("status", 1);//设置状态为1，表示操作成功
					}
				}
					
			} catch (Exception e) {
				log.error("入库失败", e);
			}
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 修改来料检验信息
	 * @param expressSort
	 * @return
	 */
	@RequestMapping("/updateExamine")
	public @ResponseBody Map updateExamine(ExpressSort expressSort){
		sne.ex();
		log.info("来料检验的数据为："+ expressSort);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		
		try {
			ExpressSort oldexpressSort = expressSortService.get(expressSort.getId());
			oldexpressSort.setConfirm_fault(expressSort.getConfirm_fault());
			oldexpressSort.setRepair_type(expressSort.getRepair_type());
			
			oldexpressSort.setPosition("来料检验");
			
			boolean flag = expressSortService.update(oldexpressSort);
			if(flag){
				jsonDatas.put("status", 1);
			}
		} catch (Exception e) {
			log.info("来料检验失败：",e);
		}
		return jsonDatas;
	}
	/**
	 * 快递分捡信息删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam(name="id",required=true)Integer id){
		sne.ex();
		log.info("要删除的数据id为："+id);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		
		try {
			boolean flag = expressSortService.delete(id);
			if(flag){
				jsonDatas.put("status", 1);
			}
		} catch (Exception e) {
			log.info("删除失败",e);
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
			sne.ex();
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
