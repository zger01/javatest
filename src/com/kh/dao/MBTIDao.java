package com.kh.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.MBTITemplate;
import com.kh.model.vo.MBTI;

public class MBTIDao {

	private Properties prop = new Properties();
	
	public MBTIDao() {		
		try {
			prop.loadFromXML(new FileInputStream("resources/query.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MBTI idCheck(Connection conn, String userId) {
		MBTI m = null;
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
				
		String sql = prop.getProperty("idCheck");
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) { 
				m = new MBTI();
				
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setBirthDate(rset.getString("BIRTH_DATE"));
				m.setGender(rset.getString("GENDER"));
				m.setTestDate(rset.getString("TEST_DATE"));
				m.setMbti(rset.getString("MBTI"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			MBTITemplate.close(rset);
			MBTITemplate.close(pstmt);
		}
		
		return m;
	}

	public ArrayList<MBTI> selectAllUser(Connection conn) {
		ArrayList<MBTI> list = new ArrayList<>();
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
				
		String sql = prop.getProperty("selectAllUser");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) { 
				MBTI m = new MBTI();
				
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setBirthDate(rset.getString("BIRTH_DATE"));
				m.setGender(rset.getString("GENDER"));
				m.setTestDate(rset.getString("TEST_DATE"));
				m.setMbti(rset.getString("MBTI"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			MBTITemplate.close(rset);
			MBTITemplate.close(pstmt);
		}
		
		return list;
	}

	public ArrayList<MBTI> searchMBTI(Connection conn, String keyword) {
		
		ArrayList<MBTI> list = new ArrayList<>();
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
				
		String sql = prop.getProperty("searchMBTI");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) { 
				MBTI m = new MBTI();
				
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setBirthDate(rset.getString("BIRTH_DATE"));
				m.setGender(rset.getString("GENDER"));
				m.setTestDate(rset.getString("TEST_DATE"));
				m.setMbti(rset.getString("MBTI"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			MBTITemplate.close(rset);
			MBTITemplate.close(pstmt);
		}
		
		return list;
	}

	public int deleteUser(Connection conn, MBTI m) {
		int result = 0; 
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("deleteUser");
			
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
						
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			MBTITemplate.close(pstmt);
		}
		
		return result;
	}

	public MBTI searchByUserName(Connection conn, String userName) {
		
		MBTI m = null;
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
				
		String sql = prop.getProperty("searchByUserName");
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			rset = pstmt.executeQuery();
			
			if(rset.next()) { 
				m = new MBTI();
				
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setBirthDate(rset.getString("BIRTH_DATE"));
				m.setGender(rset.getString("GENDER"));
				m.setTestDate(rset.getString("TEST_DATE"));
				m.setMbti(rset.getString("MBTI"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			MBTITemplate.close(rset);
			MBTITemplate.close(pstmt);
		}
		
		return m;
	}

	public MBTI checkMyMBTI(Connection conn, String userId) {
		MBTI m = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
				
		String sql = prop.getProperty("checkMyMBTI");		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				// 조회된 한 행에 대한 데이터 값들을 뽑아서 하나의 Member 객체에 담기
				m = new MBTI(rset.getString("USERID"),
							rset.getString("USERPWD"),
							rset.getString("USERNAME"),
							rset.getString("BIRTH_DATE"),
							rset.getString("GENDER"),
							rset.getString("TEST_DATE"),
							rset.getString("MBTI"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			MBTITemplate.close(rset);
			MBTITemplate.close(pstmt);
		}
		
		return m;
	}

	public int updateMBTItest(Connection conn, String userId, String nPwd) {
		int result = 0;
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("updateMyPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
						
			pstmt.setString(1, nPwd);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			MBTITemplate.close(pstmt);
		}
	
		return result; 
	}

	public int MBTITest(Connection conn, String userId, String MBTI) {
		
		int result = 0;
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("MBTITest");
		
		try {
			pstmt = conn.prepareStatement(sql);
						
			pstmt.setString(1, MBTI);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			MBTITemplate.close(pstmt);
		}
	
		return result; 
	}

	public int insertUserInfo(Connection conn, MBTI m) {
		
		int result = 0; 
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("insertUserInfo");
			
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getBirthDate());
			pstmt.setString(5, m.getGender());
						
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			MBTITemplate.close(pstmt);
		}
		
		return result;
	}

	
}
