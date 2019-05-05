package com.examsys.dao;

import java.util.List;

import com.examsys.po.ExpressCompany;
/**
 * 快递公司
 * @author edu
 *
 */
public interface ExpressCompanyDao {

	// 添加快递公司
	public void add(ExpressCompany expressCompany) throws Exception;
	
	// 更新快递公司
	public void update(ExpressCompany expressCompany) throws Exception;
	
	// 删除快递公司
	public void delete(Integer id) throws Exception;
	
	// 通过主键获得一条信息
	public ExpressCompany get(Integer id) throws Exception;
	
	// 查询快递公司
	public List<ExpressCompany> getList1() throws Exception;
	
	// 通过条件获得快递公司
	public List<ExpressCompany> getList2(ExpressCompany obj) throws Exception;
}
