package com.examsys.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.examsys.po.Employee;

public class SessionNullException {

	 public void ex() {
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		 Employee employee = (Employee)request.getSession().getAttribute("EMPLOYEE");
			if(employee.equals(null)){
					try {
						throw new Exception("session已过期，请重新登录...");
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
	 }
}
