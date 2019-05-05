package com.examsys.po;

import java.sql.Date;

/**
 * 管理员实体类
 * @author edu-1
 *
 */
public class Admin implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;//编号
	private AdminRoles adminRoles;//角色
	private String user_name;//用户名
	private String user_pass;//密码
	private String phone;//电话号码
	private Integer login_times;//登录次数
	private Date create_date;//创建时间
	private Date login_date;//最后登录日期
	private String status;//状态
	private String remark;//备注
	
	public Admin() {
		super();
	}

	public Admin(Integer id, AdminRoles adminRoles, String user_name, String user_pass, String phone,
			Integer login_times, Date create_date, Date login_date, String status, String remark) {
		super();
		this.id = id;
		this.adminRoles = adminRoles;
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.phone = phone;
		this.login_times = login_times;
		this.create_date = create_date;
		this.login_date = login_date;
		this.status = status;
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminRoles getAdminRoles() {
		return adminRoles;
	}

	public void setAdminRoles(AdminRoles adminRoles) {
		this.adminRoles = adminRoles;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getLogin_times() {
		return login_times;
	}

	public void setLogin_times(Integer login_times) {
		this.login_times = login_times;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getLogin_date() {
		return login_date;
	}

	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", adminRoles=" + adminRoles + ", user_name=" + user_name + ", user_pass="
				+ user_pass + ", phone=" + phone + ", login_times=" + login_times + ", create_date=" + create_date
				+ ", login_date=" + login_date + ", status=" + status + ", remark=" + remark + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
		Admin other = (Admin) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		return true;
	}
	
	

}
