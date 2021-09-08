package dto.couponDTO;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


public class couponDTO {

	private int couponNo;
	private String kind;
	private String state;
	private String useType;
	private String saveType;
	private String couponNm;
	private String couponDesc;
	private Date usePeriodStartDate;
	private Date usePeriodEndDate;
	private int usedDate;
	private String kindType;
	private BigDecimal benefit;
	private String benefitType;
	private String benefitFixApply;
	private boolean maxBenefitFl;
	private BigDecimal maxBenefit;
	private Date displayStartDate;
	private Date displayEndDate;
	private boolean limitSmsFl;
	private String useAblePaymentType;
	private boolean amountType;
	private int amount;
	private boolean saveDuplicateType;
	private boolean saveDuplicateLimitType;
	private int saveDuplicateLimit;
	private String applyMemberGroup;
	private String applyMemberGroupDisplayType;
	private String applyProductType;
	private String applyScm;
	private String applyCategory;
	private String applyBrand;
	private String applyGoods;
	private BigDecimal minOrderPrice;
	private boolean applyDuplicateType;
	private int adminNo;
	private int couponSaveCnt;
	private Timestamp regDt;
	private Timestamp modDt;
	
	
	public Date getUsePeriodStartDate() {
		return usePeriodStartDate;
	}
	public void setUsePeriodStartDate(Date usePeriodStartDate) {
		this.usePeriodStartDate = usePeriodStartDate;
	}
	public Date getUsePeriodEndDate() {
		return usePeriodEndDate;
	}
	public void setUsePeriodEndDate(Date usePeriodEndDate) {
		this.usePeriodEndDate = usePeriodEndDate;
	}
	public int getUsedDate() {
		return usedDate;
	}
	public void setUsedDate(int usedDate) {
		this.usedDate = usedDate;
	}
	public Date getDisplayStartDate() {
		return displayStartDate;
	}
	public void setDisplayStartDate(Date displayStartDate) {
		this.displayStartDate = displayStartDate;
	}
	public Date getDisplayEndDate() {
		return displayEndDate;
	}
	public void setDisplayEndDate(Date displayEndDate) {
		this.displayEndDate = displayEndDate;
	}
	public BigDecimal getMaxBenefit() {
		return maxBenefit;
	}
	public void setMaxBenefit(BigDecimal maxBenefit) {
		this.maxBenefit = maxBenefit;
	}
	public int getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(int couponNo) {
		this.couponNo = couponNo;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getCouponNm() {
		return couponNm;
	}
	public void setCouponNm(String couponNm) {
		this.couponNm = couponNm;
	}
	public String getCouponDesc() {
		return couponDesc;
	}
	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	
	public String getKindType() {
		return kindType;
	}
	public void setKindType(String kindType) {
		this.kindType = kindType;
	}
	public BigDecimal getBenefit() {
		return benefit;
	}
	public void setBenefit(BigDecimal benefit) {
		this.benefit = benefit;
	}
	public String getBenefitType() {
		return benefitType;
	}
	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}
	public String getBenefitFixApply() {
		return benefitFixApply;
	}
	public void setBenefitFixApply(String benefitFixApply) {
		this.benefitFixApply = benefitFixApply;
	}
	
	public boolean isMaxBenefitFl() {
		return maxBenefitFl;
	}
	public void setMaxBenefitFl(boolean maxBenefitFl) {
		this.maxBenefitFl = maxBenefitFl;
	}
	
