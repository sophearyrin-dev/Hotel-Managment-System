import React from "react";
import { Button, Table } from "react-bootstrap";

const AvailableRoomList = ({ rooms, addRoom, removeRoom }) => {

    return (
      <div>
        <h3 style={{ textAlign: "center", color: "white" }}>Available Rooms</h3>
        <Table bordered hover size="sm">
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
            {rooms.map((room) => (
              <tr key={room.roomId}>
                <td>{room.roomNumber}</td>
                <td>{room.type}</td>
                <td>{room.price}</td>
                <td>{room.bedType}</td>
                <td>{room.description}</td>
                <td>{room.available === true ? "Yes" : "No"}</td>
                <td>
                  {!room.selected ? 
                    <Button className="btn btn-primary"
                     onClick={() => addRoom(room)}
                    >
                        Add
                    </Button> :
                    <Button className="btn btn-warning"
                     onClick={() => removeRoom(room)}
                    >
                        Remove
                    </Button>}
                </td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    );
}

export default AvailableRoomList;
