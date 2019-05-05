package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.ExpressCompanyDao;
import com.examsys.po.ExpressCompany;
/**
 * 部门的业务逻辑层
 * @author edu
 *
 */

@Service("expressCompanyService")
public class ExpressCompanyServiceImpl extends AbstractBaseService<ExpressCompany, Integer> implements ExpressCompanyService {

	@Resource
	private ExpressCompanyDao dao; // 数据访问层对象
	
	public ExpressCompanyDao getDao() {
		return dao;
	}

	public void setDao(ExpressCompanyDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(ExpressCompany obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag=true;
		
		return flag;
	}

	@Override
	public boolean update(ExpressCompany obj) throws Exception {
		
		return false;
	}

	@Override
	public boolean delete(Integer obj) throws Exception {
		
		return false;
	}

	@Override
	public ExpressCompany get(Integer obj) throws Exception {
		ExpressCompany ExpressCompany = null;
		ExpressCompany = this.getDao().get(obj);
		
		return ExpressCompany;
	}

	@Override
	public List<ExpressCompany> getList() throws Exception {
		List<ExpressCompany> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<ExpressCompany> getList(ExpressCompany obj) throws Exception {
		List<ExpressCompany> list = null;
		list = this.getDao().getList2(obj);
		
		return list;
	}

}
