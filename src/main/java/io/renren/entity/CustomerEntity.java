package io.renren.entity;

import java.io.Serializable;
import java.math.BigDecimal;




/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-11 14:23:06
 */
public class CustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long custid;
	//
	private String custname;
	//
	private String custtype;
	//
	private String custprovince;
	//
	private String custcity;
	//
	private String custarea;
	//
	private String custaddress;
	//
	private String custphone;
	//
	private BigDecimal custlng;
	//
	private BigDecimal custlat;
	//
	private String custtime;
	//
	private String custstate;
	//
	private Long custemp;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setCustid(Long custid) {
		this.custid = custid;
	}
	/**
	 * 获取：
	 */
	public Long getCustid() {
		return custid;
	}
	/**
	 * 设置：
	 */
	public void setCustname(String custname) {
		this.custname = custname;
	}
	/**
	 * 获取：
	 */
	public String getCustname() {
		return custname;
	}
	/**
	 * 设置：
	 */
	public void setCusttype(String custtype) {
		this.custtype = custtype;
	}
	/**
	 * 获取：
	 */
	public String getCusttype() {
		return custtype;
	}
	/**
	 * 设置：
	 */
	public void setCustprovince(String custprovince) {
		this.custprovince = custprovince;
	}
	/**
	 * 获取：
	 */
	public String getCustprovince() {
		return custprovince;
	}
	/**
	 * 设置：
	 */
	public void setCustcity(String custcity) {
		this.custcity = custcity;
	}
	/**
	 * 获取：
	 */
	public String getCustcity() {
		return custcity;
	}
	/**
	 * 设置：
	 */
	public void setCustarea(String custarea) {
		this.custarea = custarea;
	}
	/**
	 * 获取：
	 */
	public String getCustarea() {
		return custarea;
	}
	/**
	 * 设置：
	 */
	public void setCustaddress(String custaddress) {
		this.custaddress = custaddress;
	}
	/**
	 * 获取：
	 */
	public String getCustaddress() {
		return custaddress;
	}
	/**
	 * 设置：
	 */
	public void setCustphone(String custphone) {
		this.custphone = custphone;
	}
	/**
	 * 获取：
	 */
	public String getCustphone() {
		return custphone;
	}
	/**
	 * 设置：
	 */
	public void setCustlng(BigDecimal custlng) {
		this.custlng = custlng;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getCustlng() {
		return custlng;
	}
	/**
	 * 设置：
	 */
	public void setCustlat(BigDecimal custlat) {
		this.custlat = custlat;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getCustlat() {
		return custlat;
	}
	/**
	 * 设置：
	 */
	public void setCusttime(String custtime) {
		this.custtime = custtime;
	}
	/**
	 * 获取：
	 */
	public String getCusttime() {
		return custtime;
	}
	/**
	 * 设置：
	 */
	public void setCuststate(String custstate) {
		this.custstate = custstate;
	}
	/**
	 * 获取：
	 */
	public String getCuststate() {
		return custstate;
	}
	/**
	 * 设置：
	 */
	public void setCustemp(Long custemp) {
		this.custemp = custemp;
	}
	/**
	 * 获取：
	 */
	public Long getCustemp() {
		return custemp;
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
