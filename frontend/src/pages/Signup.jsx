import React, { useState } from "react";
import { Link } from "react-router-dom";

function Signup() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [preferredUnit, setPreferredUnit] = useState("kg");
    async function handleSignup() {
        if (password !== confirmPassword) {
            alert("Passwords do not match");
            return;
        }
        const signupRequest = {
            name,
            email,
            password,
            preferredUnit
        };

        const response = await fetch("http://localhost:8080/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(signupRequest)
        });

        const result = await response.json();
        if (result.success) {

            localStorage.setItem("token", result.token);

            window.location.href = "/routine";

        } else {

            alert(result.message);

        }
    }
    return (
        <div className="auth-page">

            <div className="auth-header">

                <div className="brand">

                    <div className="brand-icon"></div>

                    <h1>Gym Progress Tracker</h1>

                </div>

                <p>
                    Train consistently.
                    <br /><br />
                    Measure your progress.
                </p>

            </div>

            <div className="card auth-card">

                <h2>Create Account</h2>

                <p>
                Start tracking every workout.
                </p>

                <input
                    type="text"
                    placeholder="Name"
                    onChange={(event) => {
                        setName(event.target.value);
                    }}
                />

                <br /><br />

                <input
                    type="email"
                    placeholder="Email"
                    onChange={(event) => {
                        setEmail(event.target.value);
                    }}
                />

                <br /><br />

                <input
                    type="password"
                    placeholder="Password"
                    onChange={(event) => {
                        setPassword(event.target.value);
                    }}
                />


                <br /><br />

                <input
                    type="password"
                    placeholder="Confirm Password"
                    onChange={(event) => {
                        setConfirmPassword(event.target.value);
                    }}
                />


                <br /><br />

                <div className="unit-selection">

                    <label>Preferred Units</label>

                    <select>

                        <option>Kilograms</option>

                        <option>Pounds</option>

                    </select>

                </div>
                <br /><br />

                <button
                    className="primary-button"
                    onClick={handleSignup}>
                    Sign up
                </button>
                <p
                    style={{
                        textAlign: "center",
                        marginTop: "20px"
                    }}
                >

                    Already have an account?{" "}

                    <Link to="/login">

                        Login

                    </Link>

                </p>

            </div>

        </div>

    );
}

export default Signup;