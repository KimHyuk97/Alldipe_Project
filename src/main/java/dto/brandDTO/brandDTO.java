package dto.brandDTO;

import java.sql.Timestamp;

public class brandDTO {
	private int sno;
	private String brandCd;
	private String brandNm;
	private String cateCd;
	private String keyword;
	private String brandImg;
	private String brandImgMobile;
	private int likeCnt;
	private String regIp;
	private Timestamp regDt;
	private Timestamp modDt;
	
	
	public String getBrandImgMobile() {
		return brandImgMobile;
	}
	public void setBrandImgMobile(String brandImgMobile) {
		this.brandImgMobile = brandImgMobile;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getBrandCd() {
		return brandCd;
	}
	public void setBrandCd(String brandCd) {
		this.brandCd = brandCd;
	}
	public String getBrandNm() {
		return brandNm;
	}
	public void setBrandNm(String brandNm) {
		this.brandNm = brandNm;
	}
	public String getCateCd() {
		return cateCd;
	}
	public void setCateCd(String cateCd) {
		this.cateCd = cateCd;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getBrandImg() {
		return brandImg;
	}
	public void setBrandImg(String brandImg) {
		this.brandImg = brandImg;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public Timestamp getRegDt() {
		return regDt;
	}
	public void setRegDt(Timestamp regDt) {
		this.regDt = regDt;
	}
	public Timestamp getModDt() {
		return modDt;
	}
	public void setModDt(Timestamp modDt) {
		this.modDt = modDt;
	}
	@Override
	public String toString() {
		return "brandDTO [sno=" + sno + ", brandCd=" + brandCd + ", brandNm=" + brandNm + ", cateCd=" + cateCd
				+ ", keyword=" + keyword + ", brandImg=" + brandImg + ", likeCnt=" + likeCnt + ", regIp=" + regIp
				+ ", regDt=" + regDt + ", modDt=" + modDt + "]";
	} 
	
	
}
