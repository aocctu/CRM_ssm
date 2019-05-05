package com.examsys.shiro;

import java.util.List;

import com.examsys.po.Rights;


/**
 * 用户身份信息，存入session 由于tomcat将session会序列化在本地硬盘上，所以使用Serializable接口
 * 
 * @author hwua
 * 
 */
public class ActiveUser implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userid;//用户id（主键）
	private String username;// 用户名称
	private String userStatus;// 用户状态
	private String rolename;// 角色名称
	private List<Rights> rights;// 权限
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public List<Rights> getRights() {
		return rights;
	}
	public void setRights(List<Rights> rights) {
		this.rights = rights;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
