package com.kh.model.vo;

public class MBTI {
	private String userId;
	private String userPwd;
	private String userName;
	private String birthDate;
	private String gender;
	private String testDate;
	private String mbti;
	
	public MBTI () {}	
	
	
	public MBTI(String userId, String userPwd, String userName, String birthDate, String gender, String testDate,
			String mbti) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.birthDate = birthDate;
		this.gender = gender;
		this.testDate = testDate;
		this.mbti = mbti;
	}
	

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getMbti() {
		return mbti;
	}
	public void setMbti(String mbti) {
		this.mbti = mbti;
	}
	@Override
	public String toString() {
		return "MBTI [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", birthDate=" + birthDate
				+ ", gender=" + gender + ", testDate=" + testDate + ", mbti=" + mbti + "]";
	}
}
