package com.dto;

import java.util.List;

import org.apache.ibatis.type.Alias; 
@Alias("TravelPage")
public class CoursePageDTO {
	private List<CourseDTO> list;
	private int curPage; //현재 페이지 번호
	private int perPage=4; //한 페이지당 게시물 수 
	private int totalCount; //전체 게시물 수
	
	public List<CourseDTO> getList() {
		return list;
	}
	public void setList(List<CourseDTO> list) {
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
