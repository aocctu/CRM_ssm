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

import com.examsys.po.JobInfo;
import com.examsys.po.JobRight;
import com.examsys.po.Rights;
import com.examsys.service.JobInfoService;
import com.examsys.service.JobRightService;
import com.examsys.service.RightsService;
import com.examsys.util.TreeNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 职位权限对照表控制层
 * @author edu
 *
 */
@Controller
@RequestMapping("/jobRight")
public class JobRightController {

	// 日志
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource  //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private JobRightService jobRightService; //注入业务逻辑层
	@Resource
	private JobInfoService jobInfoService;  // 职位
	@Resource
	private RightsService rightsService;  // 权限

	
	/**
	 * 获取功能项数据
	 * @return
	 */
	@RequestMapping("/treeDatas")
	public @ResponseBody List<TreeNode> treeDatas(){
		log.info("开始获取功能项数据失败");
		List<Rights> rightsList=new ArrayList<Rights>();
		List<TreeNode> list=new ArrayList<TreeNode>();
		try {
			//构造查找父级功能项的条件
			Rights rights=new Rights();
			Rights pRights=new Rights();
			pRights.setRid(0);//数据库中该父级菜单项为null，表示顶级功能
			rights.setRights(pRights);
			
			//通过条件去查找
			List<Rights> parentList = rightsService.getList(rights);//先把父级功能找出来
			for(Rights p:parentList){//遍历权限所拥有的功能项
					
				TreeNode pNode=new TreeNode();//创建树节点对象
				pNode.setId(p.getRid());//节点编号
				pNode.setText(p.getRightName());
				pNode.getAttributes().put("url", p.getUrl());//节点超链接
				
				//构造查找子级功能项的条件
				Rights cRights=new Rights();
				cRights.setRights(p);
				
				//通过条件去查找
				List<Rights> childList = rightsService.getList(cRights);//把它的子功能找出来
				for(Rights c:childList){
					TreeNode cNode=new TreeNode();//创建树节点对象
					cNode.setId(c.getRid());//节点编号
					cNode.setText(c.getRightName());
					cNode.getAttributes().put("url", c.getUrl());//节点超链接
					
					pNode.getChildren().add(cNode);//将树节点放入集合中
				}
				list.add(pNode); //将父级树节放入树的根节点中
			}
		} catch (Exception e) {
			log.error("获取功能项数据失败", e);
		}
		return list;//返回存放数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	
	
	/**
	 * 初始化职位下拉框
	 * @return
	 */
	@RequestMapping("/jobInfoDatas")
	public @ResponseBody List<JobInfo> jobInfoDatas(){
		log.info("获取职位下拉框数据");
		 List<JobInfo> jobInfoList = null;
		 try {
			 jobInfoList = jobInfoService.getList();
		} catch (Exception e) {
			log.error("获取下拉框数据失败",e);
		}
		 return jobInfoList; // 返回存放数据的集合，最终级springmvc转换为json数据输出给浏览器
	}
	
	
	
	/**
	 * 保存添加
	 * @param admin
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(JobRight jobRight,
			@RequestParam(name="jobInfoid",required=true) Integer jobInfoid){
		log.info("接收到页面添加的数据："+jobRight+",职位编号："+ jobInfoid );//把jobRight放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		
		try {
			JobInfo jobInfo = jobInfoService.get(jobInfoid);
			
			jobRight.setJobInfo(jobInfo);
			//通过用户名去获取管理员
			boolean flag = jobRightService.add(jobRight);
			
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
	public @ResponseBody JobRight update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的系统功能数据供前端修改");//把id放到日志
		JobRight jobRight=null;
		try {
			//通过id去获取系统功能信息
			jobRight=jobRightService.get(id);//通过id拿到功能对象赋给rolesSettings变量
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return jobRight;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
		
	}
	
	/**
	 * 保存修改
	 * @param adminRolesSettings
	 * @param parent_id
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(JobRight jobRight,
			@RequestParam(name="jobInfoid",required=false) Integer jobInfoid){
		log.info("接收到页面修改的数据："+jobRight+"职位："+jobInfoid);
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		
		try {
			JobRight oldjobRight=jobRightService.get(jobRight.getId());//通过id找到对应的这一条系统功能信息
			//找到之后，把名字放进去给它
			
			oldjobRight.setRightid(jobRight.getRightid());
			
			if(jobInfoid!=null){
				JobInfo jobInfo = jobInfoService.get(jobInfoid);
				oldjobRight.setJobInfo(jobInfo);
			}
			
			
			//调用修改的方法，修改原先的信息
			boolean flag = jobRightService.update(oldjobRight);
			
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
	public @ResponseBody Map delete(@RequestParam(name="id",required=true) Integer id){
		log.info("开始删除编号为"+id+"的功能");//把id放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			//通过id去删除数据
			boolean flag = jobRightService.delete(id);
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	/**
	 * 职位对照页面
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("开始进入页面");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jobRight/JobRightManager");
		return mv;
	}
	
	 
	
	/**
	 * 职位权限管理表格数据 
	 * @param jobRight
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(JobRight jobRight,
			// 当前页
			@RequestParam(value="page",required=false)int page,
			// 每页显示的数据
			@RequestParam(value="rows",required=false)int rows){
		
		log.info("开始获取表格数据");
		Map jsonDatas = new HashMap(); // 待返回json集合数据
		List<JobRight> jobRightsList = new ArrayList<JobRight>(); // 待返回系统的数据 
		
		try {
			// 分页处理
			PageHelper.startPage(page, rows);
			jobRightsList = jobRightService.getList(jobRight);
			// 获取记录总条数
			PageInfo<JobRight> pageInfo = new PageInfo<JobRight>(jobRightsList);
			jsonDatas.put("total", pageInfo.getTotal()); //总数
			jsonDatas.put("rows", jobRightsList);
		} catch (Exception e) {
			log.error("获取数据失败",e);
		}
		
		
		return jsonDatas;  // 返回存放数据的集合，最终给springmvc转换为json数据输出给浏览器
	}
	
	
	
}
