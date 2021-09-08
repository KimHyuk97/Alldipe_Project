package dto.memberDTO;

import java.sql.Timestamp;

public class memberhistoryDTO {
	private int sno;
	private int memNo;
	private String processor;
	private int managerNo;
	private String processorIp;
	private String updateColumn;
	private String beforeValue;
	private String afterValue;
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
	public String getProcessor() {
		return processor;
	}
	public void setProcessor(String processor) {
		this.processor = processor;
	}
	public int getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(int managerNo) {
		this.managerNo = managerNo;
	}
	public String getProcessorIp() {
		return processorIp;
	}
	public void setProcessorIp(String processorIp) {
		this.processorIp = processorIp;
	}
	public String getUpdateColumn() {
		return updateColumn;
	}
	public void setUpdateColumn(String updateColumn) {
		this.updateColumn = updateColumn;
	}
	public String getBeforeValue() {
		return beforeValue;
	}
	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}
	public String getAfterValue() {
		return afterValue;
	}
	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
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
		return "memberhistoryDTO [sno=" + sno + ", memNo=" + memNo + ", processor=" + processor + ", managerNo="
				+ managerNo + ", processorIp=" + processorIp + ", updateColumn=" + updateColumn + ", beforeValue="
				+ beforeValue + ", afterValue=" + afterValue + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
}
