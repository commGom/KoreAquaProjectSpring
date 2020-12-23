package com.controller.qna;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.qna.request.ReplyRequest;
import com.dto.QnaDTO;
import com.service.QnaService;

@Controller
public class QnaReplyController {
	@Autowired
	QnaService service;
	
	public static final Logger logger = LoggerFactory.getLogger(QnaReplyController.class);
	
	// xml에 설정된 리소스 참조
	// bean의 id가 uploadPath인 태그를 참조
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value = "/qna/reply/num/{num}", method = RequestMethod.GET)
	public ModelAndView qnaReplyUI(@PathVariable("num") String num, HttpServletRequest request) {
		QnaDTO qDTO = service.replyUI(num);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("replyUI", qDTO);
		mav.setViewName("reply");
		
		return mav;
	}
	
	@RequestMapping(value = "/qna/reply", method = RequestMethod.POST)
	public String qnaReply(ReplyRequest rRequest, MultipartFile uploadFile) {
		QnaDTO qDTO = rRequest.convertToQnaDTO();
		
		String fileName = "";
		// 추가한 파일이 있는 경우
		if(!uploadFile.isEmpty()) {
			fileName = uploadFile.getOriginalFilename();
			logger.info("filename : " + uploadFile.getOriginalFilename());
			logger.info("size : " + uploadFile.getSize());
			logger.info("contentType : " + uploadFile.getContentType());
			File target = new File(uploadPath, fileName);
			
			// 임시 디렉토리에 저장된 파일을 지정된 디렉토리(target)으로 복사
			try {
				FileCopyUtils.copy(uploadFile.getBytes(), target);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// DB에 insert할 qDTO에 fileName을 set
			qDTO.setFileName(fileName);
		}
		
		// qDTO를 service.reply로 insert
		service.reply(qDTO);
		
		return "redirect:/qna";
	}
	
}
