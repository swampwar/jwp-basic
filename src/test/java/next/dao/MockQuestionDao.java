package next.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import next.model.Question;

public class MockQuestionDao implements QuestionDao{
	Map<Long, Question> questions = Maps.newHashMap();

	@Override
	public Question insert(Question question) {
		return questions.put(question.getQuestionId(), question);
	}

	@Override
	public List<Question> findAll() {
		List<Question> list = new ArrayList<Question>(); 
		Set<Long> keySet = questions.keySet();
		Iterator<Long> iterator = keySet.iterator();
		
		while(iterator.hasNext()){
			list.add(questions.get(iterator.next()));
		}
		
		return list;
	}

	@Override
	public Question findById(long questionId) {
		return questions.get(questionId);
	}

	@Override
	public void update(Question question) {
		questions.put(question.getQuestionId(), question);
	}

	@Override
	public void delete(long questionId) {
		questions.remove(questionId);
	}

	@Override
	public void updateCountOfAnswer(long questionId) {
		Question question = questions.get(questionId);
		Question newQuestion = new Question(question.getQuestionId(), question.getWriter(), 
				question.getTitle(), question.getContents(), question.getCreatedDate(), 
				question.getCountOfComment()+1);
	    
		questions.put(questionId, newQuestion);
	}
	
	

}
