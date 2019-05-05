package com.examsys.dao;

import java.util.List;

//数据访问层接口,T表示表对象的po类型，K表示表中的主键对应的类型
public interface IBaseDao<T, K> {
	// 添加信息
	public void add(T obj) throws Exception;

	// 更新信息
	public void update(T obj) throws Exception;

	// 删除信息
	public void delete(K id) throws Exception;
	
	//通过主键获得一条信息
	public T get(K id) throws Exception;
	
	//获得信息列表
	public List<T> getList() throws Exception;
	
	//通过条件获得信息列表
	public List<T> getList(T obj) throws Exception;
}
