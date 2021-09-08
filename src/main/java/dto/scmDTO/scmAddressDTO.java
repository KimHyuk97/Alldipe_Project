package dto.scmDTO;

import java.sql.Timestamp;

public class scmAddressDTO {

	private int sno;
	private String addrNm;
	private int scmNo;
	private String zonecode;
	private String address;
	private String addressSub;
	private String extraAddress;
	private Timestamp regDt;
	
	
	public Timestamp getRegDt() {
		return regDt;
	}
	public void setRegDt(Timestamp regDt) {
		this.regDt = regDt;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	public int getScmNo() {
		return scmNo;
	}
	public void setScmNo(int scmNo) {
		this.scmNo = scmNo;
	}
	
	public String getAddrNm() {
		return addrNm;
	}
	public void setAddrNm(String addrNm) {
		this.addrNm = addrNm;
	}
	public String getZonecode() {
		return zonecode;
	}
	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
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
	public String getExtraAddress() {
		return extraAddress;
	}
	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}
}
