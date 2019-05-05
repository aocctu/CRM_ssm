package com.examsys.service;

import com.examsys.po.Admin;

/**
 * 管理员业务逻辑层接口
 * @author edu-1
 *
 */
public interface AdminService extends IBaseService<Admin, Integer> {
	
	/**
	 *通过用户名去获取管理员 
	 * @param user_name
	 * @return
	 * @throws Exception 
	 */
	public Admin getAdminByUserName(String user_name) throws Exception;
}
