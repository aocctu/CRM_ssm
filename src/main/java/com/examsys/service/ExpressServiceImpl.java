package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.ExpressDao;
import com.examsys.po.Express;

@Service("expressService")
public class ExpressServiceImpl extends AbstractBaseService<Express, Integer> implements ExpressService {

	@Resource
	private ExpressDao dao; //数据访问
	
	
	public ExpressDao getDao() {
		return dao;
	}

	public void setDao(ExpressDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(Express obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}

	@Override
	public boolean update(Express obj) throws Exception {
		boolean flag = false;
		this.getDao().update(obj);
		flag= true;
		return flag;
	}

	@Override
	public boolean delete(Integer obj) throws Exception {
		boolean flag = false;
		this.getDao().delete(obj);
		flag = true;
		return flag;
	}

	@Override
	public Express get(Integer obj) throws Exception {
		Express express = null;
		express = this.getDao().get(obj);
		return express;
	}

	@Override
	public List<Express> getList() throws Exception {
		List<Express> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<Express> getList(Express obj) throws Exception {
		List<Express> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

	@Override
	public List<Express> getList3(Express express) throws Exception {
		List<Express> list = null;
		list = this.getDao().getList3(express);
		return list;
	}

}
