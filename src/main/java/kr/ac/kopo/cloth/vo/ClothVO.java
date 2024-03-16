package kr.ac.kopo.cloth.vo;

public class ClothVO {
	private int cNum; // c_num
	private String cName; //c_name
	private String cCategory;
	private String cAddId; 
	private int cWarmPoint;
	
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcCategory() {
		return cCategory;
	}
	public void setcCategory(String cCategory) {
		this.cCategory = cCategory;
	}
	public String getcAddId() {
		return cAddId;
	}
	public void setcAddId(String cAddId) {
		this.cAddId = cAddId;
	}
	public int getCwarmPoint() {
		return cWarmPoint;
	}
	public void setCwarmPoint(int cwarmPoint) {
		this.cWarmPoint = cwarmPoint;
	}
	@Override
	public String toString() {
		return "ClothVO [cNum=" + cNum + ", cName=" + cName + ", cCategory=" + cCategory + ", cAddId=" + cAddId
				+ ", cwarmPoint=" + cWarmPoint + "]";
	}
	public ClothVO(int cNum, String cName, String cCategory, String cAddId, int cwarmPoint) {
		super();
		this.cNum = cNum;
		this.cName = cName;
		this.cCategory = cCategory;
		this.cAddId = cAddId;
		this.cWarmPoint = cwarmPoint;
	}
	public ClothVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
