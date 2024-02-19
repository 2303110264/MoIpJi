package kr.ac.kopo.member.service;

import java.util.List;

import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

public class MemberService {
	private static MemberDAO mdao = new MemberDAO();
	
	public String createSalt() {
		return mdao.createSalt();
	}
	
	public String Hashing(String pw, String salt) {
		System.out.println(pw+" "+salt);
		return mdao.Hashing(pw.getBytes(), salt);
	}
	
	public boolean join(MemberVO m) {
		return mdao.join(m);
	}
	
	public List<String> allId(){
		return mdao.allId();
	}
	
	public boolean passwordCheck(String id, String pw) {
		return mdao.passwordCheck(id, pw);
	}
	
	public MemberVO login(String id) {
		return mdao.login(id);
	}
	
}
