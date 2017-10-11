package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-24 09:11:33
 */
public class FeedbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long feedbackid;
	//
	private Long empid;
	//
	private String fbtype;
	//
	private String fbdesc;
	//
	private String fbcontent;
	//
	private String imgs;

	/**
	 * 设置：
	 */
	public void setFeedbackid(Long feedbackid) {
		this.feedbackid = feedbackid;
	}
	/**
	 * 获取：
	 */
	public Long getFeedbackid() {
		return feedbackid;
	}
	/**
	 * 设置：
	 */
	public void setEmpid(Long empid) {
		this.empid = empid;
	}
	/**
	 * 获取：
	 */
	public Long getEmpid() {
		return empid;
	}
	/**
	 * 设置：
	 */
	public void setFbtype(String fbtype) {
		this.fbtype = fbtype;
	}
	/**
	 * 获取：
	 */
	public String getFbtype() {
		return fbtype;
	}
	/**
	 * 设置：
	 */
	public void setFbdesc(String fbdesc) {
		this.fbdesc = fbdesc;
	}
	/**
	 * 获取：
	 */
	public String getFbdesc() {
		return fbdesc;
	}
	/**
	 * 设置：
	 */
	public void setFbcontent(String fbcontent) {
		this.fbcontent = fbcontent;
	}
	/**
	 * 获取：
	 */
	public String getFbcontent() {
		return fbcontent;
	}
	/**
	 * 设置：
	 */
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	/**
	 * 获取：
	 */
	public String getImgs() {
		return imgs;
	}
}
