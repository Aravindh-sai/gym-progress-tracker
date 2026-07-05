package com.aravindh.gymtracker.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoutineDayRequest {
    private String title;
    private boolean isRestDay;
    private List<ExerciseRequest> exercises;
}
