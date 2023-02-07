import React from "react";
import "../App.css";
import Container from "react-bootstrap/Container";
import { Row, Col } from "react-bootstrap";
import NavBar from "./NavBar";
import AddBookingForm from "./AddBookingForm";
import AvailableRoomList from "./AvailableRoomList";
import axios from "axios";
import BACK_END_URL from "../services/api";
import Cookies from "universal-cookie";

class Booking extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dateOfArrival: "",
      dateOfDeparture: "",
      numberOfRooms: "",
      otherReservations: "",
      // roomId: "",
      // amount: "",
      // creditCardId: "",
      roles: "",
      rooms: [],
      selectedRooms: [],
      loggedIn: false,
    };
  }
  changeHandler = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };
  notlogin = () => {
    if (this.setState({ loggedIn: false })) {
      window.location = "/login";
    }
  };
  if(_notlogin) {
    window.location = "/";
  }
  else;
  // getRooms = () => {
  //   axios
  //     // .get(BACK_END_URL + "/api/rooms/", {
  //     .get("http://localhost:8088/room", {
  //       headers: {
  //         Headers: this.cookies.get("subo8"),
  //       },
  //     })
  //     .then((res) => {
  //       this.setState({
  //         rooms: res.data.map((room) => {
  //           return {
  //             ...room,
  //             selected: false,
  //           };
  //         }),
  //       }).catch((error) => console.log(error));
  //     });
  // };

  // addRoom = (room) => {
  //   room.selected = true;
  //   let { selectedRooms } = this.state;
  //   if (selectedRooms && selectedRooms.length > 0) {
  //     selectedRooms = [
  //       ...this.state.selectedRooms.filter(
  //         (aRoom) => aRoom.roomNumber !== room.roomNumber
  //       ),
  //       room,
  //     ];
  //   } else {
  //     selectedRooms = [room];
  //   }
  //   this.setState({
  //     selectedRooms,
  //     rooms: [
  //       room,
  //       ...this.state.rooms.filter(
  //         (aRoom) => aRoom.roomNumber !== room.roomNumber
  //       ),
  //     ],
  //   });
  // };

  cookies = new Cookies();

  // removeRoom = (room) => {
  //   room.selected = false;
  //   this.setState({
  //     selectedRooms: [
  //       ...this.state.selectedRooms.filter(
  //         (aRoom) => aRoom.roomNumber !== room.roomNumber
  //       ),
  //     ],
  //     rooms: [
  //       room,
  //       ...this.state.rooms.filter(
  //         (aRoom) => aRoom.roomNumber !== room.roomNumber
  //       ),
  //     ],
  //   });
  // };
  render() {
    const { rooms, selectedRooms } = this.state;
    return (
      <div id="booking">
        <NavBar />
        <br></br>
        {/* {this.props.role === "ADMIN"
          ? "Make A Reservation"
          : "   Access DEmied"} */}
        <Container fluid>
          <Row>
            <Col sm={12} md={12}>
              <div id="image-div" style={{ margin: "0 auto 1rem" }}>
                <p className="welcome" style={{ textAlign: "center" }}>
                  Welcome To Our World, Book With Us And Enjoy Your Stay..
                </p>
                <img
                  style={{
                    height: "200px",
                    margin: "0 auto",
                    display: "block",
                  }}
                  src="/aman-CP3kqxiDlwY-unsplash.jpg"
                  height="530"
                  width="660"
                  alt=""
                ></img>
              </div>
            </Col>
          </Row>
          <Row>
            <Col sm={12} md={6}>
              <AddBookingForm />
            </Col>
            <Col></Col>
          </Row>
        </Container>
      </div>
    );
  }
}
export default Booking;
