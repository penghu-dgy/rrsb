package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-11 17:00:06
 */
public class ApplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long appId;
	//
	private Long jobId;
	//
	private String jobName;
	//
	private Long empId;
	//
	private String empName;
	//
	private String empPhone;
	//
	private String empTime;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	/**
	 * 获取：
	 */
	public Long getAppId() {
		return appId;
	}
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
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	/**
	 * 获取：
	 */
	public String getJobName() {
		return jobName;
	}
	/**
	 * 设置：
	 */
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	/**
	 * 获取：
	 */
	public Long getEmpId() {
		return empId;
	}
	/**
	 * 设置：
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * 获取：
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * 设置：
	 */
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	/**
	 * 获取：
	 */
	public String getEmpPhone() {
		return empPhone;
	}
	/**
	 * 设置：
	 */
	public void setEmpTime(String empTime) {
		this.empTime = empTime;
	}
	/**
	 * 获取：
	 */
	public String getEmpTime() {
		return empTime;
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
