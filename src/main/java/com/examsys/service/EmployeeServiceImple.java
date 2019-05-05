package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.EmployeeDao;
import com.examsys.po.Employee;
/**
 * 员工业务逻辑层
 * @author edu
 *
 */
@Service("employeeService")
public class EmployeeServiceImple extends AbstractBaseService<Employee, Integer> implements EmployeeService {

	@Resource
	private EmployeeDao dao; // 数据访问
	
	
	public EmployeeDao getDao() {
		return dao;
	}

	public void setDao(EmployeeDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(Employee obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}

	@Override
	public boolean update(Employee obj) throws Exception {
		boolean flag = false;
		this.getDao().update(obj);
		flag=true;
		return flag;
	}

	@Override
	public boolean delete(Integer obj) throws Exception {
		boolean flag = false;
		this.getDao().delete(obj);
		flag=true;
		return flag;
	}

	@Override
	public Employee get(Integer obj) throws Exception {
		Employee employee = null;
		employee = this.getDao().get(obj);
		return employee;
	}

	@Override
	public List<Employee> getList() throws Exception {
		List<Employee> list = null;
		list = this.getDao().getList1();
		
		return list;
	}

	@Override
	public List<Employee> getList(Employee obj) throws Exception {
		List<Employee> list = null;
		list = this.getDao().getList2(obj);
		
		return list;
	}

	@Override
	public Employee getByName(String username) throws Exception {
		Employee employee = null;
		employee = this.getDao().getByName(username);
		return employee;
	}



}
