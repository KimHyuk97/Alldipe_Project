package dto.scmDTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class scmDTO {

	private int scmNo;
	private String companyNm;
	private int memNo;
	private String scmState;
	private BigDecimal scmCommission;
	private BigDecimal scmCommissionDelivery;
	private String imageStorage;
	private boolean certificationFl;
	private String applyFl;
	private String ceoNm;
	private String businessNo;
	private String businessLicenseImg;
	private String onlineOrderSerial;
	private String service;
	private String item;
	private String email;
	private String zonecode;
	private String address;
	private String addressSub;
	private String phone;
	private String centerphone;
	private String fax;
	private String account;
	private boolean delFl;
	private Timestamp regDt;
	private Timestamp modDt;
	private Timestamp deleteDt;
	
	public Timestamp getDeleteDt() {
		return deleteDt;
	}
	public void setDeleteDt(Timestamp deleteDt) {
		this.deleteDt = deleteDt;
	}
	public int getScmNo() {
		return scmNo;
	}
	public void setScmNo(int scmNo) {
		this.scmNo = scmNo;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	
	public String getScmState() {
		return scmState;
	}
	public void setScmState(String scmState) {
		this.scmState = scmState;
	}
	public BigDecimal getScmCommission() {
		return scmCommission;
	}
	public void setScmCommission(BigDecimal scmCommission) {
		this.scmCommission = scmCommission;
	}
	public BigDecimal getScmCommissionDelivery() {
		return scmCommissionDelivery;
	}
	public void setScmCommissionDelivery(BigDecimal scmCommissionDelivery) {
		this.scmCommissionDelivery = scmCommissionDelivery;
	}
	public String getImageStorage() {
		return imageStorage;
	}
	public void setImageStorage(String imageStorage) {
		this.imageStorage = imageStorage;
	}
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getBusinessLicenseImg() {
		return businessLicenseImg;
	}
	public void setBusinessLicenseImg(String businessLicenseImg) {
		this.businessLicenseImg = businessLicenseImg;
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	public boolean isCertificationFl() {
		return certificationFl;
	}
	public void setCertificationFl(boolean certificationFl) {
		this.certificationFl = certificationFl;
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
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCenterphone() {
		return centerphone;
	}
	public void setCenterphone(String centerphone) {
		this.centerphone = centerphone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public boolean isDelFl() {
		return delFl;
	}
	public void setDelFl(boolean delFl) {
		this.delFl = delFl;
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
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getApplyFl() {
		return applyFl;
	}
	public void setApplyFl(String applyFl) {
		this.applyFl = applyFl;
	}
	public String getOnlineOrderSerial() {
		return onlineOrderSerial;
	}
	public void setOnlineOrderSerial(String onlineOrderSerial) {
		this.onlineOrderSerial = onlineOrderSerial;
	}
	
	
	
}
