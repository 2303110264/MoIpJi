package kr.ac.kopo.weather.vo;

public class WeatherVO {
	private int locationCode;
    private String date;
    private String time;
    private double PTY;
    private double REH;
    private double RN1;
    private double T1H;
    private double UUU;
    private double VVV;
    private double VEC;
    private double WSD;
    
	public int getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(int locationCode) {
		this.locationCode = locationCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getPTY() {
		return PTY;
	}
	public void setPTY(double pTY) {
		PTY = pTY;
	}
	public double getREH() {
		return REH;
	}
	public void setREH(double rEH) {
		REH = rEH;
	}
	public double getRN1() {
		return RN1;
	}
	public void setRN1(double rN1) {
		RN1 = rN1;
	}
	public double getT1H() {
		return T1H;
	}
	public void setT1H(double t1h) {
		T1H = t1h;
	}
	public double getUUU() {
		return UUU;
	}
	public void setUUU(double uUU) {
		UUU = uUU;
	}
	public double getVVV() {
		return VVV;
	}
	public void setVVV(double vVV) {
		VVV = vVV;
	}
	public double getVEC() {
		return VEC;
	}
	public void setVEC(double vEC) {
		VEC = vEC;
	}
	public double getWSD() {
		return WSD;
	}
	public void setWSD(double wSD) {
		WSD = wSD;
	}
	@Override
	public String toString() {
		return "WeatherVO [locationCode=" + locationCode + "\ndate=" + date + "\ntime=" + time + "\nPTY=" + PTY
				+ "\n REH=" + REH + "\n RN1=" + RN1 + "\nT1H=" + T1H + "\nUUU=" + UUU + "\nVVV=" + VVV + "\nVEC=" + VEC
				+ "\nWSD=" + WSD + "]";
	}
	public WeatherVO(int locationCode, String date, String time, double pTY, double rEH, double rN1, double t1h,
			double uUU, double vVV, double vEC, double wSD) {
		super();
		this.locationCode = locationCode;
		this.date = date;
		this.time = time;
		PTY = pTY;
		REH = rEH;
		RN1 = rN1;
		T1H = t1h;
		UUU = uUU;
		VVV = vVV;
		VEC = vEC;
		WSD = wSD;
	}
	public WeatherVO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
