package com.examsys.service;

import java.util.List;

import com.examsys.po.ExpressSort;
/**
 * 快递分类表业务逻辑层接口
 * @author Administrator
 *
 */
public interface ExpressSortService extends IBaseService<ExpressSort, Integer> {

	List<ExpressSort> getList3(ExpressSort obj) throws Exception;

	ExpressSort get2(String sn) throws Exception;
	

}
