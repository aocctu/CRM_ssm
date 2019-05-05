package com.examsys.service;

import java.util.List;

import com.examsys.po.Rights;

public interface RightsService extends IBaseService<Rights, Integer> {

	//获得信息列表
	public List<Rights> getChildrenRightsList(Integer id) throws Exception;
	
	// 权限
	public List<Rights> getByEmployeeId(Integer id) throws Exception;
}
