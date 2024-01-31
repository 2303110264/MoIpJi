package kr.ac.kopo.weather.service;

import java.util.List;

import org.w3c.dom.Document;

import kr.ac.kopo.weather.dao.ApiDAO;
import kr.ac.kopo.weather.dao.UltraSrtFNcstDAO;
import kr.ac.kopo.weather.vo.UltraSrtFNcstVO;

public class WeatherService {
	private UltraSrtFNcstDAO usdao = new UltraSrtFNcstDAO();
	private ApiDAO adao = new ApiDAO();
	
	public UltraSrtFNcstVO getUltraSrtNcst(String x, String y) {
		try {
			UltraSrtFNcstVO vo = adao.changeGPSToGrid(x, y);
			String xml = adao.ultraSrtNcst(vo.getX(), vo.getY());
			Document doc = adao.xmlToDocument(xml);
			vo = usdao.fix(usdao.docToUltraSrtVO(vo, doc));
			return vo; 
		}catch(Exception e) {
			System.out.println("오류 1회 출력: 정상");
			return null;
		}
	}
	public List<UltraSrtFNcstVO> getUltraSrtFcst(String x, String y){
		try {
			UltraSrtFNcstVO vo = adao.changeGPSToGrid(x, y);
			String xml = adao.ultraSrtFcst(vo.getX(), vo.getY());
			Document doc = adao.xmlToDocument(xml);
			List<UltraSrtFNcstVO> flist = usdao.docToUltraSrtVOList(vo, doc);
			return flist;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String getLocation(String x, String y) {
		return adao.XYToAdress(x, y);
	}
}
