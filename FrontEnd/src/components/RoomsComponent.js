import React from "react";
import "../App.css";
import Container from "react-bootstrap/Container";
import { Row, Col, Form, Button, Modal } from "react-bootstrap";
import axios from "axios";
import NavBar from "./NavBar";
import AddRoomForm from "./AddRoomForm";
import RoomsList from "./RoomsList";
import BACK_END_URL from "../services/api";

class RoomsComponent extends React.Component {
  constructor(props) {
    super(props);
    this.showModal2 = this.showModal2.bind(this);
    this.hideModal2 = this.hideModal2.bind(this);
    this.showModal = this.showModal.bind(this);
    this.hideModal = this.hideModal.bind(this);
    this.state = {
      show: false,
      show2: false,
      room: {
        roomId: "",
        roomNumber: "",
        type: "",
        price: "",
        bedType: "",
        numberOfBeds: "",
        maxNumberOfGuests: "",
        smoking: "",
        description: "",
        available: "",
        roomRating: "",
        totalRatings: "",
      },
      roomToEdit: {
        roomId: "",
        roomNumber: "",
        type: "",
        price: "",
        bedType: "",
        numberOfBeds: "",
        maxNumberOfGuests: "",
        smoking: "",
        description: "",
        available: "",
        roomRating: "",
        totalRatings: "",
      },
      rooms: [],
    };
  }

  changeHandler = (event) => {
    this.setState({
      room: {
        ...this.state.room,
        [event.target.name]: event.target.value,
      },
    });
  };
  changeHandler2 = (event) => {
    this.setState({
      roomToEdit: {
        ...this.state.roomToEdit,
        [event.target.name]: event.target.value,
      },
    });
  };

  showModal = (room) => {
    console.log(room);
    this.setState({ show: true, roomToEdit: room });
  };
  showModal2 = (room) => {
    console.log(room);
    this.setState({ show2: true, roomToEdit: room });
  };

  hideModal = () => {
    this.setState({ show: false });
  };
  hideModal2 = () => {
    this.setState({ show2: false });
  };

