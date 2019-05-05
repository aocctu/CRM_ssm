package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.ResetpassDao;
import com.examsys.po.Resetpass;
/**
 * 重置密码记录业务逻辑层
 * @author edu
 *
 */
@Service("resetpassService")
public class ResetpassServiceImpl extends AbstractBaseService<Resetpass, Integer> implements ResetpassService {

	// 注入数据访问层
	@Resource
	private ResetpassDao dao ;
	
	public ResetpassDao getDao() {
		return dao;
	}

	public void setDao(ResetpassDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(Resetpass obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}

	@Override
	public boolean update(Resetpass obj) throws Exception {
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
	public Resetpass get(Integer obj) throws Exception {
		Resetpass resetpass = null;
		resetpass = this.getDao().get(obj);
		return resetpass;
	}

	@Override
	public List<Resetpass> getList() throws Exception {
		List<Resetpass> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<Resetpass> getList(Resetpass obj) throws Exception {
		List<Resetpass> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

}
