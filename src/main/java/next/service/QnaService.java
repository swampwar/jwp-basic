package next.service;

import java.util.List;

import next.CannotDeleteException;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;

public class QnaService {
	private QuestionDao questionDao;
	private AnswerDao answerDao;

	public QnaService(QuestionDao questionDao, AnswerDao answerDao){
		this.questionDao = questionDao;
		this.answerDao = answerDao;
	}

	public Question findById(long questionId) {
		return questionDao.findById(questionId);
	}

	public List<Answer> findAllByQuestionId(long questionId) {
		return answerDao.findAllByQuestionId(questionId);
	}

	public void deleteQuestion(long questionId, User user) throws CannotDeleteException {
		Question question = questionDao.findById(questionId);
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);

		if (question == null) {
			throw new CannotDeleteException("존재하지 않는 질문입니다.");
		}

		/*
		 *  Qestion, User, Answer 의 메서드를 추가하여 질문이 삭제가 가능한지 체크하는 메서드를 구현했다.
		 *  객체지향 개발의 핵심은 여러 객체가 서로협력하면서 로직을 구현한다는 점이다.
		 */
		if(question.canDelete(user, answers)){
			questionDao.delete(questionId);
		}
	}
	
	public void delete(Long answerId){
		answerDao.delete(answerId);
	}
	
	public List<Question> findAll(){
		return questionDao.findAll();
	}
	
	public Question insert(Question question){
		return questionDao.insert(question);
	}
	
	public Answer insert(Answer answer){
		return answerDao.insert(answer);
	}
	
	public void update(Question question){
		questionDao.update(question);
	}
	
	public void updateCountOfAnswer(long questionId){
		questionDao.updateCountOfAnswer(questionId);
	}
}
