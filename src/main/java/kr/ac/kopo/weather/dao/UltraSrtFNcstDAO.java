package kr.ac.kopo.weather.dao;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.ac.kopo.weather.vo.UltraSrtFNcstVO;

public class UltraSrtFNcstDAO {

	public UltraSrtFNcstVO fix(UltraSrtFNcstVO vo) {
		vo = transPTY(vo);
		vo = transLGT(vo);
		vo = transREH(vo);
		vo = transRNI(vo);
		vo = transSKY(vo);
		vo = transTIH(vo);
		vo = transWSD(vo);
		return vo;
	}
	public UltraSrtFNcstVO transPTY(UltraSrtFNcstVO vo) {
		//(초단기) 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7)
		String pty=null;
		switch(vo.getPTY()) {
		case "0": // 없음 (맑음)
			pty = "https://cdn-icons-gif.flaticon.com/11708/11708870.gif";
			break;
		case "1": // 비
			pty = "https://cdn-icons-gif.flaticon.com/6455/6455055.gif";
			break;
		case "2": // 비/눈
			pty = "https://cdn-icons-gif.flaticon.com/6455/6455057.gif";
			break;
		case "3": // 눈
			pty = "https://cdn-icons-gif.flaticon.com/13373/13373268.gif";
			break;
		case "5": // 빗방울
			pty = "https://cdn-icons-gif.flaticon.com/6455/6455029.gif";
			break;
		case "6": // 빗방울 눈날림
			pty = "https://cdn-icons-gif.flaticon.com/6455/6455007.gif";
			break;
		case "7": // 눈날림
			pty = "https://cdn-icons-gif.flaticon.com/6455/6455056.gif";
			break;
		}
		if(pty!=null) vo.setPTY(pty);
		return vo;
	}
	public UltraSrtFNcstVO transTIH(UltraSrtFNcstVO vo) {
		// 기온
		vo.setT1H(vo.getT1H()+"℃");
		return vo;
	}
	public UltraSrtFNcstVO transREH(UltraSrtFNcstVO vo) {
		// 습도
		vo.setREH(vo.getREH()+"%");
		return vo;
	}
	public UltraSrtFNcstVO transWSD(UltraSrtFNcstVO vo) {
		// 바람 세기
		vo.setWSD(vo.getWSD()+"m/s");
		return vo;
	}
	public UltraSrtFNcstVO transLGT(UltraSrtFNcstVO vo) {
		// 보류
		// 아직 LGT 값을 못 받아봄
		return vo;
	}
	public UltraSrtFNcstVO transSKY(UltraSrtFNcstVO vo) {
		if(vo.getSKY()==null) return vo;
		switch(vo.getSKY()) {
		case "1":
			vo.setSKY("맑음");
			break;
		case "3":
			vo.setSKY("구름 많음");
			break;
		case "4":
			vo.setSKY("흐림");
			break;
		}
		return vo;
	}
	public UltraSrtFNcstVO transRNI (UltraSrtFNcstVO vo) {
		double RNI = Double.parseDouble(vo.getRN1());

		if(RNI < 1.0f) vo.setRN1("1.0mm미만 ");		
		else if(RNI >= 1.0f && RNI < 30.0f) vo.setRN1("1.0~29.0mm");
		else if(RNI >= 30.0f && RNI < 50.0f) vo.setRN1("30.0~50.0mm");
		else vo.setRN1("50.0mm이상");
		
		return vo;
	}
	
