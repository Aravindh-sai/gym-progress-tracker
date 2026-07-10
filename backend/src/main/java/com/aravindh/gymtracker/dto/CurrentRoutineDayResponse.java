package com.aravindh.gymtracker.dto;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentRoutineDayResponse {
    private String title;
    private boolean restDay;
    private List<ExerciseResponse> exercises;
}
