package com.examsys.dao;

import java.util.List;

import com.examsys.po.CustomInfo;

/**
 * 销售客户跟踪信息实体类持久层
 * @author edu
 *
 */
public interface CustomInfoDao {

	// 添加
	public void add(CustomInfo customInfo) throws Exception;
	
	// 修改
	public void update(CustomInfo customInfo)throws Exception;
	
	// 删除 
	public void delete(Integer id)throws Exception;
	
	// 通过主键获取
	public CustomInfo get(Integer id)throws Exception;
	
	// 查询所有
	public List<CustomInfo> getList1()throws Exception;
	
	// 条件查询
	public List<CustomInfo> getList2(CustomInfo customInfo)throws Exception;
}
