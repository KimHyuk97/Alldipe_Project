package dto.boardDTO;

public class answerDTO {

	private int sno;
	private int answerManagerNo;
	private String answerManagerId;
	private String answerContents;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
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
	public String getAnswerContents() {
		return answerContents;
	}
	public void setAnswerContents(String answerContents) {
		this.answerContents = answerContents;
	}
	
}
