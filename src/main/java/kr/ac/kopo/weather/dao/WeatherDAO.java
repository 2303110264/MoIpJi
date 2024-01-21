package kr.ac.kopo.weather.dao;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import kr.ac.kopo.weather.api.ApiExplorer;
import kr.ac.kopo.weather.vo.UltraSrtFNcstVO;

public class WeatherDAO {
	
	public UltraSrtFNcstVO readCode(UltraSrtFNcstVO vo) {
		vo.setPTY(transPTY(vo.getPTY()));
		vo.setT1H(transTIH(vo.getT1H()));
		vo.setREH(transREH(vo.getREH()));
		vo.setWSD(transWSD(vo.getWSD()));
		vo.setLGT(transLGT(vo.getLGT()));
		vo.setSKY(transSKY(vo.getSKY()));
		double RNI = Double.parseDouble(vo.getRN1());
		vo.setRN1(transRNI(RNI));
		return vo;
	}

	public String transPTY(String PTY) {
		//(초단기) 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7)
		switch(PTY) {
		case "0":
			return "https://cdn-icons-gif.flaticon.com/11708/11708870.gif";
		case "1":
			return "https://cdn-icons-gif.flaticon.com/6455/6455055.gif";
		case "2":
			return "https://cdn-icons-gif.flaticon.com/6455/6455057.gif";
		case "3":
			return "https://cdn-icons-gif.flaticon.com/13373/13373268.gif";
		case "5":
			return "https://cdn-icons-gif.flaticon.com/6455/6455029.gif";
		case "6":
			return "https://cdn-icons-gif.flaticon.com/6455/6455007.gif";
		case "7":
			return "https://cdn-icons-gif.flaticon.com/6455/6455056.gif";
		}
		return null;
	}
	public String transTIH(String TIH) {
		return TIH+"℃";
	}
	public String transREH(String REH) {
		// 습도
		return REH+"%";
	}
	public String transWSD(String WSD) {
		// 바람 세기
		return WSD+"m/s";
	}
	public String transLGT(String LGT) {
		if (LGT!=null) return "번개 있음";
		return "없음";
	}
	public String transSKY(String SKY) {
		if(SKY==null) return null;
		switch(SKY) {
		case "1":
			return "맑음";
		case "3":
			return "구름 많음";
		case "4":
			return "흐림";
		}
		return "check transSKY or SKY value";
	}
	public String transRNI(double RNI) {
		if(RNI < 1.0f) return "1.0mm미만 ";		
		else if(RNI >= 1.0f && RNI < 30.0f) return "1.0~29.0mm";
		else if(RNI >= 30.0f && RNI < 50.0f) return "30.0~50.0mm";
		else return "50.0mm이상";
		
	}
	// 시간 /장소 받아와야해!! (아직안받음)-(시간 추가함!)
	// 받아오지 않은 이유: api 제공 시간이 매 시각 40분부터임!
	//(실제로는 20-30분에 올라오는 것 같긴 함)
	public UltraSrtFNcstVO xmlToUltraSrtVO(String nx, String ny) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, -40); // api 제공시간때문에 추가된 딜레이
		String time = new SimpleDateFormat("HHmm").format(c.getTime()).toString();
		String date = new SimpleDateFormat("yyyyMMdd").format(c.getTime()).toString();
		// 초단기/단기 예보
		ApiExplorer a = new ApiExplorer();
		String xml = a.ultraSrtNcst(date, time, nx, ny);
		
		try {
            // XML 파일 파싱
            Document document = xmlToDocument(xml);
            // 루트 엘리먼트 가져오기
            Node root = document.getDocumentElement();

            // body 정보 가져오기
            Node body = getChildNode(root, "body");

            // items 정보 가져오기
            Node items = getChildNode(body, "items"); // <items>
            NodeList itemList = items.getChildNodes(); // <item> *n
            UltraSrtFNcstVO u = new UltraSrtFNcstVO();
            String category = "";
            
            // 각 item에 대한 정보 출력
            for (int i = 0; i < itemList.getLength(); i++) {
                Node item = itemList.item(i); // <item>
                NodeList nodeList = item.getChildNodes(); // 드디어 받아야 할 반환값 리스트
                
                for (int j = 0; j < nodeList.getLength(); j++) {
                	Node node = nodeList.item(j);
	                // 날짜
	                if (node.getNodeName().equals("baseDate")) {
	                	u.setBaseDate(node.getTextContent());
	                // 시간
	                }else if(node.getNodeName().equals("baseTime")) {
	                	u.setBaseTime(node.getTextContent());
	                // 날씨코드
	                }else if(node.getNodeName().equals("category")) {
	                	category = node.getTextContent();
	                // 코드별 값 자동 대입
	                }else if(node.getNodeName().equals("obsrValue")) {
	                	String obsrValue = node.getTextContent();
	                	switch(category) {
	                	case "PTY": 
	                		u.setPTY(obsrValue); 
	                		break;
	                	case "RN1":
	                		u.setRN1(obsrValue);
	                		break;
	                	case "T1H":
	                		u.setT1H(obsrValue);
	                		break;
	                	case "REH":
	                		u.setREH(obsrValue);
	                		break;
	                	case "WSD":
	                		u.setWSD(obsrValue);
	                		break;
	                	case "LGT":
	                		u.setLGT(obsrValue);
	                		break;
	                	case "SKY":
	                		u.setSKY(obsrValue);
	                		break;
	                	}
	                }
                }

            }   
            // 초단기예보/실황용 : LGT, SKY == 예보 전용 나머지 공용
                System.out.println("---------------");
                System.out.println("Base Date: " + u.getBaseDate());
                System.out.println("Base Time: " + u.getBaseTime());
                System.out.println("PTY(강수형태): " + u.getPTY());
                System.out.println("RNI(강수량): " + u.getRN1());
                System.out.println("TIH(기온): " + u.getT1H());
                System.out.println("REH(습도): " + u.getREH());
                System.out.println("WSD(풍속): " + u.getWSD());
                System.out.println("LGT(번개): " + u.getLGT());		// 실황일 경우
                System.out.println("SKY(하늘 상태): " + u.getSKY());	// null값
                System.out.println("x좌표: " + nx);
                System.out.println("y좌표: " + ny);
                System.out.println("---------------");
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 주어진 노드의 자식 중에서 특정한 이름의 자식 노드 찾기
    public Node getChildNode(Node parent, String childName) {
    	NodeList nodeList = parent.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(childName)) {
            	return node;
            }
        }
        return null;
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
	private UltraSrtFNcstVO changeGPSToGrid(double lat_X, double lng_Y )
	{
	    double RE = 6371.00877; // 지구 반경(km)
	    double GRID = 5.0; // 격자 간격(km)
	    double SLAT1 = 30.0; // 투영 위도1(degree)
	    double SLAT2 = 60.0; // 투영 위도2(degree)
	    double OLON = 126.0; // 기준점 경도(degree)
	    double OLAT = 38.0; // 기준점 위도(degree)
	    double XO = 43; // 기준점 X좌표(GRID)
	    double YO = 136; // 기1준점 Y좌표(GRID)

	    double DEGRAD = Math.PI / 180.0;
	    double RADDEG = 180.0 / Math.PI;

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
	    UltraSrtFNcstVO rs = new UltraSrtFNcstVO();

        
	    //위도/경도 > 그리드
        double ra = Math.tan(Math.PI * 0.25 + (lat_X) * DEGRAD * 0.5);
        ra = re * sf / Math.pow(ra, sn);
        double theta = lng_Y * DEGRAD - olon;
        if (theta > Math.PI) theta -= 2.0 * Math.PI;
        if (theta < -Math.PI) theta += 2.0 * Math.PI;
        theta *= sn;
        rs.setX((int)Math.floor(ra * Math.sin(theta) + XO + 0.5)+"");
        rs.setY((int)Math.floor(ro - ra * Math.cos(theta) + YO + 0.5)+"");

        return rs;
	}

	
}