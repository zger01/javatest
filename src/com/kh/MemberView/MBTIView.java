package com.kh.MemberView;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MBTIController;
import com.kh.model.vo.MBTI;

public class MBTIView {

	Scanner sc  = new Scanner(System.in);
	MBTIController mc = new MBTIController();

	public void mainMenu() {

		skipView();
		asciiArt();
		enterView();
		skipView();
		while(true) {
			asciiArt2();
			menuView("로그인 화면");
			System.out.print("아이디 입력 하세요 : ");
			String userId = sc.nextLine();

			System.out.print("비밀번호를 입력하세요 : ");
			String userPwd = sc.nextLine();


			int result = mc.idCheck(userId, userPwd);

			skipView();
			if(result == 2) {
				// 관리자 메인 화면 메소드 호출
				adminMenu();

			} else if(result == 1) {
				// 로그인 성공(일반회원 메뉴)
				userMenu();

			} else {
				// 로그인 실패 & 회원 가입 선택 화면
				checkSignUp();
				enterView();
				skipView();
			}
		}
	}
	// --------------------------- 메인 화면 ------------------------------------------------

	// --------------------어드민 -----------------------------
	public void adminMenu() {

		// 1. selectAllUser()	<-  전체 회원 정보 조회
		// 2. deleteUser()		<-  String 타입으로 USERID, USERPDW 받아서 DELETE
		// 3. searchMBTI()		<-	String 타입으로 받고, MBTI LIKE ?  			ArrayList
		// 4. searchByUserName()<- 	String 타입으로 USERNAME 검색해서 MBTI 조회		MBTI 하나로
		while(true) {
			asciiArt2();
			menuView("관리자 화면");
			System.out.println("1. 전체 회원 정보 조회");
			System.out.println("2. 회원정보 삭제");
			System.out.println("3. 유형별 MBTI 결과 조회");
			System.out.println("4. 유저별 MBTI 결과 조회");
			System.out.println("5. 프로그램 종료");

			int select = sc.nextInt();
			sc.nextLine();

			switch(select) {
			case 1 : selectAllUser(); break;
			case 2 : deleteUser(); break;
			case 3 : searchMBTI(); break;
			case 4 : searchByUserName(); break;
			case 5 : System.out.println("프로그램을 종료합니다."); System.exit(0); return;
			default : System.out.println("올바른 번호를 입력해 주세요.");
			}
		}

	}


	// 유저화면
	public void userMenu() {

		// 1. MBTITest()		<-  UPDATE , String 타입(아이디, MBTI)체크 후  MBTI로 전달 
		// 2. checkMyMBTI()		<-  SELECT , String 타입(USERNAME, MBTI, TEST_DATE) 	 MBTI 하나로
		// 3. updateMyPwd()		<-  UPDATE , USERID 입력받아서 USERPWD 새로운 값으로 변경  
		while(true) {
			asciiArt2();
			menuView("유저 메뉴");
			loginUser();
			System.out.println("1. MBTI 테스트 하기 ");
			System.out.println("2. 내 MBTI 확인 하기 ");
			System.out.println("3. 내 비밀번호 수정");
			System.out.println("4. 프로그램 종료");

			int select = sc.nextInt();
			sc.nextLine();

			switch(select) {
			case 1 : MBTITest(); break; 
			case 2 : checkMyMBTI(); break; 
			case 3 : updateMyPwd(); break;
			case 4 : System.out.println("프로그램을 종료합니다."); System.exit(0); return;
			default : System.out.println("올바른 번호를 입력해주세요.");
			}
		}

	}

	// 회원가입 확인 및 회원가입
	public void checkSignUp() {

		// Y
		// 1. insertUserInfo()  <- INSERT 아이디, 비번, 이름, 생년월일  입력 받기
		while(true) {
			asciiArt2();
			System.out.print("일치하는 회원 정보가 없습니다. \n회원 가입 하시겠습니까? (Y/N) : ");
			char choice = sc.nextLine().toUpperCase().charAt(0);

			if(choice == 'N') {
				System.out.println("프로그램을 종료합니다. ");
				System.exit(0);
				return;
			} else if (choice == 'Y') {
				insertUserInfo();
				break;
			} else {
				System.out.println("올바른 번호를 입력해주세요.");
			}
		}
	}


