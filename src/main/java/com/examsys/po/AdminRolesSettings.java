package com.examsys.po;

import java.util.List;

/**
 * 系统功能实体类
 * @author edu-1
 *
 */
public class AdminRolesSettings implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;//编号
	private String name;//菜单或功能名称
	private String code;//菜单或者功能代码
	private AdminRolesSettings adminRolesSettings;//父级菜单
	private Integer porder;//排序号
	private String url;//超链接
	private List<AdminRolesSettings> children;
	
	public AdminRolesSettings() {
		super();
	}

	public AdminRolesSettings(Integer id, String name, String code, AdminRolesSettings adminRolesSettings,
			Integer porder) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.adminRolesSettings = adminRolesSettings;
		this.porder = porder;
	}

	/**
	 * 获取编号
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置编号
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public AdminRolesSettings getAdminRolesSettings() {
		return adminRolesSettings;
	}

	public void setAdminRolesSettings(AdminRolesSettings adminRolesSettings) {
		this.adminRolesSettings = adminRolesSettings;
	}

	public Integer getPorder() {
		return porder;
	}

	public void setPorder(Integer porder) {
		this.porder = porder;
	}

	public String getText(){
		return name;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<AdminRolesSettings> getChildren() {
		return children;
	}

	public void setChildren(List<AdminRolesSettings> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminRolesSettings other = (AdminRolesSettings) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
