package com.controller.MSW;

import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dto.MineralSpringWaterDTO;

@Controller
public class MSWController {
	private static String getTagValue(String tag, Element eElement) {
		   NodeList nlList = eElement.getElementsByTagName(tag);
		    System.out.println("출력1"+nlList);
		    System.out.println("출력2"+nlList.item(0));
		    if(nlList.item(0)==null)
		    	return null;
		    NodeList nllList=nlList.item(0).getChildNodes();
		    Node nValue = (Node) nllList.item(0);
		    if(nValue == null) 
		        return null;
		    return nValue.getNodeValue();  
	}
	
	@RequestMapping(value = "/MineralSWMark", method = RequestMethod.GET)
	public ModelAndView beachPage(){
		ModelAndView mav=new ModelAndView();
		MineralSpringWaterDTO dto=new MineralSpringWaterDTO("", "", "", "", "",	"", "", "", "", "",	"", "", "", "");
		mav.addObject("wdto", dto);
		mav.setViewName("MineralSWMark");
		return mav;
	}
	
	@RequestMapping(value = "/MSW", method = RequestMethod.GET)
	public ModelAndView mSWinfo(String mnrlspNm) {
		ModelAndView mav=new ModelAndView();
		MineralSpringWaterDTO dto=new MineralSpringWaterDTO();
		try {
		System.out.println("");
		System.out.println("파싱한 약수터이름"+mnrlspNm);
		mnrlspNm = URLEncoder.encode(mnrlspNm, "utf-8");
		System.out.println("encoding한 약수터이름"+mnrlspNm);
		String url="http://api.data.go.kr/openapi/tn_pubr_public_appn_mnrlsp_info_api?serviceKey=3Md9YXyRjKEN438TPDzd8itwECgi8TBe%2Bwou4UlrGSG%2Bng6GKCD42ROverxTkZzBT3sNFPWBxIHzQSBu7TQIKw%3D%3D&pageNo=0&numOfRows=100&type=xml&mnrlspNm="+mnrlspNm;
		System.out.println(url.toString());
		DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(url);
		doc.getDocumentElement().normalize();
		Element element=doc.getDocumentElement();
		System.out.println(doc.getDocumentElement().getNodeName());
		
		NodeList nList=doc.getElementsByTagName("item");
		System.out.println("파싱할 데이터 리스트 갯수:"+nList.getLength());
		
		
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode=nList.item(temp);
			
			if(nNode.getNodeType()==Node.ELEMENT_NODE) {
				Element eElement=(Element)nNode;
				
				mnrlspNm=getTagValue("mnrlspNm", eElement);
				if(getTagValue("mnrlspNm", eElement)!=null) {
					mnrlspNm=getTagValue("mnrlspNm", eElement);
					}
					String rdnmadr="자료없음";
					if(getTagValue("rdnmadr", eElement)!=null) {
					rdnmadr=getTagValue("rdnmadr", eElement);
					}
					//
					System.out.println(rdnmadr);
					String lnmadr="자료없음";
					if(getTagValue("lnmadr", eElement)!=null) {
					lnmadr=getTagValue("lnmadr", eElement);
					}
					
					String latitude="자료없음";
					if(getTagValue("latitude", eElement)!=null) {
					latitude=getTagValue("latitude", eElement);
					}
					//
					String longitude="자료없음";
					if(getTagValue("longitude", eElement)!=null) {
					longitude=getTagValue("longitude", eElement);
					}
					//
					String appnDate="자료없음";
					if(getTagValue("appnDate", eElement)!=null) {
					appnDate=getTagValue("appnDate", eElement);
					}
					//
					String dayUseCn="자료없음";
					if(getTagValue("dayUseCn", eElement)!=null) {
					dayUseCn=getTagValue("dayUseCn", eElement);
					}
					//
					String qltwtrInspctDate="자료없음";
					if(getTagValue("qltwtrInspctDate", eElement)!=null) {
					//
					qltwtrInspctDate=getTagValue("qltwtrInspctDate", eElement);
					}
					//
					String qltwtrInspctResultType="자료없음";
					if(getTagValue("qltwtrInspctResultType", eElement)!=null) {
					qltwtrInspctResultType=getTagValue("qltwtrInspctResultType", eElement);
					}
					//
					String improptIem="";
					if(getTagValue("improptIem", eElement)!=null) {
					improptIem=getTagValue("improptIem", eElement);
					}
					//
					String phoneNumber="자료없음";
					if(getTagValue("phoneNumber", eElement)!=null) {
					phoneNumber=getTagValue("phoneNumber", eElement);
					}
					//
					String institutionNm="자료없음";
					if(getTagValue("institutionNm", eElement)!=null) {
					institutionNm=getTagValue("institutionNm", eElement);
					}//
					String referenceDate="자료없음";
					if(getTagValue("referenceDate", eElement)!=null) {
					referenceDate=getTagValue("referenceDate", eElement);
					}
					//
					String insttCode="자료없음";
					if(getTagValue("insttCode", eElement)!=null) {
					insttCode=getTagValue("insttCode", eElement);
					}
				
				dto=new MineralSpringWaterDTO(mnrlspNm, rdnmadr, lnmadr, latitude, longitude,
						appnDate, dayUseCn, qltwtrInspctDate, qltwtrInspctResultType, improptIem,
						phoneNumber, institutionNm, referenceDate, insttCode);
				mav.addObject("wdto", dto);
				mav.setViewName("MineralSWMark");
				System.out.println("공공데이터에서 받아서 dto에 넣어준 약수터 정보들:"+dto);
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	return mav;
}
}
