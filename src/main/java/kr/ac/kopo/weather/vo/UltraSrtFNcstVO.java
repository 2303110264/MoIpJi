package kr.ac.kopo.weather.vo;
/** 초단기실황 */

public class UltraSrtFNcstVO {
	
	//(초단기) 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7)
    private String PTY; // 강수형태 (코드값)

    //-, null, 0 = 강수없음 / 0.1~1 = 1.0mm 미만 / 1~30 = 실수값+mm / 30~50 = 30.0~50.0mm / 50 이상 = 50.0mm 이상
    private String RN1; // 강수량 1h (㎜)
    
    private String T1H; // 기온 (℃)
    private String REH; // 습도 (%)
    private String WSD; // 풍속(m/s)
    //private double UUU; // 동서바람 (m/s)
    //private double VVV; // 남북바람 (m/s)
    //private double VEC; // 풍향 (deg)
    
    
    // 초단기 예보
    private String LGT; // 낙뢰 (kA 킬로암페어)
    // 맑음(1), 구름많음(3), 흐림(4)
    private String SKY;
	public String getPTY() {
		return PTY;
	}
	public void setPTY(String pTY) {
		PTY = pTY;
	}
	public String getRN1() {
		return RN1;
	}
	public void setRN1(String rN1) {
		RN1 = rN1;
	}
	public String getT1H() {
		return T1H;
	}
	public void setT1H(String t1h) {
		T1H = t1h;
	}
	public String getREH() {
		return REH;
	}
	public void setREH(String rEH) {
		REH = rEH;
	}
	public String getWSD() {
		return WSD;
	}
	public void setWSD(String wSD) {
		WSD = wSD;
	}
	public String getLGT() {
		return LGT;
	}
	public void setLGT(String lGT) {
		LGT = lGT;
	}
	public String getSKY() {
		return SKY;
	}
	public void setSKY(String sKY) {
		SKY = sKY;
	}
	@Override
	public String toString() {
		return "UltraSrtFNcstVO [PTY=" + PTY + ", RN1=" + RN1 + ", T1H=" + T1H + ", REH=" + REH + ", WSD=" + WSD
				+ ", LGT=" + LGT + ", SKY=" + SKY + "]";
	}
	public UltraSrtFNcstVO(String pTY, String rN1, String t1h, String rEH, String wSD, String lGT, String sKY) {
		super();
		PTY = pTY;
		RN1 = rN1;
		T1H = t1h;
		REH = rEH;
		WSD = wSD;
		LGT = lGT;
		SKY = sKY;
	}
	public UltraSrtFNcstVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
    
    
    /*
    
    if(f < 1.0f) return "1.0mm미만 ";		
		else if(f >= 1.0f && f < 30.0f) return "1.0~29.0mm";
		else if(f >= 30.0f && f < 50.0f) return "30.0~50.0mm";
		else return "50.0mm이상";
    
     */
    
	
}
