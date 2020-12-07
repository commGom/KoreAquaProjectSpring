package com.service;

import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.AreaDAO;
import com.dto.AreaPage;

public class AreaService {

	/*
	 * public List<AreaDTO> AreaMain() { SqlSession session =
	 * MySqlSessionFactory.getSession(); List<AreaDTO> aDTO = null; try { AreaDAO
	 * aDAO = new AreaDAO(); aDTO = aDAO.AreaMain(session); }catch(Exception e) {
	 * e.printStackTrace(); }finally { session.close(); } return aDTO; }
	 */
	
	 public AreaPage area_main(int curPage, String area) {
		 HashMap<String, String> map = new HashMap<String, String>();
		 map.put("area", area);
		 SqlSession session = MySqlSessionFactory.getSession();
		 AreaPage pDTO = null;
		 try {
			 AreaDAO dao = new AreaDAO();
				pDTO = dao.AreaMain(session, curPage, map);
		 } catch(Exception e) {
			 e.printStackTrace();
		 }finally {
			 session.close();
		 }
		 System.out.println("AreaService 37 line pDTO:"+pDTO); 
		return pDTO;
		
		
	 }
	 
	 
}// end class
