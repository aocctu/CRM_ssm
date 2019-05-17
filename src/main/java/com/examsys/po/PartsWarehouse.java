package com.examsys.po;

/**
 * 配件仓库
 * @author Administrator
 *
 */
public class PartsWarehouse {

	private Integer id;
	private String material_code;//物料编码
	private String model;//机型
	private Integer quantity;//数量
	private String description;//描述
	private String remark;//备注
	private String type;//类型
	private Integer ichiban;//良品
	private Integer scrap;//报废
	private String status;//状态
	private String create_name;//录入人
	private String create_date;//录入时间
	private String materials_status;//物料状态
	private String receive_num;//领料数据
	
	public PartsWarehouse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PartsWarehouse(Integer id, String material_code, String model, Integer quantity, String description,
			String remark, String type, Integer ichiban, Integer scrap, String status, String create_name,
			String create_date, String materials_status, String receive_num) {
		super();
		this.id = id;
		this.material_code = material_code;
		this.model = model;
		this.quantity = quantity;
		this.description = description;
		this.remark = remark;
		this.type = type;
		this.ichiban = ichiban;
		this.scrap = scrap;
		this.status = status;
		this.create_name = create_name;
		this.create_date = create_date;
		this.materials_status = materials_status;
		this.receive_num = receive_num;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIchiban() {
		return ichiban;
	}

	public void setIchiban(Integer ichiban) {
		this.ichiban = ichiban;
	}

	public Integer getScrap() {
		return scrap;
	}

	public void setScrap(Integer scrap) {
		this.scrap = scrap;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getMaterials_status() {
		return materials_status;
	}


	public void setMaterials_status(String materials_status) {
		this.materials_status = materials_status;
	}


	public String getReceive_num() {
		return receive_num;
	}


	public void setReceive_num(String receive_num) {
		this.receive_num = receive_num;
	}



	@Override
	public String toString() {
		return "PartsWarehouse [id=" + id + ", material_code=" + material_code + ", model=" + model + ", quantity="
				+ quantity + ", description=" + description + ", remark=" + remark + ", type=" + type + ", ichiban="
				+ ichiban + ", scrap=" + scrap + ", status=" + status + ", create_name=" + create_name
				+ ", create_date=" + create_date + ", materials_status=" + materials_status + ", receive_num="
				+ receive_num + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((create_date == null) ? 0 : create_date.hashCode());
		result = prime * result + ((create_name == null) ? 0 : create_name.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((ichiban == null) ? 0 : ichiban.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((material_code == null) ? 0 : material_code.hashCode());
		result = prime * result + ((materials_status == null) ? 0 : materials_status.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result + ((receive_num == null) ? 0 : receive_num.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result + ((scrap == null) ? 0 : scrap.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		PartsWarehouse other = (PartsWarehouse) obj;
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
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (receive_num == null) {
			if (other.receive_num != null)
				return false;
		} else if (!receive_num.equals(other.receive_num))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (scrap == null) {
			if (other.scrap != null)
				return false;
		} else if (!scrap.equals(other.scrap))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


	
}