	// ==================== 어드민 화면 메소드 ===================
	private void selectAllUser() {
		skipView();
		asciiArt2();
		menuView("전체 회원 정보");
		loginUser();
		mc.selectAllUser();
		enterView();
		skipView();
	}

	public void deleteUser() {

		skipView();
		asciiArt2();
		menuView("유저 삭제");
		loginUser();
		System.out.print("삭제할 사용자의 아이디를 입력해주세요 : ");
		String userId = sc.nextLine();

		mc.deleteUser(userId);
		enterView();
		skipView();
	}

	public void searchMBTI() {

		skipView();
		asciiArt2();
		menuView("MBTI 키워드 검색");
		loginUser();
		System.out.print("조회할 MBTI 유형을 입력해주세요(키워드로 입력) : ");
		String keyword = sc.nextLine().toUpperCase();

		mc.searchMBTI(keyword);
		enterView();
		skipView();
	}

	public void searchByUserName() {

		skipView();
		asciiArt2();
		menuView("유저별 MBTI 결과 조회");
		loginUser();
		System.out.print("MBTI 유형을 조회할 사용자의 이름을 입력하세요 : ");
		String userName = sc.nextLine();

		mc.searchByUserName(userName);
		enterView();
		skipView();
	}

	// ============================================== 유저 메뉴 ===========================================

	public void MBTITest() {

		skipView();
		menuView("MBTI 테스트 하기");
		loginUser();
		asciiTest1();
		System.out.print("1. 혼자 여행을 가기로 했다. 그 숙소는 ? \n\n"
				+ "1) 사람들과 함께 묵을수 있는 게스트하우스\n"
				+ "2) 나 혼자만의 공간인 호텔\n");
		int no1 = sc.nextInt();
		sc.nextLine();
		skipView();
		menuView("MBTI 테스트 하기");
		loginUser();
		asciiTest2();
		System.out.print("2. 나에게 5000만원이 생겼다. 내가 투자할 주식 종목은?\n\n"
				+ "1) 안전이 제일!코카콜라\n"
				+ "2) 조만간 자동차는 전부 전기차로 바뀔거야! 전기차\n");
		int no2 = sc.nextInt();
		sc.nextLine();
		skipView();
		menuView("MBTI 테스트 하기");
		loginUser();
		asciiTest3();
		System.out.print("비도 오고 마음도 우중충하고 심심하다 그럴때 나는?\n\n"
				+ "1) 집에서 혼술과 TV를 본다\n"
				+ "2) 아무나 불러서 술먹고 논다\n");
		int no3 = sc.nextInt();
		sc.nextLine();
		skipView();
		menuView("MBTI 테스트 하기");
		loginUser();
		asciiTest4();
		System.out.print("친구가 갑자기 여행을 가자고 한다. 나의 행동은?\n\n"
				+ "1) 오케이! 여행은 즉흥이지!\n"
				+ "2) 어디가는데? 비용은? 준비물은??\n");
		int no4 = sc.nextInt();
		sc.nextLine();
		skipView();
		asciiArt2();
		menuView("MBTI 테스트 하기");
		loginUser();
		mc.MBTITest(no1, no2, no3, no4);		
		enterView();
		skipView();
	}

	public void checkMyMBTI() {

		skipView();
		asciiArt2();
		menuView("내 MBTI 결과 조회");
		loginUser();
		mc.checkMyMBTI();
		enterView();
		skipView();

	}

	public void updateMyPwd() {

		skipView();
		asciiArt2();
		menuView("내 정보 수정");
		loginUser();
		System.out.print("변경할 비밀번호 : ");
		String nPwd = sc.nextLine();

		mc.updateMyPwd(nPwd);
		enterView();
		skipView();
	}

	// ======================= 회원가입=========================

