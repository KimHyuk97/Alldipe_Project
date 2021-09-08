package dto.memberDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class memberdepositDTO {
	private int sno;
	private int memNo;
	private int managerNo;
	private String handleMode;
	private String handleCd;
	private String handleNo;
	private BigDecimal beforeDeposit;
	private BigDecimal afterDeposit;
	private BigDecimal deposit;
	private String reasonCd;
	private String regIp;
	private Timestamp regDt;
	private Timestamp modDt;
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
	public int getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}
	public String getHandleMode() {
		return handleMode;
	}
	public void setHandleMode(String handleMode) {
		this.handleMode = handleMode;
	}
	public String getHandleCd() {
		return handleCd;
	}
	public void setHandleCd(String handleCd) {
		this.handleCd = handleCd;
	}
	public String getHandleNo() {
		return handleNo;
	}
	public void setHandleNo(String handleNo) {
		this.handleNo = handleNo;
	}
	public BigDecimal getBeforeDeposit() {
		return beforeDeposit;
	}
	public void setBeforeDeposit(BigDecimal beforeDeposit) {
		this.beforeDeposit = beforeDeposit;
	}
	public BigDecimal getAfterDeposit() {
		return afterDeposit;
	}
	public void setAfterDeposit(BigDecimal afterDeposit) {
		this.afterDeposit = afterDeposit;
	}
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public String getReasonCd() {
		return reasonCd;
	}
	public void setReasonCd(String reasonCd) {
		this.reasonCd = reasonCd;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
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
		return "memberdepositDTO [sno=" + sno + ", memNo=" + memNo + ", managerNo=" + managerNo + ", handleMode="
				+ handleMode + ", handleCd=" + handleCd + ", handleNo=" + handleNo + ", beforeDeposit=" + beforeDeposit
				+ ", afterDeposit=" + afterDeposit + ", deposit=" + deposit + ", reasonCd=" + reasonCd + ", regIp="
				+ regIp + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
	
	
}
