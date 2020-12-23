package com.controller.qna;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.QnaDTO;
import com.service.QnaService;

@Controller
public class FileController {
	@Autowired
	QnaService service;
	
	@RequestMapping(value = "/qna/download/num/{num}", method = RequestMethod.GET)
	public void qnaDownload(@PathVariable("num") String num, HttpServletResponse response) {
		// 해당 게시글의 파일명 가져오기
		QnaDTO qDTO = service.selectByNum(Integer.parseInt(num));
		String fileName = qDTO.getFileName();
		
		// 저장된 파일 경로
		String downloadPath = "C:\\upload\\";
	    String filePath = downloadPath + fileName;
	    try {
	    	byte[] downloadFile = FileUtils.readFileToByteArray(new File(filePath));
	    	
	    	response.setContentType("application/octet-stream");
	    	response.setContentLength(downloadFile.length);
	    	response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8") + "\";");
	    	response.setHeader("Content-Transfer-Encoding", "binary");
	    	
	    	response.getOutputStream().write(downloadFile);			
	    	response.getOutputStream().flush();
	    	response.getOutputStream().close();
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
          
	}
	
	@RequestMapping(value = "/qna/file-delete", method = RequestMethod.POST)
	@ResponseBody
	public void qnaFileDelete(@RequestParam("num") String num, HttpServletResponse response) {
		// 해당 게시글의 파일명 가져오기
		QnaDTO qDTO = service.selectByNum(Integer.parseInt(num));
		String fileName = qDTO.getFileName();
		
		// 저장된 파일 경로
		String downloadPath = "C:\\upload\\";
	    String filePath = downloadPath + fileName;
	    
	    // 해당 경로의 파일 삭제
 		File uploadFile = new File(filePath);
 		uploadFile.delete();

 		// 파일명 업데이트
 		qDTO.setFileName("");
 		service.updateByNum(qDTO);
	}
	    
}
