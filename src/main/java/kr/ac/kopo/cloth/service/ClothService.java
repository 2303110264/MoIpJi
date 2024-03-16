package kr.ac.kopo.cloth.service;

import kr.ac.kopo.cloth.dao.ClothDAO;
import kr.ac.kopo.cloth.vo.ClothVO;

public class ClothService {
	private ClothDAO cdao = new ClothDAO();
	
	public boolean addCloth(ClothVO c) {
		return cdao.addCloth(c);
	}
}
