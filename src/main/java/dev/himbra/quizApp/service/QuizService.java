package dev.himbra.quizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.himbra.quizApp.entity.Question;
import dev.himbra.quizApp.entity.QuestionWrapper;
import dev.himbra.quizApp.entity.Quiz;
import dev.himbra.quizApp.entity.UserResponse;
import dev.himbra.quizApp.repository.QuestionRepository;
import dev.himbra.quizApp.repository.QuizRepository;
@Service
public class QuizService {
	@Autowired
	QuestionRepository questionRepo;
	@Autowired
	QuizRepository quizRepo;
	public ResponseEntity<Quiz> create(String category, int numQ, String title) {
		List<Question> listQues=questionRepo.findRandomQuesionsByCategory(category,numQ);
		Quiz quiz =new Quiz();
		quiz.setTitle(title);
		quiz.setQuestion(listQues);
		return new ResponseEntity<>(quizRepo.save(quiz),HttpStatus.CREATED);
	}
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz=quizRepo.findById(id);
		List<Question> questionFromDb=quiz.get().getQuestion();
		List<QuestionWrapper> questionsForUser=new ArrayList<>();
		for(Question ques: questionFromDb) {
			QuestionWrapper qw=new QuestionWrapper(ques.getId(),ques.getQuesionTitle(),ques.getOption1(),ques.getOption2(),
					ques.getOption3(),ques.getOption4());
			questionsForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
	}
	public ResponseEntity<Integer> calculateResultat(Integer id, List<UserResponse> userResponse) {
		Optional<Quiz> quiz=quizRepo.findById(id);
		List<Question> listQuestion =quiz.get().getQuestion();
		int right=0;
		for(UserResponse res:userResponse) {
			for(int i=0;i<userResponse.size();i++) {
				if(res.getId()==listQuestion.get(i).getId()) {
					if(res.getResponse().equals(listQuestion.get(i).getRightAnswer())) {
						right++;
					}
				}
			}
			
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
