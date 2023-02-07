import React from "react";
import { Row, Col, Button, Table, Container } from "react-bootstrap";
import axios from "axios";
import NavBar from "./NavBar";
import BACK_END_URL from "../services/api";

class ListOfReservations extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      bookings: [],
    };
  }
  componentDidMount() {
    axios
      .get("http://localhost:8999/", {
        headers: {
          Headers: localStorage.getItem("subo8"),
        },
      })
      .then((res) => {
        this.setState({
          bookings: res.data.map((booking) => {
            console.log(booking);
            return {
              ...booking,
              // available: room.available === true ? "Yes" : "No",
            };
          }),
        }).catch((error) => console.log(error));
      });
  }

  render() {
    return (
      <div id="reservations">
        <NavBar />
        <Container>
          <Row>
            <Col>
              <h3 style={{ textAlign: "center", color: "white" }}>
                List of Reservations
              </h3>
              <Table striped bordered hover size="sm">
                <thead style={{ color: "white" }}>
                  <tr>
                    <th>Arrival Date</th>
                    <th>Departure Date</th>
                    <th>Number of Rooms</th>
                    <th>Other Services</th>
                    <th>Reserved By?</th>
                    <th>Room Number</th>
                    <th>Room Rating</th>
                    <th colSpan={2} style={{ textAlign: "center" }}>
                      Action
                    </th>
                  </tr>
                </thead>
                <tbody style={{ color: "white" }}>
                  {this.state.bookings.map((booking) => (
                    <tr key={booking.bookingId}>
                      <td>{booking.dateOfArrival}</td>
                      <td>{booking.dateOfDeparture}</td>
                      <td>{booking.numberOfRooms}</td>
                      <td>{booking.otherReservations}</td>
                      <td>{booking.userName}</td>
                      <td>{booking.roomId}</td>
                      <td>5 Star</td>
                      {/* <td>
                        {booking.rooms
                          .map((room) => "room: " + room.roomNumber)
                          .join(", ")}
                      </td> */}
                      <td>
                        <Button
                          class="btn btn-primary"
                          onClick={() => this.props.showModal(booking)}
                        >
                          Edit
                        </Button>
                      </td>
                      <td>
                        <Button
                          variant="danger"
                          onClick={() => this.props.showModal2(booking)}
                        >
                          Delete
                        </Button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </Table>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default ListOfReservations;
