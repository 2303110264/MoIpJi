package kr.ac.kopo.weather.dao;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import kr.ac.kopo.weather.api.ApiExplorer01;
import kr.ac.kopo.weather.vo.UltraSrtFNcstVO;

public class WeatherDAO {
	// 시간 /장소 받아와야해!! (아직안받음)
	public UltraSrtFNcstVO GPT(String nx, String ny) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, -40); // api 제공시간때문
		String time = new SimpleDateFormat("HHmm").format(c.getTime()).toString();
		String date = new SimpleDateFormat("yyyyMMdd").format(c.getTime()).toString();
		// 초단기/단기 예보
		ApiExplorer01 a = new ApiExplorer01();
		String xml = a.api(date, time, nx, ny);
		
		try {
            // XML 파일 파싱
            Document document = xmlToDocument(xml);
            // 루트 엘리먼트 가져오기
            Node root = document.getDocumentElement();
            // header 정보 가져오기
            //Node header = getChildNode(root, "header");
            //String resultCode = getChildNodeValue(header, "resultCode");
            //String resultMsg = getChildNodeValue(header, "resultMsg");

            // body 정보 가져오기
            Node body = getChildNode(root, "body");

            // items 정보 가져오기
            Node items = getChildNode(body, "items");
            NodeList itemList = items.getChildNodes();
            UltraSrtFNcstVO u = new UltraSrtFNcstVO();

            // 각 item에 대한 정보 출력
            for (int i = 0; i < itemList.getLength(); i++) {
                Node item = itemList.item(i);

                String baseDate = getChildNodeValue(item, "baseDate");
                String baseTime = getChildNodeValue(item, "baseTime");
                String category = getChildNodeValue(item, "category");
                String obsrValue = getChildNodeValue(item, "obsrValue");

                System.out.println("Base Date: " + baseDate);
                System.out.println("Base Time: " + baseTime);
                System.out.println("Category: " + category);
                System.out.println("Nx: " + nx);
                System.out.println("Ny: " + ny);
                System.out.println("Observed Value: " + obsrValue);
                System.out.println("---------------");
                
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
            return u;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 주어진 노드의 자식 중에서 특정한 이름의 자식 노드를 가져오는 메서드
    private static Node getChildNode(Node parent, String childName) {
        NodeList nodeList = parent.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeName().equals(childName)) {
                return node;
            }
        }
        return null;
    }

    // 주어진 노드의 자식 중에서 특정한 이름의 자식 노드의 텍스트 값을 가져오는 메서드
    private static String getChildNodeValue(Node parent, String childName) {
        Node childNode = getChildNode(parent, childName);
        if (childNode != null) {
            return childNode.getTextContent();
        }
        return null;
	}
    
	private Document xmlToDocument(String xml) {
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

	public UltraSrtFNcstVO nodeToVO(String date, String time, String nx, String ny) {
		ApiExplorer01 a = new ApiExplorer01();
		String xml = a.api(date, time, nx, ny);

		UltraSrtFNcstVO u = new UltraSrtFNcstVO();

		try{
			Document xmlDoc = xmlToDocument(xml);
			// root element 취득
			Element element = xmlDoc.getDocumentElement();
			// child node 취득
			NodeList list = element.getChildNodes();
			// child node 가 1개 이상인 경우
			if(list.getLength() > 0) {
				for(int i=0; i<list.getLength(); i++) {
			
					NodeList childList = list.item(i).getChildNodes();
			
					if(childList.getLength() > 0) {
						for (int j = 0; j < childList.getLength(); j++) {
							Node item = childList.item(j);
							
			          // 데이터가 있는 애들만 출려되게 한다.
							if(item.getNodeName().equals("#text")==false) {
								for(int k = 0; k < ((NodeList) item).getLength(); j++) {
								String n = item.getTextContent();
								System.out.println(item.getNodeName());
								System.out.println(n);
								if (item.getNodeName().equals("item")){
									switch(item.getNodeName()) {
									case "PTY": 
										u.setPTY(n); 
										break;
									case "RN1":
										u.setRN1(n);
										break;
									case "T1H":
										u.setT1H(n);
										break;
									case "REH":
										u.setREH(n);
										break;
									case "WSD":
										u.setWSD(n);
										break;
									case "LGT":
										u.setLGT(n);
										break;
									case "SKY":
										u.setSKY(n);
										break;
									}
								}
			          }
			        }
			      }
			      System.out.println();
			    }
			  }
				return u;
		}
			} catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
		}
		return null;
		
		//
		
		/*
		UltraSrtFNcstVO u = new UltraSrtFNcstVO();
		// 출력부
		for(int i = 0; i < childList.getLength(); i++) {
			Node item = childList.item(i); // item(index) 메서드를 이용해 요소 가져올 수 있고, 공백 시작 - 태그/공백 반복하며 저장되어있음.
			if(item.getNodeType() == Node.ELEMENT_NODE) { // 노드의 타입이 Element일 경우(공백이 아닌 경우)
				// 태그에 든 내용 : getTextContext() = 태그 사이 필요 데이터 픽업?
				String n = item.getTextContent();
				System.out.println(item.getNodeName());
				System.out.println(n);
				switch(item.getNodeName()) {
				case "PTY": 
					u.setPTY(n); 
					break;
				case "RN1":
					u.setRN1(n);
					break;
				case "T1H":
					u.setT1H(n);
					break;
				case "REH":
					u.setREH(n);
					break;
				case "WSD":
					u.setWSD(n);
					break;
				case "LGT":
					u.setLGT(n);
					break;
				case "SKY":
					u.setSKY(n);
					break;
				}
			} else {
				System.out.println("-");
			}
		}
		*/
	}
	
}