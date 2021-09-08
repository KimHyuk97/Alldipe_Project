package dto.myPageDTO;

import java.sql.Timestamp;

public class memberMileageDTO {
	private int sno;			//일련번호
	private int memNo;			//회원번호
	private int managerNo;		//관리자고유번호
	private String handleMode;	//처리모드
	private String handleCd;    //처리코드(주문번호, 게시판코드,신규회원추천아이디)
	private String handleNo;	//처리번호(상품번호,게시물번호)
	private double beforeMileage; //이전 적립금
	private double afterMileage; //이후 적립금
	private double mileage; 	//적립금
	private String reasonCd;	// 지금/차감사유 코드
	private String contents;	// 지금/차감사유
	private String deleteFl;	//소멸여부,사용완료,사용중
	private Timestamp deleteScheduleDt;	//소멸예정일
	private Timestamp deleteDt;	//소멸일
	private String reglp;		//등록시ip
	private Timestamp regDt;	// 등록일
	private Timestamp modDt;	// 수정일
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
	public double getBeforeMileage() {
		return beforeMileage;
	}
	public void setBeforeMileage(double beforeMileage) {
		this.beforeMileage = beforeMileage;
	}
	public double getAfterMileage() {
		return afterMileage;
	}
	public void setAfterMileage(double afterMileage) {
		this.afterMileage = afterMileage;
	}
	public double getMileage() {
		return mileage;
	}
	public void setMileage(double mileage) {
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
	public String getReglp() {
		return reglp;
	}
	public void setReglp(String reglp) {
		this.reglp = reglp;
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
		return "memberMileageDTO [sno=" + sno + ", memNo=" + memNo + ", managerNo=" + managerNo + ", handleMode="
				+ handleMode + ", handleCd=" + handleCd + ", handleNo=" + handleNo + ", beforeMileage=" + beforeMileage
				+ ", afterMileage=" + afterMileage + ", mileage=" + mileage + ", reasonCd=" + reasonCd + ", contents="
				+ contents + ", deleteFl=" + deleteFl + ", deleteScheduleDt=" + deleteScheduleDt + ", deleteDt="
				+ deleteDt + ", reglp=" + reglp + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
	
	
	
}
