import React, { useEffect, useState } from "react";
import "../styles/Home.css";

function Home() {
    const [currentWorkout, setCurrentWorkout] = useState(null);
    useEffect(() => {

        async function fetchCurrentWorkout() {
    
            const token = localStorage.getItem("token");
    
            const response = await fetch(
                "http://localhost:8080/routine-days/current",
                {
                    method: "GET",
                    headers: {
                        "Authorization": `Bearer ${token}`
                    }
                }
            );
    
            const result = await response.json();
    
            setCurrentWorkout(result);
        }
    
        fetchCurrentWorkout();
    
    }, []);
    return (
        <div className="today-workout-section">

        <h2>Today's Workout</h2>
    
        {currentWorkout && (
            <div className="today-workout-card">
    
                <h3>{currentWorkout.title}</h3>
    
                {currentWorkout.restDay ? (
    
                    <p>Rest Day — Recover and come back stronger.</p>
    
                ) : (
    
                    <div className="today-exercise-list">
    
                        {currentWorkout.exercises.map((exercise, index) => (
    
                            <div
                                className="today-exercise-row"
                                key={index}
                            >
                                <span>{exercise.name}</span>
    
                                <span>{exercise.targetSets} sets</span>
                            </div>
    
                        ))}
    
                    </div>
    
                )}
    
            </div>
        )}
    
    </div>
    );
}

export default Home;