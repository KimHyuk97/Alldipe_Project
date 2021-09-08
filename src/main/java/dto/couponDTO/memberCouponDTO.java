package dto.couponDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class memberCouponDTO {

	private int sno;
	private int couponNo;
	private int memNo;
	private int adminNo;
	private String couponNm;
	private boolean couponBenefitType;
	private BigDecimal couponBenefit;
	private int ordergoodsNo;
	private Timestamp memberCouponStartDt;
	private Timestamp memberCouponEndDt;
	private Timestamp memberCouponUseDt;
	private boolean useState;
	private Timestamp regDt;
	private Timestamp modDt;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getCouponNm() {
		return couponNm;
	}
	public void setCouponNm(String couponNm) {
		this.couponNm = couponNm;
	}
	public BigDecimal getCouponBenefit() {
		return couponBenefit;
	}
	public void setCouponBenefit(BigDecimal couponBenefit) {
		this.couponBenefit = couponBenefit;
	}
	public int getOrdergoodsNo() {
		return ordergoodsNo;
	}
	public void setOrdergoodsNo(int ordergoodsNo) {
		this.ordergoodsNo = ordergoodsNo;
	}
	public Timestamp getMemberCouponStartDt() {
		return memberCouponStartDt;
	}
	public void setMemberCouponStartDt(Timestamp memberCouponStartDt) {
		this.memberCouponStartDt = memberCouponStartDt;
	}
	public Timestamp getMemberCouponEndDt() {
		return memberCouponEndDt;
	}
	public void setMemberCouponEndDt(Timestamp memberCouponEndDt) {
		this.memberCouponEndDt = memberCouponEndDt;
	}
	public Timestamp getMemberCouponUseDt() {
		return memberCouponUseDt;
	}
	public void setMemberCouponUseDt(Timestamp memberCouponUseDt) {
		this.memberCouponUseDt = memberCouponUseDt;
	}
	public boolean isUseState() {
		return useState;
	}
	public void setUseState(boolean useState) {
		this.useState = useState;
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
	public boolean isCouponBenefitType() {
		return couponBenefitType;
	}
	public void setCouponBenefitType(boolean couponBenefitType) {
		this.couponBenefitType = couponBenefitType;
	}
	
}
