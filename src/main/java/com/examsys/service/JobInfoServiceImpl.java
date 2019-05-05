package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.JobInfoDao;
import com.examsys.po.JobInfo;

/**
 * 职位的业务逻辑层
 * @author edu
 *
 */
@Service("jobInfoService")
public class JobInfoServiceImpl extends AbstractBaseService<JobInfo, Integer> implements JobInfoService {
	@Resource
	private JobInfoDao dao; // 数据访问层
	
	public JobInfoDao getDao() {
		return dao;
	}

	public void setDao(JobInfoDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(JobInfo obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag=true;
		return flag;
	}

	@Override
	public boolean update(JobInfo obj) throws Exception {
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
	public JobInfo get(Integer obj) throws Exception {
		JobInfo jobInfo = null;
		jobInfo=this.getDao().get(obj);
		
		return jobInfo;
	}

	@Override
	public List<JobInfo> getList() throws Exception {
		List<JobInfo> list = null;
		list = this.getDao().getList1();
		
		return list;
	}

	@Override
	public List<JobInfo> getList(JobInfo obj) throws Exception {
		List<JobInfo> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

}
