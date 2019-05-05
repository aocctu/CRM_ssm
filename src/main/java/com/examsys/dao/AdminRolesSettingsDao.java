package com.examsys.dao;

import java.util.List;

import com.examsys.po.AdminRolesSettings;

/**
 * 
 * 系统功能数据访问层接口
 * @author edu-1
 * 
 *
 */
public interface AdminRolesSettingsDao{

	// 添加信息
	public void add(AdminRolesSettings obj) throws Exception;

	// 更新信息
	public void update(AdminRolesSettings obj) throws Exception;

	// 删除信息
	public void delete(Integer id) throws Exception;
	
	//通过主键获得一条信息
	public AdminRolesSettings get(Integer id) throws Exception;
	
	//获得信息列表
	public List<AdminRolesSettings> getAllAdminRolesSettingsList() throws Exception;
	
	//获得信息列表
	public List<AdminRolesSettings> getChildrenAdminRolesSettingsList(Integer parent_id) throws Exception;
		
	//通过条件获得信息列表
	public List<AdminRolesSettings> getAdminRolesSettingsListByCase(AdminRolesSettings obj) throws Exception;
}
