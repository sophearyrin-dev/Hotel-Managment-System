import React from "react";
import "../App.css";
import { Row, Col, Form, Button, Alert, Badge } from "react-bootstrap";
import axios from "axios";
import BACK_END_URL from "../services/api";
import Cookies from "universal-cookie";

class AddBookingForm extends React.Component {
  state = {
    dateOfArrival: "",
    dateOfDeparture: "",
    numberOfRooms: "",
    otherReservations: "",
    roomId: "",
    amount: "",
    creditCardId: "",
    creditcards: [],
    roomss: [],
  };
  cookies = new Cookies();
  changeHandler = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  submitHandler = (event) => {
    event.preventDefault();
    // const rooms = this.props.rooms;
    const creditcards = this.props.creditcards;
    const roomss = this.props.roomss;
    //if (rooms != null && rooms.length < 1) return;

    // const totalPrice = rooms.reduce(
    //   (total, room) => total + (room.price || 0),
    //   0
    // );

    const bookingDetails = {
      ...this.state,
      //  totalPrice,
      //  rooms,
      // creditcards,
      // roomss,
    };

    console.log(bookingDetails);
    axios
      .post("http://localhost:8999/front", bookingDetails, {
        headers: {
          Headers: localStorage.getItem("sobu8"),
        },
      })
      .then((res) => {
        console.log(res);
        window.location = "/bookings";
      })
      .catch((err) => console.log(err));
  };
  fetchCreditCard() {
    axios
      .get("http://localhost:9001/creditcards", {
        headers: {
          Headers: localStorage.getItem("subo8"),
        },
      })
      .then((res) => {
        this.setState({
          creditcards: res.data.map((creditcard) => {
            return {
              ...creditcard,
              // available: room.available === true ? "Yes" : "No",
            };
          }),
        }).catch((error) => console.log(error));
      });
  }
  fetchRooms() {
    axios
      // .get(BACK_END_URL + "/api/rooms/", {
      .get("http://localhost:8088/room", {
        headers: {
          Headers: this.cookies.get("subo8"),
        },
      })
      .then((res) => {
        this.setState({
          roomss: res.data.map((roomm) => {
            return {
              ...roomm,
            };
          }),
        }).catch((error) => console.log(error));
      });
  }
  componentDidMount() {
    this.fetchCreditCard();
    this.fetchRooms();
  }

  render() {
    // const { rooms } = this.props;
    // const { creditcards } = this.props;

    return (
      <div>
        <Form id="booking-form" onSubmit={this.submitHandler}>
          <h3 style={{ textAlign: "center" }}>Make Your Reservation</h3>
          <Row className="mb-2">
            <Form.Group as={Col} className="mb-2" controlId="formBasicEmail">
              <Form.Label>Date of Arrival</Form.Label>
              <Form.Control
                type="date"
                name="dateOfArrival"
                onChange={this.changeHandler}
              />
            </Form.Group>
            <Form.Group as={Col} controlId="formBasicEmail">
              <Form.Label>Date Of Departure</Form.Label>
              <Form.Control
                type="date"
                name="dateOfDeparture"
                onChange={this.changeHandler}
              />
            </Form.Group>
          </Row>

          <Row className="mb-3">
            <Form.Group as={Col} className="mb-3" controlId="formBasicEmail">
              <Form.Label>Payment Method</Form.Label>
              <Form.Select
                as={Col}
                aria-label="Smooking"
                name="creditCardId"
                onChange={this.changeHandler}
              >
                <option>--Select--</option>
                {this.state.creditcards.map((creditcard) => (
                  <option name="creditCardId" value={creditcard.creditCardId}>
                    {creditcard.cardName}
                  </option>
                ))}
                ;
              </Form.Select>
            </Form.Group>

            <Form.Group as={Col} controlId="formBasicEmail">
              <Form.Label>Amount</Form.Label>
              <Form.Control
                type="text"
                name="amount"
                onChange={this.changeHandler}
              />
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group as={Col} className="mb-3" controlId="formBasicEmail">
              <Form.Label>Select A Room Type</Form.Label>
              <Form.Select
                as={Col}
                aria-label="Room"
                name="roomId"
                onChange={this.changeHandler}
              >
                <option>--Select--</option>
                {this.state.roomss.map((roomm) => (
                  <option name="roomId" value={roomm.roomId}>
                    {roomm.type}
                  </option>
                ))}
                ;
              </Form.Select>
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group className="mb-2" as={Col} controlId="formBasicEmail">
              <Form.Label>Number of Rooms</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter Number of Rooms"
                name="numberOfRooms"
                onChange={this.changeHandler}
              />
            </Form.Group>

            <Form.Group className="mb-3" as={Col} controlId="formBasicPassword">
              <Form.Label>Other Reservations</Form.Label>
              <Form.Control
                type="text"
                placeholder="Other Services rather than Rooms"
                name="otherReservations"
                onChange={this.changeHandler}
              />
            </Form.Group>
          </Row>
          <Button variant="primary" id="button" type="submit">
            Submit
          </Button>
        </Form>
      </div>
    );
  }
}

export default AddBookingForm;
