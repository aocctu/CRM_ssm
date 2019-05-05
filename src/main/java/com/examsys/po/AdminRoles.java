package com.examsys.po;

import java.sql.Date;
/**
 * 管理员角色实体类
 * @author edu-1
 *
 */
public class AdminRoles implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;//编号
	private String role_name;//角色名称
	private String role_privelege;//此角色所能操作的功能项
	private Date create_date;//创建时间
	private String remark;//备注
	
	public AdminRoles() {
		super();
	}

	public AdminRoles(Integer id, String role_name, String role_privelege, Date create_date, String remark) {
		super();
		this.id = id;
		this.role_name = role_name;
		this.role_privelege = role_privelege;
		this.create_date = create_date;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_privelege() {
		return role_privelege;
	}

	public void setRole_privelege(String role_privelege) {
		this.role_privelege = role_privelege;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "AdminRoles [id=" + id + ", role_name=" + role_name + ", role_privelege=" + role_privelege
				+ ", create_date=" + create_date + ", remark=" + remark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role_name == null) ? 0 : role_name.hashCode());
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
		AdminRoles other = (AdminRoles) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role_name == null) {
			if (other.role_name != null)
				return false;
		} else if (!role_name.equals(other.role_name))
			return false;
		return true;
	}
	

}
