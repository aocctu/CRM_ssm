package com.examsys.controller;

import java.sql.Date;
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

import com.examsys.po.AdminRoles;
import com.examsys.po.AdminRolesSettings;
import com.examsys.service.AdminRolesService;
import com.examsys.service.AdminRolesSettingsService;
import com.examsys.util.TreeNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 管理员角色控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/adminRoles")
public class AdminRolesController {
	//log4j日志对象
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private AdminRolesSettingsService adminRolesSettingsService;//业务逻辑层对象
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private AdminRolesService adminRolesService;//业务逻辑层对象
	
	/**
	 * 获取功能项数据
	 * @return
	 */
	@RequestMapping("/treeDatas")
	public @ResponseBody List<TreeNode> treeDatas(){
		log.info("开始获取功能项数据失败");
		List<AdminRolesSettings> adminRolesSettingsList=new ArrayList<AdminRolesSettings>();
		List<TreeNode> list=new ArrayList<TreeNode>();
		try {
			//构造查找父级功能项的条件
			AdminRolesSettings adminRolesSettings=new AdminRolesSettings();//com.examsys.po.AdminRolesSettings.AdminRolesSettings()
			AdminRolesSettings pAdminRolesSettings=new AdminRolesSettings();
			pAdminRolesSettings.setId(null);//数据库中该父级菜单项为null，表示顶级功能
			adminRolesSettings.setAdminRolesSettings(pAdminRolesSettings);
			
			//通过条件去查找
			List<AdminRolesSettings> parentList = adminRolesSettingsService.getList(adminRolesSettings);//先把父级功能找出来
			for(AdminRolesSettings p:parentList){//遍历权限所拥有的功能项
					
				TreeNode pNode=new TreeNode();//创建树节点对象
				pNode.setId(p.getId());//节点编号
				pNode.setText(p.getName());
				pNode.getAttributes().put("url", p.getUrl());//节点超链接
				
				//构造查找子级功能项的条件
				AdminRolesSettings cAdminRolesSettings=new AdminRolesSettings();
				cAdminRolesSettings.setAdminRolesSettings(p);
				
				//通过条件去查找
				List<AdminRolesSettings> childList = adminRolesSettingsService.getList(cAdminRolesSettings);//把它的子功能找出来
				for(AdminRolesSettings c:childList){
					TreeNode cNode=new TreeNode();//创建树节点对象
					cNode.setId(c.getId());//节点编号
					cNode.setText(c.getName());
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
	 * 保存添加
	 * @param adminRoles
	 * @return
	 */
	@RequestMapping("/addSave")
	public @ResponseBody Map addSave(AdminRoles adminRoles){
		log.info("接收到页面添加的数据："+adminRoles);
		
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			adminRoles.setCreate_date(new Date(System.currentTimeMillis()));//创建时间
			//调用业务逻辑层的添加方法保存adminRoles对象
			boolean flag = adminRolesService.add(adminRoles);
			
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
	 * @param id
	 * @return
	 */
	@RequestMapping("/update")
	public @ResponseBody AdminRoles  update(@RequestParam(name="id",required=true) Integer id){
		log.info("开始初始化编号为"+id+"的角色数据供前端修改");//把id放到日志
		
		AdminRoles adminRoles = null;
		try {
			//通过id去获取管理员角色
			adminRoles = adminRolesService.get(id);
		
		} catch (Exception e) {
			log.error("初始化修改失败", e);
		}
		return adminRoles;//返回功能对象,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 保存修改
	 * @param admin
	 * @return
	 */
	@RequestMapping("/updateSave")
	public @ResponseBody Map updateSave(AdminRoles adminRoles){
		log.info("接收到页面修改的数据："+adminRoles);//把adminRoles放到日志
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			AdminRoles oldAdminRoles = adminRolesService.get(adminRoles.getId());//通过id找到对应的这一条角色信息
			
			oldAdminRoles.setRole_name(adminRoles.getRole_name());
			oldAdminRoles.setRole_privelege(adminRoles.getRole_privelege());
			oldAdminRoles.setCreate_date(adminRoles.getCreate_date());
			oldAdminRoles.setRemark(adminRoles.getRemark());
			
			//调用修改的方法，修改原先的信息
			boolean flag = adminRolesService.update(oldAdminRoles);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		
		} catch (Exception e) {
			log.error("修改失败", e);
		}
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 删除管理员角色
	 * @param id待删除的角色的编号
	 * @return
	 */
	@RequestMapping("/delete")
	public @ResponseBody Map delete(@RequestParam(name="id",required=true) Integer id){
		log.info("开始删除编号为"+id+"的系统功能");//把id放到日志
		
		Map jsonDatas=new HashMap();//存放json数据的集合
		jsonDatas.put("status", 0);//默认状态为0，表示操作失败
		try {
			//通过id去删除数据
			boolean flag = adminRolesService.delete(id);
			
			if(flag){
				jsonDatas.put("status", 1);//设置状态为1，表示操作成功
			}
		
		} catch (Exception e) {
			log.error("删除失败", e);
		}
		
		return jsonDatas;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 管理员角色管理
	 * @return
	 */
	@RequestMapping("/select")
	public ModelAndView select(){
		log.info("进入管理员角色管理页面");
		ModelAndView mv = new ModelAndView();//创建视图模型对象
		mv.setViewName("adminRoles/AdminRolesManager");//设置显示页面
		return mv;//返回视图模型对象
	}
	
	/**
	 * 管理员角色管理表格数据
	 * @return
	 */
	@RequestMapping("/selectDatas")
	public @ResponseBody Map selectDatas(AdminRoles adminRoles,
			//page当前是第几页
			@RequestParam(value="page",required=false)int page,
			//rows每页几条数据
			@RequestParam(value="rows",required=false)int rows){
		
		log.info("开始获取系统功能管理表格数据");
		Map jsonDatas=new HashMap();//待返回json集合数据;
		try {
			//分页处理
			PageHelper.startPage(page, rows);
			List<AdminRoles> adminRolesList = adminRolesService.getList(adminRoles);
			PageInfo<AdminRoles> pageInfo = new PageInfo<AdminRoles>(adminRolesList);
			jsonDatas.put("total", pageInfo.getTotal());//总数
			jsonDatas.put("rows", adminRolesList);//前端需要的行数据
		
		} catch (Exception e) {
			log.error("获取管理员角色管理表格数据失败",e);
		}
		return jsonDatas;//返回存放数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
}