	private void insertUserInfo() {

		skipView();
		asciiArt2();
		menuView("회원 가입");
		System.out.print("사용자 아이디 : ");
		String userId = sc.nextLine();

		System.out.print("사용자 비밀번호 : ");
		String userPwd = sc.nextLine();

		System.out.print("사용자 이름 : ");
		String userName = sc.nextLine();

		System.out.print("생년월일 6글자 입력 : ");
		String birthDate = sc.nextLine();

		System.out.print("성별 입력 (M/F) : ");
		String gender = sc.nextLine().toUpperCase();

		mc.insertUserInfo(userId,userPwd,userName,birthDate,gender);

	}


	// ==================== 화면 출력용 =========================

	public void userInfo(MBTI m, int age) {
		System.out.println("아이디 : " + m.getUserId()
		+ ", 비밀번호 : " + m.getUserPwd() 
		+ ", 이름 : " + m.getUserName()
		+ ", 나이 : " + age
		+ ", MBTI : " + m.getMbti()
				);
	}

	public void displaySuccess(String message) {
		System.out.println(message + "성공!");
	}

	public void displayFail(String message) {
		System.out.println(message + "실패");
	}

	public void displayList(ArrayList<MBTI> m) {
		System.out.println("---- 목록 조회 결과 ----\n");
		for(MBTI mm : m) {
			System.out.println(mm);
		}
	}

	public void displayNodata(String message) {
		System.out.println(message + "결과가 없습니다. \n");
	}

	public void displayOne(MBTI m) {
		// 필요에 따라 출력 문구 수정 
		System.out.println(m);
	}



	// ==================== 화면 꾸미기용 =============================

	public void menuView(String message) {

		int len1 = 60;
		int len2 = message.length();		
		int len3 = (len1 - len2) / 2;

		String len = "";

		for(int i = 0; i < len3; i++)
			len += "=";

		System.out.println(len + " " + message + " " + len + "\n");

	}

	public void loginUser() {

		System.out.println("현재 로그인 중인 사용자 : " + MBTIController.user.getUserName() + "\n");
	}

	public void enterView() {
		System.out.println();
		System.out.print("                     PRESS ENTER KEY ");
		String enterView = sc.nextLine();

	}

