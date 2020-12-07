package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("MineralSpringWaterDTO")
public class MineralSpringWaterDTO {
	//�빟�닔�꽣紐�
	private String mnrlspNm;
	//�룄濡쒕챸 二쇱냼
	private String rdnmadr;
	//吏�紐� 二쇱냼
	private String lnmadr;
	//�쐞�룄
	private String latitude;
	//寃쎈룄
	private String longitude;
	//吏��젙�씪�옄
	private String appnDate;
	//�씪�룊洹좎씠�슜�씤援ъ닔
	private String dayUseCn;
	//�닔吏덇��궗�씪�옄
	private String qltwtrInspctDate;
	//�닔吏덇��궗寃곌낵援щ텇
	private String qltwtrInspctResultType;
	//遺��쟻�빀�빆紐�
	private String improptIem;
	//愿�由ш린愿��쟾�솕踰덊샇
	private String phoneNumber;
	//愿�由ш린愿�紐�
	private String institutionNm;
	//�뜲�씠�꽣湲곗��씪�옄
	private String referenceDate;
	//�젣怨듦린愿�肄붾뱶
	private String insttCode;
	public MineralSpringWaterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MineralSpringWaterDTO(String mnrlspNm, String rdnmadr, String lnmadr, String latitude, String longitude,
			String appnDate, String dayUseCn, String qltwtrInspctDate, String qltwtrInspctResultType, String improptIem,
			String phoneNumber, String institutionNm, String referenceDate, String insttCode) {
		super();
		this.mnrlspNm = mnrlspNm;
		this.rdnmadr = rdnmadr;
		this.lnmadr = lnmadr;
		this.latitude = latitude;
		this.longitude = longitude;
		this.appnDate = appnDate;
		this.dayUseCn = dayUseCn;
		this.qltwtrInspctDate = qltwtrInspctDate;
		this.qltwtrInspctResultType = qltwtrInspctResultType;
		this.improptIem = improptIem;
		this.phoneNumber = phoneNumber;
		this.institutionNm = institutionNm;
		this.referenceDate = referenceDate;
		this.insttCode = insttCode;
	}
	public String getMnrlspNm() {
		return mnrlspNm;
	}
	public void setMnrlspNm(String mnrlspNm) {
		this.mnrlspNm = mnrlspNm;
	}
	public String getRdnmadr() {
		return rdnmadr;
	}
	public void setRdnmadr(String rdnmadr) {
		this.rdnmadr = rdnmadr;
	}
	public String getLnmadr() {
		return lnmadr;
	}
	public void setLnmadr(String lnmadr) {
		this.lnmadr = lnmadr;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAppnDate() {
		return appnDate;
	}
	public void setAppnDate(String appnDate) {
		this.appnDate = appnDate;
	}
	public String getDayUseCn() {
		return dayUseCn;
	}
	public void setDayUseCn(String dayUseCn) {
		this.dayUseCn = dayUseCn;
	}
	public String getQltwtrInspctDate() {
		return qltwtrInspctDate;
	}
	public void setQltwtrInspctDate(String qltwtrInspctDate) {
		this.qltwtrInspctDate = qltwtrInspctDate;
	}
	public String getQltwtrInspctResultType() {
		return qltwtrInspctResultType;
	}
	public void setQltwtrInspctResultType(String qltwtrInspctResultType) {
		this.qltwtrInspctResultType = qltwtrInspctResultType;
	}
	public String getImproptIem() {
		return improptIem;
	}
	public void setImproptIem(String improptIem) {
		this.improptIem = improptIem;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getInstitutionNm() {
		return institutionNm;
	}
	public void setInstitutionNm(String institutionNm) {
		this.institutionNm = institutionNm;
	}
	public String getReferenceDate() {
		return referenceDate;
	}
	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}
	public String getInsttCode() {
		return insttCode;
	}
	public void setInsttCode(String insttCode) {
		this.insttCode = insttCode;
	}
	@Override
	public String toString() {
		return "MineralSpringWaterDTO [mnrlspNm=" + mnrlspNm + ", rdnmadr=" + rdnmadr + ", lnmadr=" + lnmadr
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", appnDate=" + appnDate + ", dayUseCn="
				+ dayUseCn + ", qltwtrInspctDate=" + qltwtrInspctDate + ", qltwtrInspctResultType="
				+ qltwtrInspctResultType + ", improptIem=" + improptIem + ", phoneNumber=" + phoneNumber
				+ ", institutionNm=" + institutionNm + ", referenceDate=" + referenceDate + ", insttCode=" + insttCode
				+ "]";
	}
	
}
