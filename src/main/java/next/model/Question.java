package next.model;

public class Question {
	
	private String writer;
	private String title;
	private String contents;
	private int questionId;
	private String createdDate;
	private int countOfAnswer;
	
	public Question() {
	}
	
	public Question(String writer, String title, String contents) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	public Question(String writer, String title, String contents
			, int questionId, String createdDate, int countOfAnswer) {
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.questionId = questionId;
		this.createdDate = createdDate;
		this.countOfAnswer = countOfAnswer;
	}
	
	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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
	
	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getCountOfAnswer() {
		return countOfAnswer;
	}

	public void setCountOfAnswer(int countOfAnswer) {
		this.countOfAnswer = countOfAnswer;
	}

	public String toString(){
        return "Question [writer = "+writer+", title = "+ title +", contents = "+contents+"]";
	}

}
