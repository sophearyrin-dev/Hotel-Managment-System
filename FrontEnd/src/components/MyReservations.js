import React from "react";
import { Row, Col, Button, Card, Container } from "react-bootstrap";
import axios from "axios";
import NavBar from "./NavBar";
import BACK_END_URL from "../services/api";

class MyReservations extends React.Component {
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
              {/* <h3 style={{ textAlign: "center", color: "white" }}>
                My Reservation
              </h3> */}
              <Card className="text-center">
                <Card.Header> My Reservation</Card.Header>
                <Card.Body>
                  <Card.Title>Special title treatment</Card.Title>
                  <Card.Text>
                    With supporting text below as a natural lead-in to
                    additional content.
                  </Card.Text>
                  <Button variant="primary">Cancel Reservation</Button>
                </Card.Body>
                <Card.Footer className="text-muted">2 days ago</Card.Footer>
              </Card>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default MyReservations;
