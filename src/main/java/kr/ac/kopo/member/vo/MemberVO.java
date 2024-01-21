package kr.ac.kopo.member.vo;

public class MemberVO {
	
	private String id;
	private String mail;
	private String pw;
	private String nickname;
	private String gender;
	private String birth;
	private String joindate;
	private String adm;

	public MemberVO(String id, String mail, String pw, String nickname, String gender, String birth) {
		super();
		this.id = id;
		this.mail = mail;
		this.pw = pw;
		this.nickname = nickname;
		this.gender = gender;
		this.birth = birth;
	}
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
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public String getAdm() {
		return adm;
	}
	public void setAdm(String adm) {
		this.adm = adm;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", mail=" + mail + ", pw=" + pw + ", nickname=" + nickname + ", gender=" + gender
				+ ", birth=" + birth + ", joindate=" + joindate + ", adm=" + adm + "]";
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
		this.joindate = joindate;
		this.adm = adm;
	}
	
	
}