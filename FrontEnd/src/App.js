import "./App.css";
import { Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import ProductComponent from "./components/ProductCompnent";
import Signup from "./components/Signup";
import Login from "./components/Login";
import Booking from "./components/Booking";
import Home from "./components/Home";
import RoomsComponent from "./components/RoomsComponent";
import ListOfReservations from "./components/ListOfReservations";
import MyReservations from "./components/MyReservations";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/">
          <Home />
        </Route>
        <Route path="/signup">
          <Signup />
        </Route>
        <Route path="/login">
          <Login />
        </Route>
        <Route path="/booking">
          <Booking />
        </Route>
        <Route path="/bookings">
          <ListOfReservations />
        </Route>
        <Route path="/rooms">
          <RoomsComponent />
        </Route>
        <Route path="/myreservations">
          <MyReservations />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
