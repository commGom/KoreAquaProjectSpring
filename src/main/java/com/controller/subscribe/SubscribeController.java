package com.controller.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SubscribeController {

	@Autowired
	private sendMail mailSender;
	
	
	@RequestMapping(value="/subscribemail")
	@ResponseBody
	public String subscribemail(@RequestParam(required = false) String useremail) {
		String subject = "";		
		String setfrom = "koreaqua2020@gmail.com";
		
		String title = "";
		String content ="";
		
		
		
		
		int authCode=0;
		String authCodes="";
		Boolean bool = false;

		if(useremail != null && !useremail.isEmpty()) {
			System.out.println("useremail==="+useremail);
			subject = "KoreAqua를 구독해주셔서 감사합니다!";
			content="<table align = \"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\">\n"
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
	    			+ "									<a href=\"http://localhost:8073/KoreAquaProject/index.jsp\" title target=\"_blank\" rel=\"noreferrer noopener\">\n"
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
	    			+ "														안녕하세요. KoreAqua입니다.\n"
	    			+ "														<br>\n"
	    			+ "														<br>\n"
	    			+ "														KoreAqua 구독에 감사드립니다.\n"
	    			+ "														<br>\n"
	    			+ "														<br>\n"
	    			+ "														KoreAqua는 사용자가 일상생활에서 접하는 물, 그 물에 대한 수질 정보를 쉽게 얻기 위해 제작된 홈페이지 입니다.\n"
	    			+ "														<br>\n"
	    			+ "														<br>\n"
	    			+ "														가정에서 사용하는 수돗물, 집 근처 약수터, 해수욕장의 수질 검색와 대한민국의 5대강 (한강,금강, 낙동강, 섬진강, 영상강) 근처 주요 관광지와 추천 여행코스, 물에 관련된 단어의 정의를 볼 수 있는 물과 백과사전, \n"
	    			+ "        												여러분들의 궁금한 점과 문의사항을 관리하는 Q&A게시판 등으로 구성되어 있습니다.\n"
	    			+ "														<br>\n"
	    			+ "														더 다양한 정보를 안내드릴 예정이니 기대해주시기바랍니다!\n"
	    			+ "														\n"
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
			
			try {
				mailSender.sendMail(subject, content, useremail, setfrom );
				System.out.println("이메일 발송 성공");
			}catch(Exception e) {
				System.out.println("이메일 발송 실패");
			e.printStackTrace();
			}
			
			return useremail;
			
			
		}
		
		return useremail;
	}
	
	}