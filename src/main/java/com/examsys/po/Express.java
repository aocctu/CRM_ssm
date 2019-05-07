package com.examsys.po;

/**
 * 快递录入实体类
 * @author Administrator
 *
 */
public class Express {
	
	private Integer id;
	private String exp_iphone;//快递员手机号
	private String exp_name;//收件人名字
	private String pay_type;//费用类型
	private double exp_cost;//费用
	private String exp_num;//快递单号
	private String exp_status;//状态
	private String create_date;//创建时间
	private String remark;//备注
	
	private ExpressCompany expressCompany;//快递公司
	
	private String create_name;//录入人员
	
	public Express() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Express(Integer id, String exp_iphone, String exp_name, String pay_type, double exp_cost, String exp_num,
			String exp_status, String create_date, String remark, ExpressCompany expressCompany, String create_name) {
		super();
		this.id = id;
		this.exp_iphone = exp_iphone;
		this.exp_name = exp_name;
		this.pay_type = pay_type;
		this.exp_cost = exp_cost;
		this.exp_num = exp_num;
		this.exp_status = exp_status;
		this.create_date = create_date;
		this.remark = remark;
		this.expressCompany = expressCompany;
		this.create_name = create_name;
	}







	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getExp_iphone() {
		return exp_iphone;
	}



	public void setExp_iphone(String exp_iphone) {
		this.exp_iphone = exp_iphone;
	}



	public String getExp_name() {
		return exp_name;
	}



	public void setExp_name(String exp_name) {
		this.exp_name = exp_name;
	}



	public String getPay_type() {
		return pay_type;
	}



	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}



	public double getExp_cost() {
		return exp_cost;
	}



	public void setExp_cost(double exp_cost) {
		this.exp_cost = exp_cost;
	}


	public String getExp_num() {
		return exp_num;
	}



	public void setExp_num(String exp_num) {
		this.exp_num = exp_num;
	}



	public String getCreate_name() {
		return create_name;
	}



	public void setCreate_name(String create_name) {
		this.create_name = create_name;
	}



	public String getCreate_date() {
		return create_date;
	}



	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public ExpressCompany getExpressCompany() {
		return expressCompany;
	}



	public void setExpressCompany(ExpressCompany expressCompany) {
		this.expressCompany = expressCompany;
	}


	public String getExp_status() {
		return exp_status;
	}


	public void setExp_status(String exp_status) {
		this.exp_status = exp_status;
	}


	@Override
	public String toString() {
		return "Express [id=" + id + ", exp_iphone=" + exp_iphone + ", exp_name=" + exp_name + ", pay_type=" + pay_type
				+ ", exp_cost=" + exp_cost + ", exp_num=" + exp_num + ", exp_status=" + exp_status + ", create_date="
				+ create_date + ", remark=" + remark + ", expressCompany=" + expressCompany + ", create_name="
				+ create_name + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
		result = prime * result + ((create_name == null) ? 0 : create_name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(exp_cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((exp_iphone == null) ? 0 : exp_iphone.hashCode());
		result = prime * result + ((exp_name == null) ? 0 : exp_name.hashCode());
		result = prime * result + ((exp_num == null) ? 0 : exp_num.hashCode());
		result = prime * result + ((exp_status == null) ? 0 : exp_status.hashCode());
		result = prime * result + ((expressCompany == null) ? 0 : expressCompany.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pay_type == null) ? 0 : pay_type.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
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
		Express other = (Express) obj;
		if (create_date == null) {
			if (other.create_date != null)
				return false;
		} else if (!create_date.equals(other.create_date))
			return false;
		if (create_name == null) {
			if (other.create_name != null)
				return false;
		} else if (!create_name.equals(other.create_name))
			return false;
		if (Double.doubleToLongBits(exp_cost) != Double.doubleToLongBits(other.exp_cost))
			return false;
		if (exp_iphone == null) {
			if (other.exp_iphone != null)
				return false;
		} else if (!exp_iphone.equals(other.exp_iphone))
			return false;
		if (exp_name == null) {
			if (other.exp_name != null)
				return false;
		} else if (!exp_name.equals(other.exp_name))
			return false;
		if (exp_num == null) {
			if (other.exp_num != null)
				return false;
		} else if (!exp_num.equals(other.exp_num))
			return false;
		if (exp_status == null) {
			if (other.exp_status != null)
				return false;
		} else if (!exp_status.equals(other.exp_status))
			return false;
		if (expressCompany == null) {
			if (other.expressCompany != null)
				return false;
		} else if (!expressCompany.equals(other.expressCompany))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pay_type == null) {
			if (other.pay_type != null)
				return false;
		} else if (!pay_type.equals(other.pay_type))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		return true;
	}


}