	public void skipView() {

		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public void asciiArt() {
		System.out.println("\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡖⠒⢲⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣇⣀⡼⠁⠀⠀⠀⠀⠀⠀⢠⡞⠉⠓⠀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡏⢦⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⢠⠀⠀⠀⠀⠑⠦⣄⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠘⣆⢸⠀⠀⠀⠀⠠⡏⠉⠙⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⢠⡀⠀⣸⠃⠀⠀⠀⢸⠒⠒⠂⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⡖⠒⠒⠂⠀⠀⠀⠀⠀⠇⠀⠘⠾⠀⠀⠀⠀⢸⣿⡷⢤⣾⣿⢸⡟⠻⣦⠚⢻⣿⠛⢻⣿⠀⠀⠀⠈⠉⠁⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⡧⠤⠤⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣷⣼⡟⣿⢸⡟⠛⣷⠀⢸⣿⠀⠘⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠿⠸⠿⠀⠿⠸⠷⠶⠟⠀⠸⠿⠀⠀⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠟⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠯⠷⠿⠹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠸⠈⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠹⠿⠹⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠗⠌⠉⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⢀⣠⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⣾⠛⢋⣻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⠿⣧⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠘⠋⠛⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⢐⣿⠛⣝⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠩⢂⡉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠃⢀⣾⡄⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⢀⣀⣤⣾⣶⣽⣶⣤⣄⡀⠀⠀⠀⠀⠀⠀⠙⣿⣟⣋⡁⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⠓⠂⣷⣤⣀⠀⠀⠀⠀⠀⠀⠀⣸⣿⠛⠹⣿⣇⡀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⢀⣶⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⣴⣿⣿⣿⠀⠀⣿⣿⣿⣷⡀⠀⠀⠀⠀⣾⣿⣿⣶⣶⣿⣿⣿⡀⠀⠀⠀\r\n" + 
				"⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⣿⣿⣿⠀⢠⠀⣿⣿⣿⣿⡇⠀⠀⠀⠀⣿⣿⡿⢈⡋⠉⣿⣿⣇⠀⠀⠀\r\n" + 
				"⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⡇⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⣿⣿⣿⠂⠙⠡⡟⢻⣿⣿⣿⠀⠀⠀⠀⢿⣿⣷⣿⣇⣴⡻⣿⣿⠀⠀⠀\r\n" + 
				"⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⠁⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⣿⣿⣧⣶⡇⠀⣶⣤⣿⣿⣿⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⡿⠟⠀⠀⠀\r\n" + 
				"⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⠋⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠻⠿⣿⣿⡇⠀⣿⣿⣿⡏⠉⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⢨⣿⣿⣿⣿⣿⣿⣏⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⢸⣿⣿⡇⠀⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀\r\n" + 
				"");

	}

	public void asciiArt2() {
		System.out.println("\r\n" + 
				"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n" + 
				"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n" + 
				"⣿⣿⣿⣿⣿⣿⡟⠉⠉⠙⢻⣿⣿⣿⣿⣿⣿⣿⡿⠋⠉⠉⢻⣿⡏⠉⠉⠉⠉⠉⠛⠛⣿⣿⣿⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⢹⠋⠉⠉⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n" + 
				"⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⢸⣿⡇⠀⠀⢠⣤⣤⣄⠀⠀⠀⢹⣿⣤⣤⣤⡄⠀⠀⢀⣤⣤⣤⣼⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇\r\n" + 
				"⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠈⢿⣿⣿⣿⡿⠁⠀⠀⠀⠀⢸⣿⡇⠀⠀⢸⣿⣿⣿⡇⠀⠀⢨⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿⣿⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇\r\n" + 
				"⣿⣿⣿⣿⣿⣿⡇⠀⠀⢸⡄⠀⠈⢿⣿⡿⠁⠀⢠⡇⠀⠀⢸⣿⡇⠀⠀⠘⠛⠛⠋⠀⠀⣴⣿⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿⣿⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇\r\n" + 
				"⣿⣿⣿⣿⣿⣿⡇⠀⠀⢸⣿⡄⠀⠈⣿⠁⠀⢠⣿⡇⠀⠀⢸⣿⡇⠀⠀⢠⣤⣤⣤⡀⠀⠈⠹⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿⣿⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇\r\n" + 
				"⣿⣿⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⡄⠀⠀⠀⢠⣿⣿⡇⠀⠀⢸⣿⡇⠀⠀⢸⣿⣿⣿⣿⠀⠀⠀⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿⣿⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇\r\n" + 
				"⣿⣿⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿⣄⠀⣠⣿⣿⣿⡇⠀⠀⢸⣿⡇⠀⠀⠈⠉⠉⠉⠀⠀⠀⣰⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿⣿⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇\r\n" + 
				"⣿⣿⣿⣿⣿⣿⣧⣤⣤⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣤⣤⣼⣿⣧⣤⣤⣤⣤⣤⣤⣤⣿⣿⣿⣿⣿⣿⣧⣤⣤⣼⣿⣿⣿⣿⣤⣤⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n" + 
				"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n" + 
				"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n" + 
				"");

	}

	public void asciiTest1() {

		System.out.println("          ___   ____\r\n" + 
				"        /' --;^/ ,-_\\     \\ | /\r\n" + 
				"       / / --o\\ o-\\ \\\\   --(_)--\r\n" + 
				"      /-/-/|o|-|\\-\\\\|\\\\   / | \\\r\n" + 
				"       '`  ` |-|   `` '\r\n" + 
				"             |-|\r\n" + 
				"             |-|O\r\n" + 
				"             |-(\\,__\r\n" + 
				"          ...|-|\\--,\\_....\r\n" + 
				"      ,;;;;;;;;;;;;;;;;;;;;;;;;,.\r\n" + 
				"~~,;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;,~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\r\n" + 
				"~;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;,  ______   ---------   _____     ------\n");
	}

	public void asciiTest2() {

		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣦⣤⣤⣤⣿⣿⣶⣤⣤⣶⣿⡿⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣶⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠧⣜⢿⣿⠉⢻⣿⢿⣿⣿⡇⢠⣭⣿⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⢿⣿⡄⣺⣿⣤⣿⣿⣿⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣻⣿⣿⣛⣿⣿⣾⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣟⣿⣿⣟⣻⣿⣿⣾⣿⣿⣃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⢿⠿⠟⠿⣿⢿⡉⠉⢽⡗⣶⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠞⠋⢉⠟⠀⠀⠀⠀⠀⠈⠀⠙⢄⠀⠉⢿⣇⠏⡵⢦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡶⣿⣦⠖⠀⠀⠀⠀⠀⢰⣶⣤⡄⠀⠀⠀⠀⠀⠀⠉⠺⢷⠎⣸⣻⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⢀⣴⣾⣷⣿⡟⠁⠀⢀⣴⣾⣯⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⡡⢊⡼⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⣴⢏⢗⢸⠟⠃⠀⠀⢠⣾⣿⣿⡿⢿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠸⡗⣩⠞⡔⣹⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⣠⠞⣷⢸⡸⠈⠀⠀⠀⠀⢸⣿⣿⡏⠀⣸⣿⣿⡏⠙⢿⡿⠛⠀⠀⠀⠀⠀⠀⠀⠀⠨⠵⣪⠜⡡⣻⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⢀⡾⢡⣶⡟⠋⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣶⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠚⣡⢞⡕⢡⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⡞⠀⠈⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣿⣿⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡵⢋⠔⡿⢡⣿⡀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⣼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⣣⠞⣴⣻⣿⡇⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣴⣧⡀⠀⣿⣿⣿⠀⠹⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡔⣡⣾⢟⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣷⣤⣿⣿⣿⣀⣠⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣬⣾⢟⣵⣿⡿⣻⡇⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⢞⣥⣿⣿⢟⡕⣹⠁⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠈⣆⢻⣠⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⣿⣿⣿⠛⠋⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⢟⡻⢟⣽⡿⣋⢴⡟⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠸⡌⢿⣧⡄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣇⡠⠊⡠⢊⠔⡻⢫⠞⣡⣿⣤⣤⣤⣀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠹⣄⠛⣷⣷⣤⣄⠀⠀⠀⠀⠀⢀⣄⡴⣢⢖⡰⢂⠔⣠⠖⣰⣦⢆⣴⣲⣿⢞⣶⠞⡴⢡⢞⠔⣡⣏⣾⣿⣿⣿⣿⣿⣿⣿⣶⣤⣄\r\n" + 
				"⠀⠀⠀⠈⠳⣌⠙⢿⣿⢸⣿⣷⣶⣿⣿⡾⡿⣵⢋⠔⠵⢊⡵⢮⠟⡵⣯⢞⡵⣣⡿⢃⠞⡴⡱⢋⣾⣟⣯⣭⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿\r\n" + 
				"⠀⠀⠀⠀⠀⠿⢷⣦⣭⣭⣭⣥⣭⣤⣤⣤⣤⣤⣤⣤⣶⣞⡒⠒⠲⠃⠌⣹⢿⡿⣷⣣⣞⣴⣟⣛⣒⣺⣿⣿⣿⣟⣷⣶⣶⠦⠤⠉⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠈⠉⠹⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣤⣤⣼⣿⡟⠛⠛⠳⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⡭⠿⠿⢿⣿⣶⣶⣞⣃⣀⣀⣀⡚⠛⠛⠛⠉⠉⠉⠉⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n");
	}

