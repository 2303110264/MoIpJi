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
	public String getJoindate() {
		return joinDate;
	}
	public void setJoindate(String joindate) {
		this.joinDate = joindate;
	}
	public String getAdm() {
		return mType;
	}
	public void setAdm(String adm) {
		this.mType = adm;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", mail=" + mail + ", pw=" + pw + ", nickname=" + nickname + ", gender=" + gender
				+ ", birth=" + birth + ", joindate=" + joinDate + ", adm=" + mType + "]";
	}
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberVO(String id, String mail, String pw, String nickname, String gender, String birth, String joindate,
			String adm) {
		super();
		this.id = id;
		this.mail = mail;
		this.pw = pw;
		this.nickname = nickname;
		this.gender = gender;
		this.birth = birth;
		this.joinDate = joindate;
		this.mType = adm;
	}
	
	
}