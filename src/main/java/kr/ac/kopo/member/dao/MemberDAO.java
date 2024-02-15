package kr.ac.kopo.member.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.ConnectionFactory;

public class MemberDAO {
	private StringBuilder sql;
	
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
				m.setJoindate(rs.getString(6));
				m.setAdm(rs.getString(7));
				l.add(m);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	public MemberVO login(String mail, String pw) {
		sql = new StringBuilder();
		sql.append("select id, mail, nickname, gender, birthday, join_date, m_type ");
		sql.append(" from mij_member ");
		sql.append(" where id = ? and pw = ? ");
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
				m.setJoindate(rs.getString(6));
				m.setAdm(rs.getString(7));
			}
			return m;
		}catch(Exception e) {
			return null;
		}
	}
	public boolean join(MemberVO m) {
		sql = new StringBuilder();
		sql.append("insert into mij_member(id, mail, pw, nickname, gender, birthday, m_type)");
		sql.append(" values( ? , ? , ? , ?,  "); // id, mail, pw, nickname,
		sql.append(" ? , ?, '0' ) "); // gender, birth
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getMail());
			pstmt.setString(3, m.getPw());
			if(m.getNickname()!=null) pstmt.setString(4, m.getNickname());
			else pstmt.setNull(4, java.sql.Types.VARCHAR);
			pstmt.setString(5, m.getGender());
			if(m.getNickname()!=null) pstmt.setString(6, m.getBirth());
			else pstmt.setNull(6, java.sql.Types.VARCHAR);

			pstmt.executeUpdate();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
