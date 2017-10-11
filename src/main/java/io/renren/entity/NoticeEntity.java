package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * InnoDB free: 11264 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-11 14:57:11
 */
public class NoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long noticeid;
	//
	private String noticetitle;
	//
	private String noticecontent;
	//
	private String noticeaddress;
	//
	private String noticetime;
	//
	private Long noticeemp;
	//
	private String noticeempname;
	//
	private Long noticeentid;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;

	/**
	 * 设置：
	 */
	public void setNoticeid(Long noticeid) {
		this.noticeid = noticeid;
	}
	/**
	 * 获取：
	 */
	public Long getNoticeid() {
		return noticeid;
	}
	/**
	 * 设置：
	 */
	public void setNoticetitle(String noticetitle) {
		this.noticetitle = noticetitle;
	}
	/**
	 * 获取：
	 */
	public String getNoticetitle() {
		return noticetitle;
	}
	/**
	 * 设置：
	 */
	public void setNoticecontent(String noticecontent) {
		this.noticecontent = noticecontent;
	}
	/**
	 * 获取：
	 */
	public String getNoticecontent() {
		return noticecontent;
	}
	/**
	 * 设置：
	 */
	public void setNoticeaddress(String noticeaddress) {
		this.noticeaddress = noticeaddress;
	}
	/**
	 * 获取：
	 */
	public String getNoticeaddress() {
		return noticeaddress;
	}
	/**
	 * 设置：
	 */
	public void setNoticetime(String noticetime) {
		this.noticetime = noticetime;
	}
	/**
	 * 获取：
	 */
	public String getNoticetime() {
		return noticetime;
	}
	/**
	 * 设置：
	 */
	public void setNoticeemp(Long noticeemp) {
		this.noticeemp = noticeemp;
	}
	/**
	 * 获取：
	 */
	public Long getNoticeemp() {
		return noticeemp;
	}
	/**
	 * 设置：
	 */
	public void setNoticeempname(String noticeempname) {
		this.noticeempname = noticeempname;
	}
	/**
	 * 获取：
	 */
	public String getNoticeempname() {
		return noticeempname;
	}
	/**
	 * 设置：
	 */
	public void setNoticeentid(Long noticeentid) {
		this.noticeentid = noticeentid;
	}
	/**
	 * 获取：
	 */
	public Long getNoticeentid() {
		return noticeentid;
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
