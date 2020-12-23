package com.controller.qna.request;

import com.dto.QnaDTO;

public class UpdateRequest {
	private int num;
	private String title;
	private String content;

	public UpdateRequest() {
		super();
	}
	
	public UpdateRequest(int num, String title, String content) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
	}
	
	public QnaDTO convertToQnaDTO() {
		QnaDTO qDTO = new QnaDTO();
		qDTO.setNum(this.num);
		qDTO.setTitle(this.title);
		qDTO.setContent(this.content);
		
		return qDTO;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "UpdateRequest [num=" + num + ", title=" + title + ", content=" + content + "]";
	}
	
}
