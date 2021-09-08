package dto.goodsDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class goodsOptionDTO {

	private int sno;
	private int goodsNo;
	private int optionNo;
	private int scmNo;
	private String optionNm1;
	private String optionValue1;
	private String optionNm2;
	private String optionValue2;
	private String optionNm3;
	private String optionValue3;
	private String optionNm4;
	private String optionValue4;
	private String optionNm5;
	private String optionValue5;
	private String sellerCd;
	private BigDecimal optionPrice;
	private BigDecimal optionFixedPrice;
	private boolean optionViewFl;
	private boolean optionSellFl;
	private int stockCnt;
	private String optionMemo;
	private Timestamp regDt;
	private Timestamp modDt;
	
	
	public int getScmNo() {
		return scmNo;
	}
	public void setScmNo(int scmNo) {
		this.scmNo = scmNo;
	}
	public String getOptionMemo() {
		return optionMemo;
	}
	public void setOptionMemo(String optionMemo) {
		this.optionMemo = optionMemo;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public String getOptionNm1() {
		return optionNm1;
	}
	public void setOptionNm1(String optionNm1) {
		this.optionNm1 = optionNm1;
	}
	public String getOptionValue1() {
		return optionValue1;
	}
	public void setOptionValue1(String optionValue1) {
		this.optionValue1 = optionValue1;
	}
	public String getOptionNm2() {
		return optionNm2;
	}
	public void setOptionNm2(String optionNm2) {
		this.optionNm2 = optionNm2;
	}
	public String getOptionValue2() {
		return optionValue2;
	}
	public void setOptionValue2(String optionValue2) {
		this.optionValue2 = optionValue2;
	}
	public String getOptionNm3() {
		return optionNm3;
	}
	public void setOptionNm3(String optionNm3) {
		this.optionNm3 = optionNm3;
	}
	public String getOptionValue3() {
		return optionValue3;
	}
	public void setOptionValue3(String optionValue3) {
		this.optionValue3 = optionValue3;
	}
	public String getOptionNm4() {
		return optionNm4;
	}
	public void setOptionNm4(String optionNm4) {
		this.optionNm4 = optionNm4;
	}
	public String getOptionValue4() {
		return optionValue4;
	}
	public void setOptionValue4(String optionValue4) {
		this.optionValue4 = optionValue4;
	}
	public String getOptionNm5() {
		return optionNm5;
	}
	public void setOptionNm5(String optionNm5) {
		this.optionNm5 = optionNm5;
	}
	public String getOptionValue5() {
		return optionValue5;
	}
	public void setOptionValue5(String optionValue5) {
		this.optionValue5 = optionValue5;
	}
	public BigDecimal getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(BigDecimal optionPrice) {
		this.optionPrice = optionPrice;
	}
	
	public BigDecimal getOptionFixedPrice() {
		return optionFixedPrice;
	}
	public void setOptionFixedPrice(BigDecimal optionFixedPrice) {
		this.optionFixedPrice = optionFixedPrice;
	}
	public boolean isOptionViewFl() {
		return optionViewFl;
	}
	public void setOptionViewFl(boolean optionViewFl) {
		this.optionViewFl = optionViewFl;
	}
	public boolean isOptionSellFl() {
		return optionSellFl;
	}
	public void setOptionSellFl(boolean optionSellFl) {
		this.optionSellFl = optionSellFl;
	}
	
	public String getSellerCd() {
		return sellerCd;
	}
	public void setSellerCd(String sellerCd) {
		this.sellerCd = sellerCd;
	}
	public int getStockCnt() {
		return stockCnt;
	}
	public void setStockCnt(int stockCnt) {
		this.stockCnt = stockCnt;
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
		return "goodsOptionDTO [sno=" + sno + ", goodsNo=" + goodsNo + ", optionNo=" + optionNo + ", scmNo=" + scmNo
				+ ", optionNm1=" + optionNm1 + ", optionValue1=" + optionValue1 + ", optionNm2=" + optionNm2
				+ ", optionValue2=" + optionValue2 + ", optionNm3=" + optionNm3 + ", optionValue3=" + optionValue3
				+ ", optionNm4=" + optionNm4 + ", optionValue4=" + optionValue4 + ", optionNm5=" + optionNm5
				+ ", optionValue5=" + optionValue5 + ", sellerCd=" + sellerCd + ", optionPrice=" + optionPrice
				+ ", optionFixedPrice=" + optionFixedPrice + ", optionViewFl=" + optionViewFl + ", optionSellFl="
				+ optionSellFl + ", stockCnt=" + stockCnt + ", optionMemo=" + optionMemo + ", regDt=" + regDt
				+ ", modDt=" + modDt + "]";
	}

	
}
