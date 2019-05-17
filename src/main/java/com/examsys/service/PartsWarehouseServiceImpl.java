package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.PartsWarehouseDao;
import com.examsys.po.PartsWarehouse;
/**
 * 配件仓库业务层
 * @author Administrator
 *
 */
@Service("partsWarehouseService")
public class PartsWarehouseServiceImpl extends AbstractBaseService<PartsWarehouse, Integer> implements PartsWarehouseService {

	@Resource
	private PartsWarehouseDao Dao;
	
	public PartsWarehouseDao getDao() {
		return Dao;
	}

	public void setDao(PartsWarehouseDao dao) {
		Dao = dao;
	}

	@Override
	public boolean add(PartsWarehouse obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}

	@Override
	public boolean update(PartsWarehouse obj) throws Exception {
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
	public PartsWarehouse get(Integer obj) throws Exception {
		PartsWarehouse partsWarehouse = null;
		partsWarehouse = this.getDao().get(obj);
		return partsWarehouse;
	}
	
	@Override
	public PartsWarehouse get2(String material_code) throws Exception {
		PartsWarehouse partsWarehouse = null;
		partsWarehouse = this.getDao().get2(material_code);
		return partsWarehouse;
	}
	

	@Override
	public List<PartsWarehouse> getList() throws Exception {
		List<PartsWarehouse> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<PartsWarehouse> getList(PartsWarehouse obj) throws Exception {
		List<PartsWarehouse> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}

	@Override
	public List<PartsWarehouse> getList3(PartsWarehouse partsWarehouse) throws Exception {
		List<PartsWarehouse> list = null;
		list = this.getDao().getList3(partsWarehouse);
		return list;
	}

	
	

}