	public boolean isLimitSmsFl() {
		return limitSmsFl;
	}
	public void setLimitSmsFl(boolean limitSmsFl) {
		this.limitSmsFl = limitSmsFl;
	}
	public String getUseAblePaymentType() {
		return useAblePaymentType;
	}
	public void setUseAblePaymentType(String useAblePaymentType) {
		this.useAblePaymentType = useAblePaymentType;
	}
	public boolean isAmountType() {
		return amountType;
	}
	public void setAmountType(boolean amountType) {
		this.amountType = amountType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public boolean isSaveDuplicateType() {
		return saveDuplicateType;
	}
	public void setSaveDuplicateType(boolean saveDuplicateType) {
		this.saveDuplicateType = saveDuplicateType;
	}
	public boolean isSaveDuplicateLimitType() {
		return saveDuplicateLimitType;
	}
	public void setSaveDuplicateLimitType(boolean saveDuplicateLimitType) {
		this.saveDuplicateLimitType = saveDuplicateLimitType;
	}
	public int getSaveDuplicateLimit() {
		return saveDuplicateLimit;
	}
	public void setSaveDuplicateLimit(int saveDuplicateLimit) {
		this.saveDuplicateLimit = saveDuplicateLimit;
	}
	public String getApplyMemberGroup() {
		return applyMemberGroup;
	}
	public void setApplyMemberGroup(String applyMemberGroup) {
		this.applyMemberGroup = applyMemberGroup;
	}
	public String getApplyMemberGroupDisplayType() {
		return applyMemberGroupDisplayType;
	}
	public void setApplyMemberGroupDisplayType(String applyMemberGroupDisplayType) {
		this.applyMemberGroupDisplayType = applyMemberGroupDisplayType;
	}
	public String getApplyProductType() {
		return applyProductType;
	}
	public void setApplyProductType(String applyProductType) {
		this.applyProductType = applyProductType;
	}
	public String getApplyScm() {
		return applyScm;
	}
	public void setApplyScm(String applyScm) {
		this.applyScm = applyScm;
	}
	public String getApplyCategory() {
		return applyCategory;
	}
	public void setApplyCategory(String applyCategory) {
		this.applyCategory = applyCategory;
	}
	public String getApplyBrand() {
		return applyBrand;
	}
	public void setApplyBrand(String applyBrand) {
		this.applyBrand = applyBrand;
	}
	public String getApplyGoods() {
		return applyGoods;
	}
	public void setApplyGoods(String applyGoods) {
		this.applyGoods = applyGoods;
	}
	public BigDecimal getMinOrderPrice() {
		return minOrderPrice;
	}
	public void setMinOrderPrice(BigDecimal minOrderPrice) {
		this.minOrderPrice = minOrderPrice;
	}
	public boolean isApplyDuplicateType() {
		return applyDuplicateType;
	}
	public void setApplyDuplicateType(boolean applyDuplicateType) {
		this.applyDuplicateType = applyDuplicateType;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public int getCouponSaveCnt() {
		return couponSaveCnt;
	}
	public void setCouponSaveCnt(int couponSaveCnt) {
		this.couponSaveCnt = couponSaveCnt;
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
		return "couponDTO [couponNo=" + couponNo + ", kind=" + kind + ", state=" + state + ", useType=" + useType
				+ ", saveType=" + saveType + ", couponNm=" + couponNm + ", couponDesc=" + couponDesc
				+ ", usePeriodStartDate=" + usePeriodStartDate + ", usePeriodEndDate=" + usePeriodEndDate
				+ ", usedDate=" + usedDate + ", kindType=" + kindType + ", benefit=" + benefit + ", benefitType="
				+ benefitType + ", benefitFixApply=" + benefitFixApply + ", maxBenefitFl=" + maxBenefitFl
				+ ", maxBenefit=" + maxBenefit + ", displayStartDate=" + displayStartDate + ", displayEndDate="
				+ displayEndDate + ", limitSmsFl=" + limitSmsFl + ", useAblePaymentType=" + useAblePaymentType
				+ ", amountType=" + amountType + ", amount=" + amount + ", saveDuplicateType=" + saveDuplicateType
				+ ", saveDuplicateLimitType=" + saveDuplicateLimitType + ", saveDuplicateLimit=" + saveDuplicateLimit
				+ ", applyMemberGroup=" + applyMemberGroup + ", applyMemberGroupDisplayType="
				+ applyMemberGroupDisplayType + ", applyProductType=" + applyProductType + ", applyScm=" + applyScm
				+ ", applyCategory=" + applyCategory + ", applyBrand=" + applyBrand + ", applyGoods=" + applyGoods
				+ ", minOrderPrice=" + minOrderPrice + ", applyDuplicateType=" + applyDuplicateType + ", adminNo="
				+ adminNo + ", couponSaveCnt=" + couponSaveCnt + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
	
}
