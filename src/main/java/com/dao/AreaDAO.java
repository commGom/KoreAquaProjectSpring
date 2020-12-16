package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.AreaDTO;
import com.dto.AreaPage;
@Repository
public class AreaDAO {
	@Autowired
	SqlSessionTemplate template;
	
	public int totalCount (HashMap<String, String> map) {
		return template.selectOne("TravelMapper.totalCount_area",map);
	}
	
	public int totalCount_REGIONCD (HashMap<String, String> map) {
		return template.selectOne("TravelMapper.totalCount_REGIONCD",map); 
	}
	
	public int totalCount_TITLE (HashMap<String, String> map) {
		return template.selectOne("TravelMapper.totalCount_TITLE",map);  
	}  
	
	public AreaPage AreaMain(int curPage, HashMap<String, String> map) {
		AreaPage dto = new AreaPage();
		int aperPage = dto.getPerPage();
		int offset = (curPage -1)*aperPage;
		List<AreaDTO> list = template.selectList("TravelMapper.area_main", map, new RowBounds(offset, aperPage));
		dto.setCurPage(curPage); 
		dto.setList(list);
		dto.setTotalCount(totalCount(map));
		System.out.println(list);
		return dto;
	}
	public AreaPage ATitle (HashMap<String, String> map, int curPage, String REGIONCD){
		AreaPage aPage = new AreaPage();
		AreaDTO aDTO = new AreaDTO();
		int perPage = aPage.getPerPage();  
		int offset = (curPage-1)*perPage; 
		aDTO.setREGIONCD(REGIONCD);
		List<AreaDTO> list = template.selectList("TravelMapper.ATitle", map ,new RowBounds(offset, perPage));
		System.out.println(list); 
		aPage.setCurPage(curPage);
		aPage.setList(list); 
		aPage.setTotalCount(totalCount_TITLE(map));  
		System.out.println(totalCount_TITLE(map));  
		//System.out.println(map); 
		return aPage;  
	}
	
	public AreaPage ARegion (HashMap<String, String> map, int curPage, String REGIONCD){
		AreaPage aPage = new AreaPage();
		AreaDTO aDTO = new AreaDTO();
		int perPage = aPage.getPerPage();  
		int offset = (curPage-1)*perPage; 
		aDTO.setREGIONCD(REGIONCD);
		List<AreaDTO> list = template.selectList("TravelMapper.ARegion", map ,new RowBounds(offset, perPage));
		aPage.setCurPage(curPage);
		aPage.setList(list);  
		aPage.setTotalCount(totalCount_REGIONCD(map));
		//System.out.println(totalCount_REGIONCD(session, map));
		return aPage;  
	}
}
