package com.examsys.dao;

import java.util.List;

import com.examsys.po.JobRight;

/**
 * 职位权限对照表持久层
 * @author edu
 *
 */
public interface JobRightDao {

	// 添加
	public void add(JobRight jobRight) throws Exception;
	
	// 修改
	public void update(JobRight jobRight)throws Exception;
	
	// 删除
	public void delete(Integer id)throws Exception;
	
	// 通过主键获取
	public JobRight get(Integer id)throws Exception;
	
	// 查询所有
	public List<JobRight> getList1()throws Exception;
	
	// 通过条件查询
	public List<JobRight> getList2(JobRight jobRight)throws Exception;
}
