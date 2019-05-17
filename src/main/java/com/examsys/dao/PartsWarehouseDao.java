package com.examsys.dao;

import java.util.List;

import com.examsys.po.PartsWarehouse;

/**
 * 配件仓库持久层
 * @author Administrator
 *
 */
public interface PartsWarehouseDao {

	public void add(PartsWarehouse partsWarehouse)throws Exception;
	
	public void update(PartsWarehouse partsWarehouse)throws Exception;
	
	public void delete(Integer id)throws Exception;
	
	public PartsWarehouse get(Integer id)throws Exception;
	
	/**
	 * 按物料编码查询
	 * @param material_code
	 * @return
	 * @throws Exception
	 */
	public PartsWarehouse get2(String material_code)throws Exception;
	
	public List<PartsWarehouse> getList1()throws Exception;
	
	public List<PartsWarehouse> getList2(PartsWarehouse partsWarehouse)throws Exception;
	
	/**
	 * 申请领料的LIST
	 * @param partsWarehouse
	 * @return
	 * @throws Exception
	 */
	public List<PartsWarehouse> getList3(PartsWarehouse partsWarehouse)throws Exception;
	
	
}
