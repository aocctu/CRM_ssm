package com.examsys.dao;

import java.util.List;

import com.examsys.po.Employee;
/**
 * 员工
 * @author edu
 *
 */
public interface EmployeeDao {

	// 添加
	public void add(Employee employee)throws Exception;
	
	// 修改
	public void update(Employee employee) throws Exception;
	
	// 删除 
	public void delete(Integer id)throws Exception;
	
	// 通过主键获得
	public Employee get(Integer id)throws Exception;

	// 获得名字
	public Employee getByName(String username)throws Exception;
	
	// 查询员工 
	public List<Employee> getList1()throws Exception;
	
	// 通过条件获得职位
	public List<Employee> getList2(Employee employee)throws Exception;
}
