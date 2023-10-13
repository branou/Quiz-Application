package dev.himbra.quizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.himbra.quizApp.entity.Question;
import dev.himbra.quizApp.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	QuestionService questionServ;
	@GetMapping("/all")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return questionServ.getAllQuestion();
	}
	@GetMapping("byCategory/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
		return questionServ.getQuestionsByCategory(category);
	}
	@PostMapping("insert")
	public ResponseEntity<Question> AddQuestion(@RequestBody Question question) {
		return questionServ.addQuestion(question);
	}
}
