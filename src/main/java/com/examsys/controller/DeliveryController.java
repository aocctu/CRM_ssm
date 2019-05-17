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
 * 配件发料控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/delivery")
public class DeliveryController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private PartsWarehouseService partsWarehouseService;
	
	@Resource
	private DeliveryService deliveryService;
	
	SessionNullException sne = new SessionNullException();
	
	
	
	/**
	 * 发料
	 * @param req
	 * @return
	 */
	@RequestMapping("/updateMaterials")
	public @ResponseBody Map updateMaterials(HttpServletRequest req){
		sne.ex();
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		String ids = req.getParameter("ids");
		String status = req.getParameter("status");
		if(status.contains("已发料")){
			new Exception();
		}else{
			String []id=ids.split(",");
		
			try {
				for(String i : id){
					//通过id去修改数据
					log.info("要修改数据的编号为"+i);//把id放到日志
					Delivery oldDelivery = deliveryService.get(Integer.valueOf(i).intValue());
					oldDelivery.setMaterials_status("已发料");
					
					Employee employee = (Employee)req.getSession().getAttribute("EMPLOYEE");
					String dateNowStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
					
					oldDelivery.setDelivery_name(employee.getUsername());
					oldDelivery.setDelivery_date(dateNowStr);
					
					
					
					PartsWarehouse partsWarehouse = partsWarehouseService.get2(oldDelivery.getMaterial_code());
					Integer ichiban = partsWarehouse.getIchiban(); //良品库存
					Integer quantity = partsWarehouse.getQuantity();//全部库存数量
					
					Integer receive_num = oldDelivery.getReceive_num();//需发良品数量
					
					Integer laveIchiban = ichiban - receive_num;
					Integer laveQuantity = quantity - receive_num;
					
					partsWarehouse.setIchiban(laveIchiban);
					partsWarehouse.setQuantity(laveQuantity);
					
					partsWarehouse.setMaterials_status("");
					partsWarehouse.setReceive_num("");
					
					
					boolean update = partsWarehouseService.update(partsWarehouse);
					
					boolean flag = deliveryService.update(oldDelivery);
					if(flag&&update){
						jsonDatas.put("status", 1);//设置状态为1，表示操作成功
					}
				}
					
			} catch (Exception e) {
				log.error("发料失败", e);
			}
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	
	
	
	/**
	 * 配件发料基础信息页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		Log.info("进行配件发料页面");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/delivery/DeliveryManager");
		return mv;
	}
	
	
	/**
	 * 配件发料的数据信息
	 * @param delivery
	 * @param page
	 * @param row
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(Delivery delivery,
			//page当前是第几页
			@RequestParam(value="page",required=false)int page,
			//rows每页几条数据
			@RequestParam(value="rows",required=false)int rows){
		log.info("初始化配件发料的数据："+ delivery);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		List<Delivery> deliveriesList = new ArrayList<Delivery>();
		
		try {
			
			String orderBy="materials_status desc";
			PageHelper.startPage(page, rows , orderBy);
			
			deliveriesList = deliveryService.getList(delivery);
			PageInfo<PartsWarehouse> pageInfo = new PageInfo<PartsWarehouse>();
			jsonDatas.put("total", pageInfo.getTotal());
			jsonDatas.put("rows", deliveriesList);
		} catch (Exception e) {
			log.info("初始化失败",e);
		}
		
		return jsonDatas;
	}

}
