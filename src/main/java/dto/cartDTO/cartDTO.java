package dto.cartDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class cartDTO {
	private int sno;
	private String siteKey;
	private int memNo;
	private int scmNo;
	private int goodsNo;
	private int optionNo;
	private String goodsOptionNm;
	private int goodsCnt;
	private String tmpOrderNo;
	private int useBundleGoods;
	private String linkMainTheme;
	private String goodsNm; //상품명
	private String makerNm; //제조사
	private BigDecimal goodsPrice; //상품가격
	private BigDecimal optionPrice; //판매가격
	private BigDecimal sumPrice; //할인율
	private int totalStock; //재고량
	private String representImg; //대표이미지
	private BigDecimal deliveryCost; //배송비
	private BigDecimal deliveryCostAddJeju; //제주 추가배송비
	private BigDecimal deliveryCostAdd; //도시간 추가배송비
	private Timestamp regDt;
	private Timestamp modDt;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSiteKey() {
		return siteKey;
	}
	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
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
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public int getOptionNo() {
		return optionNo;
	}
	public void setOptionNo(int optionNo) {
		this.optionNo = optionNo;
	}
	public String getGoodsOptionNm() {
		return goodsOptionNm;
	}
	public void setGoodsOptionNm(String goodsOptionNm) {
		this.goodsOptionNm = goodsOptionNm;
	}
	public int getGoodsCnt() {
		return goodsCnt;
	}
	public void setGoodsCnt(int goodsCnt) {
		this.goodsCnt = goodsCnt;
	}
	public String getTmpOrderNo() {
		return tmpOrderNo;
	}
	public void setTmpOrderNo(String tmpOrderNo) {
		this.tmpOrderNo = tmpOrderNo;
	}
	public int getUseBundleGoods() {
		return useBundleGoods;
	}
	public void setUseBundleGoods(int useBundleGoods) {
		this.useBundleGoods = useBundleGoods;
	}
	public String getLinkMainTheme() {
		return linkMainTheme;
	}
	public void setLinkMainTheme(String linkMainTheme) {
		this.linkMainTheme = linkMainTheme;
	}
	public String getGoodsNm() {
		return goodsNm;
	}
	public void setGoodsNm(String goodsNm) {
		this.goodsNm = goodsNm;
	}
	public String getMakerNm() {
		return makerNm;
	}
	public void setMakerNm(String makerNm) {
		this.makerNm = makerNm;
	}
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	
	public int getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(int totalStock) {
		this.totalStock = totalStock;
	}
	public String getRepresentImg() {
		return representImg;
	}
	public void setRepresentImg(String representImg) {
		this.representImg = representImg;
	}
	public BigDecimal getDeliveryCost() {
		return deliveryCost;
	}
	public void setDeliveryCost(BigDecimal deliveryCost) {
		this.deliveryCost = deliveryCost;
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
	public BigDecimal getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(BigDecimal optionPrice) {
		this.optionPrice = optionPrice;
	}
	public BigDecimal getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}
	@Override
	public String toString() {
		return "cartDTO [sno=" + sno + ", siteKey=" + siteKey + ", memNo=" + memNo + ", scmNo=" + scmNo + ", goodsNo="
				+ goodsNo + ", optionNo=" + optionNo + ", goodsOptionNm=" + goodsOptionNm + ", goodsCnt=" + goodsCnt
				+ ", tmpOrderNo=" + tmpOrderNo + ", useBundleGoods=" + useBundleGoods + ", linkMainTheme="
				+ linkMainTheme + ", goodsNm=" + goodsNm + ", makerNm=" + makerNm + ", goodsPrice=" + goodsPrice
				+ ", optionPrice=" + optionPrice + ", sumPrice=" + sumPrice + ", totalStock=" + totalStock
				+ ", representImg=" + representImg + ", deliveryCost=" + deliveryCost + ", deliveryCostAddJeju="
				+ deliveryCostAddJeju + ", deliveryCostAdd=" + deliveryCostAdd + ", regDt=" + regDt + ", modDt=" + modDt
				+ "]";
	}
	
}
