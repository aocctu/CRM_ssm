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

import com.examsys.po.Rights;
import com.examsys.service.RightsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * 权限控制层
 * @author edu
 *
 */
@Controller
@RequestMapping("/rights")
public class RightsController {

	// 日志
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private RightsService rightsService; //注入
	
	
	/**
	 * 初始化父级下拉框
	 * @return
	 */
	@RequestMapping("/rightsDatas")
	public @ResponseBody List<Rights> rightsDatas(){
		log.info("获取部门下拉框数据");
		 List<Rights> rightsList = null;
		 try {
			 rightsList = rightsService.getList();
		} catch (Exception e) {
			log.error("获取下拉框数据失败",e);
		}
		 return rightsList; // 返回存放数据的集合，最终级springmvc转换为json数据输出给浏览器
	}
	
	
	/**
	 * 保存添加
	 * @param admin
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(Rights rights,
			@RequestParam(name="pid",required=true) Integer pid){
		log.info("接收到页面添加的数据："+rights+",父级编号："+pid);//把rights放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			Rights rights2  = rightsService.get(pid);
			/*if(rights2.equals(null)){
				
			}*/
			
			System.out.println(rights2);
			rights.setRights(rights2);
			//通过用户名去获取管理员
			boolean flag = rightsService.add(rights);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		
		} catch (Exception e) {
			log.error("添加失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	
	
	/**
	 * 初始化修改
	 * @param id 待修改的功能的编号
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody Rights update(@RequestParam(name="rid",required=true) Integer rid){
		log.info("开始初始化编号为"+rid+"的系统功能数据供前端修改");//把id放到日志
		Rights rights=null;
		try {
			//通过id去获取系统功能信息
			rights=rightsService.get(rid);//通过id拿到功能对象赋给rolesSettings变量
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return rights;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 保存修改
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(Rights rights,
			//上面这个是父级编号，获取父级编号信息时，直接把pid放进去
			@RequestParam(name="pid",required=false) Integer pid){
		log.info("接收到页面修改的数据："+rights+",父级编号："+pid);//把adminRolesSettings放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			Rights oldrights=rightsService.get(rights.getRid());//通过id找到对应的这一条系统功能信息
			//找到之后，把名字放进去给它
			oldrights.setRightName(rights.getRightName());
			oldrights.setRightType(rights.getRightType());
			oldrights.setUrl(rights.getUrl());
			oldrights.setRightCode(rights.getRightCode());
			
			if(pid!=null){
				Rights rights2=rightsService.get(pid);//获取父级编号，并用rights2接收
				oldrights.setRights(rights2);//设置关联，把接收了的父级编号对应的rights2放到自己的rights属性里面去
			}else{
				oldrights.setRights(null);
			}
			//调用修改的方法，修改原先的信息
			boolean flag = rightsService.update(oldrights);
			
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
	public @ResponseBody Map delete(@RequestParam(name="rid",required=true) Integer rid){
		log.info("开始删除编号为"+rid+"的功能");//把id放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			//通过id去删除数据
			boolean flag = rightsService.delete(rid);
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 权限管理页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入权限管理页面");
		ModelAndView mv = new ModelAndView(); // 创建模型视图
		mv.setViewName("rights/RightsManager");
		return mv;
	}
	
	
	/**
	 * 权限管理表格数据
	 * @param rights
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(Rights rights,
			// 当前页
			@RequestParam(value="page",required=false)int page,
			// 每页显示
			@RequestParam(value="rows",required=false)int rows){
		
		log.info("开始获取权限表格数据");
		Map jsonDatas = new HashMap(); // 待返回json集合数据
		List<Rights> rightsList = new ArrayList<Rights>(); //待返回系统的数据
		
		try {
			// 分页处理
			PageHelper.startPage(page, rows);
			rightsList = rightsService.getList(rights);
			//取记录总条数
			PageInfo<Rights> pageInfo = new PageInfo<Rights>(rightsList);
			jsonDatas.put("total", pageInfo.getTotal()); // 总数
			jsonDatas.put("rows", rightsList); //前端需要的数据
		} catch (Exception e) {
			log.error("获取权限数据失败", e);
		}
		return jsonDatas; // 返回存放数据的集合，最终给springmvc转换为json数据输出给浏览器
	}
	
	
}
