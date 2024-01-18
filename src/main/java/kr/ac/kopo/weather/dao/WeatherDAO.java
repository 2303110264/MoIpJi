package kr.ac.kopo.weather.dao;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class WeatherDAO {
	// 시간 /장소 받아와야해!! (아직안받음)
	String date;
	String time;
	String nx;
	String ny; // 위치값은 DB추출이 나을것. 위치값 테이블 필요할듯.
	
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
	
	public NodeList parseDoc(String xml) {
		Document xmlDoc = xmlToDocument(xml);
		// root 요소 가져오기
		Element root = xmlDoc.getDocumentElement();
		// root 요소의 첫번째 노드는 #Text
		Node firstNode = root.getFirstChild();
		// 다음 노드는 customer
		Node customer = firstNode.getNextSibling();
		// customer 요소 안의 노드 리스트
		NodeList childList = customer.getChildNodes();
		
		return childList;
	}

	public void in(NodeList childList) {
		// 출력부
		for(int i = 0; i < childList.getLength(); i++) {
			Node item = childList.item(i); // item(index) 메서드를 이용해 요소 가져올 수 있고, 공백 시작 - 태그/공백 반복하며 저장되어있음.
			if(item.getNodeType() == Node.ELEMENT_NODE) { // 노드의 타입이 Element일 경우(공백이 아닌 경우)
				System.out.println(item.getNodeName()); // 태그이름
				System.out.println(item.getTextContent()); // 태그에 든 내용 : getTextContext() = 태그 사이 필요 데이터 픽업?
			} else {
				System.out.println("-");
			}
		}
	}
}