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
import com.examsys.po.ExpressSend;
import com.examsys.po.ExpressSort;
import com.examsys.service.ExpressSendService;
import com.examsys.service.ExpressSortService;
import com.examsys.util.SessionNullException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 快递寄送控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/expressSend")
public class ExpressSendController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ExpressSendService expressSendService;
	
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
	public @ResponseBody Map addSave(ExpressSend expressSend,HttpServletRequest req){
		sne.ex();
		log.info("接收到页面添加的数据："+ expressSend );
		Map jsonDatas = new HashMap(); //存放json数据的集合
		jsonDatas.put("status", 0); //默认状态为0，表示操作失败
		try {
			
			String sn = expressSend.getSn();
			
			String[] split = sn.split("\n");
			
			for(int i = 0 ; i < split.length ; i++){
				System.out.println(split[i]);
				ExpressSort expressSort = expressSortService.get2("13");
				if(expressSort.getPosition().equals("入库")){
					expressSort.setPosition("已寄送");
				}else {
					throw new Exception();
				}
			
				Employee employee = (Employee) req.getSession().getAttribute("EMPLOYEE");
				expressSend.setCreate_name(employee.getUsername());
				
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateNowStr = sdf.format(d);
				expressSend.setCreate_date(dateNowStr);
				expressSend.setExp_status("已寄送");
				
				boolean update = expressSortService.update(expressSort);
		
				boolean flag = expressSendService.add(expressSend);
				if(flag&&update){
					jsonDatas.put("status", 1);
				}
			
			}
		} catch (Exception e) {
			log.error("添加失败",e);
		}
		
		return jsonDatas;
	}
	
	
	/**
	 * 进入快递寄送管理页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入快递寄送管理页面");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("expressSend/ExpressSendManager");
		return mv;
	}
	
	
	
	/**
	 * 加载查询快递寄送数据
	 * @param expressSend
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(ExpressSend expressSend,
			@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="rows",required=false)Integer rows){
		
		Map jsonDatas = new HashMap<>();
		List<ExpressSend> expressSendsList = new ArrayList<ExpressSend>();
		
		try {
			//分页
			PageHelper.startPage(page, rows);
			
			expressSendsList = expressSendService.getList(expressSend);
			
			PageInfo<ExpressSend> pageInfo = new PageInfo<ExpressSend>(expressSendsList);
			jsonDatas.put("total", pageInfo.getTotal());//总数
			jsonDatas.put("rows", expressSendsList);//前端需要的行数
			
		} catch (Exception e) {
			log.info("快递寄送数据加载失败");
		}
		
		return jsonDatas;
	}
}
