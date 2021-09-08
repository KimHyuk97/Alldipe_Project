package dto.boardDTO;

import java.sql.Timestamp;

public class reviewDTO {

	private int sno;
	private int orderNo;
	private int goodsNo;
	private String title;
	private int memNo;
	private String writer;
	private String ip;
	private boolean privateFl;
	private String pw;
	private String contents;
	private int goodsPt;
	private String reviewImg;
	private boolean addMileageFl;
	private boolean isDelete;
	private Timestamp regDt;
	private Timestamp modDt;
	private int viewCnt;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public boolean isPrivateFl() {
		return privateFl;
	}
	public void setPrivateFl(boolean privateFl) {
		this.privateFl = privateFl;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getGoodsPt() {
		return goodsPt;
	}
	public void setGoodsPt(int goodsPt) {
		this.goodsPt = goodsPt;
	}
	public String getReviewImg() {
		return reviewImg;
	}
	public void setReviewImg(String reviewImg) {
		this.reviewImg = reviewImg;
	}
	public boolean isAddMileageFl() {
		return addMileageFl;
	}
	public void setAddMileageFl(boolean addMileageFl) {
		this.addMileageFl = addMileageFl;
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
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	@Override
	public String toString() {
		return "reviewDTO [sno=" + sno + ", orderNo=" + orderNo + ", goodsNo=" + goodsNo + ", title=" + title
				+ ", memNo=" + memNo + ", writer=" + writer + ", ip=" + ip + ", privateFl=" + privateFl + ", pw=" + pw
				+ ", contents=" + contents + ", goodsPt=" + goodsPt + ", reviewImg=" + reviewImg + ", addMileageFl="
				+ addMileageFl + ", isDelete=" + isDelete + ", regDt=" + regDt + ", modDt=" + modDt + ", viewCnt="
				+ viewCnt + "]";
	}
	
	
	
	
}
