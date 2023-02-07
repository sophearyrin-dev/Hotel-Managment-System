import React from "react";
import "../App.css";
import Container from "react-bootstrap/Container";
import { Row, Col, Form, Button } from "react-bootstrap";
import axios from "axios";
import NavBar from "./NavBar";
import BACK_END_URL from "../services/api";

class Signup extends React.Component {
  state = {
    email: "",
    password: "",
    username: "",
  };

  changeHandler = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  submitHandler = (event) => {
    event.preventDefault();
    const myUserData = {
      username: this.state.username,
      email: this.state.email,
      password: this.state.password,
    };

    axios
      // .post(BACK_END_URL + "/api/register", myUserData)
      .post("http://localhost:8080/auth/signup", myUserData)
      .then((res) => {
        console.log(res);

        window.location = "/login";
      })
      .catch((err) => console.log(err));
  };
  render() {
    return (
      <div id="signup">
        <NavBar />

        <Container fluid>
          <Row>
            <Col sm={12} md={4}>
              <Form id="signup-form" onSubmit={this.submitHandler}>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                  <Form.Label>User Name</Form.Label>
                  <Form.Control
                    size="lg"
                    type="text"
                    placeholder="Enter user name"
                    name="username"
                    onChange={this.changeHandler}
                  />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                  <Form.Label>Email address</Form.Label>
                  <Form.Control
                    size="lg"
                    type="email"
                    placeholder="Enter email"
                    name="email"
                    onChange={this.changeHandler}
                  />
                  <Form.Text style={{ color: "white" }}>
                    We'll never share your email with anyone else.
                  </Form.Text>
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    size="lg"
                    type="password"
                    placeholder="Password"
                    name="password"
                    onChange={this.changeHandler}
                  />
                </Form.Group>
                <Button id="button" type="submit" size="lg">
                  Sign Up
                </Button>

                <br></br>
                <br></br>
                <h1 style={{ textAlign: "center" }}>OR</h1>
                <br></br>
                <Button
                  style={{
                    backgroundColor: "#C8BFBA",
                    fontSize: "1.2rem",
                    width: "45%",
                  }}
                >
                  <img
                    src="/google.png"
                    style={{ width: "40px", padding: "5px" }}
                    alt="Google"
                  />
                  Google
                </Button>
                {"      "}

                <Button
                  style={{
                    backgroundColor: "#fff",
                    color: "#000",
                    fontSize: "1.2rem",
                    width: "50%",
                  }}
                >
                  <img
                    src="/apple.png"
                    style={{ width: "40px", padding: "5px" }}
                    alt="Apple"
                  />
                  Apple
                </Button>
              </Form>
            </Col>
            <Col md={2} sm={12}></Col>

            <Col sm={12} md={5}>
              <p id="signupp">Welcome To Our World, Get Ready By Signing Up</p>
              <p
                id="have-account-sign"
                style={{ color: "#235cbd", marginLeft: "50px" }}
              >
                Already have an account? <a href="/login">Login</a>
              </p>
            </Col>
            {/* <Col md={2} sm={12}></Col> */}
          </Row>
        </Container>
      </div>
    );
  }
}
export default Signup;
