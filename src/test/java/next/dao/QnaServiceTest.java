package next.dao;

import static org.mockito.Mockito.when;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;

import next.CannotDeleteException;
import next.model.Question;
import next.model.User;
import next.service.QnaService;

@RunWith(MockitoJUnitRunner.class)
public class QnaServiceTest {
	@Mock
	private AnswerDao answerDao;
	@Mock
	private QuestionDao questionDao;
	
	private QnaService qnaService;
	
	
	@Before
	public void setUp(){
		qnaService = new QnaService(questionDao, answerDao);
	}
	
	@Test(expected = CannotDeleteException.class)
	public void deleteQuestion_noQuestion() throws Exception{
		when(questionDao.findById(1L)).thenReturn(null);
		
		qnaService.deleteQuestion(1L, new User("id","pw","name","ema"));
	}
	
	@Test(expected = CannotDeleteException.class)
	public void deleteQuestion_otherUser() throws Exception{
		Question question = new Question(1L, "user1", "", "", new Date(), 0);
		when(questionDao.findById(1L)).thenReturn(question);
		when(answerDao.findAllByQuestionId(1L)).thenReturn(Lists.newArrayList());
		
		qnaService.deleteQuestion(1L, new User("id","pw","name","ema"));
	}
	
	@Test
	public void deleteQuestion_noComments() throws Exception{
		Question question = new Question(1L, "user1", "", "", new Date(), 0);
		when(questionDao.findById(1L)).thenReturn(question);
		when(answerDao.findAllByQuestionId(1L)).thenReturn(Lists.newArrayList());

		qnaService.deleteQuestion(1L, new User("user1","pw","name","ema"));
	}
}
