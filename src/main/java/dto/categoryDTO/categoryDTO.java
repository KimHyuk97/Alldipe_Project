package dto.categoryDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class categoryDTO {
	private int sno;
	private String cateNm;
	private String cateCd;
	private String keyword;
	private String adsgoods;
	private Boolean cateDisplayFl;
	private String cateImg;
	private String cateOverImg;
	private Boolean cateOnlyAdultFl;
	private Boolean cateOnlyAdultDisplayFl;
	private int cateSort;
	private String recomGoodsNo;
	private BigDecimal commission;
	private Timestamp regDt;
	private Timestamp modDt;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getCateNm() {
		return cateNm;
	}
	public void setCateNm(String cateNm) {
		this.cateNm = cateNm;
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
	public String getAdsgoods() {
		return adsgoods;
	}
	public void setAdsgoods(String adsgoods) {
		this.adsgoods = adsgoods;
	}
	public Boolean getCateDisplayFl() {
		return cateDisplayFl;
	}
	public void setCateDisplayFl(Boolean cateDisplayFl) {
		this.cateDisplayFl = cateDisplayFl;
	}
	public String getCateImg() {
		return cateImg;
	}
	public void setCateImg(String cateImg) {
		this.cateImg = cateImg;
	}
	public String getCateOverImg() {
		return cateOverImg;
	}
	public void setCateOverImg(String cateOverImg) {
		this.cateOverImg = cateOverImg;
	}
	public Boolean getCateOnlyAdultFl() {
		return cateOnlyAdultFl;
	}
	public void setCateOnlyAdultFl(Boolean cateOnlyAdultFl) {
		this.cateOnlyAdultFl = cateOnlyAdultFl;
	}
	public Boolean getCateOnlyAdultDisplayFl() {
		return cateOnlyAdultDisplayFl;
	}
	public void setCateOnlyAdultDisplayFl(Boolean cateOnlyAdultDisplayFl) {
		this.cateOnlyAdultDisplayFl = cateOnlyAdultDisplayFl;
	}
	public int getCateSort() {
		return cateSort;
	}
	public void setCateSort(int cateSort) {
		this.cateSort = cateSort;
	}
	public String getRecomGoodsNo() {
		return recomGoodsNo;
	}
	public void setRecomGoodsNo(String recomGoodsNo) {
		this.recomGoodsNo = recomGoodsNo;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
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
		return "categoryDTO [sno=" + sno + ", cateNm=" + cateNm + ", cateCd=" + cateCd + ", keyword=" + keyword
				+ ", adsgoods=" + adsgoods + ", cateDisplayFl=" + cateDisplayFl + ", cateImg=" + cateImg
				+ ", cateOverImg=" + cateOverImg + ", cateOnlyAdultFl=" + cateOnlyAdultFl + ", cateOnlyAdultDisplayFl="
				+ cateOnlyAdultDisplayFl + ", cateSort=" + cateSort + ", recomGoodsNo=" + recomGoodsNo + ", commission="
				+ commission + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}

		
	
	
	
}
