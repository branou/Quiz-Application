package dev.himbra.quizApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dev.himbra.quizApp.entity.Question;
import dev.himbra.quizApp.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepo;
	
	public ResponseEntity<List<Question>> getAllQuestion(){
		try {
		return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK) ;}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
		try {
		return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.OK) ;}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Question> addQuestion (Question question) {
		
		return  new ResponseEntity<>(questionRepo.save(question),HttpStatus.CREATED);
	}
	
}
