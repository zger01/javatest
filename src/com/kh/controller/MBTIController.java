package com.kh.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import com.kh.MemberView.MBTIView;
import com.kh.model.service.MBTIService;
import com.kh.model.vo.MBTI;

public class MBTIController {

	public static MBTI user = new MBTI();
	
	public int idCheck(String userId, String userPwd) {
		
		MBTI m = new MBTIService().idCheck(userId);
		if(m != null) {
			user.setUserId(m.getUserId());
			user.setUserPwd(m.getUserPwd());
			user.setUserName(m.getUserName());
			if(m.getUserId().equals("admin")) {
				return 2;
				}
			else {
				return 1;
			}
		}		
		return 0;		
	}

	public void selectAllUser() {
		
		ArrayList<MBTI> list = new MBTIService().selectAllUser();
		
		// Service or Dao 로부터 전달받은 값에 따라 응답화면 지정
		if(list.isEmpty()) {
			new MBTIView().displayNodata("전체 조회 결과가 없습니다.");
		} else {
			for(MBTI m : list) {
				int age = calculateAge(m.getBirthDate());
				new MBTIView().userInfo(m, age);
			}
		}
	}

	public void deleteUser(String userId) {

		MBTI m = new MBTI();
		
		m.setUserId(userId);
		
		int result = new MBTIService().deleteUser(m);
		
		// Service or Dao 로부터 전달받은 값에 따라 응답화면 지정
		if(result > 0) {
			new MBTIView().displaySuccess("회원 정보 삭제 성공");
		} else {
			new MBTIView().displayFail("회원 정보 삭제 실패");
		}
	}

	public void searchMBTI(String keyword) {
		
		ArrayList<MBTI> list = new MBTIService().searchMBTI(keyword); 
		
		// Service or Dao 로부터 전달받은 값에 따라 응답화면 지정
		if(list.isEmpty()) { // 조회된 회원이 없음
			new MBTIView().displayNodata(keyword + " 에 해당하는 검색 결과가 없습니다.");
		} else { // 조회된 회원이 있음
			for(MBTI m : list) {
				int age = calculateAge(m.getBirthDate());
				new MBTIView().userInfo(m, age);
			}
		}
	}

	public void searchByUserName(String userName) {
		
		MBTI m = new MBTIService().searchByUserName(userName); 
				
		if(m == null) {
			new MBTIView().displayNodata("이름 검색");
		} else {
			int age = calculateAge(m.getBirthDate());
			new MBTIView().userInfo(m, age);
		}
	}

	public void checkMyMBTI() {
		
		String userId = user.getUserId();
		
		MBTI m = new MBTIService().checkMyMBTI(userId);
		int age = calculateAge(m.getBirthDate());
		
		// Service or Dao 로부터 전달받은 값에 따라 응답화면 지정
		if(m == null) { // 조회된 회원이 없음
			new MBTIView().displayNodata(userId + " 에 해당하는 검색 결과가 없습니다.");
		} else { // 조회된 회원이 있음
			new MBTIView().userInfo(m, age);
		}
	}

	public void updateMyPwd(String nPwd) {
		String userId = user.getUserId();
		String userPwd = user.getUserPwd();
		
		int result = new MBTIService().updateMBTItest(userId, nPwd);
		
		// Service or Dao 로부터 전달받은 값에 따라 응답화면 지정
		
		if(result > 0) { // 성공
			new MBTIView().displaySuccess("회원 정보 변경");
		} else { // 실패
			new MBTIView().displayFail("회원 정보 변경");
		}
	}

	public void MBTITest(int no1, int no2, int no3, int no4) {
		
		String userId = user.getUserId();
		String MBTI = "";
		if(no1 == 1) {
			MBTI += "E";
		} else {
			MBTI += "I";
		}
		
		if(no2 == 1) {
			MBTI += "S";
		} else {
			MBTI += "N";
		}
		
		if(no3 == 1) {
			MBTI += "F";
		} else {
			MBTI += "T";
		}
		
		if(no4 == 1) {
			MBTI += "P";
		} else {
			MBTI += "J";
		}
		
		int result = new MBTIService().MBTITest(userId, MBTI);
		
		if(result > 0) { // 성공
			new MBTIView().displaySuccess("MBTI 정보 변경");
		} else { // 실패
			new MBTIView().displayFail("MBTI 정보 변경");
		}
	}

	public void insertUserInfo(String userId, String userPwd, String userName, String birthDate, String gender) {
		
		MBTI m = new MBTI();
		
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setUserName(userName);
		m.setBirthDate(birthDate);
		m.setGender(gender);
				
		int result = new MBTIService().insertUserInfo(m);
		
		// Service or Dao 로부터 전달받은 값에 따라 응답화면 지정
		if(result > 0) {
			new MBTIView().displaySuccess("회원 정보 추가");
		} else {
			new MBTIView().displayFail("회원 가입");
		}
	}

	
	public int calculateAge(String birthDate) {
	
		LocalDate now = LocalDate.now();
        int year = now.getYear();
        String aaa = birthDate.substring(0,4);
        Integer num = Integer.valueOf(aaa) ;
		
		return year - num;
	}
	
}
