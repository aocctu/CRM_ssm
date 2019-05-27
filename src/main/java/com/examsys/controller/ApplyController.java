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
import org.jfree.util.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.examsys.po.Delivery;
import com.examsys.po.Employee;
import com.examsys.po.PartsWarehouse;
import com.examsys.service.DeliveryService;
import com.examsys.service.PartsWarehouseService;
import com.examsys.util.SessionNullException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 申请领料控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/apply")
public class ApplyController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PartsWarehouseService partsWarehouseService;
	
	@Resource
	private DeliveryService deliveryService;
	
	SessionNullException sne = new SessionNullException();
	
	
	/**
	 * 申请初始化
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody PartsWarehouse update(@RequestParam(name="id",required=true)Integer id){
		log.info("要初始化的ID为："+id);
		PartsWarehouse partsWarehouse = null;
		
		try {
			partsWarehouse = partsWarehouseService.get(id);
		} catch (Exception e) {
			log.info("初始化失败",e);
		}
		
		return partsWarehouse;
	}
	
	
	/**
	 * 修改保存
	 * @param partsWarehouse
	 * @param req
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(PartsWarehouse partsWarehouse,HttpServletRequest req){
		log.info("要修改的数据为：" + partsWarehouse);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		
		
		try {
			PartsWarehouse oldPar = partsWarehouseService.get(partsWarehouse.getId());
			
			String status = oldPar.getMaterials_status();
			if(status == null){
				/*if(status.equals("领料中")){
					throw new Exception();
				}*/
				
				oldPar.setReceive_num(partsWarehouse.getReceive_num());
				oldPar.setMaterials_status("领料中");
				
				Delivery del = new Delivery();
				del.setBatch_num("");
				del.setMaterial_code(partsWarehouse.getMaterial_code());
				del.setModel(partsWarehouse.getModel());
				del.setDescription(partsWarehouse.getDescription());
				del.setIchiban(partsWarehouse.getIchiban());
				String receive_num = partsWarehouse.getReceive_num();
				del.setReceive_num(Integer.valueOf(receive_num));
				del.setMaterials_status("领料中");
			
				Employee employee = (Employee)req.getSession().getAttribute("EMPLOYEE");
				String dateNowStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				
				del.setApply_name(employee.getUsername());
				del.setApply_date(dateNowStr);
				
				//boolean add = deliveryService.add(partsWarehouse);
				boolean add = deliveryService.add(del);
				
				boolean update = partsWarehouseService.update(oldPar);
				
				if(update&&add){
					jsonDatas.put("status", 1);
				}
			}
		} catch (Exception e) {
			log.info("修改失败",e);
		}
		
		return jsonDatas;
	}
	
	
	
	
	/**
	 * 申请领料基础信息页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		Log.info("进入申请领料页面");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/apply/ApplyManager");
		return mv;
	}
	
	/**
	 * 申请领料数据
	 * @param partsWarehouse
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(PartsWarehouse partsWarehouse,
			//page当前是第几页
			@RequestParam(value="page",required=false)Integer page,
			//rows每页几条数据
			@RequestParam(value="rows",required=false)Integer rows){
		log.info("获取表格数据");
		Map jsonDatas = new HashMap();
		List<PartsWarehouse> partsWarehousesList = new ArrayList<PartsWarehouse>();
		
		try {
			sne.ex();
			//分页处理
			//String orderBy="create_date desc";
			PageHelper.startPage(page, rows );
			partsWarehousesList = partsWarehouseService.getList3(partsWarehouse);
			
			PageInfo<PartsWarehouse> pageInfo = new PageInfo<PartsWarehouse>(partsWarehousesList);
			jsonDatas.put("total", pageInfo.getTotal());
			jsonDatas.put("rows", partsWarehousesList);
		} catch (Exception e) {
			log.error("获取数据失败",e);
		}
		return jsonDatas;
	}
	
	
}
