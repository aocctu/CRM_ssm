package com.examsys.po;

/**
 * 职位权限对照表
 * @author edu
 *
 */
public class JobRight {

	private Integer id;
	
	private JobInfo jobInfo;
	private String rightid;

	public JobRight() {
		super();
	}

	public JobRight(Integer id, JobInfo jobInfo, String rightid) {
		super();
		this.id = id;
		this.jobInfo = jobInfo;
		this.rightid = rightid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public JobInfo getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		this.jobInfo = jobInfo;
	}

	public String getRightid() {
		return rightid;
	}

	public void setRightid(String rightid) {
		this.rightid = rightid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((jobInfo == null) ? 0 : jobInfo.hashCode());
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
		JobRight other = (JobRight) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (jobInfo == null) {
			if (other.jobInfo != null)
				return false;
		} else if (!jobInfo.equals(other.jobInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JobRight [id=" + id + ", jobInfo=" + jobInfo + ", rightid=" + rightid + "]";
	}
	
	
	
	
}
