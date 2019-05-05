package com.examsys.po;

/**
 * 重置密码记录实体类
 * @author edu
 *
 */
public class Resetpass {

	private Integer id;
	private String username; // 用户名 
	private String  phoneNo; // 手机

	public Resetpass() {
		super();
	}

	public Resetpass(Integer id, String username, String phoneNo) {
		super();
		this.id = id;
		this.username = username;
		this.phoneNo = phoneNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Resetpass other = (Resetpass) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resetpass [id=" + id + ", username=" + username + ", phoneNo=" + phoneNo + "]";
	}
	
	
	
}
