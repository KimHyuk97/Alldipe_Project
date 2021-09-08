package dto.boardDTO;

import java.sql.Timestamp;

public class boardDTO {

	private int no;
	private int memNo;
	private String writerNm;
	private String writerId;
	private boolean partnersFl;
	private String writerIp;
	private String boardType;
	private String theme;
	private String cate;
	private int sort;
	private String title;
	private String contents;
	private String img;
	private boolean isSecret;
	private String password;
	private int viewCnt;
	private int goodsNo;
	private int scmNo;
	private String orderNo;
	private String replyStatus;
	private boolean isDelete;
	private Timestamp regDt;
	private Timestamp modDt;
	private Timestamp deleteDt;
	private int adminNo;
	private Timestamp answerDt;
	private String answerContent;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
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
	public boolean isPartnersFl() {
		return partnersFl;
	}
	public void setPartnersFl(boolean partnersFl) {
		this.partnersFl = partnersFl;
	}
	public String getWriterIp() {
		return writerIp;
	}
	public void setWriterIp(String writerIp) {
		this.writerIp = writerIp;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
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
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public boolean isSecret() {
		return isSecret;
	}
	public void setSecret(boolean isSecret) {
		this.isSecret = isSecret;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
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
	public Timestamp getDeleteDt() {
		return deleteDt;
	}
	public void setDeleteDt(Timestamp deleteDt) {
		this.deleteDt = deleteDt;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public Timestamp getAnswerDt() {
		return answerDt;
	}
	public void setAnswerDt(Timestamp answerDt) {
		this.answerDt = answerDt;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	@Override
	public String toString() {
		return "boardDTO [no=" + no + ", memNo=" + memNo + ", writerNm=" + writerNm + ", writerId=" + writerId
				+ ", partnersFl=" + partnersFl + ", writerIp=" + writerIp + ", boardType=" + boardType + ", theme="
				+ theme + ", cate=" + cate + ", sort=" + sort + ", title=" + title + ", contents=" + contents + ", img="
				+ img + ", isSecret=" + isSecret + ", password=" + password + ", viewCnt=" + viewCnt + ", goodsNo="
				+ goodsNo + ", scmNo=" + scmNo + ", orderNo=" + orderNo + ", replyStatus=" + replyStatus + ", isDelete="
				+ isDelete + ", regDt=" + regDt + ", modDt=" + modDt + ", deleteDt=" + deleteDt + ", adminNo=" + adminNo
				+ ", answerDt=" + answerDt + ", answerContent=" + answerContent + "]";
	}
	
	
	
}
