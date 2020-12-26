package com.controller.subscribe;


import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


@Component
public class sendMail {
	

	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(String subject, String content, String useremail, String setfrom ) throws MessagingException, Exception {
		System.out.println("sendMail==="+useremail);
		System.out.println("sendMail==="+subject);
		//System.out.println("sendMail==="+content);
		System.out.println("sendMail==="+setfrom);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		
		messageHelper.setSubject(subject);
		messageHelper.setText(content, true);
		
		messageHelper.setFrom(new InternetAddress(setfrom, "KoreAqua"));
		messageHelper.setTo(new InternetAddress(useremail));
		
		mailSender.send(message);
	}
	
}