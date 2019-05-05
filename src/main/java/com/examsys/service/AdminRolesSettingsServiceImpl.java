package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.AdminRolesSettingsDao;
import com.examsys.po.AdminRolesSettings;
/**
 * 系统功能业务逻辑层实现类
 * @author edu-1
 *
 */
@Service("adminRolesSettingsService")
public class AdminRolesSettingsServiceImpl extends AbstractBaseService<AdminRolesSettings, Integer> implements AdminRolesSettingsService {

	@Resource
	private AdminRolesSettingsDao dao;//系统功能数据访问层对象
	
	public AdminRolesSettingsDao getDao() {
		return dao;
	}

	public void setDao(AdminRolesSettingsDao dao) {
		this.dao = dao;
	}

	/**
	 * 添加系统功能
	 * @throws Exception 
	 *  
	 */
	@Override
	public boolean add(AdminRolesSettings obj) throws Exception {
		boolean flag=false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}

	/**
	 * 修改系统功能
	 */
	@Override
	public boolean update(AdminRolesSettings obj) throws Exception{
		boolean flag=false;
		this.getDao().update(obj);
		flag=true;
		return flag;
	}

	/**
	 * 删除系统功能
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
	 * 获取系统功能
	 * @param id 编号
	 */
	@Override
	public AdminRolesSettings get(Integer id) throws Exception{
		AdminRolesSettings adminRolesSettings=null;
		adminRolesSettings = this.getDao().get(id);
		return adminRolesSettings;
	}

	/**
	 * 获得系统功能表所有记录
	 */
	@Override
	public List<AdminRolesSettings> getList() throws Exception{
		List<AdminRolesSettings> list=null;
		list = this.getDao().getAllAdminRolesSettingsList();
		
		return list;
	}

	/**
	 * 带条件获取系统功能记录
	 */
	@Override
	public List<AdminRolesSettings> getList(AdminRolesSettings obj) throws Exception{
		List<AdminRolesSettings> list=null;
		list = this.getDao().getAdminRolesSettingsListByCase(obj);
		
		return list;
	}

	@Override
	public List<AdminRolesSettings> getChildrenAdminRolesSettingsList(Integer parent_id) throws Exception {
		return this.getDao().getChildrenAdminRolesSettingsList(parent_id);
	}

}
