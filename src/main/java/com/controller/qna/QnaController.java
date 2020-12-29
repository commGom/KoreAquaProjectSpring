package com.controller.qna;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.qna.request.UpdateRequest;
import com.controller.qna.request.WriteRequest;
import com.dto.PageQnaDTO;
import com.dto.QnaDTO;
import com.service.QnaService;

@Controller
public class QnaController {
	@Autowired
	QnaService service;
	
	@RequestMapping(value = "/qna", method = RequestMethod.GET)
	public ModelAndView qnaListAll(HttpServletRequest request) {
		int curPage = 1; // 기본 페이지 = 1
		
		// 지정된 페이지가 있는 경우 curPage 변경
		if(request.getParameter("curPage") != null) {
			curPage = Integer.parseInt(request.getParameter("curPage"));
		}
		
		// 검색 종류, 검색어 가져오기
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		
		PageQnaDTO pDTO = new PageQnaDTO();
		
		if(searchValue != null) { // 검색어가 있을 경우
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("searchName" , searchName);
			map.put("searchValue" , searchValue);
			pDTO = service.search(curPage, map);
		} else {
			pDTO = service.page(curPage);
		}
		
		// pDTO와 검색어 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("page", pDTO);
		mav.addObject("searchName", searchName);
		mav.addObject("searchValue", searchValue);
		mav.setViewName("list");
		
		return mav;
	}
	
	@RequestMapping(value = "/qna/delete", method = RequestMethod.POST)
	public String qnaDelete(@RequestParam("num") String num) {
		// 해당 게시글 정보 가져오기
		QnaDTO qDTO = service.selectByNum(Integer.parseInt(num));
		
		String fileName = qDTO.getFileName();
		int repRoot = qDTO.getRepRoot();
		int repCount = service.repCount(repRoot); // 답글 개수
		int repStep = qDTO.getRepStep(); // 0이면 원글, 1 이상이면 답글
	
		// 해당 경로의 파일 삭제
		String filePath = "C:\\upload\\" + fileName ;
		File uploadFile = new File(filePath);
		uploadFile.delete();
		
		if(repCount != 1 && repStep == 0) { // 답글이 있는 원글인 경우 update
			qDTO.setTitle("삭제된 게시글입니다.");
			qDTO.setAuthor(" ");
			qDTO.setEmail(" ");
			qDTO.setContent(" ");
			
			service.updateByNum(qDTO);
		} else { // 그 외의 경우 delete
			service.deleteByNum(num);
		}
		return "redirect:/qna";
	}
	
	@RequestMapping(value = "/qna/num/{num}")
	public ModelAndView qnaRetrieve(@PathVariable("num") String num) {
		// 해당 게시글 가져오기
		QnaDTO qDTO = service.selectByNum(Integer.parseInt(num));
		
		// ModelAndView에 담아서 retrieve.jsp로 넘기기
		ModelAndView mav = new ModelAndView();
		mav.addObject("retrieve", qDTO);
		mav.setViewName("retrieve");
		
		return mav;
	}
	
	@RequestMapping(value = "/qna/update", method = RequestMethod.POST)
	public String qnaUpdate(UpdateRequest uRequest, MultipartFile uploadFile) {
		// update 위한 qDTO 생성
		QnaDTO qDTO = uRequest.convertToQnaDTO();
		String exFileName = service.selectByNum(uRequest.getNum()).getFileName();
		qDTO.setFileName(exFileName);
		
		// 새로 추가된 파일이 있으면
		if(!uploadFile.isEmpty()) {
			// 원래 있던 파일 삭제
			String filePath = "C:\\upload\\" + exFileName ;
			File exUploadFile = new File(filePath);
			exUploadFile.delete();
			
			// 새로운 파일 저장, update할 qDTO에 새로운 fileName set
			qDTO.setFileName(saveFile(uploadFile));
		}
		
		// qDTO update
		service.updateByNum(qDTO);
		System.out.println("글번호 ===" + qDTO.getNum());
		
		return "redirect:/qna/num/" + qDTO.getNum();
	}
	
	@RequestMapping(value = "/qna/write")
	public ModelAndView getWritePage() {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("write");
	    
	    return mav;
	}
	
	
	public static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	// xml에 설정된 리소스 참조
	// bean의 id가 uploadPath인 태그를 참조
	@Resource(name="uploadPath")
	String uploadPath;


