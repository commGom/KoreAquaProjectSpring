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
	
	
	public int Area_TotalCount (HashMap<String, String> map) {
		return template.selectOne("TravelMapper.Area_TotalCount",map);
	}
	
	public AreaPage AreaMain(int curPage, HashMap<String, String> map) {
		AreaPage dto = new AreaPage();
		int aperPage = dto.getPerPage();
		int offset = (curPage -1)*aperPage;
		List<AreaDTO> list = template.selectList("TravelMapper.Area_main", map, new RowBounds(offset, aperPage));
		dto.setCurPage(curPage); 
		dto.setList(list);
		dto.setTotalCount(Area_TotalCount(map));
		return dto;
	}
	
	
	
	public int Area_IS_TotalCount (HashMap<String, String> map) {
		return template.selectOne("TravelMapper.Area_IS_TotalCount",map);  
	}  
	
	public AreaPage Area_InitialSearch (HashMap<String, String> map, int curPage, String REGIONCD){
		AreaPage aPage = new AreaPage();
		AreaDTO aDTO = new AreaDTO();
		int perPage = aPage.getPerPage();  
		int offset = (curPage-1)*perPage; 
		aDTO.setREGIONCD(REGIONCD);
		List<AreaDTO> list = template.selectList("TravelMapper.Area_InitialSearch", map ,new RowBounds(offset, perPage));
		aPage.setCurPage(curPage);
		aPage.setList(list); 
		aPage.setTotalCount(Area_IS_TotalCount(map));  
		return aPage;  
	}
	
	
	
	public int Area_RegionCd_TotalCount (HashMap<String, String> map) {
		return template.selectOne("TravelMapper.Area_RegionCd_TotalCount",map); 
	}
	
	public AreaPage Area_Region (HashMap<String, String> map, int curPage, String REGIONCD){
		AreaPage aPage = new AreaPage();
		AreaDTO aDTO = new AreaDTO();
		int perPage = aPage.getPerPage();  
		int offset = (curPage-1)*perPage; 
		aDTO.setREGIONCD(REGIONCD);
		List<AreaDTO> list = template.selectList("TravelMapper.Area_Region", map ,new RowBounds(offset, perPage));
		aPage.setCurPage(curPage);
		aPage.setList(list);  
		aPage.setTotalCount(Area_RegionCd_TotalCount(map));
		return aPage;  
	}
}
