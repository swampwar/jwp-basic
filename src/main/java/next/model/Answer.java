package next.model;

public class Answer {
    private int answerId;
    private String writer;
    private String contents;
    private String createdDate;
    private int questionId;
    
	public Answer(String writer, String contents, int questionId) {
		super();
		this.writer = writer;
		this.contents = contents;
		this.questionId = questionId;
	}

	public Answer(int answerId, String writer, String contents, String createdDate, int questionId) {
		super();
		this.answerId = answerId;
		this.writer = writer;
		this.contents = contents;
		this.createdDate = createdDate;
		this.questionId = questionId;
	}
	
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

    
}
