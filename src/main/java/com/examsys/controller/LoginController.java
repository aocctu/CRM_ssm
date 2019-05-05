package com.examsys.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.examsys.po.Admin;
import com.examsys.po.AdminRolesSettings;
import com.examsys.po.Employee;
import com.examsys.po.JobInfo;
import com.examsys.po.JobRight;
import com.examsys.po.Rights;
import com.examsys.service.AdminRolesSettingsService;
import com.examsys.service.AdminService;
import com.examsys.service.EmployeeService;
import com.examsys.service.JobRightService;
import com.examsys.service.RightsService;
import com.examsys.util.TreeNode;

/**
 * 登录控制层
 *
 */
@Controller
public class LoginController {
	//log4j日志对象
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private AdminService adminService;//业务逻辑层对象
	
	@Resource //用@Resource注解，告诉spring这里需要把业务逻辑层对象注入进来(IoC,DI)
	private AdminRolesSettingsService adminRolesSettingsService;//业务逻辑层对象
	
	@Resource
	private RightsService rightsService;
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private JobRightService jobRightService;
	
	/**
	 * 
	 * @param req
	 * @param admin 接收登录表单提交过来的数据
	 * @param code  接收验证码
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req,Employee employee,
			@RequestParam(name="code",required=true) String code){
		
		log.info("接收到登录信息:"+employee+",验证码:"+code);//把admin放到日志
		ModelAndView mv = new ModelAndView();//创建视图模型对象
		mv.addObject("user_name", employee.getUsername());
		mv.addObject("user_pass", employee.getPass());
		mv.addObject("code", code);
		req.getSession().setAttribute("username", employee.getUsername());
		
		try {
			//从服务器缓存中拿出先前放置的验证码
			String sessionCode=(String)req.getSession().getAttribute("code");
			if(!sessionCode.equals(code)){//与页面传过来的验证码对比
				mv.addObject("msg", "验证码不正确");
				mv.setViewName("index");//如果不匹配则返回登录页
				return mv;
			}
			
			//通过用户名去获取管理员
			Employee dEmployee = employeeService.getByName(employee.getUsername());
			
			if(dEmployee==null){//如果拿不到管理员
				mv.addObject("msg", "账号不存在");
				mv.setViewName("index");//如果不匹配则返回登录页
				return mv;
			}
			
			if(!employee.getPass().equals(dEmployee.getPass())){//验证当前录入的密码与数据库返回来的密码是否相等
				mv.addObject("msg", "密码不正确");
				mv.setViewName("index");//如果不匹配则返回登录页
				return mv;
			}
			
			// shiro 的验证
			Subject currentUser = SecurityUtils.getSubject();
		    if (!currentUser.isAuthenticated()) {
		    	
		    	UsernamePasswordToken token = new UsernamePasswordToken(employee.getUsername(), employee.getPass());
		         
		        try{
		            currentUser.login(token);
		        }catch(UnknownAccountException ex){
		        	mv.addObject("msg", "account_error");
		        }catch(IncorrectCredentialsException ex){
		        	mv.addObject("msg", "password_error");
		        }catch(AuthenticationException ex){
		        	mv.addObject("msg", "authentication_error");
		        }
		    }
			req.getSession().setAttribute("EMPLOYEE", dEmployee);//将管理员对象放入session缓存中，方便其他模块使用
			mv=new ModelAndView();
			mv.setViewName("redirect:main.jsp");//重定向到主界面页
		
		} catch (Exception e) {
			log.error("登录失败", e);
		}
		
		return mv;
	}
	
	/**
	 * 登录成功后，进入主界面时，左边的树导航栏需要的数据
	 * @param req
	 * @return
	 */
	@RequestMapping("/ajaxTree")
	public @ResponseBody List<TreeNode> ajaxTree(HttpServletRequest req){
		List<TreeNode> list=new ArrayList<TreeNode>();
		//从session缓存中拿出登录的管理员对象
		Employee employee = (Employee)req.getSession().getAttribute("EMPLOYEE");
		Integer jobid = employee.getJobInfo().getId();
		JobRight obj = new JobRight();
		JobInfo jobInfo=new JobInfo();
		jobInfo.setId(jobid);
		obj.setJobInfo(jobInfo);
		List<JobRight> list1;
		try {
			list1 = jobRightService.getList(obj);
		
		if(list1 != null && list1.size() > 0){
		
			String role_privelege = list1.get(0).getRightid();
			//因为之前存在数据中是一串用逗号隔开的功能编号
			String[] priveleges = role_privelege.split(",");
			
			
				
				for(String id:priveleges){//遍历权限所拥有的功能项
					/*AdminRolesSettings adminRolesSettings = adminRolesSettingsService.get(Integer.valueOf(id));
					
					if(adminRolesSettings!=null&&adminRolesSettings.getAdminRolesSettings()==null){
						
						TreeNode treeNode=new TreeNode();//创建树节点对象
						treeNode.setId(adminRolesSettings.getId());//节点编号
						treeNode.setText(adminRolesSettings.getName());
						treeNode.getAttributes().put("url", adminRolesSettings.getUrl());//节点超链接
						list.add(treeNode);//将树节点放入集合中
					}*/
					Rights rights = rightsService.get(Integer.valueOf(id));
					
					if(rights.getRightType().equals("1")){
						
						TreeNode treeNode = new TreeNode(); //创建树节点对象
						treeNode.setId(rights.getRid()); //节点编号
						treeNode.setText(rights.getRightName());
						treeNode.getAttributes().put("url", rights.getUrl());//节点超链接
						list.add(treeNode); //将树节点放入集合中
					}
				}
			
		}
		} catch (Exception e) {
			log.error("初始化主界面树数据失败", e);
		}
		return list;//返回存放json数据的集合,最终给springmvc转换为json数据输出给浏览器
	}
	
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/code")
	public void code(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//在内存中创建图像
		int width=60,height=20;
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获取图形上下文
		Graphics g=image.getGraphics();
		//创建随机数对象
		Random random=new Random();
		//设定背景色
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		
		//设置字体
		g.setFont(new Font("Times New Roman",Font.PLAIN,18));
		
		//随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(60,200));
		for(int i=0;i<155;i++){
			int x=random.nextInt(width);
			int y=random.nextInt(height);
			int xl=random.nextInt(12);
			int yl=random.nextInt(12);
			g.drawLine(x, y, x+xl, y+yl);
		}
		
		//随机产生认证码(4位数字)
		String sRand="";
		for(int i=0;i<4;i++){
			String rand=String.valueOf(random.nextInt(10));
			sRand+=rand;
			//将认证码显示到图象中
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(rand, 13*i+6, 16);
		}
		
		//将认证码存入SESSION
		request.getSession().setAttribute("code", sRand);
		//图象生效
		g.dispose();
		
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		ImageOutputStream imageOut=ImageIO.createImageOutputStream(output);
		ImageIO.write(image, "JPEG", imageOut);
		imageOut.close();
		response.getOutputStream().write(output.toByteArray());
	}
	
	private Color getRandColor(int fc,int bc){
		//创建随机数对象
		Random random=new Random();
		if(fc>255){
			fc=255;
		}
		
		if(bc>255){
			bc=255;
		}
		
		int r=fc+random.nextInt(bc-fc);
		int g=fc+random.nextInt(bc-fc);
		int b=fc+random.nextInt(bc-fc);
		return new Color(r,g,b);
	}
}
