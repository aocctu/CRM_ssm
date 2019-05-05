package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.CustomDao;
import com.examsys.po.Custom;
/**
 * 客户基础信息业务逻辑层
 * @author edu
 *
 */
@Service("customService")
public class CustomServiceImpl extends AbstractBaseService<Custom, Integer> implements CustomService {

	// 注入数据访问
	@Resource
	private CustomDao dao;
	
	public CustomDao getDao() {
		return dao;
	}

	public void setDao(CustomDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(Custom obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}

	@Override
	public boolean update(Custom obj) throws Exception {
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
	public Custom get(Integer obj) throws Exception {
		Custom custom = null;
		custom = this.getDao().get(obj);
		return custom;
	}

	@Override
	public List<Custom> getList() throws Exception {
		List<Custom> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<Custom> getList(Custom obj) throws Exception {
		List<Custom> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

}
