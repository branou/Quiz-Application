package dev.himbra.quizApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.himbra.quizApp.entity.Quiz;
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
