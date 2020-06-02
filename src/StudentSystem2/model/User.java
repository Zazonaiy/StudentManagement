package StudentSystem2.model;

public class User {
	private String userName;
	private String passWord;
	
	public User(String un, String pw) {
		this.userName = un;
		this.passWord = pw;
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
	
	public String toString() {
		return userName + "\n" + passWord;
	}
}
