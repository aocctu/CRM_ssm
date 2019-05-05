package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.JobRightDao;
import com.examsys.po.JobRight;

/**
 * 职位权限对照表业务层
 * @author edu
 *
 */
@Service("jobRightService")
public class JobRightServiceImpl extends AbstractBaseService<JobRight, Integer> implements JobRightService {

	// 注入数据访问层
	@Resource
	private JobRightDao dao;
	
	public JobRightDao getDao() {
		return dao;
	}

	public void setDao(JobRightDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(JobRight obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}

	@Override
	public boolean update(JobRight obj) throws Exception {
		boolean flag = false;
		this.getDao().update(obj);
		flag=true;
		return flag;
	}

	@Override
	public boolean delete(Integer obj) throws Exception {
		boolean flag = false;
		this.getDao().delete(obj);
		flag=true;
		return flag;
	}

	@Override
	public JobRight get(Integer obj) throws Exception {
		JobRight jobRight = null;
		jobRight = this.getDao().get(obj);
		return jobRight;
	}

	@Override
	public List<JobRight> getList() throws Exception {
		List<JobRight> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<JobRight> getList(JobRight obj) throws Exception {
		List<JobRight> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

}
