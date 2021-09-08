package dto.partners.deposit;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class depositDTO {

	private int sno;
	private int memNo;
	private int scmNo;
	private int category;
	private boolean paidFl;
	private BigDecimal payment;
	private BigDecimal deposit;
	private String chargeInfo;
	private String eventInfo;
	private String ip;
	private Timestamp regDt;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getScmNo() {
		return scmNo;
	}
	public void setScmNo(int scmNo) {
		this.scmNo = scmNo;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public String getChargeInfo() {
		return chargeInfo;
	}
	public void setChargeInfo(String chargeInfo) {
		this.chargeInfo = chargeInfo;
	}
	public String getEventInfo() {
		return eventInfo;
	}
	public void setEventInfo(String eventInfo) {
		this.eventInfo = eventInfo;
	}
	public Timestamp getRegDt() {
		return regDt;
	}
	public void setRegDt(Timestamp regDt) {
		this.regDt = regDt;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public boolean isPaidFl() {
		return paidFl;
	}
	public void setPaidFl(boolean paidFl) {
		this.paidFl = paidFl;
	}
	
		
}
