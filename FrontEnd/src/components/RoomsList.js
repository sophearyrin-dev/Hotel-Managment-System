import React from "react";
import { Button, Table } from "react-bootstrap";
import axios from "axios";
import BACK_END_URL from "../services/api";

class RoomsList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      rooms: [],
    };
  }
  componentDidMount() {
    axios
      .get("http://localhost:8088/room", {
        headers: {
          Headers: localStorage.getItem("subo8"),
        },
      })
      .then((res) => {
        this.setState({
          rooms: res.data.map((room) => {
            return {
              ...room,
              available: room.available === true ? "Yes" : "No",
            };
          }),
        }).catch((error) => console.log(error));
      });
  }
  render() {
    return (
      <div>
        <h3 style={{ textAlign: "center", color: "white" }}>Available Rooms</h3>
        <Table striped bordered hover size="sm">
          <thead style={{ color: "white" }}>
            <tr>
              <th>Room Number</th>
              <th>Room Type</th>
              <th>Room Price</th>
              <th>Bed Type</th>
              <th>Description</th>
              <th>Is Available?</th>
              <th colSpan={2} style={{ textAlign: "center" }}>
                Action
              </th>
            </tr>
          </thead>
          <tbody style={{ color: "white" }}>
            {this.state.rooms.map((room) => (
              <tr key={room.roomId}>
                <td>{room.roomNumber}</td>
                <td>{room.type}</td>
                <td>{room.price}</td>
                <td>{room.bedType}</td>
                <td>{room.description}</td>
                <td>{room.available}</td>
                <td>
                  <Button
                    class="btn btn-primary"
                    onClick={() => this.props.showModal(room)}
                  >
                    Edit
                  </Button>
                </td>
                <td>
                  <Button
                    variant="danger"
                    onClick={() => this.props.showModal2(room)}
                  >
                    Delete
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
  }
}

export default RoomsList;