	public void asciiTest3() {

		System.out.println(
				"⣿⣿⣿⣿⣿⣿⣿⡟⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⢻⣿⣿⣿⣿⣿⣿⣿\r\n" + 
						"⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⠙⠻⣿⣿⣿⣿⣿⣿⠟⠋⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿\r\n" + 
						"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⠟⠋⠉⠉⠙⠻⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\r\n" + 
						"⣿⣿⣿⠿⠿⠿⠿⠿⠿⠿⠿⠿⠶⠶⠶⠶⠶⠶⠿⠿⠿⠿⠿⠿⠿⠿⠿⣿⣿⣿\r\n" + 
						"⣿⣿⡇⠀⣠⣤⠤⠤⠤⠤⠤⠤⠤⠤⠤⠤⠤⠤⣤⣤⣤⣤⡀⢀⣤⣀⠀⢸⣿⣿\r\n" + 
						"⣿⣿⡇⢰⡟⠁⠀⠀⠀⠀⠀⣀⣤⣴⣶⣾⣿⣿⣿⣿⣿⣿⡇⠸⣿⣿⠂⢸⣿⣿\r\n" + 
						"⣿⣿⡇⢸⡇⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⢸⣿⣿\r\n" + 
						"⣿⣿⡇⢸⡇⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⢸⣿⣿\r\n" + 
						"⣿⣿⡇⢸⡇⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢀⣀⣀⠀⢸⣿⣿\r\n" + 
						"⣿⣿⡇⢸⣇⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢈⣉⣉⠁⢸⣿⣿\r\n" + 
						"⣿⣿⡇⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢈⣉⣉⡁⢸⣿⣿\r\n" + 
						"⣿⣿⡇⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠃⠈⠉⠉⠀⢸⣿⣿\r\n" + 
						"⣿⣿⣧⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣼⣿⣿\r\n" + 
				"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n");
	}

