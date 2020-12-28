package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dto.QnaDTO;
import com.dto.PageQnaDTO;

@Repository
public class QnaDAO {
	@Autowired
	SqlSessionTemplate template;

	public int totalCount() {
		int count = template.selectOne("QnaMapper.totalCount");
		return count;
	}
	
	public int searchTotalCount(HashMap<String, String> map) {
		int count = template.selectOne("QnaMapper.searchTotalCount", map);
		return count;
	}
	
	public int repCount(int repRoot) {
		int n = template.selectOne("QnaMapper.repCount", repRoot);
		return n;
	}
	
	public PageQnaDTO search(int curPage, HashMap<String, String> map) {
		PageQnaDTO pDTO = new PageQnaDTO();
		int perPage = pDTO.getPerPage(); // 한 페이지에 보여줄 글 개수, 10
		int offset = (curPage - 1) * perPage;
		
		List<QnaDTO> list = template.selectList("QnaMapper.search", map, new RowBounds(offset, perPage));
		pDTO.setCurPage(curPage);
		pDTO.setList(list);
		pDTO.setTotalCount(searchTotalCount(map));
		
		return pDTO;
	}
	
	public List<QnaDTO> listAll() {
		List<QnaDTO> list = template.selectList("QnaMapper.listAll");
		return list;
	}
	
	public PageQnaDTO page(int curPage) {
		PageQnaDTO pDTO = new PageQnaDTO();
		
		int perPage = pDTO.getPerPage(); // 한 페이지에 보여줄 글 개수, 10
		int offset = (curPage - 1) * perPage;
		
		List<QnaDTO> list = template.selectList("QnaMapper.listAll", null, new RowBounds(offset, perPage));
		pDTO.setCurPage(curPage);
		pDTO.setList(list);
		pDTO.setTotalCount(totalCount());
		
		return pDTO;
	}
	
	public QnaDTO replyUI(String num) {
		QnaDTO qDTO = template.selectOne("QnaMapper.replyUI", Integer.parseInt(num));
		return qDTO;
	}
	
	public void reply(QnaDTO qDTO) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("repRoot", qDTO.getRepRoot());
		map.put("repStep", qDTO.getRepStep());
		repCnt(map); // 원글의 repRoot, repStep 수정
		
		int n = template.insert("QnaMapper.reply", qDTO);
	}
	
	public void repCnt(HashMap<String, Integer> map) {
		int n = template.update("QnaMapper.repCnt", map);
	}

	public void write(QnaDTO qDTO) {
		int num = template.insert("QnaMapper.write", qDTO);
	}

	public QnaDTO selectByNum(int num) {
		readCnt(num);
		QnaDTO dto = template.selectOne("QnaMapper.selectByNum", num);
		return dto;
	}
	
	private void readCnt(int num) {
		template.update("QnaMapper.readCnt", num);
	}

	public void deleteByNum(String num) {
		int x = template.delete("QnaMapper.deleteByNum", Integer.parseInt(num));
	}

	public void updateByNum(QnaDTO qDTO) {
		int x = template.update("QnaMapper.updateByNum", qDTO);
	}
	
	public QnaDTO passwdCheck(Map<String, Object> map) {
		QnaDTO qDTO = template.selectOne("QnaMapper.passwdCheck", map);
		return qDTO;
	}
	
}
