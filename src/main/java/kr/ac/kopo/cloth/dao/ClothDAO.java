package kr.ac.kopo.cloth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.cloth.vo.ClothVO;
import kr.ac.kopo.util.ConnectionFactory;

public class ClothDAO {
	/*
		private int cNum; // c_num
		private String cName; //c_name
		private String cCategory;
		private String cAddId; 
		private int cWarmPoint;
	 */
	private StringBuilder sql;
	
	public boolean addCloth(ClothVO c) {
		sql = new StringBuilder();
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			
			sql.append(" INSERT INTO MIJ_ClOSET VALUES(c_num_seq.nextval, ?, ?, ?, ?, ?) ");
			pstmt.setString(1, c.getcName());
			pstmt.setString(2, c.getcCategory());
			pstmt.setString(3, c.getcAddId());
			pstmt.setInt(4, c.getCwarmPoint());
			
			pstmt.executeQuery();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<ClothVO> allCloth(String category){
		List<ClothVO> closet = new ArrayList<>();
		sql = new StringBuilder();
		sql.append(" SELECT * FROM MIJ_CLOSET where c_category = ? ");
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
			pstmt.setString(1, category);
			ResultSet rs = pstmt.executeQuery();
			ClothVO c = new ClothVO();
			
			while(rs.next()) {
				c.setcNum(rs.getInt(1));
				c.setcName(rs.getString(2));
				c.setcCategory(rs.getString(3));
				c.setcAddId(rs.getString(4));
				c.setCwarmPoint(rs.getInt(5));
				
				closet.add(c);
			}
			
			return closet;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<ClothVO> onlyHat(){
		return null;
	}
	
	
}
