package com.examsys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.examsys.dao.AdminRolesDao;
import com.examsys.po.AdminRoles;
/**
 * 管理员角色业务逻辑层实现类
 * @author edu-1
 *
 */
@Service("adminRolesService")
public class AdminRolesServiceImpl extends AbstractBaseService<AdminRoles, Integer> implements AdminRolesService {
	@Resource
	private AdminRolesDao dao;//管理员角色数据访问层对象
	
	public AdminRolesDao getDao() {
		return dao;
	}

	public void setDao(AdminRolesDao dao) {
		this.dao = dao;
	}

	/**
	 * 添加管理员角色
	 * @throws Exception 
	 *  
	 */
	@Override
	public boolean add(AdminRoles obj) throws Exception {
		boolean flag=false;
		if(obj.getRole_name()==null//角色名称不能为null
				||"".equals(obj.getRole_name())//角色名称不能为空
				||obj.getRole_name().length()>50){//角色名称长度不能大于50个字条
			return false;
		}
		
		if(obj.getRemark().length()>50){//名称长度不能大于50个字条
			return false;
		}
		
		this.getDao().add(obj);
		flag=true;
		return flag;
	}

	/**
	 * 修改管理员角色
	 * @throws Exception 
	 */
	@Override
	public boolean update(AdminRoles obj) throws Exception {
		boolean flag=false;
		this.getDao().update(obj);
		flag=true;
		return flag;
	}

	/**
	 * 删除管理员角色
	 * @param id 编号
	 * @throws Exception 
	 */
	@Override
	public boolean delete(Integer id) throws Exception {
		boolean flag=false;
		this.getDao().delete(id);
		flag=true;
		return flag;
	}

	/**
	 * 获取管理员角色
	 * @param id 编号
	 * @throws Exception 
	 */
	@Override
	public AdminRoles get(Integer id) throws Exception {
		AdminRoles adminRoles=null;
		adminRoles = this.getDao().get(id);
		return adminRoles;
	}

	/**
	 * 获得管理员角色表所有记录
	 * @throws Exception 
	 */
	@Override
	public List<AdminRoles> getList() throws Exception {
		List<AdminRoles> list=null;
		list = this.getDao().getList1();
		
		return list;
	}

	/**
	 * 带条件获取管理员角色记录
	 * @throws Exception 
	 */
	@Override
	public List<AdminRoles> getList(AdminRoles obj) throws Exception {
		List<AdminRoles> list=null;
		list = this.getDao().getList2(obj);
		
		return list;
	}

}
