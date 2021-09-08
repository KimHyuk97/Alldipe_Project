package dto.myPageDTO;

import java.sql.Timestamp;

public class myPageDTO {
	private int dNo; 							//배송지번호
	private int memNo; 							//사용자번호
	private String deliveryFl;					//기본배송지 여부
	private String zonecode; 					//주소
	private String address; 					//주소
	private String addressSub; 					//상세주소
	private String getName; 					//받으실분
	private String phone; 						//핸드폰
	private Timestamp regDt;					//	등록일자
	private Timestamp modDt;					//	변경일자
	
	
	public int getdNo() {
		return dNo;
	}
	public void setdNo(int dNo) {
		this.dNo = dNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getDeliveryFl() {
		return deliveryFl;
	}
	public void setDeliveryFl(String deliveryFl) {
		this.deliveryFl = deliveryFl;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressSub() {
		return addressSub;
	}
	public void setAddressSub(String addressSub) {
		this.addressSub = addressSub;
	}
	public String getGetName() {
		return getName;
	}
	public void setGetName(String getName) {
		this.getName = getName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
	public String getZonecode() {
		return zonecode;
	}
	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}
	@Override
	public String toString() {
		return "myPageDTO [dNo=" + dNo + ", memNo=" + memNo + ", deliveryFl=" + deliveryFl + ", zonecode=" + zonecode
				+ ", address=" + address + ", addressSub=" + addressSub + ", getName=" + getName + ", phone=" + phone
				+ ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
}
