package com.examsys.service;

import java.util.List;

import com.examsys.po.Express;

public interface ExpressService extends IBaseService<Express, Integer> {

	public List<Express> getList3(Express express)throws Exception;
	
	
}