	@RequestMapping(value = "/qna/write", method = RequestMethod.POST)
	public String qnaWrite(WriteRequest wRequest, MultipartFile uploadFile) {
		QnaDTO qDTO = wRequest.convertToQnaDTO();
		String fileName = "";
		
		// 추가한 파일이 있는 경우 파일을 저장
		if(!uploadFile.isEmpty()) {
			fileName = saveFile(uploadFile);
		}
		
		// DB에 insert할 qDTO에 fileName set
		qDTO.setFileName(fileName);

		service.write(qDTO);
		
		try {
			sendMail(qDTO.getEmail(), qDTO.getAuthor());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/qna";
	}
	
	
	public String saveFile(MultipartFile uploadFile) {
		// 파일을 임시 디렉토리에 업로드 -> 지정된 디렉토리에 저장 -> 파일정보가 file에 저장
		String fileName = "";
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
		
		return fileName;
	}
	
	
	@Inject
	JavaMailSender mailSender;
	
	public void sendMail(String email, String author) throws Exception {
		try {
			// 이메일 객체 생성
			MimeMessage msg = mailSender.createMimeMessage();
			
			// 메일 내용 
	        String subject = "KoreAqua에 문의 주셔서 감사합니다."; // 메일 제목
	        String htmlText = "<table align = \"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\">\n"
        			+ "<tbody>\n"
        			+ "<tr>\n"
        			+ "<td valign=\"top\" style=\"padding-top:0; padding-right: 18px; padding-bottom: 9px; padding-left: 18px;\">\n"
        			+ "	<div style=\"text-align:justify;\">\n"
        			+ "		<strong>\n"
        			+ "			<span style=\"font-size:20px\"><font color=\"#50d7f7\">KoreAqua</font></span>\n"
        			+ "		</strong>\n"
        			+ "		</div>\n"
        			+ "		</td>\n"
        			+ "		</tr>\n"
        			+ "		</tbody>\n"
        			+ "		</table>\n"
        			+ "		<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100%;\">\n"
        			+ "		<tbody>\n"
        			+ "			<tr>\n"
        			+ "				<td valign=\"top\" style=\"padding: 9px\">\n"
        			+ "					<table align=\"left\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"min-width:100%\">\n"
        			+ "						<tbody>\n"
        			+ "							<tr>\n"
        			+ "								<td valign=\"top\" style=\"padding-right: 9px; padding-left: 9px; padding-top: 0; padding-bottom: 0; text-align: center;\">\n"
        			+ "									<a href=\"http://localhost:8073/KoreAqua/\" title target=\"_blank\" rel=\"noreferrer noopener\">\n"
        			+ "										<img align=\"center\" alt src=\"https://images.pexels.com/photos/1346155/pexels-photo-1346155.jpeg?cs=srgb&dl=pexels-lisa-fotios-1346155.jpg&fm=jpg\" width=\"564\" style=\"max-width: 1200px; padding-bottom: 0; display: inline !important; vertical-align: botton;\" loading=\"lazy\">\n"
        			+ "									</a>\n"
        			+ "									</td>\n"
        			+ "									</tr>\n"
        			+ "									</tbody>\n"
        			+ "									</table>\n"
        			+ "									</td>\n"
        			+ "									</tr>\n"
        			+ "									\n"
        			+ "			\n"
        			+ "			</tbody>\n"
        			+ "			</tbody>\n"
        			+ "			\n"
        			+ "			<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100%;\">\n"
        			+ "				<tbody>\n"
        			+ "				<tr>\n"
        			+ "					<td valign=\"top\">\n"
        			+ "						<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100%;\">\n"
        			+ "							<tbody>\n"
        			+ "								<tr>\n"
        			+ "									<td style=\"padding-top: 9px; padding-left: 18px; padding-bottom: 9px; padding-right: 18px;\">\n"
        			+ "										<table border=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100% !important; background-color: #FFFFFF; border: 1px dashed #50d7f7;\">\n"
        			+ "											<tbody>\n"
        			+ "											<tr>\n"
        			+ "												<td valign=\"top\" style=\"padding: 18px; color: #7A7A7A; font-size:14px; font-weight:normal; text-align:center;\">\n"
        			+ "													<div style=\"text-align: justify;\">\n"
        			+ "														안녕하세요. " + author + "님\n"
        			+ "														<br>\n"
        			+ "														<br>\n"
        			+ "														문의 주셔서 감사합니다.\n"
        			+ "														<br>\n"
        			+ "														<br>\n"
        			+ "														최대한 빠른 시일 내에 답변 드리도록 하겠습니다.\n"
        			+ "														<br>\n"
        			+ "														<br>\n"
        			+ "														<br>\n"
        			+ "													</div>\n"
        			+ "												</td>\n"
        			+ "												</tr>\n"
        			+ "												</tbody>\n"
        			+ "												</table>\n"
        			+ "												</td>\n"
        			+ "												</tr>\n"
        			+ "												</tbody>\n"
        			+ "												</table>\n"
        			+ "												</td>\n"
        			+ "												</tr>\n"
        			+ "												\n"
        			+ "													\n"
        			+ "												\n"
        			+ "							</tbody>\n"
        			+ "				</tbody>\n"
        			+ "			\n"
        			+ "			<table align =\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\">\n"
        			+ "				<tbody>\n"
        			+ "					<tr>\n"
        			+ "						<td valign=\"top\" style =\"padding-top: 0; padding-right:18px; padding-bottom: 9px; padding-left: 18px;\">\n"
        			+ "							<div style=\"text-align:center;\">\n"
        			+ "								<span style=\"font-size:11px\">\n"
        			+ "									<strong>\n"
        			+ "										<img height=\"15\" src=\"https://i.ibb.co/g6zpmLW/logo.png\" alt=\"logo\" border=\"0\"  loading= \"lazy\">\n"
        			+ "										우리는 안전하고, 깨끗한 물의 환경을 추구합니다.\n"
        			+ "									</strong>\n"
        			+ "									<br>\n"
        			+ "									<br>\n"
        			+ "									KoreAqua\n"
        			+ "									<br>\n"
        			+ "									</span>\n"
        			+ "									</div>\n"
        			+ "									</td>\n"
        			+ "									</tr>\n"
        			+ "									\n"
        			+ "				</tbody>\n"
        			+ "				\n"
        			+ "				\n"
        			+ "				<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100%;\">\n"
        			+ "					<tbody>\n"
        			+ "						<tr>\n"
        			+ "							<td align=\"center\" valign=\"top\" style=\"padding: 9px\">\n"
        			+ "								<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100%;\">\n"
        			+ "								</table>\n"
        			+ "								</td>\n"
        			+ "								</tr>\n"
        			+ "								\n"
        			+ "					</tbody>\n"
        			+ "					</table>";
	        
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
	        helper.setSubject(subject);
	        helper.setTo(email);
	        helper.setFrom(new InternetAddress("koreaqua2020@gmail.com", "KoreAqua"));
	        helper.setText(htmlText, true);
	        
	        // 이메일 발송
	        mailSender.send(msg);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	
}
