import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/auth.css";
import { Link } from "react-router-dom";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    async function handleLogin() {
        const loginRequest = {
            email: email,
            password: password
        };
        setLoading(true);
        const response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(loginRequest)
        });
        const result = await response.json();
        if (result.success) {

            localStorage.setItem("token", result.token);
            window.location.href = "/home";
        } else {
            setLoading(false);
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

                <h2>Welcome Back</h2>

                <p
                    style={{
                        marginTop: "8px",
                        marginBottom: "24px"
                    }}
                >
                    Sign in to continue your fitness journey.
                </p>

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

                <button
                    className="primary-button"
                    disabled={loading}
                    onClick={handleLogin}
                >

                    {loading ? "Signing In..." : "Login"}

                </button>
                <p
                    style={{
                        textAlign: "center",
                        marginTop: "20px"
                    }}
                >

                    Don't have an account?{" "}

                    <Link to="/signup">

                        Sign Up

                    </Link>

                </p>

            </div>

        </div>

    );
}

export default Login;
