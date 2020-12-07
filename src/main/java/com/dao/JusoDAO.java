package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.JusoDTO;
@Repository
public class JusoDAO {
	@Autowired
	SqlSessionTemplate template;

	public List<String> setSigun(String sido) {
		List<String> sigunList = template.selectList("JusoMapper.setSigun", sido);
		return sigunList;
	}
	
	public List<String> setDong(String sigun) {
		List<String> dongList = template.selectList("JusoMapper.setDong", sigun);
		return dongList;
	}

	public List<JusoDTO> searchCode(Map<String, String> map) {
		List<JusoDTO> codeList = template.selectList("JusoMapper.searchCode", map);
		return codeList;
	}
}
