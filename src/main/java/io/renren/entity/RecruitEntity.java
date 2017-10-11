package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-14 11:37:00
 */
public class RecruitEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long recruitid;
	//
	private String recruittitle;
	//
	private String recruitcontent;
	//
	private String recruittime;
	//
	private String recruitendtime;
	//
	private Long recruitent;
	//
	private Long recruitemp;
	//
	private Long recruitstate;
	//
	private Long recruitflag;
	//
	private String bz1;
	//
	private String bz2;
	//
	private String bz3;
	//
	private String recruitProvice;
	//
	private String recruitCity;
	//
	private String recruitArea;
	
	public Long getRecruitid() {
		return recruitid;
	}
	public void setRecruitid(Long recruitid) {
		this.recruitid = recruitid;
	}
	public String getRecruittitle() {
		return recruittitle;
	}
	public void setRecruittitle(String recruittitle) {
		this.recruittitle = recruittitle;
	}
	public String getRecruitcontent() {
		return recruitcontent;
	}
	public void setRecruitcontent(String recruitcontent) {
		this.recruitcontent = recruitcontent;
	}
	public String getRecruittime() {
		return recruittime;
	}
	public void setRecruittime(String recruittime) {
		this.recruittime = recruittime;
	}
	public String getRecruitendtime() {
		return recruitendtime;
	}
	public void setRecruitendtime(String recruitendtime) {
		this.recruitendtime = recruitendtime;
	}
	public Long getRecruitent() {
		return recruitent;
	}
	public void setRecruitent(Long recruitent) {
		this.recruitent = recruitent;
	}
	public Long getRecruitemp() {
		return recruitemp;
	}
	public void setRecruitemp(Long recruitemp) {
		this.recruitemp = recruitemp;
	}
	public Long getRecruitstate() {
		return recruitstate;
	}
	public void setRecruitstate(Long recruitstate) {
		this.recruitstate = recruitstate;
	}
	public Long getRecruitflag() {
		return recruitflag;
	}
	public void setRecruitflag(Long recruitflag) {
		this.recruitflag = recruitflag;
	}
	public String getBz1() {
		return bz1;
	}
	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}
	public String getBz2() {
		return bz2;
	}
	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}
	public String getBz3() {
		return bz3;
	}
	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}
	public String getrecruitProvice() {
		return recruitProvice;
	}
	public void setrecruitProvice(String recruitProvice) {
		this.recruitProvice = recruitProvice;
	}
	public String getrecruitCity() {
		return recruitCity;
	}
	public void setrecruitCity(String recruitCity) {
		this.recruitCity = recruitCity;
	}
	public String getRecruitArea() {
		return recruitArea;
	}
	public void setRecruitArea(String recruitArea) {
		this.recruitArea = recruitArea;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
