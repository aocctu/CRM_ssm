package com.examsys.service;

import com.examsys.po.Employee;

public interface EmployeeService extends IBaseService<Employee, Integer> {

	public Employee getByName(String username)throws Exception;
}
