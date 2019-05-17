package com.examsys.dao;

import java.util.List;

import com.examsys.po.Delivery;
import com.examsys.po.PartsWarehouse;

/**
 * 配件发料持久层
 * @author Administrator
 *
 */
public interface DeliveryDao {

	public void add(Delivery obj)throws Exception;
	
	public void update(Delivery delivery)throws Exception;
	
	public void delete(Integer id)throws Exception;
	
	public Delivery get(Integer id)throws Exception;
	
	public List<Delivery> getList1()throws Exception;
	
	public List<Delivery> getList2(Delivery delivery)throws Exception;

	/**
	 * 将领料数据保存到发料表中
	 */
	public void add(PartsWarehouse obj)throws Exception;;
	
	
}
