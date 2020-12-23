package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.config.MySqlSessionFactory;
import com.dao.JusoDAO;
import com.dao.QnaDAO;
import com.dto.QnaDTO;
import com.dto.PageQnaDTO;

@Service
public class QnaService {
	@Autowired
	QnaDAO dao;
	
	public PageQnaDTO search(int curPage, HashMap<String, String> map) {
		PageQnaDTO pDTO = null;
		pDTO = dao.search(curPage, map);
		
		return pDTO;
	}
	
	public PageQnaDTO page(int curPage) {
		PageQnaDTO pDTO = null;
		pDTO = dao.page(curPage);
		
		return pDTO;
	}
	
	public int totalCount() {
		int count = 0;
		count = dao.totalCount();
		
		return count;
	}
	
	public List<QnaDTO> listAll() {
		List<QnaDTO> list = null;
		list = dao.listAll();
		
		return list;
	}
	
	public QnaDTO replyUI(String num) {
		QnaDTO qDTO = null;
		qDTO = dao.replyUI(num);
		
		return qDTO;
	}

	public void reply(QnaDTO qDTO) {
		dao.reply(qDTO);
	}
	
	public void write(QnaDTO dto) {
		dao.write(dto);
	}

	public QnaDTO selectByNum(int num) {
		QnaDTO dto = null;
		dto = dao.selectByNum(num);
		
		return dto;
	}

	public void deleteByNum(String num) {
		dao.deleteByNum(num);
		
	}

	public void updateByNum(QnaDTO dto) {
		dao.updateByNum(dto);
		
	}

	public QnaDTO passwdCheck(Map<String, Object> map) {
		QnaDTO qDTO = null;
		qDTO = dao.passwdCheck(map);
		
		return qDTO;
	}

	public int repCount(int repRoot) {
		int n = 0;
		n = dao.repCount(repRoot);
		
		return n;
	}

}
