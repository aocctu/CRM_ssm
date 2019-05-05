package com.examsys.service;

import java.util.List;

import com.examsys.po.AdminRolesSettings;

/**
 * 系统功能业务逻辑层接口
 * @author edu-1
 *
 */
public interface AdminRolesSettingsService extends IBaseService<AdminRolesSettings, Integer> {
	//获得信息列表
	public List<AdminRolesSettings> getChildrenAdminRolesSettingsList(Integer parent_id) throws Exception;
}
