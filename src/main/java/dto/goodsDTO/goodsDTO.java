package dto.goodsDTO;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import oracle.sql.TIMESTAMPLTZ;

public class goodsDTO {

	private int goodsNo;
	private String goodsNm;
	private int goodsSellFl;
	private int scmNo;
	
	private boolean memberOnly;
	
	private String applyFl;
	private String applyMsg;
	private Timestamp applyDt;
	private Timestamp salesStartDt;
	private Timestamp salesEndDt;
	private String cateCd;
	private String brandCd;
	private String makerNm;
	private String keyword;
	private BigDecimal commission;
	private BigDecimal goodsPrice;
	private BigDecimal fixedPrice;
	private BigDecimal discountPercent;
	private int totalStock;
	private String discountInfo;
	private Timestamp periodDiscountStart;
	private Timestamp periodDiscountEnd;
	private boolean goodsDiscountFl;
	private boolean goodsDiscountType;
	private BigDecimal goodsDiscountPrice;
	private BigDecimal goodsDiscountPercent;
	private BigDecimal costPrice;
	private String originNm;
	private String goodsMustInfo;
	private String kcmarkInfo;
	private boolean onlyAdultFl;
	private String taxFreeFl;
	private BigDecimal taxPercent;
	private BigDecimal goodsWeight;
	private String fixedSales;
	private String fixedOrderCnt;
	private int salesUnit;
	
	private String limitOption;
	private boolean limitFl;
	
