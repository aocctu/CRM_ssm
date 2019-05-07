package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.ExpressSortDao;
import com.examsys.po.ExpressSort;

@Service("expressSort")
public class ExpressSortServiceImpl extends AbstractBaseService<ExpressSort, Integer> implements ExpressSortService {

	@Resource
	private ExpressSortDao dao;//数据访问
	
	public ExpressSortDao getDao() {
		return dao;
	}

	public void setDao(ExpressSortDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(ExpressSort obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}

	@Override
	public boolean update(ExpressSort obj) throws Exception {
		boolean flag = false;
		this.getDao().update(obj);
		flag = true;
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
	public ExpressSort get(Integer obj) throws Exception {
		ExpressSort sort = null;
		sort = this.getDao().get(obj);
		return sort;
	}

	@Override
	public List<ExpressSort> getList() throws Exception {
		List<ExpressSort> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<ExpressSort> getList(ExpressSort obj) throws Exception {
		List<ExpressSort> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

}
