package com.examsys.dao;

import java.util.List;

import com.examsys.po.ExpressSend;
/**
 * 快递寄送表持久层
 * @author Administrator
 *
 */
public interface ExpressSendDao {
	// 添加
	public void add(ExpressSend send) throws Exception;
	
	// 修改
	public void update(ExpressSend send)throws Exception;
	
	// 删除
	public void delete(Integer id)throws Exception;
	
	// 通过主键获取 
	public ExpressSend get(Integer id)throws Exception;
	
	// 查询所有
	public List<ExpressSend> getList1()throws Exception;
	
	// 条件查询
	public List<ExpressSend> getList2(ExpressSend send)throws Exception;
	
}
