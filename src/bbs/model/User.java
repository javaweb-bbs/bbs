package bbs.model;

/**
 * 用户实体对象
 * @author 王k
 *
 */
public class User {

	private int userId;
	private String userName;
	private String passWord;
	private int sex;
	private String email;
	private Boolean isAdmin;
	
	public User() {
		super();
	}
	
	public User(int userId, String passWord) {
		super();
		this.userId = userId;
		this.passWord = passWord;
	}

	public User(int userId, int sex, String email) {
		super();
		this.userId = userId;
		this.sex = sex;
		this.email = email;
	}

	public User(String userName, String passWord, Boolean isAdmin) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.isAdmin = isAdmin;
	}
	
	public User(String userName, String passWord, int sex, String email, Boolean isAdmin) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.sex = sex;
		this.email = email;
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
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
