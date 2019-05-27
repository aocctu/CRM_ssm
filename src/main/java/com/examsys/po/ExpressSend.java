package com.examsys.po;

/**
 * 快递寄送实体类
 * @author Administrator
 *
 */
public class ExpressSend {

	private Integer id;
	private String exp_num;//快递单号
	private String model;//机型
	private String sn;//SN
	private String create_name;//创建人
	private String create_date;//创建时间
	private String exp_status;//快递状态
	public ExpressSend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExpressSend(Integer id, String exp_num, String model, String sn, String create_name, String create_date,
			String exp_status) {
		super();
		this.id = id;
		this.exp_num = exp_num;
		this.model = model;
		this.sn = sn;
		this.create_name = create_name;
		this.create_date = create_date;
		this.exp_status = exp_status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExp_num() {
		return exp_num;
	}
	public void setExp_num(String exp_num) {
		this.exp_num = exp_num;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
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
	public String getExp_status() {
		return exp_status;
	}
	public void setExp_status(String exp_status) {
		this.exp_status = exp_status;
	}
	@Override
	public String toString() {
		return "ExpressSend [id=" + id + ", exp_num=" + exp_num + ", model=" + model + ", sn=" + sn + ", create_name="
				+ create_name + ", create_date=" + create_date + ", exp_status=" + exp_status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
		result = prime * result + ((create_name == null) ? 0 : create_name.hashCode());
		result = prime * result + ((exp_num == null) ? 0 : exp_num.hashCode());
		result = prime * result + ((exp_status == null) ? 0 : exp_status.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((sn == null) ? 0 : sn.hashCode());
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
		ExpressSend other = (ExpressSend) obj;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (sn == null) {
			if (other.sn != null)
				return false;
		} else if (!sn.equals(other.sn))
			return false;
		return true;
	}
	
}