	// 시간 /장소 받아와야해!! (아직안받음)-(시간 추가함!)
	// 받아오지 않은 이유: api 제공 시간이 매 시각 40분부터임!
	//(실제로는 20-30분에 올라오는 것 같긴 함)
	public UltraSrtFNcstVO docToUltraSrtVO(UltraSrtFNcstVO u, Document document) {
		try {
            // 루트 엘리먼트 가져오기
            Node root = document.getDocumentElement();

            // body 정보 가져오기
            Node body = getChildNode(root, "body");

            // items 정보 가져오기
            Node items = getChildNode(body, "items"); // <items>
            NodeList itemList = items.getChildNodes(); // <item> *n
            String category = "";
            
            // 각 item에 대한 정보 출력
            for (int i = 0; i < itemList.getLength(); i++) {
                Node item = itemList.item(i); // <item>
                NodeList nodeList = item.getChildNodes(); // 드디어 받아야 할 반환값 리스트
                
                // nodeList.item(0) = baseDate
                // nodeList.item(1) = baseTime
                // nodeList.item(2) = category
                // nodeList.item(3) = nx
                // nodeList.item(4) = ny
                // nodeList.item(5) = obsrValue
                
	                /*
	                 * if(cnt==6) cnt=0;
                String category = nodeList.item(2).getTextContent();
                String fcstValue = nodeList.item(5).getTextContent();
            	switch(category) {
            	case "SKY":
            		flist.get(cnt).setSKY(fcstValue);
            		break;
            	}
            	cnt++;
	                 */
            	// 날짜
            	u.setBaseDate(nodeList.item(0).getTextContent());
                // 시간
            	u.setBaseTime(nodeList.item(1).getTextContent());
                // 날씨코드
            	category = nodeList.item(2).getTextContent();
                // 코드별 값 자동 대입
            	String obsrValue = nodeList.item(5).getTextContent();
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
            	u.setX(nodeList.item(3).getTextContent());
            	u.setY(nodeList.item(4).getTextContent());
            }
            // 초단기예보/실황용 : LGT, SKY == 예보 전용 나머지 공용
                System.out.println("---------------");
                System.out.println("Base Date: " + u.getBaseDate());
                System.out.println("Base Time: " + u.getBaseTime());
                System.out.println("PTY(강수형태): " + u.getPTY());
                System.out.println("RN1(강수량): " + u.getRN1());
                System.out.println("T1H(기온): " + u.getT1H());
                System.out.println("REH(습도): " + u.getREH());
                System.out.println("WSD(풍속): " + u.getWSD());
                System.out.println("x좌표: " + u.getX());
                System.out.println("y좌표: " + u.getY());
                System.out.println("---------------");
            return u;
        } catch (Exception e) {
            System.out.println("udao.docToUltraSrtVO");
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
	public List<UltraSrtFNcstVO> docToUltraSrtVOList(UltraSrtFNcstVO vo, Document document) {
		try {
            // 루트 엘리먼트 가져오기
            Node root = document.getDocumentElement();

            // body 정보 가져오기
            Node body = getChildNode(root, "body");

            // items 정보 가져오기
            Node items = getChildNode(body, "items"); // <items>
            NodeList itemList = items.getChildNodes(); // <item> *n
            List<UltraSrtFNcstVO> flist = new ArrayList<UltraSrtFNcstVO>();
            for (int i = 0; i<6 ; i++) {
            	flist.add(new UltraSrtFNcstVO());
            	flist.get(i).setX(vo.getX());
            	flist.get(i).setY(vo.getY());
            }
            int cnt = 0;
            // 각 item에 대한 정보 출력
            for (int i = 0; i < itemList.getLength(); i++) {
                Node item = itemList.item(i); // <item>
                NodeList nodeList = item.getChildNodes(); // 드디어 받아야 할 반환값 리스트
                // nodeList.item(0) = baseDate
                // nodeList.item(1) = baseTime
                // nodeList.item(2) = category
                // nodeList.item(3) = fcstDate
                // nodeList.item(4) = fcstTime
                // nodeList.item(5) = fcstValue
                if(cnt==6) cnt=0;
                flist.get(cnt).setBaseDate(nodeList.item(3).getTextContent());
                flist.get(cnt).setBaseTime(nodeList.item(4).getTextContent());
                String category = nodeList.item(2).getTextContent();
                String fcstValue = nodeList.item(5).getTextContent();
            	switch(category) {
            	case "PTY": 
            		flist.get(cnt).setPTY(fcstValue); 
            		break;
            	case "RN1":
            		flist.get(cnt).setRN1(fcstValue);
            		break;
            	case "T1H":
            		flist.get(cnt).setT1H(fcstValue);
            		break;
            	case "REH":
            		flist.get(cnt).setREH(fcstValue);
            		break;
            	case "WSD":
            		flist.get(cnt).setWSD(fcstValue);
            		break;
            	case "LGT":
            		flist.get(cnt).setLGT(fcstValue);
            		break;
            	case "SKY":
            		flist.get(cnt).setSKY(fcstValue);
            		break;
            	}
            	cnt++;
            }
            return flist;
        } catch (Exception e) {
            System.out.println("udao.docToUltraSrtVOList");
            return null;
        }
	}
	
	public List<UltraSrtFNcstVO> fixList(List<UltraSrtFNcstVO> fcstList){
		
		return null;
	}
    
}