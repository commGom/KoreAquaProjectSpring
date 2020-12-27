package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("SubscribeDTO")
public class SubscribeDTO {
	private String useremail;

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	@Override
	public String toString() {
		return "SubscribeDTO [useremail=" + useremail + "]";
	}

	public SubscribeDTO(String useremail) {
		super();
		this.useremail = useremail;
	}

	public SubscribeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
