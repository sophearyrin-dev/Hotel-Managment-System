import React from "react";
import "../App.css";
import axios from "axios";
import Container from "react-bootstrap/Container";
import { Row, Col, Form, Button } from "react-bootstrap";
import NavBar from "./NavBar";
import BACK_END_URL from "../services/api";
import Cookies from "universal-cookie";
import AuthService from "../services/auth.service";
class Login extends React.Component {
  state = {
    username: "",
    password: "",
  };
  cookies = new Cookies();
  changeHandler = (event) => {
    this.setState({ [event.target.name]: event.target.value });
  };

  submitHandler = (event) => {
    event.preventDefault();
    let temp = { ...this.state };
    axios
      .post("http://localhost:8080/auth/signin", temp)
      // .post(BACK_END_URL + "/api/authenticate", temp)
      .then((res) => {
        localStorage.setItem("subo8", JSON.stringify(res.data));
        const userDetails = localStorage.getItem("subo8");
        console.log("User details: " + userDetails);
        // let head = res.headers;
        // console.log(head);
        //this.cookies.set("subo8", JSON.stringify(res.data));
        console.log(res.data);
        // window.location = "/booking";
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
                  <Form.Label>Email address or Username</Form.Label>
                  <Form.Control
                    size="lg"
                    type="text"
                    placeholder="Enter username"
                    name="username"
                    onChange={this.changeHandler}
                  />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    size="lg"
                    type="password"
                    placeholder="Password"
                    onChange={this.changeHandler}
                    name="password"
                  />
                </Form.Group>
                <br></br>
                <Button id="button" type="submit" size="lg">
                  Login
                </Button>
                <br></br>
                <br></br>
                <h1 style={{ textAlign: "center" }}>OR</h1>

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
                    alt=""
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
                <br></br>
                <br></br>
              </Form>
            </Col>

            <Col sm={12} md={5}>
              <p id="Loginn">Login to your account</p>
              <p id="account">
                Have an account? <a href="/signup">Sign up</a>
              </p>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
export default Login;
