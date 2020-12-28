package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.DictionDTO;
import com.dto.DictionPageDTO;
@Repository
public class DictionDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public int totalCount(HashMap<String, String> map) {
		return template.selectOne("DictionMapper.totalCount", map);
	}
	
	public DictionPageDTO searchAll (int curPage, HashMap<String, String> map){
		DictionPageDTO pDTO = new DictionPageDTO(); //Autowired는 singletone 이므로 여러 메서드에서 사용할수 없다. 그러므로 직접 객체를 생성해줘야한다.
		int perPage = pDTO.getPerPage(); 
		int offset = (curPage-1)*perPage;
		List<DictionDTO> list = template.selectList("DictionMapper.searchAll", map ,new RowBounds(offset, perPage));
		pDTO.setCurPage(curPage);
		pDTO.setList(list);
		pDTO.setTotalCount(totalCount(map));
		return pDTO;
		
	}

	public DictionPageDTO search (HashMap<String, String> map, int curPage){
		DictionPageDTO pDTO = new DictionPageDTO();
		int perPage = pDTO.getPerPage(); 
		int offset = (curPage-1)*perPage;
		List<DictionDTO> list = template.selectList("DictionMapper.search", map ,new RowBounds(offset, perPage));
		pDTO.setCurPage(curPage);
		pDTO.setList(list);
		pDTO.setTotalCount(totalCount(map));
		return pDTO; 
	}
	
	public DictionPageDTO initial (HashMap<String, String> map, int curPage){
		DictionPageDTO pDTO = new DictionPageDTO();
		int perPage = pDTO.getPerPage(); 
		int offset = (curPage-1)*perPage;
		List<DictionDTO> list = template.selectList("DictionMapper.initial", map ,new RowBounds(offset, perPage));
		pDTO.setCurPage(curPage);
		pDTO.setList(list);
		pDTO.setTotalCount(totalCount(map));
		return pDTO; 
	}
	
	
}
