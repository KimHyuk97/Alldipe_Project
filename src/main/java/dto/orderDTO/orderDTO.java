package dto.orderDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class orderDTO {
	private String orderNo;
	private int memNo;
	private String orderStatus;
	private String orderIp;
	private String orderEmail;
	private BigDecimal settlePrice;
	private BigDecimal taxSupplyPrice;
	private BigDecimal taxVatPrice;
	private BigDecimal taxFreePrice;
	private BigDecimal realTaxSupplyPrice;
	private BigDecimal realTaxVatPrice;
	private BigDecimal realTaxFreePrice;
	private BigDecimal useMileage;
	private BigDecimal useDeposit;
	private BigDecimal totalGoodsDcPrice;
	private BigDecimal totalMemberDcPrice;
	private BigDecimal totalMileage;
	private BigDecimal totalEnuriDcPrice;
	private BigDecimal totalDeliveryCharge;
	private BigDecimal totalGoodsPrice;
	private boolean mileageGiveExclude;
	private boolean eventCouponFl;
	private String adminMemo;
	private String orderPGLog;
	private String orderDeliveryLog;
	private String pgName;
	private String pgResultCode;
	private String pgTid;
	private String pgAuthCode;
	private String pgAuthDate;
	private String transType;
	private String settleKind;
	
	
	
	private String bankCode;
	private String bankName;
	private String rcptType;
	private String rcptTID;
	private String rcptAuthCode;
	private String vbankBankCode;
	private String vbankBankName;
	private String vbankNum;
	private String vbankExpDate;
	private String vbankExpTime;
	private String cardCode;
	private String cardName;
	private String cardNo;
	private String cardQuota;
	private String cardInterest;
	private String acquCardCode;
	private String acquCardName;
	private String cardCl;
	private String ccPartCl;
	private String clickPayCl;
	private BigDecimal couponAmt;
	private BigDecimal couponMinAmt;
	private BigDecimal pointAppAmt;
	private String multiCl;
	private int multiCardAcquAmt;
	private int multiPointAmt;
	private int multiCouponAmt;
	private Timestamp regDt;
	private Timestamp modDt;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderIp() {
		return orderIp;
	}
	public void setOrderIp(String orderIp) {
		this.orderIp = orderIp;
	}
	public String getOrderEmail() {
		return orderEmail;
	}
	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}
	public BigDecimal getSettlePrice() {
		return settlePrice;
	}
	public void setSettlePrice(BigDecimal settlePrice) {
		this.settlePrice = settlePrice;
	}
	public BigDecimal getTaxSupplyPrice() {
		return taxSupplyPrice;
	}
	public void setTaxSupplyPrice(BigDecimal taxSupplyPrice) {
		this.taxSupplyPrice = taxSupplyPrice;
	}
	public BigDecimal getTaxVatPrice() {
		return taxVatPrice;
	}
	public void setTaxVatPrice(BigDecimal taxVatPrice) {
		this.taxVatPrice = taxVatPrice;
	}
	public BigDecimal getTaxFreePrice() {
		return taxFreePrice;
	}
	public void setTaxFreePrice(BigDecimal taxFreePrice) {
		this.taxFreePrice = taxFreePrice;
	}
	public BigDecimal getRealTaxSupplyPrice() {
		return realTaxSupplyPrice;
	}
	public void setRealTaxSupplyPrice(BigDecimal realTaxSupplyPrice) {
		this.realTaxSupplyPrice = realTaxSupplyPrice;
	}
	public BigDecimal getRealTaxVatPrice() {
		return realTaxVatPrice;
	}
	public void setRealTaxVatPrice(BigDecimal realTaxVatPrice) {
		this.realTaxVatPrice = realTaxVatPrice;
	}
	public BigDecimal getRealTaxFreePrice() {
		return realTaxFreePrice;
	}
	public void setRealTaxFreePrice(BigDecimal realTaxFreePrice) {
		this.realTaxFreePrice = realTaxFreePrice;
	}
	public BigDecimal getUseMileage() {
		return useMileage;
	}
	public void setUseMileage(BigDecimal useMileage) {
		this.useMileage = useMileage;
	}
	public BigDecimal getUseDeposit() {
		return useDeposit;
	}
	public void setUseDeposit(BigDecimal useDeposit) {
		this.useDeposit = useDeposit;
	}
	public BigDecimal getTotalGoodsDcPrice() {
		return totalGoodsDcPrice;
	}
	public void setTotalGoodsDcPrice(BigDecimal totalGoodsDcPrice) {
		this.totalGoodsDcPrice = totalGoodsDcPrice;
	}
	public BigDecimal getTotalMemberDcPrice() {
		return totalMemberDcPrice;
	}
	public void setTotalMemberDcPrice(BigDecimal totalMemberDcPrice) {
		this.totalMemberDcPrice = totalMemberDcPrice;
	}
	public BigDecimal getTotalMileage() {
		return totalMileage;
	}
	public void setTotalMileage(BigDecimal totalMileage) {
		this.totalMileage = totalMileage;
	}
	public BigDecimal getTotalEnuriDcPrice() {
		return totalEnuriDcPrice;
	}
	public void setTotalEnuriDcPrice(BigDecimal totalEnuriDcPrice) {
		this.totalEnuriDcPrice = totalEnuriDcPrice;
	}
	public BigDecimal getTotalDeliveryCharge() {
		return totalDeliveryCharge;
	}
	public void setTotalDeliveryCharge(BigDecimal totalDeliveryCharge) {
		this.totalDeliveryCharge = totalDeliveryCharge;
	}
	public BigDecimal getTotalGoodsPrice() {
		return totalGoodsPrice;
	}
	public void setTotalGoodsPrice(BigDecimal totalGoodsPrice) {
		this.totalGoodsPrice = totalGoodsPrice;
	}
	public boolean isMileageGiveExclude() {
		return mileageGiveExclude;
	}
	public void setMileageGiveExclude(boolean mileageGiveExclude) {
		this.mileageGiveExclude = mileageGiveExclude;
	}
	public boolean isEventCouponFl() {
		return eventCouponFl;
	}
	public void setEventCouponFl(boolean eventCouponFl) {
		this.eventCouponFl = eventCouponFl;
	}
	public String getAdminMemo() {
		return adminMemo;
	}
	public void setAdminMemo(String adminMemo) {
		this.adminMemo = adminMemo;
	}
	public String getOrderPGLog() {
		return orderPGLog;
	}
	public void setOrderPGLog(String orderPGLog) {
		this.orderPGLog = orderPGLog;
	}
	public String getOrderDeliveryLog() {
		return orderDeliveryLog;
	}
	public void setOrderDeliveryLog(String orderDeliveryLog) {
		this.orderDeliveryLog = orderDeliveryLog;
	}
	public String getPgName() {
		return pgName;
	}
	public void setPgName(String pgName) {
		this.pgName = pgName;
	}
	public String getPgResultCode() {
		return pgResultCode;
	}
	public void setPgResultCode(String pgResultCode) {
		this.pgResultCode = pgResultCode;
	}
	public String getPgTid() {
		return pgTid;
	}
	public void setPgTid(String pgTid) {
		this.pgTid = pgTid;
	}
	public String getPgAuthCode() {
		return pgAuthCode;
	}
	public void setPgAuthCode(String pgAuthCode) {
		this.pgAuthCode = pgAuthCode;
	}
	public String getPgAuthDate() {
		return pgAuthDate;
	}
	public void setPgAuthDate(String pgAuthDate) {
		this.pgAuthDate = pgAuthDate;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getSettleKind() {
		return settleKind;
	}
	public void setSettleKind(String settleKind) {
		this.settleKind = settleKind;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getRcptType() {
		return rcptType;
	}
	public void setRcptType(String rcptType) {
		this.rcptType = rcptType;
	}
	public String getRcptTID() {
		return rcptTID;
	}
	public void setRcptTID(String rcptTID) {
		this.rcptTID = rcptTID;
	}
	public String getRcptAuthCode() {
		return rcptAuthCode;
	}
	public void setRcptAuthCode(String rcptAuthCode) {
		this.rcptAuthCode = rcptAuthCode;
	}
	public String getVbankBankCode() {
		return vbankBankCode;
	}
	public void setVbankBankCode(String vbankBankCode) {
		this.vbankBankCode = vbankBankCode;
	}
	public String getVbankBankName() {
		return vbankBankName;
	}
	public void setVbankBankName(String vbankBankName) {
		this.vbankBankName = vbankBankName;
	}
	public String getVbankNum() {
		return vbankNum;
	}
	public void setVbankNum(String vbankNum) {
		this.vbankNum = vbankNum;
	}
	public String getVbankExpDate() {
		return vbankExpDate;
	}
	public void setVbankExpDate(String vbankExpDate) {
		this.vbankExpDate = vbankExpDate;
	}
	public String getVbankExpTime() {
		return vbankExpTime;
	}
	public void setVbankExpTime(String vbankExpTime) {
		this.vbankExpTime = vbankExpTime;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardQuota() {
		return cardQuota;
	}
	public void setCardQuota(String cardQuota) {
		this.cardQuota = cardQuota;
	}
	public String getCardInterest() {
		return cardInterest;
	}
	public void setCardInterest(String cardInterest) {
		this.cardInterest = cardInterest;
	}
	public String getAcquCardCode() {
		return acquCardCode;
	}
	public void setAcquCardCode(String acquCardCode) {
		this.acquCardCode = acquCardCode;
	}
	public String getAcquCardName() {
		return acquCardName;
	}
	public void setAcquCardName(String acquCardName) {
		this.acquCardName = acquCardName;
	}
	public String getCardCl() {
		return cardCl;
	}
	public void setCardCl(String cardCl) {
		this.cardCl = cardCl;
	}
	public String getCcPartCl() {
		return ccPartCl;
	}
	public void setCcPartCl(String ccPartCl) {
		this.ccPartCl = ccPartCl;
	}
	public String getClickPayCl() {
		return clickPayCl;
	}
	public void setClickPayCl(String clickPayCl) {
		this.clickPayCl = clickPayCl;
	}
	public BigDecimal getCouponAmt() {
		return couponAmt;
	}
	public void setCouponAmt(BigDecimal couponAmt) {
		this.couponAmt = couponAmt;
	}
	public BigDecimal getCouponMinAmt() {
		return couponMinAmt;
	}
	public void setCouponMinAmt(BigDecimal couponMinAmt) {
		this.couponMinAmt = couponMinAmt;
	}
	public BigDecimal getPointAppAmt() {
		return pointAppAmt;
	}
	public void setPointAppAmt(BigDecimal pointAppAmt) {
		this.pointAppAmt = pointAppAmt;
	}
	public String getMultiCl() {
		return multiCl;
	}
	public void setMultiCl(String multiCl) {
		this.multiCl = multiCl;
	}
	public int getMultiCardAcquAmt() {
		return multiCardAcquAmt;
	}
	public void setMultiCardAcquAmt(int multiCardAcquAmt) {
		this.multiCardAcquAmt = multiCardAcquAmt;
	}
	public int getMultiPointAmt() {
		return multiPointAmt;
	}
	public void setMultiPointAmt(int multiPointAmt) {
		this.multiPointAmt = multiPointAmt;
	}
	public int getMultiCouponAmt() {
		return multiCouponAmt;
	}
	public void setMultiCouponAmt(int multiCouponAmt) {
		this.multiCouponAmt = multiCouponAmt;
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
		return "orderDTO [orderNo=" + orderNo + ", memNo=" + memNo + ", orderStatus=" + orderStatus + ", orderIp="
				+ orderIp + ", orderEmail=" + orderEmail + ", settlePrice=" + settlePrice + ", taxSupplyPrice="
				+ taxSupplyPrice + ", taxVatPrice=" + taxVatPrice + ", taxFreePrice=" + taxFreePrice
				+ ", realTaxSupplyPrice=" + realTaxSupplyPrice + ", realTaxVatPrice=" + realTaxVatPrice
				+ ", realTaxFreePrice=" + realTaxFreePrice + ", useMileage=" + useMileage + ", useDeposit=" + useDeposit
				+ ", totalGoodsDcPrice=" + totalGoodsDcPrice + ", totalMemberDcPrice=" + totalMemberDcPrice
				+ ", totalMileage=" + totalMileage + ", totalEnuriDcPrice=" + totalEnuriDcPrice
				+ ", totalDeliveryCharge=" + totalDeliveryCharge + ", totalGoodsPrice=" + totalGoodsPrice
				+ ", mileageGiveExclude=" + mileageGiveExclude + ", eventCouponFl=" + eventCouponFl + ", adminMemo="
				+ adminMemo + ", orderPGLog=" + orderPGLog + ", orderDeliveryLog=" + orderDeliveryLog + ", pgName="
				+ pgName + ", pgResultCode=" + pgResultCode + ", pgTid=" + pgTid + ", pgAuthCode=" + pgAuthCode
				+ ", pgAuthDate=" + pgAuthDate + ", transType=" + transType + ", settleKind=" + settleKind
				+ ", bankCode=" + bankCode + ", bankName=" + bankName + ", rcptType=" + rcptType + ", rcptTID="
				+ rcptTID + ", rcptAuthCode=" + rcptAuthCode + ", vbankBankCode=" + vbankBankCode + ", vbankBankName="
				+ vbankBankName + ", vbankNum=" + vbankNum + ", vbankExpDate=" + vbankExpDate + ", vbankExpTime="
				+ vbankExpTime + ", cardCode=" + cardCode + ", cardName=" + cardName + ", cardNo=" + cardNo
				+ ", cardQuota=" + cardQuota + ", cardInterest=" + cardInterest + ", acquCardCode=" + acquCardCode
				+ ", acquCardName=" + acquCardName + ", cardCl=" + cardCl + ", ccPartCl=" + ccPartCl + ", clickPayCl="
				+ clickPayCl + ", couponAmt=" + couponAmt + ", couponMinAmt=" + couponMinAmt + ", pointAppAmt="
				+ pointAppAmt + ", multiCl=" + multiCl + ", multiCardAcquAmt=" + multiCardAcquAmt + ", multiPointAmt="
				+ multiPointAmt + ", multiCouponAmt=" + multiCouponAmt + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}

	
	
	
	
	
	
}
