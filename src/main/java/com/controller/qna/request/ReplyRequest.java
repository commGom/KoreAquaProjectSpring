package com.controller.qna.request;

import com.dto.QnaDTO;

public class ReplyRequest {
	private String title;
	private String author;
	private String content;
	private String fileName;
	private int repRoot;
	private int repStep;
	private int repIndent;
	
	public ReplyRequest() {
		super();
	}
	
	public ReplyRequest(String title, String author, String content, String fileName, int repRoot, int repStep,
			int repIndent) {
		super();
		this.title = title;
		this.author = author;
		this.content = content;
		this.fileName = fileName;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
	}

	public QnaDTO convertToQnaDTO() {
		QnaDTO qDTO = new QnaDTO();
		qDTO.setTitle(this.title);
		qDTO.setAuthor(this.author);
		qDTO.setContent(this.content);
		qDTO.setFileName(this.fileName);
		qDTO.setRepRoot(this.repRoot);
		qDTO.setRepStep(this.repStep + 1);
		qDTO.setRepIndent(this.repIndent + 1);
		
		return qDTO;
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

	public int getRepRoot() {
		return repRoot;
	}

	public void setRepRoot(int repRoot) {
		this.repRoot = repRoot;
	}

	public int getRepStep() {
		return repStep;
	}

	public void setRepStep(int repStep) {
		this.repStep = repStep;
	}

	public int getRepIndent() {
		return repIndent;
	}

	public void setRepIndent(int repIndent) {
		this.repIndent = repIndent;
	}

	@Override
	public String toString() {
		return "ReplyRequest [title=" + title + ", author=" + author + ", content=" + content + ", fileName=" + fileName
				+ ", repRoot=" + repRoot + ", repStep=" + repStep + ", repIndent=" + repIndent + "]";
	}
}