	public void asciiTest4() {

		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣄⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⣠⠤⣤⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⠶⠛⠛⢉⣽⠿⠛⣋⣉⣭⠭⠿⠓⠶⣄⡀\r\n" + 
				"⣠⡴⠋⠁⠀⠀⠀⠉⠉⠛⠒⠲⠤⣤⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⢴⠚⠋⣽⠟⠲⡄⠀⠈⠳⠚⠉⣉⣤⠤⠤⠤⠤⢄⣸⡇\r\n" + 
				"⠛⠦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢉⣹⢿⣶⠦⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠚⠉⣼⠞⢢⡀⠘⠷⠖⠃⣀⡤⠴⠚⠉⠁⠀⠀⠀⠀⠀⠀⣸⠧\r\n" + 
				"⠀⠀⠈⠙⠶⣄⡀⠀⠀⠀⠀⠀⣠⠶⠛⠉⢻⣄⣈⡇⢀⣀⣽⠿⣗⠶⠤⣄⣴⠚⠉⣶⠞⢲⠀⠘⠷⠚⢁⣀⠴⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠋⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠈⠙⠲⣄⡀⠀⣾⠁⠀⠀⣀⡤⣾⡿⠟⠉⠘⢧⣀⣘⡷⠀⠀⠈⠉⣳⣾⠟⠉⢀⣠⠴⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠁⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠳⣾⣗⠚⠋⠁⣼⠋⠀⠀⠀⢀⣠⠟⠉⠀⣀⡤⠖⠋⣁⣤⠶⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⢀⣤⣄⡀⠀⠀⠀⠀⠉⠛⢶⣤⡛⠲⠶⠒⠋⢉⣀⡴⠖⢋⣁⡤⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠚⠁⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⢸⡇⠀⠉⠳⢤⡀⠀⢀⣠⠶⣿⣙⡳⢦⡴⠞⢋⣡⡴⠖⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠴⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠘⣇⠀⠀⠀⠀⣩⠖⣿⣗⠀⠿⠼⢃⣠⡴⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣶⣞⣯⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⣠⠤⠤⠤⠼⣤⣤⣴⣊⡀⠀⠛⣾⣡⡴⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⠴⣲⠿⠛⠉⠹⣧⣼⣏⠳⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠳⣤⣀⠀⠀⠀⠀⣀⣴⣋⠴⠞⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡤⠴⠖⠛⠙⠣⣄⠐⣯⣀⣀⣤⣴⠿⠛⣟⠙⡏⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠉⠙⠛⣿⡿⠛⠉⠀⠀⠀⢀⣀⣀⣀⣀⣤⠤⠴⠶⠚⠋⠉⠀⠀⠀⠀⠀⠀⠀⠈⠙⠷⣄⣀⣿⣁⣀⣤⠼⠛⠁⠀⠙⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠈⠉⠋⠉⠉⠙⠿⣍⡉⠉⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣤⣀⠀⢀⣀⣠⠤⠴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠓⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" 
				);

	}

}
