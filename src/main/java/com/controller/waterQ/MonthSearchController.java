package com.controller.waterQ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dto.JusoDTO;
import com.dto.MonthDTO;
import com.service.JusoService;

@Controller
public class MonthSearchController {
	@Autowired
	JusoService service;
	
	@RequestMapping(value="/MonthSearch" ,method=RequestMethod.GET)	
	public ModelAndView MonthSearch(String stdt, String sido, String sigun, String dong) throws Exception{
		System.out.println("들어왔다!!!!");		
		System.out.println("sigun::"+sigun);
		System.out.println("dong::"+dong);
		System.out.println(stdt);
	
		ModelAndView mav = new ModelAndView();
		//시도, 시군, 동이 모두 입력된 경우(dong != null) 아래 코드를 수행
		if(dong != null) {
			
			//API 호출을 위한 필수 변수값을 설정
			Map<String, String> map = new HashMap<String, String>();
			map.put("sigun", sigun);
			map.put("dong", dong);
			
			List<JusoDTO> codeList = service.searchCode(map);
			
			String sgccd = codeList.get(0).getSgccd();
			String sitecd = codeList.get(0).getSitecd();
			System.out.println("지자체코드==" + sgccd);
			System.out.println("정수장코드==" + sitecd);
			String numOfRows = "100";
			String pageNo = "1";
			
			StringBuilder sb = new StringBuilder();
			List<MonthDTO> mList = new ArrayList<MonthDTO>();
			JSONParser parser = new JSONParser();
			
			if(sgccd == null) { 
				mav.addObject("mesg", "현재 서비스를 제공하고 있지 않습니다.");
				mav.setViewName("MonthSearch");
				
			} else if(sgccd.equals("1")) { //광역 정수장 API 호출				
				String year = stdt.substring(0, 4);				
				String month = stdt.substring(6, 7);
				System.out.println(year+month);
						
				if(month.length() == 1) {
					month = "0" + month;
				}

				StringBuilder urlBuilder = new StringBuilder("http://opendata.kwater.or.kr/openapi-data/service/pubd/waterways/wdr/dmntwater/list"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=xUaCQjbwxEcfxfh7%2FHktyNRhuSsKDSauOvqlvcPI01iUuOpKUc%2FO0cvLrIHqa5DCB%2F1oYsEj1Y6nqvssEstEWg%3D%3D"); /*Service Key*/
		        urlBuilder.append("&" + URLEncoder.encode("fcode","UTF-8") + "=" + URLEncoder.encode(sitecd, "UTF-8")); /*정수장 코드 조회 서비스 참조*/
		        urlBuilder.append("&" + URLEncoder.encode("stdt","UTF-8") + "=" + URLEncoder.encode(year+"-"+month, "UTF-8")); /*조회시작일*/
		        urlBuilder.append("&" + URLEncoder.encode("eddt","UTF-8") + "=" + URLEncoder.encode(year+"-"+month, "UTF-8")); /*조회종료일*/
		        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*줄수*/
		        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
		        urlBuilder.append("&_type=json");
		        System.out.println(urlBuilder);
		        URL url = new URL(urlBuilder.toString());
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		            
		            mav.addObject("mesg", "공공데이터 호출 오류입니다. 다시 조회하세요.");
					mav.setViewName("MonthSearch");
		
		        }
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString());
		        System.out.println("광역정수장 API 호출 ===== ");
		        try {
			        JSONObject obj = (JSONObject) parser.parse(sb.toString());
					JSONObject parseResponse = (JSONObject)obj.get("response");
					JSONObject parseBody = (JSONObject)parseResponse.get("body");
					
					String totalCount = (String) parseBody.get("totalCount").toString();
					System.out.println("totalCount===="+totalCount);
					JSONObject monthWaterQlt = null;
					if(totalCount.equals("0")) {
						mav.addObject("mesg", "현재 서비스를 제공하고 있지 않습니다.");
						mav.setViewName("MonthSearch");
					} else {
		    			JSONObject parseItems = (JSONObject)parseBody.get("items");
		    			monthWaterQlt = (JSONObject) parseItems.get("item");
		    			
						MonthDTO mDTO = new MonthDTO();
						String date = (String)monthWaterQlt.get("mesurede").toString();
						String taste = (String)monthWaterQlt.get("item47").toString();
						String smell = (String)monthWaterQlt.get("item46").toString();
						String chromaticity = (String)monthWaterQlt.get("item49").toString();
						String pH = (String)monthWaterQlt.get("item51").toString();
						String turbidity = (String)monthWaterQlt.get("item57").toString();
						String residualCI = (String)monthWaterQlt.get("item33").toString();
						String bacteria = (String)monthWaterQlt.get("item1").toString();// 일반세균
						String totalColi = (String)monthWaterQlt.get("item2").toString();// 총대장균군
						String coli = (String)monthWaterQlt.get("item3").toString();// 대장균/분원성대장균군
						String ammonia = (String)monthWaterQlt.get("item12").toString();// 암모니아성 질소
						String nnitrogen = (String)monthWaterQlt.get("item13").toString();// 질산성질소
						String evaresi = (String)monthWaterQlt.get("item54").toString();// 증발잔류물
						
						mDTO.setDate(date);
						mDTO.setTaste(taste);
						mDTO.setSmell(smell);
						mDTO.setChromaticity(chromaticity);
						mDTO.setpH(pH);
						mDTO.setTurbidity(turbidity);
						mDTO.setResidualCI(residualCI);
						mDTO.setBacteria(bacteria);
						mDTO.setTotalColi(totalColi);
						mDTO.setColi(coli);
						mDTO.setAmmonia(ammonia);
						mDTO.setNnitrogen(nnitrogen);
						mDTO.setEvaresi(evaresi);
						
						mList.add(mDTO);						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(sgccd.equals("2")) { //데이터가 없는 지역 - 안내 mesg 저장
				mav.addObject("mesg", "이 지역은 현재 서비스를 제공하고 있지 않습니다.");
				mav.setViewName("MonthSearch");
			}else{ //지방 정수장 API 호출
				String year = stdt.substring(0, 4);				
				String month = stdt.substring(6, 7);
				System.out.println(year+month);						
				if(month.length() == 1) {
					month = "0" + month;
				}
				//API url 생성
				StringBuilder urlBuilder = new StringBuilder("http://opendata.kwater.or.kr/openapi-data/service/pubd/waterinfos/waterquality/monthwater/list"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=xUaCQjbwxEcfxfh7%2FHktyNRhuSsKDSauOvqlvcPI01iUuOpKUc%2FO0cvLrIHqa5DCB%2F1oYsEj1Y6nqvssEstEWg%3D%3D"); /*Service Key*/
		        urlBuilder.append("&" + URLEncoder.encode("sgccd","UTF-8") + "=" + URLEncoder.encode(sgccd, "UTF-8")); /*지자체코드*/
		        urlBuilder.append("&" + URLEncoder.encode("sitecd","UTF-8") + "=" + URLEncoder.encode(sitecd, "UTF-8")); /*정수장코드*/
		        urlBuilder.append("&" + URLEncoder.encode("stdt","UTF-8") + "=" + URLEncoder.encode(year+month, "UTF-8")); /*조회시작일*/
		        urlBuilder.append("&" + URLEncoder.encode("eddt","UTF-8") + "=" + URLEncoder.encode(year+month.replaceAll("="," "), "UTF-8")); /*조회종료일*/
		        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*줄수*/
		        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
		        urlBuilder.append("&_type=json");
		        System.out.println(urlBuilder);		        
//		        //URL 객체 생성하기
		        URL url = new URL(urlBuilder.toString());
		        
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setRequestMethod("GET");
		        conn.setRequestProperty("Content-type", "application/json");
		        System.out.println("Response code: " + conn.getResponseCode());
		        
		        BufferedReader rd;
		        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        } else {
		            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		            mav.addObject("mesg", "공공데이터 호출 오류입니다. 다시 시도해 주세요.");
					mav.setViewName("MonthSearch");
		        }
		        String line;
		        while ((line = rd.readLine()) != null) {
		            sb.append(line);
		        }
		        rd.close();
		        conn.disconnect();
		        System.out.println(sb.toString());
		        System.out.println("지방정수장 API 호출 ===== ");
		        try {
			        JSONObject obj = (JSONObject) parser.parse(sb.toString());
					JSONObject parseResponse = (JSONObject)obj.get("response");
					JSONObject parseBody = (JSONObject)parseResponse.get("body");
					String totalCount = (String) parseBody.get("totalCount").toString();
					System.out.println("totalCount===" + totalCount);
					JSONObject monthWaterQlt = null;
					
					if(totalCount.equals("0")) {
						 mav.addObject("mesg", "현재 서비스를 제공하고 있지 않습니다.");
							mav.setViewName("MonthSearch");
					} else {
						JSONObject parseItems = (JSONObject)parseBody.get("items");
						monthWaterQlt = (JSONObject) parseItems.get("item");
						
						MonthDTO mDTO = new MonthDTO();
						String date = (String)monthWaterQlt.get("cltdt").toString();
						String taste = (String)monthWaterQlt.get("data42").toString();
						String smell = (String)monthWaterQlt.get("data41").toString();
						String chromaticity = (String)monthWaterQlt.get("data44").toString();
						String pH = (String)monthWaterQlt.get("data46").toString();
						String turbidity = (String)monthWaterQlt.get("data52").toString();
						String residualCI = (String)monthWaterQlt.get("data33").toString();
						String bacteria = (String)monthWaterQlt.get("data1").toString();// 일반세균
						String totalColi = (String)monthWaterQlt.get("data2").toString();// 총대장균군
						String coli = (String)monthWaterQlt.get("data3").toString();// 대장균/분원성대장균군
						String ammonia = (String)monthWaterQlt.get("data11").toString();// 암모니아성 질소
						String nnitrogen = (String)monthWaterQlt.get("data12").toString();// 질산성질소
						String evaresi = (String)monthWaterQlt.get("data49").toString();// 증발잔류물
						
						mDTO.setDate(date);
						mDTO.setTaste(taste);
						mDTO.setSmell(smell);
						mDTO.setChromaticity(chromaticity);
						mDTO.setpH(pH);
						mDTO.setTurbidity(turbidity);
						mDTO.setResidualCI(residualCI);
						mDTO.setBacteria(bacteria);
						mDTO.setTotalColi(totalColi);
						mDTO.setColi(coli);
						mDTO.setAmmonia(ammonia);
						mDTO.setNnitrogen(nnitrogen);
						mDTO.setEvaresi(evaresi);
						
						mList.add(mDTO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		        
			}
			mav.addObject("mList", mList);
			mav.setViewName("MonthSearch");
			
		//dong == null인 경우
		} else {
			
		}
		
	return mav;
		
	}
}
