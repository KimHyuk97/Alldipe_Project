package dto.memberDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class membergradeDTO {
	private int sno;
	private String gradeNm;
	private int gradeSort;
	private String gradeIcon;
	private String gradeImage;
	private String dcType;
	private BigDecimal dcPercent;
	private BigDecimal dcPrice;
	private String overlapDcType;
	private BigDecimal overlapDcPercent;
	private BigDecimal overlapDcPrice;
	private int mileageLine;
	private boolean mileageType;
	private BigDecimal mileagePercent;
	private BigDecimal mileagePrice;
	private boolean deliveryFreeFl;
	private int priceLine;
	private int orderCnt;
	private int orderPriceMore;
	private int orderPriceBelow;
	private String couponCd;
	private int managerNo;
	private String managerNm;
	private Timestamp regDt;
	private Timestamp modDt;
	
	
	public boolean isMileageType() {
		return mileageType;
	}
	public void setMileageType(boolean mileageType) {
		this.mileageType = mileageType;
	}
	public boolean isDeliveryFreeFl() {
		return deliveryFreeFl;
	}
	public void setDeliveryFreeFl(boolean deliveryFreeFl) {
		this.deliveryFreeFl = deliveryFreeFl;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getGradeNm() {
		return gradeNm;
	}
	public void setGradeNm(String gradeNm) {
		this.gradeNm = gradeNm;
	}
	public int getGradeSort() {
		return gradeSort;
	}
	public void setGradeSort(int gradeSort) {
		this.gradeSort = gradeSort;
	}
	public String getGradeIcon() {
		return gradeIcon;
	}
	public void setGradeIcon(String gradeIcon) {
		this.gradeIcon = gradeIcon;
	}
	public String getGradeImage() {
		return gradeImage;
	}
	public void setGradeImage(String gradeImage) {
		this.gradeImage = gradeImage;
	}
	public String getDcType() {
		return dcType;
	}
	public void setDcType(String dcType) {
		this.dcType = dcType;
	}
	public BigDecimal getDcPercent() {
		return dcPercent;
	}
	public void setDcPercent(BigDecimal dcPercent) {
		this.dcPercent = dcPercent;
	}
	public BigDecimal getDcPrice() {
		return dcPrice;
	}
	public void setDcPrice(BigDecimal dcPrice) {
		this.dcPrice = dcPrice;
	}
	public String getOverlapDcType() {
		return overlapDcType;
	}
	public void setOverlapDcType(String overlapDcType) {
		this.overlapDcType = overlapDcType;
	}
	public BigDecimal getOverlapDcPercent() {
		return overlapDcPercent;
	}
	public void setOverlapDcPercent(BigDecimal overlapDcPercent) {
		this.overlapDcPercent = overlapDcPercent;
	}
	public BigDecimal getOverlapDcPrice() {
		return overlapDcPrice;
	}
	public void setOverlapDcPrice(BigDecimal overlapDcPrice) {
		this.overlapDcPrice = overlapDcPrice;
	}
	public int getMileageLine() {
		return mileageLine;
	}
	public void setMileageLine(int mileageLine) {
		this.mileageLine = mileageLine;
	}
	
	public BigDecimal getMileagePercent() {
		return mileagePercent;
	}
	public void setMileagePercent(BigDecimal mileagePercent) {
		this.mileagePercent = mileagePercent;
	}
	public BigDecimal getMileagePrice() {
		return mileagePrice;
	}
	public void setMileagePrice(BigDecimal mileagePrice) {
		this.mileagePrice = mileagePrice;
	}
	
	public int getPriceLine() {
		return priceLine;
	}
	public void setPriceLine(int priceLine) {
		this.priceLine = priceLine;
	}
	public int getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}
	public int getOrderPriceMore() {
		return orderPriceMore;
	}
	public void setOrderPriceMore(int orderPriceMore) {
		this.orderPriceMore = orderPriceMore;
	}
	public int getOrderPriceBelow() {
		return orderPriceBelow;
	}
	public void setOrderPriceBelow(int orderPriceBelow) {
		this.orderPriceBelow = orderPriceBelow;
	}
	public String getCouponCd() {
		return couponCd;
	}
	public void setCouponCd(String couponCd) {
		this.couponCd = couponCd;
	}
	public int getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}
	public String getManagerNm() {
		return managerNm;
	}
	public void setManagerNm(String managerNm) {
		this.managerNm = managerNm;
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
		return "membergradeDTO [sno=" + sno + ", gradeNm=" + gradeNm + ", gradeSort=" + gradeSort + ", gradeIcon="
				+ gradeIcon + ", gradeImage=" + gradeImage + ", dcType=" + dcType + ", dcPercent=" + dcPercent
				+ ", dcPrice=" + dcPrice + ", overlapDcType=" + overlapDcType + ", overlapDcPercent=" + overlapDcPercent
				+ ", overlapDcPrice=" + overlapDcPrice + ", mileageLine=" + mileageLine + ", mileageType=" + mileageType
				+ ", mileagePercent=" + mileagePercent + ", mileagePrice=" + mileagePrice + ", deliveryFreeFl="
				+ deliveryFreeFl + ", priceLine=" + priceLine + ", orderCnt=" + orderCnt + ", orderPriceMore="
				+ orderPriceMore + ", orderPriceBelow=" + orderPriceBelow + ", couponCd=" + couponCd + ", managerNo="
				+ managerNo + ", managerNm=" + managerNm + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
	
	
	
	
}
