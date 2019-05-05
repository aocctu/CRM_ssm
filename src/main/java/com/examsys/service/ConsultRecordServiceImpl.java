package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.ConsultRecordDao;
import com.examsys.po.ConsultRecord;

/**
 * 咨询师跟单记录业务逻辑层
 * @author edu
 *
 */
@Service("consultRecordService")
public class ConsultRecordServiceImpl extends AbstractBaseService<ConsultRecord, Integer>
		implements ConsultRecordService {

	// 注入数据访问
	@Resource
	private ConsultRecordDao dao;
	
	public ConsultRecordDao getDao() {
		return dao;
	}

	public void setDao(ConsultRecordDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(ConsultRecord obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}

	@Override
	public boolean update(ConsultRecord obj) throws Exception {
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
	public ConsultRecord get(Integer obj) throws Exception {
		ConsultRecord consultRecord = null;
		consultRecord = this.getDao().get(obj);
		return consultRecord;
	}

	@Override
	public List<ConsultRecord> getList() throws Exception {
		List<ConsultRecord> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<ConsultRecord> getList(ConsultRecord obj) throws Exception {
		List<ConsultRecord> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

}
