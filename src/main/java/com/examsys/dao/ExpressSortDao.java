package com.examsys.dao;

import java.util.List;

import com.examsys.po.ExpressSort;

public interface ExpressSortDao {
	
	//添加
	public void add(ExpressSort expressSort)throws Exception;
	
	//修改
	public void update(ExpressSort expressSort)throws Exception;
	
	//删除
	public void delete(Integer id)throws Exception;
	
	//通过主键获取
	public ExpressSort get(Integer id)throws Exception;
	
	//查询所有
	public List<ExpressSort> getList1()throws Exception;
	
	//条件查询
	public List<ExpressSort> getList2(ExpressSort expressSort)throws Exception;

	public List<ExpressSort> getList3(ExpressSort obj);
	
	// 通过SN查询
	public ExpressSort get2(String sn)throws Exception;

}
