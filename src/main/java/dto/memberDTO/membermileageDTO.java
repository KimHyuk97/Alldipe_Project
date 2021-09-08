package dto.memberDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class membermileageDTO {
	private int sno;
	private int memNo;
	private int managerNo;
	private String handleMode;
	private String handleCd;
	private String handleNo;
	private BigDecimal beforeMileage;
	private BigDecimal afterMileage;
	private BigDecimal mileage;
	private String reasonCd;
	private String contents;
	private String deleteFl;
	private Timestamp deleteScheduleDt;
	private Timestamp deleteDt;
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
	public BigDecimal getBeforeMileage() {
		return beforeMileage;
	}
	public void setBeforeMileage(BigDecimal beforeMileage) {
		this.beforeMileage = beforeMileage;
	}
	public BigDecimal getAfterMileage() {
		return afterMileage;
	}
	public void setAfterMileage(BigDecimal afterMileage) {
		this.afterMileage = afterMileage;
	}
	public BigDecimal getMileage() {
		return mileage;
	}
	public void setMileage(BigDecimal mileage) {
		this.mileage = mileage;
	}
	public String getReasonCd() {
		return reasonCd;
	}
	public void setReasonCd(String reasonCd) {
		this.reasonCd = reasonCd;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDeleteFl() {
		return deleteFl;
	}
	public void setDeleteFl(String deleteFl) {
		this.deleteFl = deleteFl;
	}
	public Timestamp getDeleteScheduleDt() {
		return deleteScheduleDt;
	}
	public void setDeleteScheduleDt(Timestamp deleteScheduleDt) {
		this.deleteScheduleDt = deleteScheduleDt;
	}
	public Timestamp getDeleteDt() {
		return deleteDt;
	}
	public void setDeleteDt(Timestamp deleteDt) {
		this.deleteDt = deleteDt;
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
		return "membermileageDTO [sno=" + sno + ", memNo=" + memNo + ", managerNo=" + managerNo + ", handleMode="
				+ handleMode + ", handleCd=" + handleCd + ", handleNo=" + handleNo + ", beforeMileage=" + beforeMileage
				+ ", afterMileage=" + afterMileage + ", mileage=" + mileage + ", reasonCd=" + reasonCd + ", contents="
				+ contents + ", deleteFl=" + deleteFl + ", deleteScheduleDt=" + deleteScheduleDt + ", deleteDt="
				+ deleteDt + ", regIp=" + regIp + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
}
