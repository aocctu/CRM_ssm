package com.examsys.dao;

import java.util.List;

import com.examsys.po.Custom;

/**
 * 客户基础信息持久层
 * @author edu
 *
 */
public interface CustomDao {

	// 添加
	public void add(Custom custom) throws Exception;
	
	// 修改
	public void update(Custom custom)throws Exception;
	
	// 删除
	public void delete(Integer id)throws Exception;
	
	// 通过主键获取 
	public Custom get(Integer id)throws Exception;
	
	// 查询所有
	public List<Custom> getList1()throws Exception;
	
	// 条件查询
	public List<Custom> getList2(Custom custom)throws Exception;
}
