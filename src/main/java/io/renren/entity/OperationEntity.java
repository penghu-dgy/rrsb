package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-07 14:37:32
 */
public class OperationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long operid;
	//
	private String operdesc;
	//
	private String opertime;
	//
	private Long operemp;
	//
	private String operorder;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setOperid(Long operid) {
		this.operid = operid;
	}
	/**
	 * 获取：
	 */
	public Long getOperid() {
		return operid;
	}
	/**
	 * 设置：
	 */
	public void setOperdesc(String operdesc) {
		this.operdesc = operdesc;
	}
	/**
	 * 获取：
	 */
	public String getOperdesc() {
		return operdesc;
	}
	/**
	 * 设置：
	 */
	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}
	/**
	 * 获取：
	 */
	public String getOpertime() {
		return opertime;
	}
	/**
	 * 设置：
	 */
	public void setOperemp(Long operemp) {
		this.operemp = operemp;
	}
	/**
	 * 获取：
	 */
	public Long getOperemp() {
		return operemp;
	}
	/**
	 * 设置：
	 */
	public void setOperorder(String operorder) {
		this.operorder = operorder;
	}
	/**
	 * 获取：
	 */
	public String getOperorder() {
		return operorder;
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
