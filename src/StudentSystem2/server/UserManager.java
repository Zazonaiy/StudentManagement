package StudentSystem2.server;



public interface UserManager {
	//登录系统
	public boolean loadSystem(String userName, String passWord);
	//更换用户
	public boolean changeUser(String userName, String passWord);
	
	
	public String toString() ;
}
