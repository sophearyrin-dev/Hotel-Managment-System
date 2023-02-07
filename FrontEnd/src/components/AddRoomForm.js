import React from "react";
import "../App.css";
import { Row, Col, Form, Button } from "react-bootstrap";
import axios from "axios";
import BACK_END_URL from "../services/api";

class AddRoomForm extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
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
      },
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

  submitHandler = (event) => {
    event.preventDefault();
    const { room } = this.state;
    const roomDetails = {
      roomId: room.roomId,
      roomNumber: room.roomNumber,
      type: room.type,
      price: room.price,
      bedType: room.bedType,
      numberOfBeds: room.numberOfBeds,
      maxNumberOfGuests: room.maxNumberOfGuests,
      smoking: room.smoking,
      description: room.description,
      available: room.available,
    };

    axios
      .post("http://localhost:8088/", roomDetails, {
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
    // const { roomToEdit } = this.state;
    return (
      <div>
        <Form id="room-form" onSubmit={this.submitHandler}>
          <h3 style={{ textAlign: "center" }}>Add Room details</h3>
          <Row className="mb-3">
            <Form.Group as={Col} className="mb-3" controlId="formBasicEmail">
              <Form.Label>Room Number</Form.Label>
              <Form.Control
                type="text"
                name="roomNumber"
                onChange={this.changeHandler}
              />
            </Form.Group>
            <Form.Group as={Col} controlId="formBasicEmail">
              <Form.Label>Type</Form.Label>
              <Form.Control
                type="text"
                name="type"
                onChange={this.changeHandler}
              />
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group as={Col} className="mb-3" controlId="formBasicEmail">
              <Form.Label>Price</Form.Label>
              <Form.Control
                type="text"
                name="price"
                onChange={this.changeHandler}
              />
            </Form.Group>
            <Form.Group as={Col} controlId="formBasicEmail">
              <Form.Label>Bed Type</Form.Label>
              <Form.Control
                type="text"
                name="bedType"
                onChange={this.changeHandler}
              />
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group as={Col} className="mb-3" controlId="formBasicEmail">
              <Form.Label>Number Of Beds</Form.Label>
              <Form.Control
                type="text"
                name="numberOfBeds"
                onChange={this.changeHandler}
              />
            </Form.Group>
            <Form.Group as={Col} controlId="formBasicEmail">
              <Form.Label>Max Number Of Guests </Form.Label>
              <Form.Control
                type="text"
                name="maxNumberOfGuests"
                onChange={this.changeHandler}
              />
            </Form.Group>
          </Row>
          <Row className="mb-3">
            <Form.Group as={Col} className="mb-3" controlId="formBasicEmail">
              <Form.Label>Smooking</Form.Label>
              <Form.Select
                as={Col}
                aria-label="Smooking"
                name="smoking"
                onChange={this.changeHandler}
              >
                <option>Select</option>
                <option
                  value={true}
                  name="smoking"
                  onChange={this.changeHandler}
                >
                  Yes
                </option>
                <option
                  value={false}
                  name="smoking"
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
                onChange={this.changeHandler}
              />
            </Form.Group>
          </Row>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Is Available?</Form.Label>
            <Form.Select
              as={Col}
              aria-label="Smooking"
              onChange={this.changeHandler}
              name="available"
            >
              <option>Select</option>
              <option
                value={true}
                name="available"
                onChange={this.changeHandler}
              >
                Yes
              </option>
              <option
                value={false}
                name="available"
                onChange={this.changeHandler}
              >
                No
              </option>
            </Form.Select>
          </Form.Group>

          <Button id="button" type="submit" variant="primary">
            Submit
          </Button>
        </Form>
      </div>
    );
  }
}
export default AddRoomForm;
