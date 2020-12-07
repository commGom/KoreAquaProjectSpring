package com.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.dto.AreaDTO;
import com.dto.AreaPage;

public class AreaDAO {
	/*
	 * public List<AreaDTO> AreaMain(SqlSession session ) { List<AreaDTO> aDTO =
	 * session.selectList("TravelMapper.area_main"); System.out.println(aDTO);
	 * return aDTO; }
	 */
	
	public int totalCount (SqlSession session, HashMap<String, String> map) {
		return session.selectOne("TravelMapper.totalCount_area",map);
	}
	public AreaPage AreaMain(SqlSession session, int curPage, HashMap<String, String> map) {
		AreaPage dto = new AreaPage();
		int aperPage = dto.getPerPage();
		int offset = (curPage -1)*aperPage;
		List<AreaDTO> list = session.selectList("TravelMapper.area_main", map, new RowBounds(offset, aperPage));
		dto.setCurPage(curPage); 
		dto.setList(list);
		dto.setTotalCount(totalCount(session,map));
		
		//System.out.println("AreaDAO 32 lin dto :"+ dto);
		return dto;
	}
}
