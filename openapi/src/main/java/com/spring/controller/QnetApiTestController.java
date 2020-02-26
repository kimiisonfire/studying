package com.spring.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class QnetApiTestController {
	
	
	@RequestMapping("/list")
	public String list() throws SQLException {
		String url = "test/test";
		return url;
	}
	
	
	@RequestMapping("/jmcdList")
	@ResponseBody
	public Map<String, String> apiList (@RequestParam("jmCd") String jmcd,
										ModelAndView mnv) throws SQLException {
		
		
		System.out.println("jmcd 종목코드 = " + jmcd);
		
		String serviceKey = "serviceKey";	// 사이트에서 제공받은 인증키
		
		Map<String, String> dataMap = new HashMap<>();
		
		try {
	        StringBuilder urlBuilder = new StringBuilder("http://openapi.q-net.or.kr/api/service/rest/InquiryTestInformationNTQSVC/getJMList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("jmCd","UTF-8") + "=" + URLEncoder.encode(jmcd, "UTF-8")); /*종목코드*/
	        URL url = new URL(urlBuilder.toString());
	        
	        System.out.println("url : " + urlBuilder.toString());
	        
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
		            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
		            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	        
	        dataMap.put("msg", "SUCCESS");
	        dataMap.put("buffer", sb.toString());
		} catch(Exception e) {
			dataMap.put("msg", "ERROR");
			dataMap.put("buffer", null);
		}
		
		return dataMap;
	}
	
	
}	
