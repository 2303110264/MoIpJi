package kr.ac.kopo.member.dao;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.ConnectionFactory;

public class MemberDAO {
	private StringBuilder sql;
	//Simple
	//byte[] byteArray = string.getBytes();
	//String string = new String(byteArray);
	//디버깅용 로그 남길 때 사용 
	private String byteToString(byte[] temp){ //byte to string
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}
	public String createSalt() {
		SecureRandom rnd = new SecureRandom();
		byte[] temp = new byte[16];
		rnd.nextBytes(temp);
		return byteToString(temp);
	}
	
	public String Hashing(byte[] password, String Salt) {
		
		// SHA-256 해시함수를 사용 
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			for(int i = 0; i < 10000; i++) {
				String temp = byteToString(password) + Salt;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성 
				md.update(temp.getBytes());						// temp 의 문자열을 해싱하여 md 에 저장해둔다 
				password = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다 
			}
			
			return byteToString(password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberVO.Hashing(byte[], String) Error");
			return null;
		}
 
		// key-stretching
	}
	
	public List<String> allId(){
		List<String> l = new ArrayList<>();
		sql = new StringBuilder();
		sql.append("select * ");
		sql.append(" from mij_member ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				l.add(rs.getString("id"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	public List<MemberVO> allUser(){
		List<MemberVO> l = new ArrayList<>();
		sql = new StringBuilder();
		sql.append("select id, case when mail=null then '-' end mail, ");
		sql.append(" case when nickname=null then '-' end nickname, gender, ");
		sql.append(" case when birthday=null then '-' end birthday, to_char(join_date, 'yyyy-MM-dd') join_date, ");
		sql.append(" case when m_type='0' then '사용자' when m_type='1' then '관리자' end m_type ");
		sql.append(" from mij_member ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//id, mail, pw, nickname, gender, birth, adm
				MemberVO m = new MemberVO();
				m.setId(rs.getString(1)); //id
				m.setMail(rs.getString(2));	
				m.setNickname(rs.getString(3));
				m.setGender(rs.getString(4));
				m.setBirth(rs.getString(5));
				m.setJoinDate(rs.getString(6));
				m.setMType(rs.getString(7));
				l.add(m);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	public boolean loginTry(String id, String pw) {
		
		return false;
	}
	public MemberVO loginSuccess(String mail, String pw) {
		sql = new StringBuilder();
		sql.append("select id, mail, nickname, gender, birthday, join_date, m_type, salt ");
		sql.append(" from mij_member ");
		sql.append(" where id = ? ");
		MemberVO m = new MemberVO();

		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, mail);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setId(rs.getString(1));
				m.setMail(rs.getString(2));
				m.setNickname(rs.getString(3));
				m.setGender(rs.getString(4));
				m.setBirth(rs.getString(5));
				m.setJoinDate(rs.getString(6));
				m.setMType(rs.getString(7));
				m.setSalt(rs.getString(8));
			}
			return m;
		}catch(Exception e) {
			return null;
		}
	}
	public boolean join(MemberVO m) {
		sql = new StringBuilder();
		sql.append("insert into mij_member(id, mail, pw, nickname, gender, birthday, m_type, salt)");
		sql.append(" values( ? , ? , ? , ?,  "); // id, mail, pw, nickname,
		sql.append(" ? , ?, '0', ? ) "); // gender, birth
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			String salt = createSalt();
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getMail());
			pstmt.setString(3, m.getPw());
			if(m.getNickname()!=null) pstmt.setString(4, m.getNickname());
			else pstmt.setNull(4, java.sql.Types.VARCHAR);
			pstmt.setString(5, m.getGender());
			if(m.getNickname()!=null) pstmt.setString(6, m.getBirth());
			else pstmt.setNull(6, java.sql.Types.VARCHAR);
			pstmt.setString(7, salt);

			pstmt.executeUpdate();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
