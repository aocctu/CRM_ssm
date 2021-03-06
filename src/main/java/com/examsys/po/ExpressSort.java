package com.examsys.po;


/**
 *  快递分类实体类
 * @author Administrator
 *
 */
public class ExpressSort {

	private Integer id;
	private String exp_num; //快递单号
	private String model; //机型
	private String material_type; //物料类型
	private String quantity; //数量
	private String fault; //来料故障
	private String type; //类型
	private String remark; //备注
	private String color; //颜色
	private String sn;
	private String create_name;//分捡人员
	private String create_date;//创建时间 
	private String confirm_fault;//确认故障
	private String repair_type;//维修类型
	private String position;//位置
	private String ichiban;//良品
	private String scrap;//报废
	
	public ExpressSort() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExpressSort(Integer id, String exp_num, String model, String material_type, String quantity, String fault,
			String type, String remark, String color, String sn, String create_name, String create_date,
			String confirm_fault, String repair_type, String position, String ichiban, String scrap) {
		super();
		this.id = id;
		this.exp_num = exp_num;
		this.model = model;
		this.material_type = material_type;
		this.quantity = quantity;
		this.fault = fault;
		this.type = type;
		this.remark = remark;
		this.color = color;
		this.sn = sn;
		this.create_name = create_name;
		this.create_date = create_date;
		this.confirm_fault = confirm_fault;
		this.repair_type = repair_type;
		this.position = position;
		this.ichiban = ichiban;
		this.scrap = scrap;
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


	public String getMaterial_type() {
		return material_type;
	}


	public void setMaterial_type(String material_type) {
		this.material_type = material_type;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getFault() {
		return fault;
	}


	public void setFault(String fault) {
		this.fault = fault;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
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


	public String getConfirm_fault() {
		return confirm_fault;
	}


	public void setConfirm_fault(String confirm_fault) {
		this.confirm_fault = confirm_fault;
	}


	public String getRepair_type() {
		return repair_type;
	}


	public void setRepair_type(String repair_type) {
		this.repair_type = repair_type;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getIchiban() {
		return ichiban;
	}


	public void setIchiban(String ichiban) {
		this.ichiban = ichiban;
	}


	public String getScrap() {
		return scrap;
	}


	public void setScrap(String scrap) {
		this.scrap = scrap;
	}


	@Override
	public String toString() {
		return "ExpressSort [id=" + id + ", exp_num=" + exp_num + ", model=" + model + ", material_type="
				+ material_type + ", quantity=" + quantity + ", fault=" + fault + ", type=" + type + ", remark="
				+ remark + ", color=" + color + ", sn=" + sn + ", create_name=" + create_name + ", create_date="
				+ create_date + ", confirm_fault=" + confirm_fault + ", repair_type=" + repair_type + ", position="
				+ position + ", ichiban=" + ichiban + ", scrap=" + scrap + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((confirm_fault == null) ? 0 : confirm_fault.hashCode());
		result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
		result = prime * result + ((create_name == null) ? 0 : create_name.hashCode());
		result = prime * result + ((exp_num == null) ? 0 : exp_num.hashCode());
		result = prime * result + ((fault == null) ? 0 : fault.hashCode());
		result = prime * result + ((ichiban == null) ? 0 : ichiban.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((material_type == null) ? 0 : material_type.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((repair_type == null) ? 0 : repair_type.hashCode());
		result = prime * result + ((scrap == null) ? 0 : scrap.hashCode());
		result = prime * result + ((sn == null) ? 0 : sn.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ExpressSort other = (ExpressSort) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (confirm_fault == null) {
			if (other.confirm_fault != null)
				return false;
		} else if (!confirm_fault.equals(other.confirm_fault))
			return false;
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
		if (fault == null) {
			if (other.fault != null)
				return false;
		} else if (!fault.equals(other.fault))
			return false;
		if (ichiban == null) {
			if (other.ichiban != null)
				return false;
		} else if (!ichiban.equals(other.ichiban))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (material_type == null) {
			if (other.material_type != null)
				return false;
		} else if (!material_type.equals(other.material_type))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (repair_type == null) {
			if (other.repair_type != null)
				return false;
		} else if (!repair_type.equals(other.repair_type))
			return false;
		if (scrap == null) {
			if (other.scrap != null)
				return false;
		} else if (!scrap.equals(other.scrap))
			return false;
		if (sn == null) {
			if (other.sn != null)
				return false;
		} else if (!sn.equals(other.sn))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}



}
