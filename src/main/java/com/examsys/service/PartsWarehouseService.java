package com.examsys.service;

import java.util.List;

import com.examsys.po.PartsWarehouse;

public interface PartsWarehouseService extends IBaseService<PartsWarehouse, Integer> {

	public List<PartsWarehouse> getList3(PartsWarehouse partsWarehouse)throws Exception;
	
	public PartsWarehouse get2(String material_code)throws Exception;
}
