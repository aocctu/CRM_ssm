package com.examsys.service;

import com.examsys.po.Delivery;
import com.examsys.po.PartsWarehouse;

public interface DeliveryService extends IBaseService<Delivery, Integer> {

	boolean add(PartsWarehouse obj) throws Exception;

}
