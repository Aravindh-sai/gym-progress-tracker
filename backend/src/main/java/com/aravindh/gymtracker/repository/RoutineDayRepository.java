package com.aravindh.gymtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aravindh.gymtracker.entity.RoutineDay;
import com.aravindh.gymtracker.entity.User;


public interface RoutineDayRepository
        extends JpaRepository <RoutineDay, Long> {
            List<RoutineDay> findByUser(User user);
}