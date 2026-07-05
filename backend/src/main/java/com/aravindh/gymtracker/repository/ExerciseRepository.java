package com.aravindh.gymtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aravindh.gymtracker.entity.Exercises;


public interface ExerciseRepository extends JpaRepository<Exercises, Long> {
    
}
