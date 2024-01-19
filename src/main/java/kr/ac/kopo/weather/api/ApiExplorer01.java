package kr.ac.kopo.weather.api;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
//import java.io.IOException;

public class ApiExplorer01 implements IApi{
	/** 단기예보 ~3일 */
	public String api(String date, String time, String nx, String ny){
        StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"); /*URL*/
        String key = "pK84UlXP6sWp3IemLK8XFeQWgiCqhf+8q8Fq8swWpmNDa91O0TQdVZIEAAzYP3X0k3/fEDVP+pkV1YyVqzGFrA==";
        
        String d = "20240119";
        String t = "1100";
        String x = "55";
        String y = "127";
        try {
        	
        	// 초단기 실황 조회
        	urlBuilder.append("getUltraSrtNcst");
        	
        	// 초단기 예보 조회
        	// urlBuilder.append("getUltraSrtFcst");
        	
        	// 단기 예보 조회
        	// urlBuilder.append("getVilageFcst");
	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+URLEncoder.encode(key, "UTF-8")); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
	        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
	        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /*‘21년 6월 28일 발표*/
	        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(time, "UTF-8")); /*06시 발표(정시단위) */
	        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
	        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/
	        
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        
	        System.out.println("Response code: " + conn.getResponseCode()); // get response code
	        
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
        	return null;
        }
        }

}