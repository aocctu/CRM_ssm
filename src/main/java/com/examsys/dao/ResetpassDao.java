package com.examsys.dao;

import java.util.List;

import com.examsys.po.Resetpass;

/**
 * 重置密码记录持久层
 * @author edu
 *
 */
public interface ResetpassDao {

	// 添加
	public void add(Resetpass resetpass) throws Exception;
	
	// 修改
	public void update(Resetpass resetpass)throws Exception;
	
	// 删除 
	public void delete(Integer id)throws Exception;
	
	// 通过主键获取
	public Resetpass get(Integer id)throws Exception;
	
	// 查询所有
	public List<Resetpass> getList1()throws Exception;
	
	// 条件查询
	public List<Resetpass> getList2(Resetpass resetpass)throws Exception;
}
