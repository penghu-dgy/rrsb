package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-13 13:59:53
 */
public class JobEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long jobId;
	//
	private String jobTitle;
	//
	private String jobCount;
	//
	private String jobSalary;
	//
	private String province;
	//
	private String city;
	//
	private String area;
	//
	private String jobPubTime;
	//
	private String jobExpireTime;
	//
	private String jobDesc;
	//
	private Long pubId;
	//
	private String pubName;
	//
	private String pubPhone;
	//
	private String company;
	//
	private String jobState;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	/**
	 * 获取：
	 */
	public Long getJobId() {
		return jobId;
	}
	/**
	 * 设置：
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
	 * 获取：
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * 设置：
	 */
	public void setJobCount(String jobCount) {
		this.jobCount = jobCount;
	}
	/**
	 * 获取：
	 */
	public String getJobCount() {
		return jobCount;
	}
	/**
	 * 设置：
	 */
	public void setJobSalary(String jobSalary) {
		this.jobSalary = jobSalary;
	}
	/**
	 * 获取：
	 */
	public String getJobSalary() {
		return jobSalary;
	}
	/**
	 * 设置：
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 设置：
	 */
	public void setJobPubTime(String jobPubTime) {
		this.jobPubTime = jobPubTime;
	}
	/**
	 * 获取：
	 */
	public String getJobPubTime() {
		return jobPubTime;
	}
	/**
	 * 设置：
	 */
	public void setJobExpireTime(String jobExpireTime) {
		this.jobExpireTime = jobExpireTime;
	}
	/**
	 * 获取：
	 */
	public String getJobExpireTime() {
		return jobExpireTime;
	}
	/**
	 * 设置：
	 */
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	/**
	 * 获取：
	 */
	public String getJobDesc() {
		return jobDesc;
	}
	/**
	 * 设置：
	 */
	public void setPubId(Long pubId) {
		this.pubId = pubId;
	}
	/**
	 * 获取：
	 */
	public Long getPubId() {
		return pubId;
	}
	/**
	 * 设置：
	 */
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	/**
	 * 获取：
	 */
	public String getPubName() {
		return pubName;
	}
	/**
	 * 设置：
	 */
	public void setPubPhone(String pubPhone) {
		this.pubPhone = pubPhone;
	}
	/**
	 * 获取：
	 */
	public String getPubPhone() {
		return pubPhone;
	}
	/**
	 * 设置：
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * 获取：
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * 设置：
	 */
	public void setJobState(String jobState) {
		this.jobState = jobState;
	}
	/**
	 * 获取：
	 */
	public String getJobState() {
		return jobState;
	}
	/**
	 * 设置：
	 */
	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}
	/**
	 * 获取：
	 */
	public String getBz1() {
		return bz1;
	}
	/**
	 * 设置：
	 */
	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}
	/**
	 * 获取：
	 */
	public String getBz2() {
		return bz2;
	}
	/**
	 * 设置：
	 */
	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}
	/**
	 * 获取：
	 */
	public String getBz3() {
		return bz3;
	}
}
