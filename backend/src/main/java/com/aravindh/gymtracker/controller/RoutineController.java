package com.aravindh.gymtracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aravindh.gymtracker.dto.CreateRoutineDayRequest;
import com.aravindh.gymtracker.dto.RoutineResponse;
import com.aravindh.gymtracker.service.RoutineService;

@RestController
public class RoutineController {
    private final RoutineService routineService;
    public  RoutineController( RoutineService routineService){
        this.routineService = routineService;
    }
    @PostMapping("/routine-days")
    public RoutineResponse createRoutine(@RequestBody CreateRoutineDayRequest request){
          return routineService.createRoutine(request);
    }

}
