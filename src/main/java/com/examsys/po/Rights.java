package com.examsys.po;

import java.util.List;

/**
 * 权限表
 * @author edu
 *
 */
public class Rights {

	private Integer rid;  // 权限编号
	private String rightName; // 权限名称
	private String rightType; // 权限级别
	private String url; // 选项卡url值
	private Rights rights; //类别编号 父级
	private String rightCode;  // 权限的验证
	private List<Rights> children;

	public Rights() {
		super();
	}

	public Rights(Integer rid, String rightName, String rightType, String url, Rights rights) {
		super();
		this.rid = rid;
		this.rightName = rightName;
		this.rightType = rightType;
		this.url = url;
		this.rights = rights;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightType() {
		return rightType;
	}

	public void setRightType(String rightType) {
		this.rightType = rightType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Rights getRights() {
		return rights;
	}

	public void setRights(Rights rights) {
		this.rights = rights;
	}
	
	public List<Rights> getChildren() {
		return children;
	}

	public void setChildren(List<Rights> children) {
		this.children = children;
	}
	
	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rid == null) ? 0 : rid.hashCode());
		result = prime * result + ((rightName == null) ? 0 : rightName.hashCode());
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
		Rights other = (Rights) obj;
		if (rid == null) {
			if (other.rid != null)
				return false;
		} else if (!rid.equals(other.rid))
			return false;
		if (rightName == null) {
			if (other.rightName != null)
				return false;
		} else if (!rightName.equals(other.rightName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rights [rid=" + rid + ", rightName=" + rightName + ", rightType=" + rightType + ", url=" + url
				+ ", rights=" + rights + "]";
	}
	
	
	
}
