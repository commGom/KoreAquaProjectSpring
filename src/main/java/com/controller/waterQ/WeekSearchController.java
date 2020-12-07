package com.controller.waterQ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dto.JusoDTO;
import com.dto.WeekDTO;
import com.service.JusoService;

@Controller
public class WeekSearchController {

	@Autowired
	JusoService service;
	@RequestMapping(value="/WeekSearch" ,method=RequestMethod.GET)
	public ModelAndView WeekSearch(String sigun,String dong,String stdt, String eddt )throws Exception{
		System.out.println("들어왔다");
		System.out.println("sigun::"+sigun);
		System.out.println("dong::"+dong);
		System.out.println("stdt::"+stdt+"eddt:::"+eddt);
		ModelAndView mav= new ModelAndView();
		
        // 시도, 시군, 동이 모두 입력된 경우(dong != null) 아래 코드를 수행
        if (dong != null) {
            
            // API 호출을 위한 필수 변수값을 설정
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
            if (sgccd == null ) {
            	mav.addObject("mesg", "현재 서비스를 제공하고 있지 않습니다.");
				mav.setViewName("WeekSearch");

            }else if(sgccd.equals("2")) {//데이터가 아예 없는 지역
            	
            	mav.addObject("mesg", "이 지역은 현재 서비스를 제공하고 있지 않습니다.");
				mav.setViewName("WeekSearch");
            	
            }else if (sgccd.equals("1")) {
            	stdt = stdt.substring(0, 7);
            	System.out.println(stdt);
                eddt = eddt.substring(0, 7);
                System.out.println(eddt);
            		System.out.println("광역정수장======");
                    String fcode = sitecd;
                    System.out.println("fcode==="+fcode);
                try {
                   
                    String serviceKey = "xUaCQjbwxEcfxfh7%2FHktyNRhuSsKDSauOvqlvcPI01iUuOpKUc%2FO0cvLrIHqa5DCB%2F1oYsEj1Y6nqvssEstEWg%3D%3D";

                    String urlStr = "http://opendata.kwater.or.kr/openapi-data/service/pubd/waterways/wdr/wikwater/list";
                    urlStr += "?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey;
                    urlStr += "&" + URLEncoder.encode("fcode", "UTF-8") + "=" + fcode;
                    urlStr += "&" + URLEncoder.encode("stdt", "UTF-8") + "=" + stdt;
                    urlStr += "&" + URLEncoder.encode("eddt", "UTF-8") + "=" + eddt;
                    urlStr += "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows;
                    urlStr += "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo;
                    urlStr += "&" + URLEncoder.encode("_type", "UTF-8") + "=json";

                    URL url = new URL(urlStr);

                    System.out.println(url);
                    String line = "";
                    String result = "";

                    BufferedReader br;                
                    br = new BufferedReader(new InputStreamReader(url.openStream()));
                    
                    while ((line = br.readLine()) != null) {
                        result = result.concat(line);
                        // System.out.println(line);
                    }
                     // JSON parser 만들어 문자열 데이터를 객체화한다.
                    JSONParser parser = new JSONParser();
                    JSONObject obj;

                    obj = (JSONObject) parser.parse(result);
                    // System.out.println(obj);
                    List<WeekDTO> wList = new ArrayList<WeekDTO>();
                    		
                    JSONObject parse_response = (JSONObject) obj.get("response"); // response 로 부터 body 찾아옵니다.
                    JSONObject parse_body = (JSONObject) parse_response.get("body"); // body 로 부터 items 받아옵니다.
                    String totalC = (String) parse_body.get("totalCount").toString();
                    System.out.println("totalc=" + totalC);
                    
                    if(totalC.equals("0")){
                    	System.out.println("totalC가 0일때");
                    	mav.addObject("mesg", "이 지역은 현재 서비스를 제공하고 있지 않습니다.");
        				mav.setViewName("WeekSearch");
                    	
                    }else if (totalC.equals("1")) {
                        System.out.println("들어왔");
                        JSONObject parse_items = (JSONObject) parse_body.get("items"); // items로 부터 itemlist 를 받아오기 itemlist
                        // : 뒤에 [ 로 시작하므로 jsonarray이다
                        System.out.println(parse_items.size());
                        JSONObject weekWaterQlt = (JSONObject) parse_items.get("item");

                        WeekDTO wDTO = new WeekDTO();
                        String cltdt = (String) weekWaterQlt.get("mesurede").toString();                      
                        String bacteria = (String) weekWaterQlt.get("item1").toString(); // 일반세균
                        String totalColita = (String) weekWaterQlt.get("itmem2").toString(); // 총대장균군
                        String coli = (String) weekWaterQlt.get("item3").toString(); // 대장균/분원성대장균
                        String ammonia = (String) weekWaterQlt.get("item4").toString(); // 암모니아성질소
                        String nnitrogen = (String) weekWaterQlt.get("item5").toString(); // 질산성질소
                        String evaresi = (String) weekWaterQlt.get("item7").toString(); // 증발잔류물

                        wDTO.setCltdt(cltdt);
                        wDTO.setBacteria(bacteria);
                        wDTO.setTotalColi(totalColita);
                        wDTO.setColi(coli);
                        wDTO.setAmmonia(ammonia);
                        wDTO.setNnitrogen(nnitrogen);
                        wDTO.setEvaresi(evaresi);

                        wList.add(wDTO);

                    } else {
                    	JSONObject parse_items = (JSONObject) parse_body.get("items"); // items로 부터 itemlist 를 받아오기 itemlist
                        // : 뒤에 [ 로 시작하므로 jsonarray이다

                    	System.out.println(parse_items.size());
                        JSONArray parse_item = (JSONArray) parse_items.get("item");

                        JSONObject weekWaterQlt;

                        for (int i = 0; i < parse_item.size(); i++) {
                            weekWaterQlt = (JSONObject) parse_item.get(i);
                            WeekDTO wDTO = new WeekDTO();
                            String cltdt = (String) weekWaterQlt.get("mesurede").toString();
                            String bacteria = (String) weekWaterQlt.get("item1").toString(); // 일반세균
                            String totalColita = (String) weekWaterQlt.get("item2").toString(); // 총대장균군
                            String coli = (String) weekWaterQlt.get("item3").toString(); // 대장균/분원성대장균
                            String ammonia = (String) weekWaterQlt.get("item4").toString(); // 암모니아성질소
                            String nnitrogen = (String) weekWaterQlt.get("item5").toString(); // 질산성질소
                            String evaresi = (String) weekWaterQlt.get("item7").toString(); // 증발잔류물


                            wDTO.setCltdt(cltdt);
                            wDTO.setBacteria(bacteria);
                            wDTO.setTotalColi(totalColita);
                            wDTO.setColi(coli);
                            wDTO.setAmmonia(ammonia);
                            wDTO.setNnitrogen(nnitrogen);
                            wDTO.setEvaresi(evaresi);

                            wList.add(wDTO);

                        }
                    }
                    mav.addObject("wList", wList);
    				mav.setViewName("WeekSearch");
                   
                    }catch (ParseException e) {
                        e.printStackTrace();
                        
                    }
                }
                 else {
                	 System.out.println("지방정수장======");
                    try {
                       String serviceKey = "xUaCQjbwxEcfxfh7%2FHktyNRhuSsKDSauOvqlvcPI01iUuOpKUc%2FO0cvLrIHqa5DCB%2F1oYsEj1Y6nqvssEstEWg%3D%3D";

                        String urlStr = "http://opendata.kwater.or.kr/openapi-data/service/pubd/waterinfos/waterquality/weekwater/list";
                        urlStr += "?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey;
                        urlStr += "&" + URLEncoder.encode("sgccd", "UTF-8") + "=" + sgccd;
                        urlStr += "&" + URLEncoder.encode("sitecd", "UTF-8") + "=" + sitecd;
                        urlStr += "&" + URLEncoder.encode("stdt", "UTF-8") + "=" + stdt;
                        urlStr += "&" + URLEncoder.encode("eddt", "UTF-8") + "=" + eddt;
                        urlStr += "&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + numOfRows;
                        urlStr += "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + pageNo;
                        urlStr += "&" + URLEncoder.encode("_type", "UTF-8") + "=json";

                        URL url = new URL(urlStr);

                        System.out.println(url);
                        String line = "";
                        String result = "";

                        BufferedReader br;
                        br = new BufferedReader(new InputStreamReader(url.openStream()));
                        while ((line = br.readLine()) != null) {
                            result = result.concat(line);
                            // System.out.println(line);
                        }

                        // JSON parser 만들어 문자열 데이터를 객체화한다.
                        JSONParser parser = new JSONParser();
                        JSONObject obj;

                        obj = (JSONObject) parser.parse(result);
                        // System.out.println(obj);
                        List<WeekDTO> wList = new ArrayList<WeekDTO>();

                        JSONObject parse_response = (JSONObject) obj.get("response"); // response 로 부터 body 찾아옵니다.
                        JSONObject parse_body = (JSONObject) parse_response.get("body"); // body 로 부터 items 받아옵니다.
                        String totalC = (String) parse_body.get("totalCount").toString();
                        System.out.println("totalc=" + totalC);
                        
                        
                        if(totalC.equals("0")) {
                        	System.out.println("지방=======");
                        	mav.addObject("mesg", "이 지역은 현재 서비스를 제공하고 있지 않습니다.");
            				mav.setViewName("WeekSearch");
                        	
                        }else if (totalC.equals("1")) {
                            System.out.println("들어왔");
                            JSONObject parse_items = (JSONObject) parse_body.get("items"); // items로 부터 itemlist 를 받아오기 itemlist
                            System.out.println(parse_items);                                                              // : 뒤에 [ 로 시작하므로 jsonarray이다
                            System.out.println(parse_items.size());
                            JSONObject weekWaterQlt = (JSONObject) parse_items.get("item");

                            WeekDTO wDTO = new WeekDTO();
                            String cltdt = (String) weekWaterQlt.get("cltdt").toString();
                            System.out.println(cltdt);
                            String bacteria = (String) weekWaterQlt.get("data1").toString(); // 일반세균
                            String totalColita = (String) weekWaterQlt.get("data2").toString(); // 총대장균군
                            String coli = (String) weekWaterQlt.get("data3").toString(); // 대장균/분원성대장균
                            String ammonia = (String) weekWaterQlt.get("data4").toString(); // 암모니아성질소
                            String nnitrogen = (String) weekWaterQlt.get("data5").toString(); // 질산성질소
                            String potaPerm = (String) weekWaterQlt.get("data6").toString(); // 과망간산칼륨소비량
                            String evaresi = (String) weekWaterQlt.get("data7").toString(); // 증발잔류물


                            wDTO.setCltdt(cltdt);
                            wDTO.setBacteria(bacteria);
                            wDTO.setTotalColi(totalColita);
                            wDTO.setColi(coli);
                            wDTO.setAmmonia(ammonia);
                            wDTO.setNnitrogen(nnitrogen);
                            wDTO.setEvaresi(evaresi);

                            wList.add(wDTO);

                        } else {
                        	JSONObject parse_items = (JSONObject) parse_body.get("items"); // items로 부터 itemlist 를 받아오기 itemlist
                            System.out.println(parse_items);                                                              // : 뒤에 [ 로 시작하므로 jsonarray이다
                            System.out.println(parse_items.size());
                            JSONArray parse_item = (JSONArray) parse_items.get("item");

                            JSONObject weekWaterQlt;

                            for (int i = 0; i < parse_item.size(); i++) {
                                weekWaterQlt = (JSONObject) parse_item.get(i);
                                WeekDTO wDTO = new WeekDTO();
                                String cltdt = (String) weekWaterQlt.get("cltdt").toString();
                                String bacteria = (String) weekWaterQlt.get("data1").toString(); // 일반세균
                                String totalColita = (String) weekWaterQlt.get("data2").toString(); // 총대장균군
                                String coli = (String) weekWaterQlt.get("data3").toString(); // 대장균/분원성대장균
                                String ammonia = (String) weekWaterQlt.get("data4").toString(); // 암모니아성질소
                                String nnitrogen = (String) weekWaterQlt.get("data5").toString(); // 질산성질소
                                String evaresi = (String) weekWaterQlt.get("data7").toString(); // 증발잔류물

                                wDTO.setCltdt(cltdt);
                                wDTO.setBacteria(bacteria);
                                wDTO.setTotalColi(totalColita);
                                wDTO.setColi(coli);
                                wDTO.setAmmonia(ammonia);
                                wDTO.setNnitrogen(nnitrogen);
                                wDTO.setEvaresi(evaresi);

                                wList.add(wDTO);
                                
                            }
                        }
                        mav.addObject("wList", wList);
        				mav.setViewName("WeekSearch");
                      
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("catch실행===");
                        mav.addObject("mesg", "조회할 지역을 모두 선택하세요.");
        				mav.setViewName("WeekSearch");
                        
                    }
                }
        } else {
        	
        }
        return mav;
		
	}
}
