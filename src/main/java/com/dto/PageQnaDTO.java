package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("PageQnaDTO")
public class PageQnaDTO {
	private List<QnaDTO> list; // 목록 리스트 저장
	private int curPage; // 현재 페이지 번호
	private int perPage = 10; // 한 페이지당 보여줄 글 개수
	private int totalCount; // 전체 레코드 개수
	public PageQnaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PageQnaDTO(List<QnaDTO> list, int curPage, int perPage, int totalCount) {
		super();
		this.list = list;
		this.curPage = curPage;
		this.perPage = perPage;
		this.totalCount = totalCount;
	}
	
	public List<QnaDTO> getList() {
		return list;
	}
	public void setList(List<QnaDTO> list) {
		this.list = list;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	@Override
	public String toString() {
		return "PageDTO [list=" + list + ", curPage=" + curPage + ", perPage=" + perPage + ", totalCount=" + totalCount
				+ "]";
	}
	
	
}
