package com.examsys.dao;

import java.util.List;

import com.examsys.po.AdminRolesSettings;
import com.examsys.po.Rights;

/**
 *  权限持久层
 * @author edu
 *
 */
public interface RightsDao {

	// 添加
	public void add(Rights rights) throws Exception;
	
	// 修改
	public void update(Rights rights) throws Exception;
	
	// 删除
	public void delete (Integer id)throws Exception;
	
	// 通过主键获取
	public Rights get(Integer id)throws Exception;
	
	// 查询所有
	public List<Rights> getList1()throws Exception;
	
	// 通过条件查询
	public List<Rights> getList2(Rights rights)throws Exception;
	
	//获得信息列表
	public List<Rights> getChildrenRightsList(Integer id) throws Exception;
	
	// 权限
	public List<Rights> getByEmployeeId(String ids) throws Exception;
	
	public String getRightidByEmployeeId(Integer id)throws Exception;
}
