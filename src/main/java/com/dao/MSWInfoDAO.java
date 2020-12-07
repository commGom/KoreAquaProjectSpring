package com.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MSWInfoDAO {
	@Autowired
	SqlSessionTemplate template;

	public List<String> setSigun(String doNm) {
		List<String> siguList = template.selectList("MSWMapper.setSigu", doNm);
		return siguList;
	}
	
	public List<String> setMnrlspNm(String siGuNm) {
		List<String> mnrlspNmList = template.selectList("MSWMapper.setMnrlspNm", siGuNm);
		return mnrlspNmList;
	}
}
