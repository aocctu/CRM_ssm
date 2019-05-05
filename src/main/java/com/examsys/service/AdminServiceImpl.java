package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.AdminDao;
import com.examsys.po.Admin;
/**
 * 管理员业务逻辑层实现类
 * @author edu-1
 *
 */
@Service("adminService")
public class AdminServiceImpl extends AbstractBaseService<Admin, Integer> implements AdminService {
	@Resource
	private AdminDao dao;//管理员数据访问层对象
	
	public AdminDao getDao() {
		return dao;
	}

	public void setDao(AdminDao dao) {
		this.dao = dao;
	}

	/**
	 * 添加管理员
	 *  
	 */
	@Override
	public boolean add(Admin obj) throws Exception{
		boolean flag=false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}

	/**
	 * 修改管理员
	 */
	@Override
	public boolean update(Admin obj) throws Exception{
		boolean flag=false;
		this.getDao().update(obj);
		flag=true;
		return flag;
	}

	/**
	 * 删除管理员
	 * @param id 编号
	 */
	@Override
	public boolean delete(Integer id) throws Exception{
		boolean flag=false;
		this.getDao().delete(id);
		flag=true;
		return flag;
	}

	/**
	 * 获取管理员
	 * @param id 编号
	 */
	@Override
	public Admin get(Integer id) throws Exception{
		Admin admin=null;
		admin = this.getDao().get(id);
		return admin;
	}

	/**
	 * 获得管理员表所有记录
	 */
	@Override
	public List<Admin> getList() throws Exception{
		List<Admin> list=null;
		list = this.getDao().getAllAdminList();
		
		return list;
	}

	/**
	 * 带条件获取管理员记录
	 */
	@Override
	public List<Admin> getList(Admin obj) throws Exception{
		List<Admin> list=null;
		list = this.getDao().getAdminListByCase(obj);
		
		return list;
	}
	
	/**
	 *通过用户名去获取管理员 
	 * @param user_name
	 * @return
	 */
	public Admin getAdminByUserName(String user_name) throws Exception{
		Admin admin=null;
		admin = this.getDao().getAdminByUserName(user_name);
		return admin;
	}

}
