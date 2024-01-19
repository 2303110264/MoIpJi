package kr.ac.kopo.weather.api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
//import java.io.IOException;
// 중기예보 (3~10일)
public class ApiExplorer02 implements IApi{
    public String api(String t, String loc){
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst"); /*URL*/
        String key = "pK84UlXP6sWp3IemLK8XFeQWgiCqhf%2B8q8Fq8swWpmNDa91O0TQdVZIEAAzYP3X0k3%2FfEDVP%2BpkV1YyVqzGFrA%3D%3D";
        String today = "202401170600";
        String locationCode = "11B00000"; // 서울/인천/경기
        
        try {
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + key); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("24", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
	        urlBuilder.append("&" + URLEncoder.encode("stnId","UTF-8") + "=" + URLEncoder.encode(locationCode, "UTF-8")); /*108 전국, 109 서울, 인천, 경기도 등 (활용가이드 하단 참고자료 참조)*/
	        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode(today, "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/
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
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        return sb.toString();
        }catch(Exception e) {
        	e.printStackTrace();
        	return "error";
        }
        }


	@Override
	public String api(String date, String time, String nx, String ny) {
		// TODO Auto-generated method stub
		return null;
	}
}