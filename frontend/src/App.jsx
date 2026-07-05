import { Routes, Route, Navigate } from "react-router-dom";

import Login from "./pages/Login";
import Signup from "./pages/Signup";
import RoutinePage from "./pages/Routine";
import HomePage from "./pages/Home";

function App() {
    const token = localStorage.getItem("token");

    return (
        <Routes>
            <Route
                path="/"
                element={
                    token ? <Navigate to="/home" /> : <Login />
                }
            ></Route>
            <Route
                path="/login"
                element={
                    token ? <Navigate to="/home" /> : <Login />
                }
            />
            <Route
                path="/signup"
                element={
                    token ? <Navigate to="/home" /> : <Signup />
                }
            />
            <Route
                path="/routine"
                element={<RoutinePage />}
            />
            <Route
                path="/home"
                element={
                    token ? <HomePage /> : <Navigate to="/login" />
                }
            />
        </Routes>
    );
}

export default App
