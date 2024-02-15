package kr.ac.kopo.weather.dao;
import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import kr.ac.kopo.weather.vo.UltraSrtFNcstVO;

public class ApiDAO{
	private String key = "pK84UlXP6sWp3IemLK8XFeQWgiCqhf+8q8Fq8swWpmNDa91O0TQdVZIEAAzYP3X0k3/fEDVP+pkV1YyVqzGFrA==";
	private StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/"); /*URL*/
    /** 초단기예보-단기예보 최대 ~3일 */

	// 초단기 실황 조회
	public String ultraSrtNcst(String nx, String ny){
        try {
        	Calendar c = Calendar.getInstance();
    		c.add(Calendar.MINUTE, -40); // api 제공시간때문에 추가된 딜레이
    		String time = new SimpleDateFormat("HHmm").format(c.getTime()).toString();
    		String date = new SimpleDateFormat("yyyyMMdd").format(c.getTime()).toString();
    		
        	urlBuilder.append("getUltraSrtNcst");
        	
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
	        System.out.println(url);
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        
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
	
	// 초단기 예보 조회
	public String ultraSrtFcst(String nx, String ny){
		try {
			
			Calendar c = Calendar.getInstance();
    		c.add(Calendar.MINUTE, -45); // api 제공시간때문에 추가된 딜레이
    		String time = new SimpleDateFormat("HHmm").format(c.getTime()).toString();
    		String date = new SimpleDateFormat("yyyyMMdd").format(c.getTime()).toString();
    		
			urlBuilder.append("getUltraSrtFcst");
			
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

	// 단기 예보 조회
	public String villageFcst(String date, String time, String nx, String ny){
		try {
			urlBuilder.append("getVilageFcst");
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
	
	// String을 Document 형태로 변환하기
	public Document xmlToDocument(String xml) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource( new StringReader( xml ) );
			Document d = builder.parse( is );
			return d;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//위도/경도값 grid로 바꾸기
	public UltraSrtFNcstVO changeGPSToGrid(String x, String y)
	{
		try {
		double lat_X = Double.parseDouble(x);
		double lng_Y = Double.parseDouble(y);
	    double RE = 6371.00877; // 지구 반경(km)
	    double GRID = 5.0; // 격자 간격(km)
	    double SLAT1 = 30.0; // 투영 위도1(degree)
	    double SLAT2 = 60.0; // 투영 위도2(degree)
	    double OLON = 126.0; // 기준점 경도(degree)
	    double OLAT = 38.0; // 기준점 위도(degree)
	    double XO = 43; // 기준점 X좌표(GRID)
	    double YO = 136; // 기1준점 Y좌표(GRID)

	    double DEGRAD = Math.PI / 180.0;

	    double re = RE / GRID;
	    double slat1 = SLAT1 * DEGRAD;
	    double slat2 = SLAT2 * DEGRAD;
	    double olon = OLON * DEGRAD;
	    double olat = OLAT * DEGRAD;

	    double sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	    sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
	    double sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
	    sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
	    double ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
	    ro = re * sf / Math.pow(ro, sn);

        
	    //위도/경도 > 그리드
        double ra = Math.tan(Math.PI * 0.25 + (lat_X) * DEGRAD * 0.5);
        ra = re * sf / Math.pow(ra, sn);
        double theta = lng_Y * DEGRAD - olon;
        if (theta > Math.PI) theta -= 2.0 * Math.PI;
        if (theta < -Math.PI) theta += 2.0 * Math.PI;
        theta *= sn;
        
        UltraSrtFNcstVO rs = new UltraSrtFNcstVO();
        rs.setX((int)Math.floor(ra * Math.sin(theta) + XO + 0.5)+"");
        rs.setY((int)Math.floor(ro - ra * Math.cos(theta) + YO + 0.5)+"");

        return rs;
        }catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}
	public String XYToAddress(String x, String y) {
		StringBuilder kakaoUrl = new StringBuilder("https://dapi.kakao.com/v2/local/geo/coord2address.json");
		//String kakaoKey = "2c3d392f0bce9320bed9a1cb573ea0dd"; //REST Api key
		String kakaoKey = "a096af0b7420171e2ec8e60100485ec6"; //adminKey
		
		try{
            kakaoUrl.append("?" + URLEncoder.encode("x","UTF-8") + "=" + URLEncoder.encode(x, "UTF-8")); 
            kakaoUrl.append("&" + URLEncoder.encode("y","UTF-8") + "=" + URLEncoder.encode(y, "UTF-8")); 
			
            URL url = new URL(kakaoUrl.toString());
            
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            //conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Requested-With", "curl");
            conn.setRequestProperty("Authorization", "KakaoAK " + kakaoKey);
            //conn.setRequestProperty("content-type", "application/json");
            //conn.setDoOutput(true);
            
            Charset charset = Charset.forName("UTF-8");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
            
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
            	response.append(inputLine); 
            } 
            System.out.println("responseCode : "+conn.getResponseCode());
            System.out.println(response.toString());
            return response.toString();
            
		} catch (Exception e) {
    		e.printStackTrace();
			return "XYToAddress error";
		}
		
	}
}