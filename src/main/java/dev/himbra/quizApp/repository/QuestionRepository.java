package dev.himbra.quizApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.himbra.quizApp.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	public List<Question> findByCategory(String category);
    @Query(value="SELECT * FROM questions q WHERE q.category=:category ORDER BY RANDOM()"
    		+ "LIMIT :numQ ", nativeQuery = true)
	public List<Question> findRandomQuesionsByCategory(String category,int numQ);
}
