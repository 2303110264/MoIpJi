package kr.ac.kopo.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCTestMain {
	public static void main(String[] args) {
		StringBuilder sql;
		List<String> l = new ArrayList<>();
		sql = new StringBuilder();
		sql.append("select * ");
		sql.append(" from ms_userlist ");
		
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
		for(String a:l) {
			System.out.println(a);
		}
	}

}
