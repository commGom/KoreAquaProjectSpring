package com.dto;

import org.apache.ibatis.type.Alias;

/**
 * @author hyunw
 *
 */
@Alias("MSWInfoDTO")
public class MSWInfoDTO {
	//�빟�닔�꽣紐�
	private String mnrlspNm;
	//�룄�씠由�
	private String doNm;
	//�떆援� �씠由�
	private String siGuNm;
	//�쐞�룄
	private String latitude;
	//寃쎈룄
	private String longitude;
	public MSWInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MSWInfoDTO(String mnrlspNm, String doNm, String siGuNm, String latitude, String longitude) {
		super();
		this.mnrlspNm = mnrlspNm;
		this.doNm = doNm;
		this.siGuNm = siGuNm;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getMnrlspNm() {
		return mnrlspNm;
	}
	public void setMnrlspNm(String mnrlspNm) {
		this.mnrlspNm = mnrlspNm;
	}
	public String getDoNm() {
		return doNm;
	}
	public void setDoNm(String doNm) {
		this.doNm = doNm;
	}
	public String getSiGuNm() {
		return siGuNm;
	}
	public void setSiGuNm(String siGuNm) {
		this.siGuNm = siGuNm;
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
	@Override
	public String toString() {
		return "MSWInfoDTO [mnrlspNm=" + mnrlspNm + ", doNm=" + doNm + ", siGuNm=" + siGuNm + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
	
	
}
