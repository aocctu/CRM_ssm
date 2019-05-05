package com.examsys.dao;

import java.util.List;

import com.examsys.po.AdminRoles;
/**
 * 管理员角色数据访问层接口
 * @author edu-1
 *
 */
public interface AdminRolesDao {
	// 添加信息
	public void add(AdminRoles obj) throws Exception;

	// 更新信息
	public void update(AdminRoles obj) throws Exception;

	// 删除信息
	public void delete(Integer id) throws Exception;
	
	//通过主键获得一条信息
	public AdminRoles get(Integer id) throws Exception;
	
	//获得信息列表
	public List<AdminRoles> getList1() throws Exception;
	
	//通过条件获得信息列表
	public List<AdminRoles> getList2(AdminRoles obj) throws Exception;
}
