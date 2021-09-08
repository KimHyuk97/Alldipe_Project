package dto.memberDTO;

import java.sql.Timestamp;

public class memberhackoutDTO {
	private int sno;
	private String hackType;
	private String rejoinFl;
	private int memNo;
	private String dupeinfo;
	private String reasonCd;
	private String reasonDesc;
	private String adminMemo;
	private int managerSno;
	private String managerIp;
	private Timestamp hackDt;
	private String regIp;
	private String state;
	private Timestamp regDt;
	private Timestamp modDt;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getHackType() {
		return hackType;
	}
	public void setHackType(String hackType) {
		this.hackType = hackType;
	}
	public String getRejoinFl() {
		return rejoinFl;
	}
	public void setRejoinFl(String rejoinFl) {
		this.rejoinFl = rejoinFl;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getDupeinfo() {
		return dupeinfo;
	}
	public void setDupeinfo(String dupeinfo) {
		this.dupeinfo = dupeinfo;
	}
	public String getReasonCd() {
		return reasonCd;
	}
	public void setReasonCd(String reasonCd) {
		this.reasonCd = reasonCd;
	}
	public String getReasonDesc() {
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
	public String getAdminMemo() {
		return adminMemo;
	}
	public void setAdminMemo(String adminMemo) {
		this.adminMemo = adminMemo;
	}
	public int getManagerSno() {
		return managerSno;
	}
	public void setManagerSno(int managerSno) {
		this.managerSno = managerSno;
	}
	public String getManagerIp() {
		return managerIp;
	}
	public void setManagerIp(String managerIp) {
		this.managerIp = managerIp;
	}
	public Timestamp getHackDt() {
		return hackDt;
	}
	public void setHackDt(Timestamp hackDt) {
		this.hackDt = hackDt;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
		return "memberhackoutDTO [sno=" + sno + ", hackType=" + hackType + ", rejoinFl=" + rejoinFl + ", memNo=" + memNo
				+ ", dupeinfo=" + dupeinfo + ", reasonCd=" + reasonCd + ", reasonDesc=" + reasonDesc + ", adminMemo="
				+ adminMemo + ", managerSno=" + managerSno + ", managerIp=" + managerIp + ", hackDt=" + hackDt
				+ ", regIp=" + regIp + ", state=" + state + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
	
}
