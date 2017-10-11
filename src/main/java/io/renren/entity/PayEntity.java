package io.renren.entity;

import java.io.Serializable;




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-21 09:46:01
 */
public class PayEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long payid;
	//
	private Long paymoney;
	//
	private String payexpiretime;
	//
	private String paytime;
	//
	private Long payemp;
	//
	private Long entid;
	//
	private String zflx;
	//
	private Long zfzh;
	//
	private Long zfbh;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setPayid(Long payid) {
		this.payid = payid;
	}
	/**
	 * 获取：
	 */
	public Long getPayid() {
		return payid;
	}
	/**
	 * 设置：
	 */
	public void setPaymoney(Long paymoney) {
		this.paymoney = paymoney;
	}
	/**
	 * 获取：
	 */
	public Long getPaymoney() {
		return paymoney;
	}
	/**
	 * 设置：
	 */
	public void setPayexpiretime(String payexpiretime) {
		this.payexpiretime = payexpiretime;
	}
	/**
	 * 获取：
	 */
	public String getPayexpiretime() {
		return payexpiretime;
	}
	/**
	 * 设置：
	 */
	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}
	/**
	 * 获取：
	 */
	public String getPaytime() {
		return paytime;
	}
	/**
	 * 设置：
	 */
	public void setPayemp(Long payemp) {
		this.payemp = payemp;
	}
	/**
	 * 获取：
	 */
	public Long getPayemp() {
		return payemp;
	}
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
	public void setZflx(String zflx) {
		this.zflx = zflx;
	}
	/**
	 * 获取：
	 */
	public String getZflx() {
		return zflx;
	}
	/**
	 * 设置：
	 */
	public void setZfzh(Long zfzh) {
		this.zfzh = zfzh;
	}
	/**
	 * 获取：
	 */
	public Long getZfzh() {
		return zfzh;
	}
	/**
	 * 设置：
	 */
	public void setZfbh(Long zfbh) {
		this.zfbh = zfbh;
	}
	/**
	 * 获取：
	 */
	public Long getZfbh() {
		return zfbh;
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
