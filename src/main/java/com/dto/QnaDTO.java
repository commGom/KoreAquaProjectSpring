package com.dto;

import org.apache.ibatis.type.Alias;

@Alias("QnaDTO")
public class QnaDTO {

	private int num;
	private String title;
	private String author;
	private String email;
	private String passwd;
	private String content;
	private String fileName;
	private String writeday;
	private int readCnt;  //조회수
	private int repRoot;
	private int repStep;
	private int repIndent;
	public QnaDTO() {
		super();
	}
	public QnaDTO(int num, String title, String author, String email, String passwd, String content, String fileName,
			String writeday, int readCnt, int repRoot, int repStep, int repIndent) {
		super();
		this.num = num;
		this.title = title;
		this.author = author;
		this.email = email;
		this.passwd = passwd;
		this.content = content;
		this.fileName = fileName;
		this.writeday = writeday;
		this.readCnt = readCnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
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
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writeday) {
		this.writeday = writeday;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
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
		return "QnaDTO [num=" + num + ", title=" + title + ", author=" + author + ", email=" + email + ", passwd="
				+ passwd + ", content=" + content + ", fileName=" + fileName + ", writeday=" + writeday + ", readCnt="
				+ readCnt + ", repRoot=" + repRoot + ", repStep=" + repStep + ", repIndent=" + repIndent + "]";
	}
	
	
}