  submitHandler = (event) => {
    event.preventDefault();
    const { roomToEdit } = this.state;
    const roomDetails = {
      roomId: roomToEdit.roomId,
      roomNumber: roomToEdit.roomNumber,
      type: roomToEdit.type,
      price: roomToEdit.price,
      bedType: roomToEdit.bedType,
      numberOfBeds: roomToEdit.numberOfBeds,
      maxNumberOfGuests: roomToEdit.maxNumberOfGuests,
      smoking: roomToEdit.smoking,
      description: roomToEdit.description,
      available: roomToEdit.available,
      roomRating: roomToEdit.roomRating,
      totalRatings: roomToEdit.totalRatings,
    };

    axios
      .put(`http://localhost:8088/room/${roomToEdit.roomId}`, roomDetails, {
        headers: {
          Authorization:
            "Bearer " + localStorage.getItem("subo8").replace(/"/g, ""),
        },
      })
      .then((res) => {
        console.log(res);
        window.location = "/rooms";
      })
      .catch((err) => console.log(err));
  };
  deleteHandler = (event) => {
    event.preventDefault();
    const { roomToEdit } = this.state;
    axios
      .delete(`http://localhost:8088/room/${roomToEdit.roomId}`, {
        headers: {
          Headers: localStorage.getItem("subo8"),
        },
      })
      .then((res) => {
        console.log(res);
        window.location = "/rooms";
      })
      .catch((err) => console.log(err));
  };
  render() {
    const { roomToEdit } = this.state;
    return (
      <div id="rooms-div">
        <NavBar />
        <br></br>

        <Modal size="lg" show={this.state.show2} handleClose={this.hideModal2}>
          <Modal.Header
            closeButton
            show2={this.state.show2}
            onClick={this.hideModal2}
          >
            <Modal.Title>
              {roomToEdit ? `Room:#${roomToEdit.roomNumber}` : "Room"}
              {console.log(roomToEdit)}
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <h3>
              Are you sure you want to delete room{" "}
              {roomToEdit ? `Room ${roomToEdit.roomNumber}` : "Room"}?
            </h3>
            <Form onSubmit={this.deleteHandler}>
              <Modal.Footer>
                <Button
                  variant="secondary"
                  show={this.state.show}
                  onClick={this.hideModal2}
                >
                  Close
                </Button>
                <Button variant="danger" type="submit">
                  Confirm Delete
                </Button>
              </Modal.Footer>
            </Form>
          </Modal.Body>
        </Modal>

        <Modal size="lg" show={this.state.show} handleClose={this.hideModal}>
          <Modal.Header
            closeButton
            show={this.state.show}
            onClick={this.hideModal}
          >
            <Modal.Title>
              {roomToEdit ? `Room:#${roomToEdit.roomNumber}` : "Room"}
              {console.log(roomToEdit)}
            </Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form onSubmit={this.submitHandler}>
              <h3 style={{ textAlign: "center" }}>Edit Room details</h3>
              <Row className="mb-3">
                <Form.Group
                  as={Col}
                  className="mb-6"
                  controlId="formBasicEmail"
                >
                  <Form.Label>Room Number</Form.Label>
                  <Form.Control
                    type="text"
                    name="roomNumber"
                    value={roomToEdit.roomNumber}
                    onChange={this.changeHandler2}
                  />
                </Form.Group>
                <Form.Group as={Col} controlId="formBasicEmail">
                  <Form.Label>Type</Form.Label>
                  <Form.Control
                    type="text"
                    name="type"
                    value={roomToEdit.type}
                    onChange={this.changeHandler2}
                  />
                </Form.Group>
              </Row>
              <Row className="mb-6">
                <Form.Group
                  as={Col}
                  className="mb-6"
                  controlId="formBasicEmail"
                >
                  <Form.Label>Price</Form.Label>
                  <Form.Control
                    type="text"
                    name="price"
                    value={roomToEdit.price}
                    onChange={this.changeHandler2}
                  />
                </Form.Group>
                <Form.Group as={Col} controlId="formBasicEmail">
                  <Form.Label>Bed Type</Form.Label>
                  <Form.Control
                    type="text"
                    name="bedType"
                    value={roomToEdit.bedType}
                    onChange={this.changeHandler2}
                  />
                </Form.Group>
              </Row>
              <Row className="mb-6">
                <Form.Group
                  as={Col}
                  className="mb-3"
                  controlId="formBasicEmail"
                >
                  <Form.Label>Number Of Beds</Form.Label>
                  <Form.Control
                    type="text"
                    name="numberOfBeds"
                    value={roomToEdit.numberOfBeds}
                    onChange={this.changeHandler2}
                  />
                </Form.Group>

                <Form.Group as={Col} controlId="formBasicEmail">
                  <Form.Label>Max Number Of Guests </Form.Label>
                  <Form.Control
                    type="text"
                    name="maxNumberOfGuests"
                    value={roomToEdit.maxNumberOfGuests}
                    onChange={this.changeHandler2}
                  />
                </Form.Group>
              </Row>

              <Row className="mb-6">
                <Form.Group
                  as={Col}
                  className="mb-3"
                  controlId="formBasicEmail"
                >
                  <Form.Label>Room Rating</Form.Label>
                  <Form.Control
                    type="text"
                    name="roomRating"
                    value={roomToEdit.roomRating}
                    onChange={this.changeHandler2}
                  />
                </Form.Group>

                <Form.Group as={Col} controlId="formBasicEmail">
                  <Form.Label>Total Ratings </Form.Label>
                  <Form.Control
                    type="text"
                    name="totalRatings"
                    value={roomToEdit.totalRatings}
                    onChange={this.changeHandler2}
                  />
                </Form.Group>
              </Row>

              <Row className="mb-6">
                <Form.Group
                  as={Col}
                  className="mb-6"
                  controlId="formBasicEmail"
                >
                  <Form.Label>Smooking</Form.Label>
                  <Form.Select
                    as={Col}
                    aria-label="Smooking"
                    name="smoking"
                    // value={roomToEdit.smoking}
                    onChange={this.changeHandler}
                  >
                    <option>Select</option>
                    <option
                      value={true}
                      name="smoking"
                      //   value={roomToEdit.smoking}
                      onChange={this.changeHandler2}
                    >
                      Yes
                    </option>
                    <option
                      //   value={false}
                      name="smoking"
                      value={roomToEdit.smoking}
                      onChange={this.changeHandler}
                    >
                      No
                    </option>
                  </Form.Select>
                </Form.Group>

                <Form.Group as={Col} controlId="formBasicEmail">
                  <Form.Label>Description</Form.Label>
                  <Form.Control
                    type="text"
                    name="description"
                    value={roomToEdit.description}
                    onChange={this.changeHandler2}
                  />
                </Form.Group>
              </Row>

              <Form.Group className="mb-6" controlId="formBasicPassword">
                <Form.Label>Is Available?</Form.Label>
                <Form.Select
                  as={Col}
                  aria-label="Available"
                  value={roomToEdit.available}
                  onChange={this.changeHandler2}
                  name="available"
                >
                  <option>Select</option>

                  <option
                    value={true}
                    // value={roomToEdit.available}
                    name="available"
                    onChange={this.changeHandler2}
                  >
                    Yes
                  </option>
                  <option
                    value={false}
                    name="available"
                    //value={roomToEdit.available}
                    onChange={this.changeHandler2}
                  >
                    No
                  </option>
                </Form.Select>
              </Form.Group>
              <br></br>
              <Modal.Footer>
                <Button
                  variant="secondary"
                  show={this.state.show}
                  onClick={this.hideModal}
                >
                  Close
                </Button>
                <Button style={{ backgroundColor: "#a99a93" }} type="submit">
                  Save Changes
                </Button>
              </Modal.Footer>
            </Form>
          </Modal.Body>
        </Modal>

        <Container fluid>
          <Row>
            <Col md={2}></Col>
            <Col md={5}>
              <AddRoomForm />
            </Col>
            <Col>
              <div id="image-div-room">
                <img src="/hotel.jpg" height="590" width="560" alt=""></img>
              </div>
            </Col>
          </Row>
        </Container>
        <br></br>
        <br></br>
        <Container>
          <Row>
            <Col>
              <RoomsList
                showModal={this.showModal}
                showModal2={this.showModal2}
              />
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
export default RoomsComponent;
