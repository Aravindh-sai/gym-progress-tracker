package com.aravindh.gymtracker.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRequest {
    private String name;
    private Integer targetSets;
}
