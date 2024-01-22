package kr.ac.kopo.weather.service;

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
			e.printStackTrace();
			return null;
		}
		return vo; 
	}
	public UltraSrtFNcstVO GPSToGrid(String x, String y) {
		return adao.changeGPSToGrid(x, y);
	}
}
