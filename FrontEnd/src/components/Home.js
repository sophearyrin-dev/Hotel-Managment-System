import React from "react";
import "../App.css";
import Container from "react-bootstrap/Container";
import { Row, Col } from "react-bootstrap";
import NavBar from "./NavBar";

class Home extends React.Component {
  render() {
    return (
      <div id="container">
        <NavBar />
        <br></br>
        <Container fluid>
          <Row>
            <Col sm={12} md={3}></Col>
            <Col sm={12} md={3}></Col>
            <Col sm={12} md={3}></Col>
            <Col className="welcome" sm={12} md={3}>
              Welcome To Maharishi Hotel
            </Col>
          </Row>
        </Container>
        <Container style={{ marginTop: 265 }} fluid>
          <Row>
            <Col md={4}></Col>
            <Col md={4}></Col>
            <Col className="reserve" md={4} sm={12}>
              Would You Like To Make Reservations With Us? Click{" "}
              <span>
                <a href="/signup">Here </a>
              </span>{" "}
              To Continue.
            </Col>
          </Row>
        </Container>
        <Container fluid>
          <Row>
            <Col></Col>
            <Col></Col>
            <Col></Col>
            <Col></Col>
            <Col className="rights" sm={12} md={3}>
              All Rights Reserved
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
export default Home;
