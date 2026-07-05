package com.aravindh.gymtracker.service;

import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aravindh.gymtracker.dto.CreateRoutineDayRequest;
import com.aravindh.gymtracker.dto.ExerciseRequest;
import com.aravindh.gymtracker.dto.RoutineResponse;
import com.aravindh.gymtracker.entity.Exercises;
import com.aravindh.gymtracker.entity.RoutineDay;
import com.aravindh.gymtracker.entity.User;
import com.aravindh.gymtracker.repository.ExerciseRepository;
import com.aravindh.gymtracker.repository.RoutineDayRepository;
import com.aravindh.gymtracker.repository.UserRepository;

@Service
public class RoutineService {
    private final RoutineDayRepository routineDayRepository;
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    public RoutineService(
            RoutineDayRepository routineDayRepository,
            ExerciseRepository exerciseRepository,
            UserRepository userRepository) {

        this.routineDayRepository = routineDayRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
    }

    public RoutineResponse createRoutine(CreateRoutineDayRequest request) {
        String email = (String) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
        
        User user = userRepository.findByEmail(email);
        List<RoutineDay> routinedays = routineDayRepository.findByUser(user);
        int nextPosition = routinedays.size() + 1;
        RoutineDay routineDay = new RoutineDay();
        routineDay.setTitle(request.getTitle());
        
        routineDay.setPosition(nextPosition);
        
        routineDay.setRestDay(request.isRestDay());
        
        routineDay.setUser(user);
        
        routineDayRepository.save(routineDay);
        
        if(!request.isRestDay()){
        for (ExerciseRequest exerciseRequest : request.getExercises()) {
            Exercises exercise = new Exercises();
            exercise.setName(exerciseRequest.getName());
            exercise.setTargetSets(exerciseRequest.getTargetSets());
            exercise.setRoutineDay(routineDay);
            exerciseRepository.save(exercise);
            
        }
    }
        RoutineResponse response = new RoutineResponse();
                response.setSuccess(true);
                response.setMessage("Routine Saved Successfully");       
                return response;                         
    }
}
