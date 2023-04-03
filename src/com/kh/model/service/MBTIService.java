package com.kh.model.service;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.MBTITemplate;
import com.kh.dao.MBTIDao;
import com.kh.model.vo.MBTI;

public class MBTIService {

	public MBTI idCheck(String userId) {
		
		Connection conn = MBTITemplate.getConnection();	
		MBTI m = new MBTIDao().idCheck(conn, userId); 
				
		MBTITemplate.close(conn);
		
		return m;
	}

	public ArrayList<MBTI> selectAllUser() {
		Connection conn = MBTITemplate.getConnection();		
		ArrayList<MBTI> list = new MBTIDao().selectAllUser(conn);
		
		MBTITemplate.close(conn);
		
		return list;
	}

	public int deleteUser(MBTI m) {
		Connection conn = MBTITemplate.getConnection();
		
		int result = new MBTIDao().deleteUser(conn, m); 
			
		if(result > 0) { 
			MBTITemplate.commit(conn);
		} else {
			MBTITemplate.rollback(conn);
		}
		
		MBTITemplate.close(conn);
		
		return result;
	}

	public ArrayList<MBTI> searchMBTI(String keyword) {
		Connection conn = MBTITemplate.getConnection();		
		ArrayList<MBTI> list = new MBTIDao().searchMBTI(conn, keyword);
		
		MBTITemplate.close(conn);
		
		return list;
	}

	public MBTI searchByUserName(String userName) {
		
		Connection conn = MBTITemplate.getConnection();		
		MBTI m = new MBTIDao().searchByUserName(conn, userName);
		
		MBTITemplate.close(conn);
		
		return m;
	}

	public MBTI checkMyMBTI(String userId) {

		Connection conn = MBTITemplate.getConnection();		
		MBTI m = new MBTIDao().checkMyMBTI(conn,userId);
		
		MBTITemplate.close(conn);
		
		return m;
		
	}

	public int updateMBTItest(String userId, String nPwd) {
		
		Connection conn = MBTITemplate.getConnection();		
		int result = new MBTIDao().updateMBTItest(conn, userId, nPwd); 
			
		if(result > 0) { 
			MBTITemplate.commit(conn);
		} else {
			MBTITemplate.rollback(conn);
		}
		
		MBTITemplate.close(conn);
		
		return result;
	}

	public int MBTITest(String userId, String MBTI) {

		Connection conn = MBTITemplate.getConnection();		
		int result = new MBTIDao().MBTITest(conn,userId, MBTI); 
			
		if(result > 0) { 
			MBTITemplate.commit(conn);
		} else {
			MBTITemplate.rollback(conn);
		}
		
		MBTITemplate.close(conn);
		
		return result;		
	}

	public int insertUserInfo(MBTI m) {
		
		Connection conn = MBTITemplate.getConnection();
		
		int result = new MBTIDao().insertUserInfo(conn, m); 
			
		if(result > 0) { 
			MBTITemplate.commit(conn);
		} else {
			MBTITemplate.rollback(conn);
		}
		
		MBTITemplate.close(conn);
		
		return result;
	}


}
