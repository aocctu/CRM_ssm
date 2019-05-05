package com.examsys.po;

/**
 * 员工实体类
 * @author edu
 *
 */
public class Employee {

	private Integer id;
	private String username;
	private String pass;
	private String nickname;
	private String realname;
	
	private JobInfo jobInfo;
	private Department department;
	
	private String phoneNo;
	private String officeTel;
	private String workStatu;
	
	public Employee() {
		super();
	}

	public Employee(Integer id, String username, String pass, String nickname, String realname, JobInfo jobInfo,
			Department department, String phoneNo, String officeTel, String workStatu) {
		super();
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.nickname = nickname;
		this.realname = realname;
		this.jobInfo = jobInfo;
		this.department = department;
		this.phoneNo = phoneNo;
		this.officeTel = officeTel;
		this.workStatu = workStatu;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getWorkStatu() {
		return workStatu;
	}

	public void setWorkStatu(String workStatu) {
		this.workStatu = workStatu;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", pass=" + pass + ", nickname=" + nickname
				+ ", realname=" + realname + ", jobInfo=" + jobInfo + ", department=" + department + ", phoneNo="
				+ phoneNo + ", officeTel=" + officeTel + ", workStatu=" + workStatu + "]";
	}
	
	
	
	
	
}
