package com.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.MSWInfoDAO;

@Service
public class MSWInfoService {
	@Autowired
	MSWInfoDAO dao;
	
	public MSWInfoService() {
		super();
	}

public List<String> setSigu(String doNm) {
		
		 List<String> siguList = dao.setSigun(doNm);
		return siguList;
	}

	public List<String> setMnrlspNm(String siGuNm) {
		List<String> mnrlspNmList = dao.setMnrlspNm(siGuNm);
		return mnrlspNmList;
	}

}
