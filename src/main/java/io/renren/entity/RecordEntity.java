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
public class RecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long recordid;
	//
	private String recordcontent;
	//
	private String recordaddress;
	//
	private String recordtime;
	//
	private Long recordemp;
	//
	private String recordorderid;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setRecordid(Long recordid) {
		this.recordid = recordid;
	}
	/**
	 * 获取：
	 */
	public Long getRecordid() {
		return recordid;
	}
	/**
	 * 设置：
	 */
	public void setRecordcontent(String recordcontent) {
		this.recordcontent = recordcontent;
	}
	/**
	 * 获取：
	 */
	public String getRecordcontent() {
		return recordcontent;
	}
	/**
	 * 设置：
	 */
	public void setRecordaddress(String recordaddress) {
		this.recordaddress = recordaddress;
	}
	/**
	 * 获取：
	 */
	public String getRecordaddress() {
		return recordaddress;
	}
	/**
	 * 设置：
	 */
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	/**
	 * 获取：
	 */
	public String getRecordtime() {
		return recordtime;
	}
	/**
	 * 设置：
	 */
	public void setRecordemp(Long recordemp) {
		this.recordemp = recordemp;
	}
	/**
	 * 获取：
	 */
	public Long getRecordemp() {
		return recordemp;
	}
	/**
	 * 设置：
	 */
	public void setRecordorderid(String recordorderid) {
		this.recordorderid = recordorderid;
	}
	/**
	 * 获取：
	 */
	public String getRecordorderid() {
		return recordorderid;
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
