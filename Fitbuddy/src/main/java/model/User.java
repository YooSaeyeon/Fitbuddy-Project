package model;
import java.util.UUID;

public class User {
	private int userId;
	private String nickname;
	private String password;
	private String gender;
	private String photo;
	
	public User() {
    }
	
	public User(int userId, String nickname,String password,String gender, String photo) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.photo= photo;
    }
	
	public User(String nickname,String password,String gender, String photo) {
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
        this.photo= photo;
    }
	
	public User(int userId,String nickname, String password,String gender) {
		this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
    }

	public User(String nickname, String password, int userId, String gender) {
		this.nickname = nickname;
		this.password = password;
		this.userId = userId;
		this.gender = gender;
	}
	
	public User(int userId, String nickname, String password) {
		this.userId = userId;
		this.nickname = nickname;
		this.password = password;
	}
	
	public User(String nickname,String password) {
        this.nickname = nickname;
        this.password = password;
    }
	
	public User(String nickname,String password, String gender) {
        this.nickname = nickname;
        this.password = password;
        this.gender = gender;
    }
	
	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }
	
	public String getNickname() { return nickname; }
	public void setNickname(String nickname) { this.nickname = nickname; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getGender() { return gender; }
	public void setGender(String gender) { this.gender = gender; }
	
	public String getPhoto() { return photo; }
	public void setPhoto(String photo) { this.photo = photo; }
	
	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	
}

