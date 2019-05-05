package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.DepartmentDao;
import com.examsys.po.Department;
/**
 * 部门的业务逻辑层
 * @author edu
 *
 */

@Service("departmentService")
public class DepartmentServiceImpl extends AbstractBaseService<Department, Integer> implements DepartmentService {

	@Resource
	private DepartmentDao dao; // 数据访问层对象
	
	public DepartmentDao getDao() {
		return dao;
	}

	public void setDao(DepartmentDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(Department obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag=true;
		
		return flag;
	}

	@Override
	public boolean update(Department obj) throws Exception {
		
		return false;
	}

	@Override
	public boolean delete(Integer obj) throws Exception {
		
		return false;
	}

	@Override
	public Department get(Integer obj) throws Exception {
		Department department = null;
		department = this.getDao().get(obj);
		
		return department;
	}

	@Override
	public List<Department> getList() throws Exception {
		List<Department> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<Department> getList(Department obj) throws Exception {
		List<Department> list = null;
		list = this.getDao().getList2(obj);
		
		return list;
	}

}