	private int minOrderCnt;
	private int maxOrderCnt;
	private boolean restockFl;
	private String representImg;
	private String subImg;
	private String shortDescription;
	private String eventDescription;
	private String goodsDescription;
	private String shipmentZonecode;
	private String shipmentAddress;
	private String shipmentAddressSub;
	private String recoveryZonecode;
	private String recoveryAddress;
	private String recoveryAddressSub;
	private String deliveryCompany;
	private String deliveryType;
	private String deliveryWay;
	private String deliveryKind;
	private BigDecimal deliveryFreeCondition;
	private BigDecimal deliveryCost;
	private String deliveryArea;
	private BigDecimal deliveryCostAddJeju;
	private BigDecimal deliveryCostAdd;
	private BigDecimal deliveryRefundCost;
	private String relationGoodsNo;
	private String detailInfoDelivery;
	private String detailInfoAS;
	private String detailInfoRefund;
	private String detailInfoExchange;
	private int orderCnt;
	private int orderGoodsCnt;
	private int hitCnt;
	private int wishCnt;
	private int reviewCnt;
	private String couponCd;
	private String icon;
	private boolean delFl;
	private String adminMsg;
	private Timestamp regDt;
	private Timestamp modDt;
	private Timestamp delDt;
	
	
	public String getLimitOption() {
		return limitOption;
	}
	public void setLimitOption(String limitOption) {
		this.limitOption = limitOption;
	}
	public boolean isMemberOnly() {
		return memberOnly;
	}
	public void setMemberOnly(boolean memberOnly) {
		this.memberOnly = memberOnly;
	}
	public String getSubImg() {
		return subImg;
	}
	public void setSubImg(String subImg) {
		this.subImg = subImg;
	}
	public boolean isLimitFl() {
		return limitFl;
	}
	public void setLimitFl(boolean limitFl) {
		this.limitFl = limitFl;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsNm() {
		return goodsNm;
	}
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	public int getGoodsSellFl() {
		return goodsSellFl;
	}
	public void setGoodsSellFl(int goodsSellFl) {
		this.goodsSellFl = goodsSellFl;
	}
	public int getScmNo() {
		return scmNo;
	}
	public void setScmNo(int scmNo) {
		this.scmNo = scmNo;
	}
	public String getApplyFl() {
		return applyFl;
	}
	public void setApplyFl(String applyFl) {
		this.applyFl = applyFl;
	}
	public String getApplyMsg() {
		return applyMsg;
	}
	public void setApplyMsg(String applyMsg) {
		this.applyMsg = applyMsg;
	}
	public Timestamp getApplyDt() {
		return applyDt;
	}
	public void setApplyDt(Timestamp applyDt) {
		this.applyDt = applyDt;
	}
	public Timestamp getSalesStartDt() {
		return salesStartDt;
	}
	public void setSalesStartDt(Timestamp salesStartDt) {
		this.salesStartDt = salesStartDt;
	}
	public Timestamp getSalesEndDt() {
		return salesEndDt;
	}
	public void setSalesEndDt(Timestamp salesEndDt) {
		this.salesEndDt = salesEndDt;
	}
	public String getCateCd() {
		return cateCd;
	}
	public void setCateCd(String cateCd) {
		this.cateCd = cateCd;
	}
	public String getBrandCd() {
		return brandCd;
	}
	public void setBrandCd(String brandCd) {
		this.brandCd = brandCd;
	}
	public String getMakerNm() {
		return makerNm;
	}
	public void setMakerNm(String makerNm) {
		this.makerNm = makerNm;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public BigDecimal getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(BigDecimal fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	public BigDecimal getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
	}
	public int getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}
	public String getDiscountInfo() {
		return discountInfo;
	}
	public void setDiscountInfo(String discountInfo) {
		this.discountInfo = discountInfo;
	}
	public Timestamp getPeriodDiscountStart() {
		return periodDiscountStart;
	}
	public void setPeriodDiscountStart(Timestamp periodDiscountStart) {
		this.periodDiscountStart = periodDiscountStart;
	}
	public Timestamp getPeriodDiscountEnd() {
		return periodDiscountEnd;
	}
	public void setPeriodDiscountEnd(Timestamp periodDiscountEnd) {
		this.periodDiscountEnd = periodDiscountEnd;
	}
	public boolean isGoodsDiscountFl() {
		return goodsDiscountFl;
	}
	public void setGoodsDiscountFl(boolean goodsDiscountFl) {
		this.goodsDiscountFl = goodsDiscountFl;
	}
	public boolean isGoodsDiscountType() {
		return goodsDiscountType;
	}
	public void setGoodsDiscountType(boolean goodsDiscountType) {
		this.goodsDiscountType = goodsDiscountType;
	}
	public BigDecimal getGoodsDiscountPrice() {
		return goodsDiscountPrice;
	}
	public void setGoodsDiscountPrice(BigDecimal goodsDiscountPrice) {
		this.goodsDiscountPrice = goodsDiscountPrice;
	}
	public BigDecimal getGoodsDiscountPercent() {
		return goodsDiscountPercent;
	}
	public void setGoodsDiscountPercent(BigDecimal goodsDiscountPercent) {
		this.goodsDiscountPercent = goodsDiscountPercent;
	}
	public BigDecimal getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	public String getOriginNm() {
		return originNm;
	}
	public void setOriginNm(String originNm) {
		this.originNm = originNm;
	}
	public String getGoodsMustInfo() {
		return goodsMustInfo;
	}
	public void setGoodsMustInfo(String goodsMustInfo) {
		this.goodsMustInfo = goodsMustInfo;
	}
	public String getKcmarkInfo() {
		return kcmarkInfo;
	}
	public void setKcmarkInfo(String kcmarkInfo) {
		this.kcmarkInfo = kcmarkInfo;
	}
	public boolean isOnlyAdultFl() {
		return onlyAdultFl;
	}
	public void setOnlyAdultFl(boolean onlyAdultFl) {
		this.onlyAdultFl = onlyAdultFl;
	}
	public String getTaxFreeFl() {
		return taxFreeFl;
	}
	public void setTaxFreeFl(String taxFreeFl) {
		this.taxFreeFl = taxFreeFl;
	}
	public BigDecimal getTaxPercent() {
		return taxPercent;
	}
	public void setTaxPercent(BigDecimal taxPercent) {
		this.taxPercent = taxPercent;
	}
	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}
	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	public String getFixedSales() {
		return fixedSales;
	}
	public void setFixedSales(String fixedSales) {
		this.fixedSales = fixedSales;
	}
	public String getFixedOrderCnt() {
		return fixedOrderCnt;
	}
	public void setFixedOrderCnt(String fixedOrderCnt) {
		this.fixedOrderCnt = fixedOrderCnt;
	}
	public int getSalesUnit() {
		return salesUnit;
	}
	public void setSalesUnit(int salesUnit) {
		this.salesUnit = salesUnit;
	}
	public int getMinOrderCnt() {
		return minOrderCnt;
	}
	public void setMinOrderCnt(int minOrderCnt) {
		this.minOrderCnt = minOrderCnt;
	}
	public int getMaxOrderCnt() {
		return maxOrderCnt;
	}
	public void setMaxOrderCnt(int maxOrderCnt) {
		this.maxOrderCnt = maxOrderCnt;
	}
	public boolean isRestockFl() {
		return restockFl;
	}
	public void setRestockFl(boolean restockFl) {
		this.restockFl = restockFl;
	}
	public String getRepresentImg() {
		return representImg;
	}
	public void setRepresentImg(String representImg) {
		this.representImg = representImg;
	}
	
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getGoodsDescription() {
		return goodsDescription;
	}
	public void setGoodsDescription(String goodsDescription) {
		this.goodsDescription = goodsDescription;
	}
	public String getShipmentZonecode() {
		return shipmentZonecode;
	}
	public void setShipmentZonecode(String shipmentZonecode) {
		this.shipmentZonecode = shipmentZonecode;
	}
	public String getShipmentAddress() {
		return shipmentAddress;
	}
	public void setShipmentAddress(String shipmentAddress) {
		this.shipmentAddress = shipmentAddress;
	}
	public String getShipmentAddressSub() {
		return shipmentAddressSub;
	}
	public void setShipmentAddressSub(String shipmentAddressSub) {
		this.shipmentAddressSub = shipmentAddressSub;
	}
	public String getRecoveryZonecode() {
		return recoveryZonecode;
	}
	public void setRecoveryZonecode(String recoveryZonecode) {
		this.recoveryZonecode = recoveryZonecode;
	}
	public String getRecoveryAddress() {
		return recoveryAddress;
	}
	public void setRecoveryAddress(String recoveryAddress) {
		this.recoveryAddress = recoveryAddress;
	}
	public String getRecoveryAddressSub() {
		return recoveryAddressSub;
	}
	public void setRecoveryAddressSub(String recoveryAddressSub) {
		this.recoveryAddressSub = recoveryAddressSub;
	}
	public String getDeliveryCompany() {
		return deliveryCompany;
	}
	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getDeliveryWay() {
		return deliveryWay;
	}
	public void setDeliveryWay(String deliveryWay) {
		this.deliveryWay = deliveryWay;
	}
	public String getDeliveryKind() {
		return deliveryKind;
	}
	public void setDeliveryKind(String deliveryKind) {
		this.deliveryKind = deliveryKind;
	}
	public BigDecimal getDeliveryFreeCondition() {
		return deliveryFreeCondition;
	}
	public void setDeliveryFreeCondition(BigDecimal deliveryFreeCondition) {
		this.deliveryFreeCondition = deliveryFreeCondition;
	}
	public BigDecimal getDeliveryCost() {
		return deliveryCost;
	}
	public void setDeliveryCost(BigDecimal deliveryCost) {
		this.deliveryCost = deliveryCost;
	}
	public String getDeliveryArea() {
		return deliveryArea;
	}
	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}
	public BigDecimal getDeliveryCostAddJeju() {
		return deliveryCostAddJeju;
	}
	public void setDeliveryCostAddJeju(BigDecimal deliveryCostAddJeju) {
		this.deliveryCostAddJeju = deliveryCostAddJeju;
	}
	public BigDecimal getDeliveryCostAdd() {
		return deliveryCostAdd;
	}
	public void setDeliveryCostAdd(BigDecimal deliveryCostAdd) {
		this.deliveryCostAdd = deliveryCostAdd;
	}
	public BigDecimal getDeliveryRefundCost() {
		return deliveryRefundCost;
	}
	public void setDeliveryRefundCost(BigDecimal deliveryRefundCost) {
		this.deliveryRefundCost = deliveryRefundCost;
	}
	public String getRelationGoodsNo() {
		return relationGoodsNo;
	}
	public void setRelationGoodsNo(String relationGoodsNo) {
		this.relationGoodsNo = relationGoodsNo;
	}
	public String getDetailInfoDelivery() {
		return detailInfoDelivery;
	}
	public void setDetailInfoDelivery(String detailInfoDelivery) {
		this.detailInfoDelivery = detailInfoDelivery;
	}
	public String getDetailInfoAS() {
		return detailInfoAS;
	}
	public void setDetailInfoAS(String detailInfoAS) {
		this.detailInfoAS = detailInfoAS;
	}
	public String getDetailInfoRefund() {
		return detailInfoRefund;
	}
	public void setDetailInfoRefund(String detailInfoRefund) {
		this.detailInfoRefund = detailInfoRefund;
	}
	public String getDetailInfoExchange() {
		return detailInfoExchange;
	}
	public void setDetailInfoExchange(String detailInfoExchange) {
		this.detailInfoExchange = detailInfoExchange;
	}
	public int getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}
	public int getOrderGoodsCnt() {
		return orderGoodsCnt;
	}
	public void setOrderGoodsCnt(int orderGoodsCnt) {
		this.orderGoodsCnt = orderGoodsCnt;
	}
	public int getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(int hitCnt) {
		this.hitCnt = hitCnt;
	}
	public int getWishCnt() {
		return wishCnt;
	}
	public void setWishCnt(int wishCnt) {
		this.wishCnt = wishCnt;
	}
	public int getReviewCnt() {
		return reviewCnt;
	}
	public void setReviewCnt(int reviewCnt) {
		this.reviewCnt = reviewCnt;
	}
	public String getCouponCd() {
		return couponCd;
	}
	public void setCouponCd(String couponCd) {
		this.couponCd = couponCd;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean isDelFl() {
		return delFl;
	}
	public void setDelFl(boolean delFl) {
		this.delFl = delFl;
	}
	public String getAdminMsg() {
		return adminMsg;
	}
	public void setAdminMsg(String adminMsg) {
		this.adminMsg = adminMsg;
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
	public Timestamp getDelDt() {
		return delDt;
	}
	public void setDelDt(Timestamp delDt) {
		this.delDt = delDt;
	}
	@Override
	public String toString() {
		return "goodsDTO [goodsNo=" + goodsNo + ", goodsNm=" + goodsNm + ", goodsSellFl=" + goodsSellFl + ", scmNo="
				+ scmNo + ", memberOnly=" + memberOnly + ", applyFl=" + applyFl + ", applyMsg=" + applyMsg
				+ ", applyDt=" + applyDt + ", salesStartDt=" + salesStartDt + ", salesEndDt=" + salesEndDt + ", cateCd="
				+ cateCd + ", brandCd=" + brandCd + ", makerNm=" + makerNm + ", keyword=" + keyword + ", commission="
				+ commission + ", goodsPrice=" + goodsPrice + ", fixedPrice=" + fixedPrice + ", discountPercent="
				+ discountPercent + ", totalStock=" + totalStock + ", discountInfo=" + discountInfo
				+ ", periodDiscountStart=" + periodDiscountStart + ", periodDiscountEnd=" + periodDiscountEnd
				+ ", goodsDiscountFl=" + goodsDiscountFl + ", goodsDiscountType=" + goodsDiscountType
				+ ", goodsDiscountPrice=" + goodsDiscountPrice + ", goodsDiscountPercent=" + goodsDiscountPercent
				+ ", costPrice=" + costPrice + ", originNm=" + originNm + ", goodsMustInfo=" + goodsMustInfo
				+ ", kcmarkInfo=" + kcmarkInfo + ", onlyAdultFl=" + onlyAdultFl + ", taxFreeFl=" + taxFreeFl
				+ ", taxPercent=" + taxPercent + ", goodsWeight=" + goodsWeight + ", fixedSales=" + fixedSales
				+ ", fixedOrderCnt=" + fixedOrderCnt + ", salesUnit=" + salesUnit + ", limitFl=" + limitFl
				+ ", minOrderCnt=" + minOrderCnt + ", maxOrderCnt=" + maxOrderCnt + ", restockFl=" + restockFl
				+ ", representImg=" + representImg + ", subImg=" + subImg + ", shortDescription=" + shortDescription
				+ ", eventDescription=" + eventDescription + ", goodsDescription=" + goodsDescription
				+ ", shipmentZonecode=" + shipmentZonecode + ", shipmentAddress=" + shipmentAddress
				+ ", shipmentAddressSub=" + shipmentAddressSub + ", recoveryZonecode=" + recoveryZonecode
				+ ", recoveryAddress=" + recoveryAddress + ", recoveryAddressSub=" + recoveryAddressSub
				+ ", deliveryCompany=" + deliveryCompany + ", deliveryType=" + deliveryType + ", deliveryWay="
				+ deliveryWay + ", deliveryKind=" + deliveryKind + ", deliveryFreeCondition=" + deliveryFreeCondition
				+ ", deliveryCost=" + deliveryCost + ", deliveryArea=" + deliveryArea + ", deliveryCostAddJeju="
				+ deliveryCostAddJeju + ", deliveryCostAdd=" + deliveryCostAdd + ", deliveryRefundCost="
				+ deliveryRefundCost + ", relationGoodsNo=" + relationGoodsNo + ", detailInfoDelivery="
				+ detailInfoDelivery + ", detailInfoAS=" + detailInfoAS + ", detailInfoRefund=" + detailInfoRefund
				+ ", detailInfoExchange=" + detailInfoExchange + ", orderCnt=" + orderCnt + ", orderGoodsCnt="
				+ orderGoodsCnt + ", hitCnt=" + hitCnt + ", wishCnt=" + wishCnt + ", reviewCnt=" + reviewCnt
				+ ", couponCd=" + couponCd + ", icon=" + icon + ", delFl=" + delFl + ", adminMsg=" + adminMsg
				+ ", regDt=" + regDt + ", modDt=" + modDt + ", delDt=" + delDt + "]";
	}
	
	
}
