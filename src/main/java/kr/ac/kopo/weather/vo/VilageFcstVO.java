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
	
	
	//private double UUU; // 동서바람 (m/s)
    //private double VVV; // 남북바람 (m/s)
    //private double VEC; // 풍향

    
    
	
	
	
}
