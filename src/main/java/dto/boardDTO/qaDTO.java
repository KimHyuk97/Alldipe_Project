package dto.boardDTO;

import java.sql.Timestamp;

public class qaDTO {

	private int sno;
	private int memNo;
	private boolean partnersFl;
	private String writerNm;
	private String writerId;
	private String writerIp;
	private String theme;
	private String cate;
	private String title;
	private String contents;
	private String file1;
	private boolean secret;
	private String writerPw;
	private int viewCnt;
	private int goodsNo;
	private int scmNo;
	private String orderNo;
	private boolean replyStatus;
	private boolean isDelete;
	private boolean smsFl;
	private boolean emailFl;
	private boolean privacyuseapproval;
	private boolean othersofferapproval;
	private Timestamp regDt;
	private Timestamp modDt;
	private int answerManagerNo;
	private String answerManagerId;
	private Timestamp answerModDt;
	private String answerContents;
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
	public boolean isPartnersFl() {
		return partnersFl;
	}
	public void setPartnersFl(boolean partnersFl) {
		this.partnersFl = partnersFl;
	}
	public String getWriterNm() {
		return writerNm;
	}
	public void setWriterNm(String writerNm) {
		this.writerNm = writerNm;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getWriterIp() {
		return writerIp;
	}
	public void setWriterIp(String writerIp) {
		this.writerIp = writerIp;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public boolean isSecret() {
		return secret;
	}
	public void setSecret(boolean isSecret) {
		this.secret = isSecret;
	}
	public String getWriterPw() {
		return writerPw;
	}
	public void setWriterPw(String writerPw) {
		this.writerPw = writerPw;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public int getScmNo() {
		return scmNo;
	}
	public void setScmNo(int scmNo) {
		this.scmNo = scmNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public boolean isReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(boolean replyStatus) {
		this.replyStatus = replyStatus;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public boolean isSmsFl() {
		return smsFl;
	}
	public void setSmsFl(boolean smsFl) {
		this.smsFl = smsFl;
	}
	public boolean isEmailFl() {
		return emailFl;
	}
	public void setEmailFl(boolean emailFl) {
		this.emailFl = emailFl;
	}
	public boolean isPrivacyuseapproval() {
		return privacyuseapproval;
	}
	public void setPrivacyuseapproval(boolean privacyuseapproval) {
		this.privacyuseapproval = privacyuseapproval;
	}
	public boolean isOthersofferapproval() {
		return othersofferapproval;
	}
	public void setOthersofferapproval(boolean othersofferapproval) {
		this.othersofferapproval = othersofferapproval;
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
	public int getAnswerManagerNo() {
		return answerManagerNo;
	}
	public void setAnswerManagerNo(int answerManagerNo) {
		this.answerManagerNo = answerManagerNo;
	}
	public String getAnswerManagerId() {
		return answerManagerId;
	}
	public void setAnswerManagerId(String answerManagerId) {
		this.answerManagerId = answerManagerId;
	}
	public Timestamp getAnswerModDt() {
		return answerModDt;
	}
	public void setAnswerModDt(Timestamp answerModDt) {
		this.answerModDt = answerModDt;
	}
	public String getAnswerContents() {
		return answerContents;
	}
	public void setAnswerContents(String answerContents) {
		this.answerContents = answerContents;
	}
	@Override
	public String toString() {
		return "qaDTO [sno=" + sno + ", memNo=" + memNo + ", partnersFl=" + partnersFl + ", writerNm=" + writerNm
				+ ", writerId=" + writerId + ", writerIp=" + writerIp + ", theme=" + theme + ", cate=" + cate
				+ ", title=" + title + ", contents=" + contents + ", file1=" + file1 + ", secret=" + secret
				+ ", writerPw=" + writerPw + ", viewCnt=" + viewCnt + ", goodsNo=" + goodsNo + ", scmNo=" + scmNo
				+ ", orderNo=" + orderNo + ", replyStatus=" + replyStatus + ", isDelete=" + isDelete + ", smsFl="
				+ smsFl + ", emailFl=" + emailFl + ", privacyuseapproval=" + privacyuseapproval
				+ ", othersofferapproval=" + othersofferapproval + ", regDt=" + regDt + ", modDt=" + modDt
				+ ", answerManagerNo=" + answerManagerNo + ", answerManagerId=" + answerManagerId + ", answerModDt="
				+ answerModDt + ", answerContents=" + answerContents + "]";
	}
	
	
	
}
