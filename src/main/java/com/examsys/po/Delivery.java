package com.examsys.po;

/**
 * 配件发料
 * @author Administrator
 *
 */
public class Delivery {

	private Integer id;
	private String batch_num;//批次号
	private String material_code;//物料编码
	private String model;//机型
	private String description;//描述
	private Integer ichiban;//库存良品
	private String materials_status;//物料状态
	private Integer receive_num;//领料数量
	private String apply_name;//需领料人
	private String apply_date;//领料单创建时间
	private String delivery_name;//发料人
	private String delivery_date;//发料时间
	
	public Delivery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Delivery(Integer id, String batch_num, String material_code, String model, String description,
			Integer ichiban, String materials_status, Integer receive_num, String apply_name, String apply_date,
			String delivery_name, String delivery_date) {
		super();
		this.id = id;
		this.batch_num = batch_num;
		this.material_code = material_code;
		this.model = model;
		this.description = description;
		this.ichiban = ichiban;
		this.materials_status = materials_status;
		this.receive_num = receive_num;
		this.apply_name = apply_name;
		this.apply_date = apply_date;
		this.delivery_name = delivery_name;
		this.delivery_date = delivery_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBatch_num() {
		return batch_num;
	}

	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}

	public String getMaterial_code() {
		return material_code;
	}

	public void setMaterial_code(String material_code) {
		this.material_code = material_code;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIchiban() {
		return ichiban;
	}

	public void setIchiban(Integer ichiban) {
		this.ichiban = ichiban;
	}

	public String getMaterials_status() {
		return materials_status;
	}

	public void setMaterials_status(String materials_status) {
		this.materials_status = materials_status;
	}

	public Integer getReceive_num() {
		return receive_num;
	}

	public void setReceive_num(Integer receive_num) {
		this.receive_num = receive_num;
	}

	public String getApply_name() {
		return apply_name;
	}

	public void setApply_name(String apply_name) {
		this.apply_name = apply_name;
	}

	public String getApply_date() {
		return apply_date;
	}

	public void setApply_date(String apply_date) {
		this.apply_date = apply_date;
	}

	public String getDelivery_name() {
		return delivery_name;
	}

	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}

	public String getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	@Override
	public String toString() {
		return "Delivery [id=" + id + ", batch_num=" + batch_num + ", material_code=" + material_code + ", model="
				+ model + ", description=" + description + ", ichiban=" + ichiban + ", materials_status="
				+ materials_status + ", receive_num=" + receive_num + ", apply_name=" + apply_name + ", apply_date="
				+ apply_date + ", delivery_name=" + delivery_name + ", delivery_date=" + delivery_date + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apply_date == null) ? 0 : apply_date.hashCode());
		result = prime * result + ((apply_name == null) ? 0 : apply_name.hashCode());
		result = prime * result + ((batch_num == null) ? 0 : batch_num.hashCode());
		result = prime * result + ((delivery_date == null) ? 0 : delivery_date.hashCode());
		result = prime * result + ((delivery_name == null) ? 0 : delivery_name.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((ichiban == null) ? 0 : ichiban.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((material_code == null) ? 0 : material_code.hashCode());
		result = prime * result + ((materials_status == null) ? 0 : materials_status.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((receive_num == null) ? 0 : receive_num.hashCode());
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
		Delivery other = (Delivery) obj;
		if (apply_date == null) {
			if (other.apply_date != null)
				return false;
		} else if (!apply_date.equals(other.apply_date))
			return false;
		if (apply_name == null) {
			if (other.apply_name != null)
				return false;
		} else if (!apply_name.equals(other.apply_name))
			return false;
		if (batch_num == null) {
			if (other.batch_num != null)
				return false;
		} else if (!batch_num.equals(other.batch_num))
			return false;
		if (delivery_date == null) {
			if (other.delivery_date != null)
				return false;
		} else if (!delivery_date.equals(other.delivery_date))
			return false;
		if (delivery_name == null) {
			if (other.delivery_name != null)
				return false;
		} else if (!delivery_name.equals(other.delivery_name))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
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
		if (material_code == null) {
			if (other.material_code != null)
				return false;
		} else if (!material_code.equals(other.material_code))
			return false;
		if (materials_status == null) {
			if (other.materials_status != null)
				return false;
		} else if (!materials_status.equals(other.materials_status))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (receive_num == null) {
			if (other.receive_num != null)
				return false;
		} else if (!receive_num.equals(other.receive_num))
			return false;
		return true;
	}

	

	
}
