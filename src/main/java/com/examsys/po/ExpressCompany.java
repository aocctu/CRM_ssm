package com.examsys.po;

/**
 * 快递公司实体类对象
 * @author edu
 *
 */
public class ExpressCompany {

	private Integer id;
	private String exp_company; // 快递公司
	
	public ExpressCompany(Integer id, String exp_company) {
		super();
		this.id = id;
		this.exp_company = exp_company;
	}
	public ExpressCompany() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExp_company() {
		return exp_company;
	}
	public void setExp_company(String exp_company) {
		this.exp_company = exp_company;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exp_company == null) ? 0 : exp_company.hashCode());
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
		ExpressCompany other = (ExpressCompany) obj;
		if (exp_company == null) {
			if (other.exp_company != null)
				return false;
		} else if (!exp_company.equals(other.exp_company))
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
		return "ExpressCompany [id=" + id + ", exp_company=" + exp_company + "]";
	}
	
	
}
