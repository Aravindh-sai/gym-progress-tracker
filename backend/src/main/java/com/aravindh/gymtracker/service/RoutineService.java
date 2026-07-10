package com.aravindh.gymtracker.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.aravindh.gymtracker.dto.CreateRoutineDayRequest;
import com.aravindh.gymtracker.dto.CurrentRoutineDayResponse;
import com.aravindh.gymtracker.dto.ExerciseRequest;
import com.aravindh.gymtracker.dto.ExerciseResponse;
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

        if (!request.isRestDay()) {
            for (ExerciseRequest exerciseRequest : request.getExercises()) {
                Exercises exercise = new Exercises();
                exercise.setName(exerciseRequest.getName());
                exercise.setTargetSets(exerciseRequest.getTargetSets());
                exercise.setRoutineDay(routineDay);
                exerciseRepository.save(exercise);

            }
        }

        if (request.isRestDay()) {
            System.out.println("REST DAY RECEIVED - SETTING ROUTINE START DATE");
            user.setRoutineStartDate(LocalDate.now());
            userRepository.save(user);
        }
        RoutineResponse response = new RoutineResponse();
        response.setSuccess(true);
        response.setMessage("Routine Saved Successfully");
        return response;
    }

    public CurrentRoutineDayResponse getCurrentRoutineDay() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findByEmail(email);
        List<RoutineDay> routineDays = routineDayRepository.findByUserOrderByPositionAsc(user);
        if (routineDays.isEmpty()) {
            return null;
        }

        if (user.getRoutineStartDate() == null) {
            return null;
        }
        long daysElapsed = ChronoUnit.DAYS.between(
                user.getRoutineStartDate(),
                LocalDate.now());
        int currentIndex = (int) (daysElapsed % routineDays.size());
        RoutineDay currentDay = routineDays.get(currentIndex);
        CurrentRoutineDayResponse response = new CurrentRoutineDayResponse();

        response.setTitle(currentDay.getTitle());
        response.setRestDay(currentDay.isRestDay());
        List<Exercises> exercises = exerciseRepository.findByRoutineDay(currentDay);

        List<ExerciseResponse> exerciseResponses = new ArrayList<>();

        for (Exercises exercise : exercises) {

            ExerciseResponse exerciseResponse = new ExerciseResponse();

            exerciseResponse.setName(exercise.getName());
            exerciseResponse.setTargetSets(exercise.getTargetSets());

            exerciseResponses.add(exerciseResponse);
        }

        response.setExercises(exerciseResponses);

        return response;

    }
}
