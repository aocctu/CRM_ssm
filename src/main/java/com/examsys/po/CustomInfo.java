package com.examsys.po;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 销售客户跟踪信息实体类
 * @author edu
 *
 */
public class CustomInfo {

	private Integer id;
	
	private Custom custom;  // 客户编号
	private Employee employee; // 跟单人编号 
	
	private String statu; //跟单状态
	@JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
	private Date startDate; // 开始日期
	@JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
	private Date lastFollowDate; // 最近联系
	@JsonFormat(pattern="yyyy-MM-dd",locale="zh",timezone="GMT+8")
	private Date planDate; // 计划联系日期
	private String mark; // 备注

	public CustomInfo() {
		super();
	}

	public CustomInfo(Integer id, Custom custom, Employee employee, String statu, Date startDate, Date lastFollowDate,
			Date planDate, String mark) {
		super();
		this.id = id;
		this.custom = custom;
		this.employee = employee;
		this.statu = statu;
		this.startDate = startDate;
		this.lastFollowDate = lastFollowDate;
		this.planDate = planDate;
		this.mark = mark;
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

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLastFollowDate() {
		return lastFollowDate;
	}

	public void setLastFollowDate(Date lastFollowDate) {
		this.lastFollowDate = lastFollowDate;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
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
		CustomInfo other = (CustomInfo) obj;
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
		return "CustomInfo [id=" + id + ", custom=" + custom + ", employee=" + employee + ", statu=" + statu
				+ ", startDate=" + startDate + ", lastFollowDate=" + lastFollowDate + ", planDate=" + planDate
				+ ", mark=" + mark + "]";
	}
	
	
	
	
}
