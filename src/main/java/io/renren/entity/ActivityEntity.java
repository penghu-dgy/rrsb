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
public class ActivityEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long actid;
	//
	private String acttype;
	//
	private String actbegintime;
	//
	private String actendtime;
	//
	private Long actdays;
	//
	private String actreason;
	//
	private String actaddress;
	//
	private Long actpublisher;
	//
	private Long actchecker;
	//
	private Long actstate;
	//
	private String actpublishtime;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setActid(Long actid) {
		this.actid = actid;
	}
	/**
	 * 获取：
	 */
	public Long getActid() {
		return actid;
	}
	/**
	 * 设置：
	 */
	public void setActtype(String acttype) {
		this.acttype = acttype;
	}
	/**
	 * 获取：
	 */
	public String getActtype() {
		return acttype;
	}
	/**
	 * 设置：
	 */
	public void setActbegintime(String actbegintime) {
		this.actbegintime = actbegintime;
	}
	/**
	 * 获取：
	 */
	public String getActbegintime() {
		return actbegintime;
	}
	/**
	 * 设置：
	 */
	public void setActendtime(String actendtime) {
		this.actendtime = actendtime;
	}
	/**
	 * 获取：
	 */
	public String getActendtime() {
		return actendtime;
	}
	/**
	 * 设置：
	 */
	public void setActdays(Long actdays) {
		this.actdays = actdays;
	}
	/**
	 * 获取：
	 */
	public Long getActdays() {
		return actdays;
	}
	/**
	 * 设置：
	 */
	public void setActreason(String actreason) {
		this.actreason = actreason;
	}
	/**
	 * 获取：
	 */
	public String getActreason() {
		return actreason;
	}
	/**
	 * 设置：
	 */
	public void setActaddress(String actaddress) {
		this.actaddress = actaddress;
	}
	/**
	 * 获取：
	 */
	public String getActaddress() {
		return actaddress;
	}
	/**
	 * 设置：
	 */
	public void setActpublisher(Long actpublisher) {
		this.actpublisher = actpublisher;
	}
	/**
	 * 获取：
	 */
	public Long getActpublisher() {
		return actpublisher;
	}
	/**
	 * 设置：
	 */
	public void setActchecker(Long actchecker) {
		this.actchecker = actchecker;
	}
	/**
	 * 获取：
	 */
	public Long getActchecker() {
		return actchecker;
	}
	/**
	 * 设置：
	 */
	public void setActstate(Long actstate) {
		this.actstate = actstate;
	}
	/**
	 * 获取：
	 */
	public Long getActstate() {
		return actstate;
	}
	/**
	 * 设置：
	 */
	public void setActpublishtime(String actpublishtime) {
		this.actpublishtime = actpublishtime;
	}
	/**
	 * 获取：
	 */
	public String getActpublishtime() {
		return actpublishtime;
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
