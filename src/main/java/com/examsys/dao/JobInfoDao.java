package com.examsys.dao;

import java.util.List;

import com.examsys.po.JobInfo;
/**
 * 职位
 * @author edu
 *
 */
public interface JobInfoDao {

	// 添加
	public void add(JobInfo jobInfo) throws Exception;
	
	// 修改
	public void update(JobInfo info) throws Exception;
	
	// 删除
	public void delete(Integer id)throws Exception;
	
	// 通过主键获得一条信息
	public JobInfo get(Integer id)throws Exception;
	
	// 查询职位
	public List<JobInfo> getList1()throws Exception;
	
	// 通过条件获得职位
	public List<JobInfo> getList2(JobInfo info) throws Exception;
}
