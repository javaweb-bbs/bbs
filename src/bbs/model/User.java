package bbs.model;

public class User {

	private int userId;
	private String userName;
	private String passWord;
	private int sex;
	private int isAdmin;
	
	public User() {
		super();
	}
	
	public User(String userName, String passWord, int isAdmin) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.isAdmin = isAdmin;
	}

	public User(int userId, String userName, String passWord, int sex, int isAdmin) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.sex = sex;
		this.isAdmin = isAdmin;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
