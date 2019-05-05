package com.examsys.po;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 客户基础信息
 * @author edu
 *
 */
public class Custom {

	private Integer id;
	private String name;
	private String education;
	private String phoneNo;
	private String qq;
	private String email;
	private String customStatu;
	@JsonFormat(pattern="yyyy-MM-dd HH-mm-ss",locale="zh",timezone="GMT+8")
	private Date createDate;
	private String inviteName;

	public Custom() {
		super();
	}

	public Custom(Integer id, String name, String education, String phoneNo, String qq, String email,
			String customStatu, Date createDate, String inviteName) {
		super();
		this.id = id;
		this.name = name;
		this.education = education;
		this.phoneNo = phoneNo;
		this.qq = qq;
		this.email = email;
		this.customStatu = customStatu;
		this.createDate = createDate;
		this.inviteName = inviteName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomStatu() {
		return customStatu;
	}

	public void setCustomStatu(String customStatu) {
		this.customStatu = customStatu;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getInviteName() {
		return inviteName;
	}

	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
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
		Custom other = (Custom) obj;
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

	@Override
	public String toString() {
		return "Custom [id=" + id + ", name=" + name + ", education=" + education + ", phoneNo=" + phoneNo + ", qq="
				+ qq + ", email=" + email + ", customStatu=" + customStatu + ", createDate=" + createDate
				+ ", inviteName=" + inviteName + "]";
	}
	
	
	
	
}
