package dto.themeDTO;

import java.sql.Timestamp;

public class themeDTO {

	private int sno;
	private int adminNo;
	private String themeNm;
	private String themeDescription;
	private String relationCd;
	private String representImg;
	private String seoTagFl;
	private int seoTagSno;
	private int sort;
	private String goodsNos;
	private Timestamp regDt;
	private Timestamp modDt;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getThemeNm() {
		return themeNm;
	}
	public void setThemeNm(String themeNm) {
		this.themeNm = themeNm;
	}
	public String getThemeDescription() {
		return themeDescription;
	}
	public void setThemeDescription(String themeDescription) {
		this.themeDescription = themeDescription;
	}
	public String getSeoTagFl() {
		return seoTagFl;
	}
	public void setSeoTagFl(String seoTagFl) {
		this.seoTagFl = seoTagFl;
	}
	public int getSeoTagSno() {
		return seoTagSno;
	}
	public void setSeoTagSno(int seoTagSno) {
		this.seoTagSno = seoTagSno;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getGoodsNos() {
		return goodsNos;
	}
	public void setGoodsNos(String goodsNos) {
		this.goodsNos = goodsNos;
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
	public String getRelationCd() {
		return relationCd;
	}
	public void setRelationCd(String relationCd) {
		this.relationCd = relationCd;
	}
	public String getRepresentImg() {
		return representImg;
	}
	public void setRepresentImg(String representImg) {
		this.representImg = representImg;
	}
	
}
