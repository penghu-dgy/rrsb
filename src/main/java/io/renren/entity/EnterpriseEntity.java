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
public class EnterpriseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long entid;
	//
	private String entname;
	//
	private String entcreatetime;
	//
	private Long entemp;
	//
	private String entmanager;
	//
	private String entphone;
	//
	private Long entstate;
	//
	private String entprovice;
	//
	private String entcity;
	//
	private String entarea;
	//
	private String entaddress;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setEntid(Long entid) {
		this.entid = entid;
	}
	/**
	 * 获取：
	 */
	public Long getEntid() {
		return entid;
	}
	/**
	 * 设置：
	 */
	public void setEntname(String entname) {
		this.entname = entname;
	}
	/**
	 * 获取：
	 */
	public String getEntname() {
		return entname;
	}
	/**
	 * 设置：
	 */
	public void setEntcreatetime(String entcreatetime) {
		this.entcreatetime = entcreatetime;
	}
	/**
	 * 获取：
	 */
	public String getEntcreatetime() {
		return entcreatetime;
	}
	/**
	 * 设置：
	 */
	public void setEntemp(Long entemp) {
		this.entemp = entemp;
	}
	/**
	 * 获取：
	 */
	public Long getEntemp() {
		return entemp;
	}
	/**
	 * 设置：
	 */
	public void setEntmanager(String entmanager) {
		this.entmanager = entmanager;
	}
	/**
	 * 获取：
	 */
	public String getEntmanager() {
		return entmanager;
	}
	/**
	 * 设置：
	 */
	public void setEntphone(String entphone) {
		this.entphone = entphone;
	}
	/**
	 * 获取：
	 */
	public String getEntphone() {
		return entphone;
	}
	/**
	 * 设置：
	 */
	public void setEntstate(Long entstate) {
		this.entstate = entstate;
	}
	/**
	 * 获取：
	 */
	public Long getEntstate() {
		return entstate;
	}
	/**
	 * 设置：
	 */
	public void setEntprovice(String entprovice) {
		this.entprovice = entprovice;
	}
	/**
	 * 获取：
	 */
	public String getEntprovice() {
		return entprovice;
	}
	/**
	 * 设置：
	 */
	public void setEntcity(String entcity) {
		this.entcity = entcity;
	}
	/**
	 * 获取：
	 */
	public String getEntcity() {
		return entcity;
	}
	/**
	 * 设置：
	 */
	public void setEntarea(String entarea) {
		this.entarea = entarea;
	}
	/**
	 * 获取：
	 */
	public String getEntarea() {
		return entarea;
	}
	/**
	 * 设置：
	 */
	public void setEntaddress(String entaddress) {
		this.entaddress = entaddress;
	}
	/**
	 * 获取：
	 */
	public String getEntaddress() {
		return entaddress;
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
