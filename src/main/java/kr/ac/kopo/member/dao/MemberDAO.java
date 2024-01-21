package kr.ac.kopo.member.dao;
/**
 * 

create table moipji_userlist(
    id varchar2(15) primary key
    , pw varchar2(20) not null
    , mail varchar2(40) unique
    , nickname varchar2(21)
    , birth date
    , joindate date default sysdate
    , gender varchar2(6)
    , adm varchar2(1)
);

insert into moipji_userlist(id, pw, mail, nickname, birth, gender, adm)
values('daquarter', '1234', '2303110264@office.kopo.ac.kr', '관리자','1996-07-23', 'Female', '1')
;

create SEQUENCE mij_user_no;

create table ms_board(
    no number(8) default mij_user_no.nextval primary key
    , title varchar(200) not null
    , writer varchar(15) not null
    , FOREIGN key (writer) REFERENCES moipji_userlist(id)
    , content varchar(2000)
    , view_cnt number(8) default 0
    , reg_date date default sysdate
);


 */
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
		sql.append(" from moipji_userlist ");
		
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
		sql.append(" case when birth=null then '-' end birth, to_char(joindate, 'yyyy-MM-dd') joindate, ");
		sql.append(" case when adm='0' then '사용자' when adm='1' then '관리자' end adm ");
		sql.append(" from moipji_userlist ");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				//id, mail, pw, nickname, gender, birth, adm
				MemberVO m = new MemberVO();
				m.setId(rs.getString("id"));
				m.setNickname(rs.getString("nickname"));
				m.setGender(rs.getString("gender"));
				m.setBirth(rs.getString("birth"));
				m.setAdm(rs.getString("adm"));
				m.setJoindate(rs.getString("joindate"));
				l.add(m);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return l;
	}
	public MemberVO login(String mail, String pw) {
		sql = new StringBuilder();
		sql.append("select * ");
		sql.append(" from moipji_userlist ");
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
				m.setId(rs.getString("id"));
				m.setMail(rs.getString("mail"));
				m.setNickname(rs.getString("nickname"));
				m.setGender(rs.getString("gender"));
				m.setBirth(rs.getString("birth"));
				m.setJoindate(rs.getString("joindate"));
				m.setAdm(rs.getString("adm"));
			}
			return m;
		}catch(Exception e) {
			return null;
		}
	}
	public boolean join(MemberVO m) {
		sql = new StringBuilder();
		sql.append("insert into moipji_userlist(id, mail, pw, nickname, gender, birth, adm)");
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
