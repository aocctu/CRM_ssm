package com.examsys.dao;

import java.util.List;

import com.examsys.po.Express;
/**
 * 快递管理表持久层
 * @author Administrator
 *
 */
public interface ExpressDao {
	// 添加
	public void add(Express express) throws Exception;
	
	// 修改
	public void update(Express express)throws Exception;
	
	// 删除
	public void delete(Integer id)throws Exception;
	
	// 通过主键获取 
	public Express get(Integer id)throws Exception;
	
	// 查询所有
	public List<Express> getList1()throws Exception;
	
	// 条件查询
	public List<Express> getList2(Express express)throws Exception;
	
	public List<Express> getList3(Express express)throws Exception;
	

}
