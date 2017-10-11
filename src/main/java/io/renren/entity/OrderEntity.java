package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-13 15:51:09
 */
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String orderid;
	//
	private String ordertype;
	//
	private String orderbegintime;
	//
	private String orderendtime;
	//
	private String orderdesc;
	//
	private Long ordercust;
	//
	private Long orderstate;
	//
	private Long orderemp;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;
	//
	private Long entid;

	/**
	 * 设置：
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：
	 */
	public String getOrderid() {
		return orderid;
	}
	/**
	 * 设置：
	 */
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	/**
	 * 获取：
	 */
	public String getOrdertype() {
		return ordertype;
	}
	/**
	 * 设置：
	 */
	public void setOrderbegintime(String orderbegintime) {
		this.orderbegintime = orderbegintime;
	}
	/**
	 * 获取：
	 */
	public String getOrderbegintime() {
		return orderbegintime;
	}
	/**
	 * 设置：
	 */
	public void setOrderendtime(String orderendtime) {
		this.orderendtime = orderendtime;
	}
	/**
	 * 获取：
	 */
	public String getOrderendtime() {
		return orderendtime;
	}
	/**
	 * 设置：
	 */
	public void setOrderdesc(String orderdesc) {
		this.orderdesc = orderdesc;
	}
	/**
	 * 获取：
	 */
	public String getOrderdesc() {
		return orderdesc;
	}
	/**
	 * 设置：
	 */
	public void setOrdercust(Long ordercust) {
		this.ordercust = ordercust;
	}
	/**
	 * 获取：
	 */
	public Long getOrdercust() {
		return ordercust;
	}
	/**
	 * 设置：
	 */
	public void setOrderstate(Long orderstate) {
		this.orderstate = orderstate;
	}
	/**
	 * 获取：
	 */
	public Long getOrderstate() {
		return orderstate;
	}
	/**
	 * 设置：
	 */
	public void setOrderemp(Long orderemp) {
		this.orderemp = orderemp;
	}
	/**
	 * 获取：
	 */
	public Long getOrderemp() {
		return orderemp;
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
}
