package com.examsys.dao;

import java.util.List;

import com.examsys.po.Department;
/**
 * 部门
 * @author edu
 *
 */
public interface DepartmentDao {

	// 添加部门
	public void add(Department department) throws Exception;
	
	// 更新部门
	public void update(Department department) throws Exception;
	
	// 删除部门
	public void delete(Integer id) throws Exception;
	
	// 通过主键获得一条信息
	public Department get(Integer id) throws Exception;
	
	// 查询部门
	public List<Department> getList1() throws Exception;
	
	// 通过条件获得部门
	public List<Department> getList2(Department obj) throws Exception;
}
