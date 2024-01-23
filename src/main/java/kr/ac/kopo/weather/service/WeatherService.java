package kr.ac.kopo.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import kr.ac.kopo.weather.dao.ApiDAO;
import kr.ac.kopo.weather.dao.UltraSrtFNcstDAO;
import kr.ac.kopo.weather.vo.UltraSrtFNcstVO;

public class WeatherService {
	private UltraSrtFNcstDAO usdao = new UltraSrtFNcstDAO();
	private ApiDAO adao = new ApiDAO();
	
	public UltraSrtFNcstVO getUltraSrtNcst(UltraSrtFNcstVO vo) {
		try {
		String xml = adao.ultraSrtNcst(vo.getX(), vo.getY());
		Document doc = adao.xmlToDocument(xml);
		vo = usdao.fix(usdao.docToUltraSrtVO(vo, doc));
		}catch(Exception e) {
			System.out.println("NullPointerException 1회 팝업 == 정상");
			System.out.println("Loading...");
			return null;
		}
		return vo; 
	}
	public UltraSrtFNcstVO GPSToGrid(String x, String y) {
		return adao.changeGPSToGrid(x, y);
	}
	
	public List<UltraSrtFNcstVO> getUltraSrtFcst(UltraSrtFNcstVO vo) {
		try {
			List<UltraSrtFNcstVO> fcstList = new ArrayList<UltraSrtFNcstVO>();
			String xml = adao.ultraSrtFcst(vo.getX(), vo.getY());
			Document doc = adao.xmlToDocument(xml);
			fcstList = usdao.fixList(usdao.docToUltraSrtVOList(vo, doc));
			return fcstList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
