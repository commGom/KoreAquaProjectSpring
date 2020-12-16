package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.TravelPage;
import com.dto.TravelDTO;
@Repository
public class TravelDAO { 
	@Autowired
	SqlSessionTemplate template;
	
	public int totalCount(HashMap<String, String> map) {
		return template.selectOne("TravelMapper.totalCount_course", map);
	}
	
	public TravelPage main(int curPage, HashMap<String, String> map) {
		TravelPage pDTO = new TravelPage();
		int perPage = pDTO.getPerPage(); 
		int offset = (curPage-1)*perPage;
		List<TravelDTO> list = template.selectList("TravelMapper.main", map ,new RowBounds(offset, perPage));
		pDTO.setCurPage(curPage);   
		pDTO.setList(list);
		pDTO.setTotalCount(totalCount(map));
		return pDTO; 
	}
	
	public TravelPage course(int curPage, HashMap<String, String> map ) {
		TravelPage pDTO = new TravelPage();
		int perPage = pDTO.getPerPage(); 
		int offset = (curPage-1)*perPage; 
		List<TravelDTO> list = template.selectList("TravelMapper.course", map ,new RowBounds(offset, perPage));
		//System.out.println("TravelDAO 35 line list :" +list); 
		pDTO.setCurPage(curPage);   
		pDTO.setList(list);
		pDTO.setTotalCount(totalCount(map));
		return pDTO; 
	}
}
