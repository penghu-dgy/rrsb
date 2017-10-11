package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-23 15:48:51
 */
public class WorkEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long workid;
	//
	private String workcontent;
	//
	private String worktime;
	//
	private Long workemp;
	//
	private String workimage;
	//
	private Long workcust;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setWorkid(Long workid) {
		this.workid = workid;
	}
	/**
	 * 获取：
	 */
	public Long getWorkid() {
		return workid;
	}
	/**
	 * 设置：
	 */
	public void setWorkcontent(String workcontent) {
		this.workcontent = workcontent;
	}
	/**
	 * 获取：
	 */
	public String getWorkcontent() {
		return workcontent;
	}
	/**
	 * 设置：
	 */
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	/**
	 * 获取：
	 */
	public String getWorktime() {
		return worktime;
	}
	/**
	 * 设置：
	 */
	public void setWorkemp(Long workemp) {
		this.workemp = workemp;
	}
	/**
	 * 获取：
	 */
	public Long getWorkemp() {
		return workemp;
	}
	/**
	 * 设置：
	 */
	public void setWorkimage(String workimage) {
		this.workimage = workimage;
	}
	/**
	 * 获取：
	 */
	public String getWorkimage() {
		return workimage;
	}
	/**
	 * 设置：
	 */
	public void setWorkcust(Long workcust) {
		this.workcust = workcust;
	}
	/**
	 * 获取：
	 */
	public Long getWorkcust() {
		return workcust;
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
