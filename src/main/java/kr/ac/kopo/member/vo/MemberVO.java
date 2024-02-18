package kr.ac.kopo.member.vo;

public class MemberVO {
	
	private String id; // v20
	private String mail; // v60
	private String pw; //v100
	private String nickname; //v24
	private String gender; //number (1)
	private String birth; // date
	private String joinDate; // date
	private String mType; // number(1)
	private String salt;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getMType() {
		return mType;
	}
	public void setMType(String mType) {
		this.mType = mType;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", mail=" + mail + ", pw=" + pw + ", nickname=" + nickname + ", gender=" + gender
				+ ", birth=" + birth + ", joinDate=" + joinDate + ", mType=" + mType + ", salt=" + salt + "]";
	}
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String id, String mail, String pw, String nickname, String gender, String birth, String salt) {
		super();
		this.id = id;
		this.mail = mail;
		this.pw = pw;
		this.nickname = nickname;
		this.gender = gender;
		this.birth = birth;
		this.mType = "0";
		this.salt = salt;
	}
	
	
}