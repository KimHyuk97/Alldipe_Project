package dto.orderDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class orderGoods {
	private int sno; // 일련번호
	private String orderNo; // 주문번호
	private int orderCd; // 주문코드(순서)
	private int eventSno; // 기획전 일련번호
	private String orderStatus; // 주문상태
	private String invoiceNo; // 송장번호
	private int memNo; //회원번호
	private int scmNo; // 공급사 번호
	private int scmAdjustNo; // 공급사 정산 고유 번호
	private boolean timeSaleFl; // 타임세일 구매 여부
	private int goodsNo; // 상품 번호
	private String representImg; // 상품 이미지
	private String goodsNm; // 상품명
	private String makerNm;	// 제조사 이름
	private int goodsCnt; // 상품 수량
	private String goodsOptionNm; //상품옵션명
	private BigDecimal fixedPrice; // 상품 가격
	private BigDecimal optionFixedPrice; // 선택 옵션 금액
	private BigDecimal deliveryPrice; //배송비
	private BigDecimal sumPrice; // 옵션 포함 가격
	private BigDecimal taxSupplyGoodsPrice; // 복합과세 상품 공급가
	private BigDecimal taxVatGoodsPrice; // 복합과세 상품 부가세
	private BigDecimal taxFreeGoodsPrice; // 복합과세 상품 면세
	private BigDecimal goodsDcPrice; // 상품 할인 금액 (상품에만 적용)
	private BigDecimal memberDcPrice; // 회원 할인 금액 (추가상품 제외)
	private BigDecimal couponGoodsDcPrice; // 상품쿠폰 할인 금액 (추가상품 제외)
	private int couponCd; // 사용된 멤버 쿠폰코드
	private BigDecimal timeSaleDcPrice; // 타임세일 할인 금액 (상품에만 적용)
	private BigDecimal goodsMileage; // 상품 적립마일리지
	private BigDecimal memMileage; // 회원등급별 적립마일리지
	private boolean minusDepositFl; // 마일리지 차감 여부
	private BigDecimal usedDeposit;
	private boolean minusRestoreDepositFl; // 복원 여부 (적립 적립금)
	private boolean minusMileageFl; // 사용 마일리지 차감 여부
	private BigDecimal usedMileage;
	private boolean minusRestoreMileageFl; // 사용 마일리지 복원 여부
	private boolean plusMileageFl; // 적립 마일리지 지급 여부
	private boolean plusRestoreMileageFl; // 적립 마일리지 복원 여부
	private boolean minusStockFl; // 차감 여부 (재고)
	private boolean minusRestoreStockFl; // 복원 여부 (재고)
	private int optionNo; // 상품옵션 일련번호
	private String optionTextInfo; // 텍스트 옵션 정보
	private String goodsTaxInfo; // 상품 부가세 정보
	private Timestamp cancelDt; // 취소완료일자
	private Timestamp paymentDt; // 입금일자
	private String sendSmsFl; // 문자발송여부
	private int invoiceCompanySno; // 택배사 SNO
	private Timestamp invoiceDt; // 송장번호 등록일
	private Timestamp deliveryDt; // 배송일자
	private Timestamp deliveryCompleteDt; // 배송완료일자
	private Timestamp finishDt; // 구매확정일자
	private String goodsDiscountInfo; // 주문당시상품할인정보
	private String goodsMileageAddInfo; // 주문당시상품적립정보
	private Timestamp regDt; // 등록일
	private Timestamp modDt; // 수정일
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getOrderCd() {
		return orderCd;
	}
	public void setOrderCd(int orderCd) {
		this.orderCd = orderCd;
	}
	public int getEventSno() {
		return eventSno;
	}
	public void setEventSno(int eventSno) {
		this.eventSno = eventSno;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public int getScmNo() {
		return scmNo;
	}
	public void setScmNo(int scmNo) {
		this.scmNo = scmNo;
	}
	public int getScmAdjustNo() {
		return scmAdjustNo;
	}
	public void setScmAdjustNo(int scmAdjustNo) {
		this.scmAdjustNo = scmAdjustNo;
	}
	public boolean isTimeSaleFl() {
		return timeSaleFl;
	}
	public void setTimeSaleFl(boolean timeSaleFl) {
		this.timeSaleFl = timeSaleFl;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getRepresentImg() {
		return representImg;
	}
	public void setRepresentImg(String representImg) {
		this.representImg = representImg;
	}
	public String getGoodsNm() {
		return goodsNm;
	}
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	public int getGoodsCnt() {
		return goodsCnt;
	}
	public void setGoodsCnt(int goodsCnt) {
		this.goodsCnt = goodsCnt;
	}
	public String getGoodsOptionNm() {
		return goodsOptionNm;
	}
	public void setGoodsOptionNm(String goodsOptionNm) {
		this.goodsOptionNm = goodsOptionNm;
	}
	
	
	
	public BigDecimal getOptionFixedPrice() {
		return optionFixedPrice;
	}
	public void setOptionFixedPrice(BigDecimal optionFixedPrice) {
		this.optionFixedPrice = optionFixedPrice;
	}
	public BigDecimal getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}
	public BigDecimal getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(BigDecimal deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public BigDecimal getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(BigDecimal fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	public BigDecimal getTaxSupplyGoodsPrice() {
		return taxSupplyGoodsPrice;
	}
	public void setTaxSupplyGoodsPrice(BigDecimal taxSupplyGoodsPrice) {
		this.taxSupplyGoodsPrice = taxSupplyGoodsPrice;
	}
	public BigDecimal getTaxVatGoodsPrice() {
		return taxVatGoodsPrice;
	}
	public void setTaxVatGoodsPrice(BigDecimal taxVatGoodsPrice) {
		this.taxVatGoodsPrice = taxVatGoodsPrice;
	}
	public BigDecimal getTaxFreeGoodsPrice() {
		return taxFreeGoodsPrice;
	}
	public void setTaxFreeGoodsPrice(BigDecimal taxFreeGoodsPrice) {
		this.taxFreeGoodsPrice = taxFreeGoodsPrice;
	}
	public BigDecimal getGoodsDcPrice() {
		return goodsDcPrice;
	}
	public void setGoodsDcPrice(BigDecimal goodsDcPrice) {
		this.goodsDcPrice = goodsDcPrice;
	}
	public BigDecimal getMemberDcPrice() {
		return memberDcPrice;
	}
	public void setMemberDcPrice(BigDecimal memberDcPrice) {
		this.memberDcPrice = memberDcPrice;
	}
	public BigDecimal getCouponGoodsDcPrice() {
		return couponGoodsDcPrice;
	}
	public void setCouponGoodsDcPrice(BigDecimal couponGoodsDcPrice) {
		this.couponGoodsDcPrice = couponGoodsDcPrice;
	}
	public int getCouponCd() {
		return couponCd;
	}
	public void setCouponCd(int couponCd) {
		this.couponCd = couponCd;
	}
	public BigDecimal getTimeSaleDcPrice() {
		return timeSaleDcPrice;
	}
	public void setTimeSaleDcPrice(BigDecimal timeSaleDcPrice) {
		this.timeSaleDcPrice = timeSaleDcPrice;
	}
	public BigDecimal getGoodsMileage() {
		return goodsMileage;
	}
	public void setGoodsMileage(BigDecimal goodsMileage) {
		this.goodsMileage = goodsMileage;
	}
	public BigDecimal getMemMileage() {
		return memMileage;
	}
	public void setMemMileage(BigDecimal memMileage) {
		this.memMileage = memMileage;
	}
	public boolean isMinusDepositFl() {
		return minusDepositFl;
	}
	public void setMinusDepositFl(boolean minusDepositFl) {
		this.minusDepositFl = minusDepositFl;
	}
	public BigDecimal getUsedDeposit() {
		return usedDeposit;
	}
	public void setUsedDeposit(BigDecimal usedDeposit) {
		this.usedDeposit = usedDeposit;
	}
	public boolean isMinusRestoreDepositFl() {
		return minusRestoreDepositFl;
	}
	public void setMinusRestoreDepositFl(boolean minusRestoreDepositFl) {
		this.minusRestoreDepositFl = minusRestoreDepositFl;
	}
	public boolean isMinusMileageFl() {
		return minusMileageFl;
	}
	public void setMinusMileageFl(boolean minusMileageFl) {
		this.minusMileageFl = minusMileageFl;
	}
	public BigDecimal getUsedMileage() {
		return usedMileage;
	}
	public void setUsedMileage(BigDecimal usedMileage) {
		this.usedMileage = usedMileage;
	}
	public boolean isMinusRestoreMileageFl() {
		return minusRestoreMileageFl;
	}
	public void setMinusRestoreMileageFl(boolean minusRestoreMileageFl) {
		this.minusRestoreMileageFl = minusRestoreMileageFl;
	}
	public boolean isPlusMileageFl() {
		return plusMileageFl;
	}
	public void setPlusMileageFl(boolean plusMileageFl) {
		this.plusMileageFl = plusMileageFl;
	}
	public boolean isPlusRestoreMileageFl() {
		return plusRestoreMileageFl;
	}
	public void setPlusRestoreMileageFl(boolean plusRestoreMileageFl) {
		this.plusRestoreMileageFl = plusRestoreMileageFl;
	}
	public boolean isMinusStockFl() {
		return minusStockFl;
	}
	public void setMinusStockFl(boolean minusStockFl) {
		this.minusStockFl = minusStockFl;
	}
	public boolean isMinusRestoreStockFl() {
		return minusRestoreStockFl;
	}
	public void setMinusRestoreStockFl(boolean minusRestoreStockFl) {
		this.minusRestoreStockFl = minusRestoreStockFl;
	}
	public int getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}
	public String getOptionTextInfo() {
		return optionTextInfo;
	}
	public void setOptionTextInfo(String optionTextInfo) {
		this.optionTextInfo = optionTextInfo;
	}
	public String getGoodsTaxInfo() {
		return goodsTaxInfo;
	}
	public void setGoodsTaxInfo(String goodsTaxInfo) {
		this.goodsTaxInfo = goodsTaxInfo;
	}
	public Timestamp getCancelDt() {
		return cancelDt;
	}
	public void setCancelDt(Timestamp cancelDt) {
		this.cancelDt = cancelDt;
	}
	public Timestamp getPaymentDt() {
		return paymentDt;
	}
	public void setPaymentDt(Timestamp paymentDt) {
		this.paymentDt = paymentDt;
	}
	public String getSendSmsFl() {
		return sendSmsFl;
	}
	public void setSendSmsFl(String sendSmsFl) {
		this.sendSmsFl = sendSmsFl;
	}
	public int getInvoiceCompanySno() {
		return invoiceCompanySno;
	}
	public void setInvoiceCompanySno(int invoiceCompanySno) {
		this.invoiceCompanySno = invoiceCompanySno;
	}
	public Timestamp getInvoiceDt() {
		return invoiceDt;
	}
	public void setInvoiceDt(Timestamp invoiceDt) {
		this.invoiceDt = invoiceDt;
	}
	public Timestamp getDeliveryDt() {
		return deliveryDt;
	}
	public void setDeliveryDt(Timestamp deliveryDt) {
		this.deliveryDt = deliveryDt;
	}
	public Timestamp getDeliveryCompleteDt() {
		return deliveryCompleteDt;
	}
	public void setDeliveryCompleteDt(Timestamp deliveryCompleteDt) {
		this.deliveryCompleteDt = deliveryCompleteDt;
	}
	public Timestamp getFinishDt() {
		return finishDt;
	}
	public void setFinishDt(Timestamp finishDt) {
		this.finishDt = finishDt;
	}
	public String getGoodsDiscountInfo() {
		return goodsDiscountInfo;
	}
	public void setGoodsDiscountInfo(String goodsDiscountInfo) {
		this.goodsDiscountInfo = goodsDiscountInfo;
	}
	public String getGoodsMileageAddInfo() {
		return goodsMileageAddInfo;
	}
	public void setGoodsMileageAddInfo(String goodsMileageAddInfo) {
		this.goodsMileageAddInfo = goodsMileageAddInfo;
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
	
	
	public String getMakerNm() {
		return makerNm;
	}
	public void setMakerNm(String makerNm) {
		this.makerNm = makerNm;
	}
	@Override
	public String toString() {
		return "orderGoods [sno=" + sno + ", orderNo=" + orderNo + ", orderCd=" + orderCd + ", eventSno=" + eventSno
				+ ", orderStatus=" + orderStatus + ", invoiceNo=" + invoiceNo + ", memNo=" + memNo + ", scmNo=" + scmNo
				+ ", scmAdjustNo=" + scmAdjustNo + ", timeSaleFl=" + timeSaleFl + ", goodsNo=" + goodsNo
				+ ", representImg=" + representImg + ", goodsNm=" + goodsNm + ", goodsCnt=" + goodsCnt
				+ ", goodsOptionNm=" + goodsOptionNm + ", makerNm=" + makerNm +  ", fixedPrice=" + fixedPrice + ", optionFixedPrice="
				+ optionFixedPrice + ", deliveryPrice=" + deliveryPrice + ", sumPrice=" + sumPrice
				+ ", taxSupplyGoodsPrice=" + taxSupplyGoodsPrice + ", taxVatGoodsPrice=" + taxVatGoodsPrice
				+ ", taxFreeGoodsPrice=" + taxFreeGoodsPrice + ", goodsDcPrice=" + goodsDcPrice + ", memberDcPrice="
				+ memberDcPrice + ", couponGoodsDcPrice=" + couponGoodsDcPrice + ", couponCd=" + couponCd
				+ ", timeSaleDcPrice=" + timeSaleDcPrice + ", goodsMileage=" + goodsMileage + ", memMileage="
				+ memMileage + ", minusDepositFl=" + minusDepositFl + ", usedDeposit=" + usedDeposit
				+ ", minusRestoreDepositFl=" + minusRestoreDepositFl + ", minusMileageFl=" + minusMileageFl
				+ ", usedMileage=" + usedMileage + ", minusRestoreMileageFl=" + minusRestoreMileageFl
				+ ", plusMileageFl=" + plusMileageFl + ", plusRestoreMileageFl=" + plusRestoreMileageFl
				+ ", minusStockFl=" + minusStockFl + ", minusRestoreStockFl=" + minusRestoreStockFl + ", optionNo="
				+ optionNo + ", optionTextInfo=" + optionTextInfo + ", goodsTaxInfo=" + goodsTaxInfo + ", cancelDt="
				+ cancelDt + ", paymentDt=" + paymentDt + ", sendSmsFl=" + sendSmsFl + ", invoiceCompanySno="
				+ invoiceCompanySno + ", invoiceDt=" + invoiceDt + ", deliveryDt=" + deliveryDt
				+ ", deliveryCompleteDt=" + deliveryCompleteDt + ", finishDt=" + finishDt + ", goodsDiscountInfo="
				+ goodsDiscountInfo + ", goodsMileageAddInfo=" + goodsMileageAddInfo + ", regDt=" + regDt + ", modDt="
				+ modDt + "]";
	}
	
}
