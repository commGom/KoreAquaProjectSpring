package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.CoursePageDTO;
import com.dto.CourseDTO;
@Repository
public class TravelDAO { 
	@Autowired
	SqlSessionTemplate template;
	
	public int Course_TotalCount(HashMap<String, String> map) {
		return template.selectOne("TravelMapper.Course_TotalCount", map);
	}
	
	public CoursePageDTO Course_Main(int curPage, HashMap<String, String> map) {
		CoursePageDTO pDTO = new CoursePageDTO();
		int perPage = pDTO.getPerPage(); 
		int offset = (curPage-1)*perPage;
		List<CourseDTO> list = template.selectList("TravelMapper.Course_Main", map ,new RowBounds(offset, perPage));
		pDTO.setCurPage(curPage);   
		pDTO.setList(list);
		pDTO.setTotalCount(Course_TotalCount(map));
		return pDTO; 
	}
	
	public CoursePageDTO Course_SelectedArea(int curPage, HashMap<String, String> map ) {
		CoursePageDTO pDTO = new CoursePageDTO();
		int perPage = pDTO.getPerPage(); 
		int offset = (curPage-1)*perPage; 
		List<CourseDTO> list = template.selectList("TravelMapper.Course_SelectedArea", map ,new RowBounds(offset, perPage)); 
		pDTO.setCurPage(curPage);   
		pDTO.setList(list);
		pDTO.setTotalCount(Course_TotalCount(map));
		return pDTO; 
	}
}
