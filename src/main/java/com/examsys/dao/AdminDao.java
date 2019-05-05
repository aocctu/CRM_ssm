package com.examsys.dao;

import java.util.List;

import com.examsys.po.Admin;
/**
 * 管理员数据访问层接口
 * @author edu-1
 *
 */
public interface AdminDao {
	
	/**
	 * 添加信息
	 * @param obj
	 * @throws Exception
	 */
	public void add(Admin obj) throws Exception;

	/**
	 * 更新信息
	 * @param obj
	 * @throws Exception
	 */
	public void update(Admin obj) throws Exception;

	/**
	 * 删除信息
	 * @param id
	 * @throws Exception
	 */
	public void delete(Integer id) throws Exception;
	
	/**
	 * 通过主键获得一条信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Admin get(Integer id) throws Exception;
	
	/**
	 * 获得信息列表
	 * @return
	 * @throws Exception
	 */
	public List<Admin> getAllAdminList() throws Exception;
	
	/**
	 * 通过条件获得信息列表
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List<Admin> getAdminListByCase(Admin obj) throws Exception;
	
	/**
	 *通过用户名去获取管理员 
	 * @param user_name
	 * @return
	 */
	public Admin getAdminByUserName(String user_name) throws Exception;
}
