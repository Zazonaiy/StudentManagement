package StudentSystem2.server;



public interface UserManager {
	//��¼ϵͳ
	public boolean loadSystem(String userName, String passWord);
	//�����û�
	public boolean changeUser(String userName, String passWord);
	
	
	public String toString() ;
}
