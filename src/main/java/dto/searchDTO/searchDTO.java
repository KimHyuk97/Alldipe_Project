package dto.searchDTO;

import java.sql.Timestamp;

public class searchDTO {
	private int sno;
	private int memNo;
	private String siteKey;
	private String keyword;
	private int keywordCnt;
	private Timestamp regDt;
	
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
	public String getSiteKey() {
		return siteKey;
	}
	public void setSiteKey(String siteKey) {
		this.siteKey = siteKey;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getKeywordCnt() {
		return keywordCnt;
	}
	public void setKeywordCnt(int keywordCnt) {
		this.keywordCnt = keywordCnt;
	}
	public Timestamp getRegDt() {
		return regDt;
	}
	public void setRegDt(Timestamp regDt) {
		this.regDt = regDt;
	}
	@Override
	public String toString() {
		return "searchDTO [sno=" + sno + ", memNo=" + memNo + ", siteKey=" + siteKey + ", keyword=" + keyword
				+ ", keywordCnt=" + keywordCnt + ", regDt=" + regDt + "]";
	}
	
	
	
	
	
	
}
