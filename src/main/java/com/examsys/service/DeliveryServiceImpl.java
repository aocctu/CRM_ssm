package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.DeliveryDao;
import com.examsys.po.Delivery;
import com.examsys.po.PartsWarehouse;
/**
 * 配件仓库业务层
 * @author Administrator
 *
 */
@Service("deliveryService")
public class DeliveryServiceImpl extends AbstractBaseService<Delivery, Integer> implements DeliveryService {

	@Resource
	private DeliveryDao Dao;
	
	public DeliveryDao getDao() {
		return Dao;
	}

	public void setDao(DeliveryDao dao) {
		Dao = dao;
	}

	/**
	 * 将领料数据保存到发料表中
	 */
	@Override
	public boolean add(PartsWarehouse obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}
	
	@Override
	public boolean add(Delivery obj) throws Exception {
		boolean flag = false;
		this.getDao().add(obj);
		flag = true;
		return flag;
	}


	@Override
	public boolean update(Delivery obj) throws Exception {
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
	public Delivery get(Integer obj) throws Exception {
		Delivery Delivery = null;
		Delivery = this.getDao().get(obj);
		return Delivery;
	}

	@Override
	public List<Delivery> getList() throws Exception {
		List<Delivery> list = null;
		list = this.getDao().getList1();
		return list;
	}

	@Override
	public List<Delivery> getList(Delivery obj) throws Exception {
		List<Delivery> list = null;
		list = this.getDao().getList2(obj);
		return list;
	}


	

}
