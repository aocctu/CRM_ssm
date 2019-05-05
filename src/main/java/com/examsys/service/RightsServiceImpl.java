package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.RightsDao;
import com.examsys.po.Rights;
/**
 * 权限表的业务逻辑层
 * @author edu
 *
 */
@Service("rightsService")
public class RightsServiceImpl extends AbstractBaseService<Rights, Integer> implements RightsService {

	// 注入数据访问层
	@Resource
	private RightsDao dao;
	
	public RightsDao getDao() {
		return dao;
	}

	public void setDao(RightsDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(Rights obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);;
		flag=true;
		return flag;
	}

	@Override
	public boolean update(Rights obj) throws Exception {
		boolean flag = false;
		this.getDao().update(obj);;
		flag=true;
		return flag;
	}

	@Override
	public boolean delete(Integer obj) throws Exception {
		boolean flag = false;
		this.getDao().delete(obj);;
		flag=true;
		return flag;
	}

	@Override
	public Rights get(Integer obj) throws Exception {
		Rights rights = null;
		rights = this.getDao().get(obj);
		return rights;
	}

	@Override
	public List<Rights> getList() throws Exception {
		List<Rights> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<Rights> getList(Rights obj) throws Exception {
		List<Rights> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

	@Override
	public List<Rights> getChildrenRightsList(Integer id) throws Exception {
		
		return  this.getDao().getChildrenRightsList(id);
		
	}

	@Override
	public List<Rights> getByEmployeeId(Integer id) throws Exception {
		String ids=this.getDao().getRightidByEmployeeId(id);
		List<Rights> list = null;
		list = this.getDao().getByEmployeeId(ids);
		
		return list;
	}

}
