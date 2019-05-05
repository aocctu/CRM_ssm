package com.examsys.po;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 咨询师跟单记录表实体类
 * @author edu
 *
 */
public class ConsultRecord {

	private Integer id;
	
	private Custom custom; // 客户
	private Employee employee; //员工 
	
	private String consultStatu; // 咨询状态
	@JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
	private Date consultDate;  // 咨询日期
	private String result;  // 咨询备注

	public ConsultRecord() {
		super();
	}

	public ConsultRecord(Integer id, Custom custom, Employee employee, String consultStatu, Date consultDate,
			String result) {
		super();
		this.id = id;
		this.custom = custom;
		this.employee = employee;
		this.consultStatu = consultStatu;
		this.consultDate = consultDate;
		this.result = result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Custom getCustom() {
		return custom;
	}

	public void setCustom(Custom custom) {
		this.custom = custom;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getConsultStatu() {
		return consultStatu;
	}

	public void setConsultStatu(String consultStatu) {
		this.consultStatu = consultStatu;
	}

	public Date getConsultDate() {
		return consultDate;
	}

	public void setConsultDate(Date consultDate) {
		this.consultDate = consultDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((custom == null) ? 0 : custom.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ConsultRecord other = (ConsultRecord) obj;
		if (custom == null) {
			if (other.custom != null)
				return false;
		} else if (!custom.equals(other.custom))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsultRecord [id=" + id + ", custom=" + custom + ", employee=" + employee + ", consultStatu="
				+ consultStatu + ", consultDate=" + consultDate + ", result=" + result + "]";
	}
	
	
	
	
}
