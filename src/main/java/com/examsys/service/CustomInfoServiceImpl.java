package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.CustomInfoDao;
import com.examsys.po.CustomInfo;
/**
 * 销售客户跟踪信息业务逻辑层
 * @author edu
 *
 */
@Service("customInfoService")
public class CustomInfoServiceImpl extends AbstractBaseService<CustomInfo, Integer> implements CustomInfoService {

	// 注入数据访问层
	@Resource
	private CustomInfoDao dao;

	public CustomInfoDao getDao() {
		return dao;
	}

	public void setDao(CustomInfoDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(CustomInfo obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}

	@Override
	public boolean update(CustomInfo obj) throws Exception {
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
	public CustomInfo get(Integer obj) throws Exception {
		CustomInfo customInfo = null;
		customInfo = this.getDao().get(obj);
		return customInfo;
	}

	@Override
	public List<CustomInfo> getList() throws Exception {
		List<CustomInfo> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<CustomInfo> getList(CustomInfo obj) throws Exception {
		List<CustomInfo> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

}
