package com.examsys.dao;

import java.util.List;

import com.examsys.po.ConsultRecord;

/**
 * 咨询师中意记录表持久层
 * @author edu
 *
 */
public interface ConsultRecordDao {

	// 添加
	public void add(ConsultRecord consultRecord) throws Exception;
	
	// 修改
	public void update(ConsultRecord consultRecord)throws Exception;
	
	// 删除 
	public void delete(Integer id)throws Exception;
	
	// 通过主键获取
	public ConsultRecord get(Integer id)throws Exception;
	
	// 查询所有
	public List<ConsultRecord> getList1()throws Exception;
	
	// 条件查询
	public List<ConsultRecord> getList2(ConsultRecord consultRecord)throws Exception;
}
