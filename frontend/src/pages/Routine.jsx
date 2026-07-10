import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/routine.css";

function Routine() {
    const navigate = useNavigate();
    const [routineDays, setRoutineDays] = useState([
        {
            title: "",
            isRestDay: false,
            isSaved: false,
            exercises: [
                {
                    name: "",
                    targetSets: ""
                }
            ]
        }

    ]);
    async function saveRoutineDay(routineDayRequest) {

        const token = localStorage.getItem("token");

        const response = await fetch("http://localhost:8080/routine-days", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(routineDayRequest)
        });

        return await response.json();

    }
    async function handleAddNewDay(index) {

        if (routineDays[index].title.trim() === "") {
            alert("Please enter a day title.");
            return;
        }
        for (const exercise of routineDays[index].exercises) {

            if (exercise.name.trim() === "") {
                alert("Please enter an exercise name.");
                return;
            }

            if (exercise.targetSets <= 0) {
                alert("Target sets must be greater than 0.");
                return;
            }

        }

        const routineDayRequest = {
            title: routineDays[index].title,
            restDay: routineDays[index].isRestDay,
            exercises: routineDays[index].exercises
        };

        const saveDayresult = await saveRoutineDay(routineDayRequest);
        if (saveDayresult.success) {

            const newRoutineDays = [...routineDays];

            newRoutineDays[index].isSaved = true;

            newRoutineDays.push({
                title: "",
                isRestDay: false,
                isSaved: false,
                exercises: [
                    {
                        name: "",
                        targetSets: ""
                    }
                ]
            });

            setRoutineDays(newRoutineDays);

        } else {

    
            alert(saveDayresult.message);

        }

    }
    async function handleAddRestDay(index) {

        if (routineDays[index].title.trim() === "") {
            alert("Please enter a day title.");
            return;
        }
        for (const exercise of routineDays[index].exercises) {

            if (exercise.name.trim() === "") {
                alert("Please enter an exercise name.");
                return;
            }

            if (exercise.targetSets <= 0) {
                alert("Target sets must be greater than 0.");
                return;
            }

        }

        const routineDayRequest = {
            title: routineDays[index].title,
            restDay: routineDays[index].isRestDay,
            exercises: routineDays[index].exercises
        };
        const saveLastDayresult = await saveRoutineDay(routineDayRequest);
        if (saveLastDayresult.success) {

            const restDayRequest = {
                title: "Rest Day",
                restDay: true,
                exercises: []
            };


            const saveRestDayresult = await saveRoutineDay(restDayRequest);
            if (saveRestDayresult.success) {

                const newRoutineDays = [...routineDays];

                newRoutineDays[index].isSaved = true;

                setRoutineDays(newRoutineDays);

                alert("Routine created successfully!");

                navigate("/home");
            }
            else {
               
                alert(saveRestDayresult.message);
            }
        } else {
            
            alert(saveLastDayresult.message);
        }
    }
    return (
        <div className="routine-page">

            {/* ---------- Header ---------- */}

            <div className="routine-header">

                <div className="brand">

                    <div className="brand-icon"></div>

                    <h1>Gym Progress Tracker</h1>

                </div>

                <h2>Routine Setup</h2>

                <p>

                    Good to see you, Aravindh.

                    <br /><br />

                    Create your first workout cycle.

                    <br /><br />

                    A cycle ends with a Rest Day.

                </p>

            </div>

            {/* ---------- Cards ---------- */}

            <div className="routine-cards">

                {routineDays.map((day, index) => (

                    <div
                    className={`routine-card ${day.isSaved ? "saved-card" : ""}`}
                        key={index}
                    >

                        {/* ---------- Card Header ---------- */}

                        <div className="card-header">

                            <h2>Day {index + 1}</h2>

                            <input
                                type="text"
                                placeholder="Day Title"
                                value={day.title}
                                disabled={day.isSaved}
                                onChange={(event) => {

                                    const newRoutineDays = [...routineDays];

                                    newRoutineDays[index].title = event.target.value;

                                    setRoutineDays(newRoutineDays);

                                }}
                            />

                        </div>

                        <br />
                        {!day.isRestDay && (
                            <>
                                {/* ---------- Exercise List ---------- */}

                                <div className="exercise-list">

                                    {day.exercises.map((exercise, exerciseIndex) => (

                                        <div
                                            className="exercise-row"
                                            key={exerciseIndex}
                                        >

                                            <h4>Exercise {exerciseIndex + 1}</h4>

                                            <input
                                                type="text"
                                                placeholder="Exercise Name"
                                                value={exercise.name}
                                                disabled={day.isSaved}
                                                onChange={(event) => {

                                                    const newRoutineDays = [...routineDays];

                                                    newRoutineDays[index]
                                                        .exercises[exerciseIndex]
                                                        .name = event.target.value;

                                                    setRoutineDays(newRoutineDays);

                                                }}
                                            />

                                            <input
                                                type="number"
                                                placeholder="Sets"
                                                value={exercise.targetSets}
                                                disabled={day.isSaved}
                                                onChange={(event) => {

                                                    const newRoutineDays = [...routineDays];

                                                    newRoutineDays[index]
                                                        .exercises[exerciseIndex]
                                                        .targetSets = Number(event.target.value);

                                                    setRoutineDays(newRoutineDays);

                                                }}
                                            />

                                            <br /><br />

                                        </div>

                                    ))}

                                </div>

                                {/* ---------- Exercise Actions ---------- */}

                                <div className="exercise-actions">

                                    <button
                                        disabled={day.isSaved}
                                        onClick={() => {

                                            const newRoutineDays = [...routineDays];

                                            newRoutineDays[index].exercises.push({
                                                name: "",
                                                targetSets: ""
                                            });

                                            setRoutineDays(newRoutineDays);

                                        }}
                                    >
                                        + Add Exercise
                                    </button>

                                </div>
                            </>
                        )}

                    </div>

                ))}

            </div>

            {/* ---------- Bottom Actions ---------- */}

            <div className="routine-actions">

                <button onClick={() => handleAddNewDay(routineDays.length - 1)}>
                    + Add New Day
                </button>

                <button onClick={() => handleAddRestDay(routineDays.length - 1)}>
                    + Add Rest Day
                </button>

            </div>

        </div>
    );
}

export default Routine;