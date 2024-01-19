package kr.ac.kopo.weather.vo;
/** 단기 예보 */
public class VilageFcstVO {
	// 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
	private String PTY; // 강수형태
	
	//-, null, 0 = 강수없음 / 0.1~1 = 1.0mm 미만 / 1~30 = 실수값+mm / 30~50 = 30.0~50.0mm / 50 이상 = 50.0mm 이상
	private double PCP; // 1시간 강수량
	private double POP; // 강수확률 (%)
	private double SNO; // 1시간 신적설
	
	private double TMP; // 1시간 기온
	private double TMN; // 일 최저 기온
	private double TMX; // 일 최고 기온
	private double REH; // 습도
	private double WSD; // 풍속(m/s)
	

	// 맑음(1), 구름많음(3), 흐림(4)
	private String SKY; // 하늘 상태
	private double WAV; // 파고
	@Override
	public String toString() {
		return "VilageFcstVO [PTY=" + PTY + ", PCP=" + PCP + ", POP=" + POP + ", SNO=" + SNO + ", TMP=" + TMP + ", TMN="
				+ TMN + ", TMX=" + TMX + ", REH=" + REH + ", WSD=" + WSD + ", SKY=" + SKY + ", WAV=" + WAV + "]";
	}
	public VilageFcstVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VilageFcstVO(String pTY, double pCP, double pOP, double sNO, double tMP, double tMN, double tMX, double rEH,
			double wSD, String sKY, double wAV) {
		super();
		PTY = pTY;
		PCP = pCP;
		POP = pOP;
		SNO = sNO;
		TMP = tMP;
		TMN = tMN;
		TMX = tMX;
		REH = rEH;
		WSD = wSD;
		SKY = sKY;
		WAV = wAV;
	}
	public String getPTY() {
		return PTY;
	}
	public void setPTY(String pTY) {
		PTY = pTY;
	}
	public double getPCP() {
		return PCP;
	}
	public void setPCP(double pCP) {
		PCP = pCP;
	}
	public double getPOP() {
		return POP;
	}
	public void setPOP(double pOP) {
		POP = pOP;
	}
	public double getSNO() {
		return SNO;
	}
	public void setSNO(double sNO) {
		SNO = sNO;
	}
	public double getTMP() {
		return TMP;
	}
	public void setTMP(double tMP) {
		TMP = tMP;
	}
	public double getTMN() {
		return TMN;
	}
	public void setTMN(double tMN) {
		TMN = tMN;
	}
	public double getTMX() {
		return TMX;
	}
	public void setTMX(double tMX) {
		TMX = tMX;
	}
	public double getREH() {
		return REH;
	}
	public void setREH(double rEH) {
		REH = rEH;
	}
	public double getWSD() {
		return WSD;
	}
	public void setWSD(double wSD) {
		WSD = wSD;
	}
	public String getSKY() {
		return SKY;
	}
	public void setSKY(String sKY) {
		SKY = sKY;
	}
	public double getWAV() {
		return WAV;
	}
	public void setWAV(double wAV) {
		WAV = wAV;
	}
	
	
	//private double UUU; // 동서바람 (m/s)
    //private double VVV; // 남북바람 (m/s)
    //private double VEC; // 풍향

    
    
	
	
	
}
