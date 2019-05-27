package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.ExpressSendDao;
import com.examsys.po.ExpressSend;

/**
 * 快递寄送业务逻辑层
 * @author Administrator
 *
 */
@Service("expressSendService")
public class ExpressSendServiceImpl extends AbstractBaseService<ExpressSend, Integer> implements ExpressSendService {

	@Resource
	private ExpressSendDao Dao;
	
	public ExpressSendDao getDao() {
		return Dao;
	}

	public void setDao(ExpressSendDao dao) {
		Dao = dao;
	}

	@Override
	public boolean add(ExpressSend obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}

	@Override
	public boolean update(ExpressSend obj) throws Exception {
		boolean flag = false;
		this.getDao().update(obj);
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
	public ExpressSend get(Integer obj) throws Exception {
		ExpressSend expressSend = null;
		expressSend = this.getDao().get(obj);
		return expressSend;
	}

	@Override
	public List<ExpressSend> getList() throws Exception {
		List<ExpressSend> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<ExpressSend> getList(ExpressSend obj) throws Exception {
		List<ExpressSend> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

}
