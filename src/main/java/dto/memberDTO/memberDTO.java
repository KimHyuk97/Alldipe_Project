package dto.memberDTO;

import java.sql.Date;
import java.sql.Timestamp;

public class memberDTO {

	private int memNo;							//  사용자 번호
	private String memId;						//  사용자 아이디
	private int gradeSno;						//  회원등급
	private Timestamp gradeModDt;				//  등급 수정일
	private Timestamp gradeValidDt;				//	등급 유효일
	private String memNm;						//	이름
	private String memPw;						//	비밀번호
	private Timestamp changPwDt;				//  비밀번호 변경일	
	private Timestamp guidePwDt;				//	비밀번호 변경 안내일
	private boolean appFl;						//	승인여뷰
	private Timestamp approvalDt;				//	가입승인일
	private String memFl;						//	회원구분
	private Timestamp entryBenefitOfferDt;		//	가입 혜택 지급일
	private String sexFl;						//	성별
	private java.sql.Date birthDt;						//	생년월일
	private boolean birthEventFl;				//	생일이벤트 여부
	private String email;						//	이메일	
	private String zonecode;					//	우편번호 5자리
	private String address;						//	주소
	private String addressSub;					//	상세주소
	private String phoneCountryCode;			//	전화번호 국가코드
	private String phone;						//	전화번호
	private String cellPhoneCountryCode;		//	휴대전화 국가코드
	private String cellPhone;					//	휴대전화
	private String fax;							//	fax
	private boolean partnersFl;					//  파트너스구분
	private String company;						//	회사명
	private boolean approvalFl1;				//	판매 이용약관 동의
	private boolean approvalFl2;		        //	전자금융거래이용약관 동의
	private boolean approvalFl3;	            //	판매자 개인정보 이용 동의
	private String service;						//	업태
	private String item;						//	종목
	private String busiNo;						//	사업자번호
	private String ceo;							//	대표자
	private String comZonecode;					//	사업장 우편번호 5자리
	private String comAddress;					//	사업장 주소
	private String comAddressSub;				//	사업장 상세주소
	private float mileage;						//	적립금
	private float deposit;						//	예치금
	private float freedeposit;					//	무상예치금
	private boolean maillingFl;					//	메일 수신 동의
	private boolean smsFl;						//	SMS 수신 동의
	private boolean marriFl;					//	결혼 여부
	private java.sql.Date marriDt;						//	결혼기념일
	private String job;							//	직업
	private String interest;					//	관심분야
	private boolean reEntryFl;					//	재가입 여부
	private String entryPath;					//	가입 경로
	private boolean loginLimit;					//	로그인 제한
	private Timestamp lastLoginDt;				//	마지막 로그인 날짜
	private String lastLoginIp;					//	마지막 로그인 IP
	private Timestamp lastSaleDt;				//	마지막 구입 날짜
	private int loginCnt;						//	로그인 횟수
	private int saleCnt;						//	구매 횟수
	private float saleAmt;						//	총 구매 금액
	private String memo;						//	남기는 말
	private String recommId;					//	추천인 ID
	private boolean recommFl;					//	추천인 등록 여부
	private boolean privateUtilizationFl;
	private boolean privateFinanceFl;
	private boolean privateApprovalFl;			//	개인정보 수집 및 이용 필수
	private boolean privateApprovalOptionFl;	//	개인정보 수집 및 이용 선택
	private boolean privateOfferFl;				//	개인정보 동의 제 3자 제공
	private boolean privateConsignFl;			//	개인정보 동의 취급업무 위탁
	private boolean under14ConsentFl;			//	만 14세 이상 동의
	private boolean expirationFl;				//	개인정보 유효기간
	private boolean foreigner;					//	내외국인 구분
	private boolean adultFl;					//	성인 여부
	private Timestamp adultConfirmDt;			//	성인 인증 시각
	private String rncheck;						//	본인인증방법
	private String adminMemo;					//	관리자 메모
	private boolean sleepFl;					//	휴면회원 여부
	private boolean sleepMailFl;				//	휴면전환 안내메일 발송여부
	private boolean sleepSmsFl;					//	휴면전환 안내 SMS 발송 여부
	private Timestamp sleepWakeDt;				//	휴면 해제일
	private boolean simpleJoinFl;				//	회원가입 이벤트 참여
	private String perferBrand; 				//  선호브랜드
	private Timestamp regDt;					//	등록일자
	private Timestamp modDt;					//	변경일자
	private String deleteFl;					//  탈퇴여부
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public int getGradeSno() {
		return gradeSno;
	}
	public void setGradeSno(int gradeSno) {
		this.gradeSno = gradeSno;
	}
	public Timestamp getGradeModDt() {
		return gradeModDt;
	}
	public void setGradeModDt(Timestamp gradeModDt) {
		this.gradeModDt = gradeModDt;
	}
	public Timestamp getGradeValidDt() {
		return gradeValidDt;
	}
	public void setGradeValidDt(Timestamp gradeValidDt) {
		this.gradeValidDt = gradeValidDt;
	}
	public String getMemNm() {
		return memNm;
	}
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public Timestamp getChangPwDt() {
		return changPwDt;
	}
	public void setChangPwDt(Timestamp changPwDt) {
		this.changPwDt = changPwDt;
	}
	public Timestamp getGuidePwDt() {
		return guidePwDt;
	}
	public void setGuidePwDt(Timestamp guidePwDt) {
		this.guidePwDt = guidePwDt;
	}
	public boolean isAppFl() {
		return appFl;
	}
	public void setAppFl(boolean appFl) {
		this.appFl = appFl;
	}
	public Timestamp getApprovalDt() {
		return approvalDt;
	}
	public void setApprovalDt(Timestamp approvalDt) {
		this.approvalDt = approvalDt;
	}
	public String getMemFl() {
		return memFl;
	}
	public void setMemFl(String memFl) {
		this.memFl = memFl;
	}
	public Timestamp getEntryBenefitOfferDt() {
		return entryBenefitOfferDt;
	}
	public void setEntryBenefitOfferDt(Timestamp entryBenefitOfferDt) {
		this.entryBenefitOfferDt = entryBenefitOfferDt;
	}
	public String getSexFl() {
		return sexFl;
	}
	public void setSexFl(String sexFl) {
		this.sexFl = sexFl;
	}
	public java.sql.Date getBirthDt() {
		return birthDt;
	}
	public void setBirthDt(java.sql.Date birthDt) {
		this.birthDt = birthDt;
	}
	public boolean isBirthEventFl() {
		return birthEventFl;
	}
	public void setBirthEventFl(boolean birthEventFl) {
		this.birthEventFl = birthEventFl;
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
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressSub() {
		return addressSub;
	}
	public void setAddressSub(String addressSub) {
		this.addressSub = addressSub;
	}
	public String getPhoneCountryCode() {
		return phoneCountryCode;
	}
	public void setPhoneCountryCode(String phoneCountryCode) {
		this.phoneCountryCode = phoneCountryCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCellPhoneCountryCode() {
		return cellPhoneCountryCode;
	}
	public void setCellPhoneCountryCode(String cellPhoneCountryCode) {
		this.cellPhoneCountryCode = cellPhoneCountryCode;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public boolean isPartnersFl() {
		return partnersFl;
	}
	public void setPartnersFl(boolean partnersFl) {
		this.partnersFl = partnersFl;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public boolean isApprovalFl1() {
		return approvalFl1;
	}
	public void setApprovalFl1(boolean approvalFl1) {
		this.approvalFl1 = approvalFl1;
	}
	public boolean isApprovalFl2() {
		return approvalFl2;
	}
	public void setApprovalFl2(boolean approvalFl2) {
		this.approvalFl2 = approvalFl2;
	}
	public boolean isApprovalFl3() {
		return approvalFl3;
	}
	public void setApprovalFl3(boolean approvalFl3) {
		this.approvalFl3 = approvalFl3;
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
	public String getBusiNo() {
		return busiNo;
	}
	public void setBusiNo(String busiNo) {
		this.busiNo = busiNo;
	}
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getComZonecode() {
		return comZonecode;
	}
	public void setComZonecode(String comZonecode) {
		this.comZonecode = comZonecode;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	public String getComAddressSub() {
		return comAddressSub;
	}
	public void setComAddressSub(String comAddressSub) {
		this.comAddressSub = comAddressSub;
	}
	public float getMileage() {
		return mileage;
	}
	public void setMileage(float mileage) {
		this.mileage = mileage;
	}
	public float getDeposit() {
		return deposit;
	}
	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}
	public float getFreedeposit() {
		return freedeposit;
	}
	public void setFreedeposit(float freedeposit) {
		this.freedeposit = freedeposit;
	}
	public boolean isMaillingFl() {
		return maillingFl;
	}
	public void setMaillingFl(boolean maillingFl) {
		this.maillingFl = maillingFl;
	}
	public boolean isSmsFl() {
		return smsFl;
	}
	public void setSmsFl(boolean smsFl) {
		this.smsFl = smsFl;
	}
	public boolean isMarriFl() {
		return marriFl;
	}
	public void setMarriFl(boolean marriFl) {
		this.marriFl = marriFl;
	}
	public java.sql.Date getMarriDt() {
		return marriDt;
	}
	public void setMarriDt(java.sql.Date marriDt) {
		this.marriDt = marriDt;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public boolean isReEntryFl() {
		return reEntryFl;
	}
	public void setReEntryFl(boolean reEntryFl) {
		this.reEntryFl = reEntryFl;
	}
	public String getEntryPath() {
		return entryPath;
	}
	public void setEntryPath(String entryPath) {
		this.entryPath = entryPath;
	}
	public boolean isLoginLimit() {
		return loginLimit;
	}
	public void setLoginLimit(boolean loginLimit) {
		this.loginLimit = loginLimit;
	}
	public Timestamp getLastLoginDt() {
		return lastLoginDt;
	}
	public void setLastLoginDt(Timestamp lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Timestamp getLastSaleDt() {
		return lastSaleDt;
	}
	public void setLastSaleDt(Timestamp lastSaleDt) {
		this.lastSaleDt = lastSaleDt;
	}
	public int getLoginCnt() {
		return loginCnt;
	}
	public void setLoginCnt(int loginCnt) {
		this.loginCnt = loginCnt;
	}
	public int getSaleCnt() {
		return saleCnt;
	}
	public void setSaleCnt(int saleCnt) {
		this.saleCnt = saleCnt;
	}
	public float getSaleAmt() {
		return saleAmt;
	}
	public void setSaleAmt(float saleAmt) {
		this.saleAmt = saleAmt;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRecommId() {
		return recommId;
	}
	public void setRecommId(String recommId) {
		this.recommId = recommId;
	}
	public boolean isRecommFl() {
		return recommFl;
	}
	public void setRecommFl(boolean recommFl) {
		this.recommFl = recommFl;
	}
	public boolean isPrivateUtilizationFl() {
		return privateUtilizationFl;
	}
	public void setPrivateUtilizationFl(boolean privateUtilizationFl) {
		this.privateUtilizationFl = privateUtilizationFl;
	}
	public boolean isPrivateFinanceFl() {
		return privateFinanceFl;
	}
	public void setPrivateFinanceFl(boolean privateFinanceFl) {
		this.privateFinanceFl = privateFinanceFl;
	}
	public boolean isPrivateApprovalFl() {
		return privateApprovalFl;
	}
	public void setPrivateApprovalFl(boolean privateApprovalFl) {
		this.privateApprovalFl = privateApprovalFl;
	}
	public boolean isPrivateApprovalOptionFl() {
		return privateApprovalOptionFl;
	}
	public void setPrivateApprovalOptionFl(boolean privateApprovalOptionFl) {
		this.privateApprovalOptionFl = privateApprovalOptionFl;
	}
	public boolean isPrivateOfferFl() {
		return privateOfferFl;
	}
	public void setPrivateOfferFl(boolean privateOfferFl) {
		this.privateOfferFl = privateOfferFl;
	}
	public boolean isPrivateConsignFl() {
		return privateConsignFl;
	}
	public void setPrivateConsignFl(boolean privateConsignFl) {
		this.privateConsignFl = privateConsignFl;
	}
	public boolean isUnder14ConsentFl() {
		return under14ConsentFl;
	}
	public void setUnder14ConsentFl(boolean under14ConsentFl) {
		this.under14ConsentFl = under14ConsentFl;
	}
	public boolean isExpirationFl() {
		return expirationFl;
	}
	public void setExpirationFl(boolean expirationFl) {
		this.expirationFl = expirationFl;
	}
	public boolean isForeigner() {
		return foreigner;
	}
	public void setForeigner(boolean foreigner) {
		this.foreigner = foreigner;
	}
	public boolean isAdultFl() {
		return adultFl;
	}
	public void setAdultFl(boolean adultFl) {
		this.adultFl = adultFl;
	}
	public Timestamp getAdultConfirmDt() {
		return adultConfirmDt;
	}
	public void setAdultConfirmDt(Timestamp adultConfirmDt) {
		this.adultConfirmDt = adultConfirmDt;
	}
	public String getRncheck() {
		return rncheck;
	}
	public void setRncheck(String rncheck) {
		this.rncheck = rncheck;
	}
	public String getAdminMemo() {
		return adminMemo;
	}
	public void setAdminMemo(String adminMemo) {
		this.adminMemo = adminMemo;
	}
	public boolean isSleepFl() {
		return sleepFl;
	}
	public void setSleepFl(boolean sleepFl) {
		this.sleepFl = sleepFl;
	}
	public boolean isSleepMailFl() {
		return sleepMailFl;
	}
	public void setSleepMailFl(boolean sleepMailFl) {
		this.sleepMailFl = sleepMailFl;
	}
	public boolean isSleepSmsFl() {
		return sleepSmsFl;
	}
	public void setSleepSmsFl(boolean sleepSmsFl) {
		this.sleepSmsFl = sleepSmsFl;
	}
	public Timestamp getSleepWakeDt() {
		return sleepWakeDt;
	}
	public void setSleepWakeDt(Timestamp sleepWakeDt) {
		this.sleepWakeDt = sleepWakeDt;
	}
	public boolean isSimpleJoinFl() {
		return simpleJoinFl;
	}
	public void setSimpleJoinFl(boolean simpleJoinFl) {
		this.simpleJoinFl = simpleJoinFl;
	}
	public String getPerferBrand() {
		return perferBrand;
	}
	public void setPerferBrand(String perferBrand) {
		this.perferBrand = perferBrand;
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
	public String getDeleteFl() {
		return deleteFl;
	}
	public void setDeleteFl(String deleteFl) {
		this.deleteFl = deleteFl;
	}
	@Override
	public String toString() {
		return "memberDTO [memNo=" + memNo + ", memId=" + memId + ", gradeSno=" + gradeSno + ", gradeModDt="
				+ gradeModDt + ", gradeValidDt=" + gradeValidDt + ", memNm=" + memNm + ", memPw=" + memPw
				+ ", changPwDt=" + changPwDt + ", guidePwDt=" + guidePwDt + ", appFl=" + appFl + ", approvalDt="
				+ approvalDt + ", memFl=" + memFl + ", entryBenefitOfferDt=" + entryBenefitOfferDt + ", sexFl=" + sexFl
				+ ", birthDt=" + birthDt + ", birthEventFl=" + birthEventFl + ", email=" + email + ", zonecode="
				+ zonecode + ", address=" + address + ", addressSub=" + addressSub + ", phoneCountryCode="
				+ phoneCountryCode + ", phone=" + phone + ", cellPhoneCountryCode=" + cellPhoneCountryCode
				+ ", cellPhone=" + cellPhone + ", fax=" + fax + ", partnersFl=" + partnersFl + ", company=" + company
				+ ", approvalFl1=" + approvalFl1 + ", approvalFl2=" + approvalFl2 + ", approvalFl3=" + approvalFl3
				+ ", service=" + service + ", item=" + item + ", busiNo=" + busiNo + ", ceo=" + ceo + ", comZonecode="
				+ comZonecode + ", comAddress=" + comAddress + ", comAddressSub=" + comAddressSub + ", mileage="
				+ mileage + ", deposit=" + deposit + ", freedeposit=" + freedeposit + ", maillingFl=" + maillingFl
				+ ", smsFl=" + smsFl + ", marriFl=" + marriFl + ", marriDt=" + marriDt + ", job=" + job + ", interest="
				+ interest + ", reEntryFl=" + reEntryFl + ", entryPath=" + entryPath + ", loginLimit=" + loginLimit
				+ ", lastLoginDt=" + lastLoginDt + ", lastLoginIp=" + lastLoginIp + ", lastSaleDt=" + lastSaleDt
				+ ", loginCnt=" + loginCnt + ", saleCnt=" + saleCnt + ", saleAmt=" + saleAmt + ", memo=" + memo
				+ ", recommId=" + recommId + ", recommFl=" + recommFl + ", privateUtilizationFl=" + privateUtilizationFl
				+ ", privateFinanceFl=" + privateFinanceFl + ", privateApprovalFl=" + privateApprovalFl
				+ ", privateApprovalOptionFl=" + privateApprovalOptionFl + ", privateOfferFl=" + privateOfferFl
				+ ", privateConsignFl=" + privateConsignFl + ", under14ConsentFl=" + under14ConsentFl
				+ ", expirationFl=" + expirationFl + ", foreigner=" + foreigner + ", adultFl=" + adultFl
				+ ", adultConfirmDt=" + adultConfirmDt + ", rncheck=" + rncheck + ", adminMemo=" + adminMemo
				+ ", sleepFl=" + sleepFl + ", sleepMailFl=" + sleepMailFl + ", sleepSmsFl=" + sleepSmsFl
				+ ", sleepWakeDt=" + sleepWakeDt + ", simpleJoinFl=" + simpleJoinFl + ", perferBrand=" + perferBrand
				+ ", regDt=" + regDt + ", modDt=" + modDt + ", deleteFl=" + deleteFl + "]";
	}
	
	
	
}
