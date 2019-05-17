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
import com.examsys.po.PartsWarehouse;
import com.examsys.service.PartsWarehouseService;
import com.examsys.util.SessionNullException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 配件仓库管理控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/partsWarehouse")
public class PartsWarehouseController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PartsWarehouseService partsWarehouseService;
	
	SessionNullException sne = new SessionNullException();
	
	
	
	/**
	 * 配件仓库入库
	 * @param partsWarehouse
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(PartsWarehouse partsWarehouse,HttpServletRequest req){
		log.info("要添加的数据是："+partsWarehouse);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		try {
			
			String type = partsWarehouse.getType();
			if(type.equals("1")){
				partsWarehouse.setIchiban(partsWarehouse.getQuantity());
				partsWarehouse.setScrap(0);
			}else {
				partsWarehouse.setScrap(partsWarehouse.getQuantity());
				partsWarehouse.setIchiban(0);
			}
			
			Employee employee = (Employee) req.getSession().getAttribute("EMPLOYEE");
			partsWarehouse.setCreate_name(employee.getUsername());
			
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateNowStr = sdf.format(d);
			partsWarehouse.setCreate_date(dateNowStr);
			
			partsWarehouse.setStatus("来料");
			
			boolean flag = partsWarehouseService.add(partsWarehouse);
			if(flag){
				jsonDatas.put("status", 1);
			}
		} catch (Exception e) {
			log.info("添加失败",e);
		}
		
		return jsonDatas;
	}
	
	
	/**
	 * 修改初始化
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody PartsWarehouse update(@RequestParam(name="id",required=true)Integer id){
		log.info("初始化的数据id："+ id);
		PartsWarehouse partsWarehouse = null;
		
		try {
			partsWarehouse = partsWarehouseService.get(id);
		} catch (Exception e) {
			log.info("数据初始化失败",e);
		}
		
		return partsWarehouse;
	}
	
	/**
	 * 保存修改数量
	 * @param partsWarehouse
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(PartsWarehouse partsWarehouse){
		log.info("保存修改的数据：" + partsWarehouse);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		
		try {
			
			PartsWarehouse oldPart = partsWarehouseService.get(partsWarehouse.getId());
			
			oldPart.setModel(partsWarehouse.getModel());
			oldPart.setDescription(partsWarehouse.getDescription());
			oldPart.setRemark(partsWarehouse.getRemark());
			
			boolean flag = partsWarehouseService.update(oldPart);
			if(flag){
				jsonDatas.put("status", 1);
			}
		} catch (Exception e) {
			log.info("保存修改的数据失败",e);
		}
		
		return jsonDatas;
	}
	
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam(name="id",required=true)Integer id){
		log.info("要删除的数据id为："+ id);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		
		try {
			sne.ex();
			boolean flag = partsWarehouseService.delete(id);
			if(flag){
				jsonDatas.put("status", 1);
			}
		} catch (Exception e) {
			log.info("删除失败",e);
		}
		
		return jsonDatas;
	}
	
	/**
	 * 配件仓库页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("开始进入页面");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("partsWarehouse/PartsWarehouseManager");
		return mv;
	}
	
	/**
	 * 配件仓库表格数据
	 * @param partsWarehouse
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(PartsWarehouse partsWarehouse,
			//page当前是第几页
			@RequestParam(value="page",required=false)int page,
			//rows每页几条数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("获取表格数据");
		Map jsonDatas = new HashMap();
		List<PartsWarehouse> partsWarehousesList = new ArrayList<PartsWarehouse>();
		
		try {
			sne.ex();
			//分页处理
			String orderBy="create_date desc";
			PageHelper.startPage(page, rows , orderBy);
			partsWarehousesList = partsWarehouseService.getList(partsWarehouse);
			
			PageInfo<PartsWarehouse> pageInfo = new PageInfo<PartsWarehouse>();
			jsonDatas.put("total", pageInfo.getTotal());
			jsonDatas.put("rows", partsWarehousesList);
		} catch (Exception e) {
			log.error("获取数据失败",e);
		}
		return jsonDatas;
	}
}
