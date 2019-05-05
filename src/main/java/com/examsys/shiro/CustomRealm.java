package com.examsys.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.examsys.po.Employee;
import com.examsys.po.Rights;
import com.examsys.service.EmployeeService;
import com.examsys.service.RightsService;

/**
 * created on 2017年03月31日 
 *
 * 自定义shiro realm
 *
 * @author  hwua
 * @version  1.0.0
 */
public class CustomRealm extends AuthorizingRealm {

	
	
	/**
	 * 注入service AuthorizationFilter
	 */
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RightsService rightsService;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 设置realm的名称
	 */
	@Override
	public void setName(String name) {
		super.setName("customRealm");
	}

	/**
	 * realm的认证方法，从数据库查询用户信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		// token是用户输入的用户名和密码 
		// 第一步从token中取出用户名
		String username = (String) token.getPrincipal();
		
		// 第二步：根据用户输入的username从数据库查询
		Employee employee = null;
		
		try {
			employee = employeeService.getByName(username);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		// 如果查询不到返回null
		if(employee==null){
			return null;
		}
		
		String password = employee.getPass();

		// 如果查询到返回认证信息AuthenticationInfo
		//activeUser就是用户身份信息
		ActiveUser activeUser = new ActiveUser();
		
		activeUser.setUserid(employee.getId());
		activeUser.setUsername(employee.getUsername());
		activeUser.setUserStatus(employee.getWorkStatu());
		
		activeUser.setRolename(employee.getJobInfo().getJob());
		
		
		//根据用户id取出菜单
		/*List<Employee> menus  = null;
		try {
			//通过service取出菜单 
			menus = employeeService.findMenuListByUserId(employee.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//将用户菜单设置到activeUser
		activeUser.setMenus(menus);*/

		//ByteSource q = ByteSource.Util.bytes(sysUser.getSalt());
		//将activeUser设置simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				activeUser, password, this.getName());

		return simpleAuthenticationInfo;
	}
	
	// 用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		//从 principals获取主身份信息
		//将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
		ActiveUser activeUser =  (ActiveUser) principals.getPrimaryPrincipal();
		
		//根据身份信息获取权限信息
		//从数据库获取到权限数据
		List<Rights> permissionList = null;
		
		try {
			permissionList = rightsService.getByEmployeeId(activeUser.getUserid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		//单独定一个集合对象 
		List<String> rightList = new ArrayList<String>();
		if(permissionList!=null){
			for(Rights rights:permissionList){
				//将数据库中的权限标签 符放入集合
				rightList.add(rights.getRightCode());
				//System.out.println(rights.getRightCode());
			}
		}
		
		//查到权限数据，返回授权信息(要包括 上边的permissions)
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		//将上边查询到授权信息填充到simpleAuthorizationInfo对象中
		simpleAuthorizationInfo.addStringPermissions(rightList);

		return simpleAuthorizationInfo;
	}
	
	//清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
}
