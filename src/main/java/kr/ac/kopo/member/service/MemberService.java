package kr.ac.kopo.member.service;

import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

public class MemberService {
	private static MemberDAO mdao = new MemberDAO();
	
	public String createSalt() {
		return mdao.createSalt();
	}
	
	public String Hashing(String pw, String salt) {
		return mdao.Hashing(pw.getBytes(), salt);
	}
	
	public boolean join(MemberVO m) {
		return mdao.join(m);
	}
	
}
