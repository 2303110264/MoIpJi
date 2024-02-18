import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
 
public class Test {
	
	private static ArrayList<String[]> UserData = new ArrayList<>();
	
	
	// 들어온 ID 와 비밀번호가 일치하는지 체크 
	public boolean check(String ID, String Hasing_password) {
		for(int i = 0; i < UserData.size(); i++) {
			if(ID.equals(UserData.get(i)[0])) {				// ID 일치하는 것을 찾을경우 
				if(Hasing_password.equals(UserData.get(i)[1])) {	// 다이제스트도 일치할 경우 true
					return true;
				}
			}
		}
		return false;
	}
	
	// 해당 ID 의 SALT 값 찾기 
	public String get_SALT(String ID) {
		String err = null;		// 아이디가 존재하지 않을 경우 null 리턴 
		for(int i = 0; i < UserData.size(); i++) {
			if(ID.equals(UserData.get(i)[0])) {
				return UserData.get(i)[2];
			}
		}
		return err;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(String[] temp : UserData) {
			sb.append("ID : " +temp[0] + "\tPassword : " + temp[1] + "\tSALT : "+ temp[2]).append("\n\n");
		}
		return sb.toString();
	}
 
}

class User {
	
	private static final int SALT_SIZE = 16;
	private static Test test = new Test();
	
    
	// 유저 정보와 대조한 뒤 로그인 하기 
	public void get_User(String ID, byte[] password) throws Exception {
		String temp_salt = test.get_SALT(ID);					// 해당 ID의 SALT 값을 찾는다 
		
		String temp_pass = Hashing(password, temp_salt);	// 얻어온 Salt 와 password 를 조합해본다.
		
		if(test.check(ID,temp_pass)) {						// db 에 저장된 아이디와 비밀번호를 대조한다 
			System.out.println("로그인 성공");
		}
		else {
			System.out.println("로그인 실패");
		}
		
	}
	
	
	// 비밀번호 해싱  
	private String Hashing(byte[] password, String Salt) throws Exception {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");	// SHA-256 해시함수를 사용 
 
		// key-stretching
		for(int i = 0; i < 10000; i++) {
			String temp = Byte_to_String(password) + Salt;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성 
			md.update(temp.getBytes());						// temp 의 문자열을 해싱하여 md 에 저장해둔다 
			password = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다 
		}
		
		return Byte_to_String(password);
	}
	
	
	// SALT 값 생성  
	public String getSALT() throws Exception {
		SecureRandom rnd = new SecureRandom();
		byte[] temp = new byte[SALT_SIZE];
		rnd.nextBytes(temp);
		
		return Byte_to_String(temp);
		
	}
	
	
	// 바이트 값을 16진수로 변경해준다 
	private String Byte_to_String(byte[] temp) {
		StringBuilder sb = new StringBuilder();
		for(byte a : temp) {
			sb.append(String.format("%02x", a));
		}
		return sb.toString();
	}
	
	public void get_DB() {
		System.out.println(test);
	}
}