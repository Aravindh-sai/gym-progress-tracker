package com.aravindh.gymtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aravindh.gymtracker.entity.Exercises;
import com.aravindh.gymtracker.entity.RoutineDay;


public interface ExerciseRepository extends JpaRepository<Exercises, Long> {
     List<Exercises> findByRoutineDay(RoutineDay routineDay);
}
