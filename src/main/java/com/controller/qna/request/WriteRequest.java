package com.controller.qna.request;

import com.dto.QnaDTO;

public class WriteRequest {
	private String title;
	private String author;
	private String email;
	private String passwd;
	private String content;
	private String fileName;
	
	public WriteRequest() {
		super();
	}
	
	public QnaDTO convertToQnaDTO() {
		QnaDTO qDTO = new QnaDTO();
		qDTO.setTitle(this.title);
		qDTO.setAuthor(this.author);
		qDTO.setEmail(this.email);
		qDTO.setPasswd(this.passwd);
		qDTO.setContent(this.content);
		qDTO.setFileName(this.fileName);
		
		return qDTO;
	}

	public WriteRequest(String title, String author, String email, String passwd, String content, String fileName) {
		super();
		this.title = title;
		this.author = author;
		this.email = email;
		this.passwd = passwd;
		this.content = content;
		this.fileName = fileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "WriteRequest [title=" + title + ", author=" + author + ", email=" + email + ", passwd=" + passwd
				+ ", content=" + content + ", fileName=" + fileName + "]";
	}

}
