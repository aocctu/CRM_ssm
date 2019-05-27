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
import com.examsys.po.ExpressCompany;
import com.examsys.service.ExpressCompanyService;
import com.examsys.service.ExpressService;
import com.examsys.util.SessionNullException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 快递管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/express")
public class ExpressController {

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
			new SessionNullException().ex();
			
			
			String exp_num = express.getExp_num();
			
			String[] res = exp_num.split("\n");
			
			//System.out.println(res.length + "-=-=-=");
			for(int i = 0 ; i < res.length ; i++)
			{
				//System.out.println(res[i]);
				
				ExpressCompany expressCompany = expressCompanyService.get(exp_company_id);
				Employee employee = (Employee)req.getSession().getAttribute("EMPLOYEE");
				express.setExpressCompany(expressCompany);
				express.setCreate_name(employee.getUsername());
				
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateNowStr = sdf.format(d);
				express.setCreate_date(dateNowStr);
				
				express.setExp_num(res[i]); //快递单号
				
				express.setExp_status("已录入");
				
				
				boolean flag = expressService.add(express);
				//System.out.println(express+"------");
				if(flag){
					jsonDatas.put("status", 1);//设置状态为1，表示操作成功
				}
				
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
	 * 修改保存
	 * @param express
	 * @param exp_company_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(Express express,
			@RequestParam(name="exp_company_id",required=true) Integer exp_company_id){
		log.info("接收到要修改的数据为："+express);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		try {
			new SessionNullException().ex();
			Express oldExpress = expressService.get(express.getId());
			oldExpress.setExp_iphone(express.getExp_iphone());
			oldExpress.setExp_name(express.getExp_name());
			oldExpress.setExp_num(express.getExp_num());
			
			String pay_type = express.getPay_type();
			
			oldExpress.setPay_type(pay_type);
			if(pay_type.equals("2")){
				oldExpress.setExp_cost(express.getExp_cost());
			}
			
			ExpressCompany expressCompany = expressCompanyService.get(exp_company_id);
			express.setExpressCompany(expressCompany);

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
	 * 快递信息删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam(name="id",required=true)Integer id){
		log.info("要删除的数据id为:"+id);
		Map jsonDatas = new HashMap<>();
		jsonDatas.put("status", 0);
		
		try {
			new SessionNullException().ex();
			boolean flag = expressService.delete(id);
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
		mv.setViewName("express/ExpressManager");
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
			@RequestParam(value="page",required=false)Integer page,
			//rows每页几条数据
			@RequestParam(value="rows",required=false)Integer rows){
		log.info("开始获取快递录入管理表数据");
		Map jsonDatas = new HashMap();
		List<Express> expList = new ArrayList<Express>();
		try {
			new SessionNullException().ex();
			//分页处理
			//String orderBy="create_date desc";
			PageHelper.startPage(page, rows );
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
