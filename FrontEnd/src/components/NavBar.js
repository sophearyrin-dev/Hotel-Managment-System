import React from "react";
import "../App.css";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Container from "react-bootstrap/Container";
import NavDropdown from "react-bootstrap/NavDropdown";

class NavBar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      loggedIn: false,
      currentUser: undefined,
      // user,
    };
  }

  // useEffect(() => {
  //   const user = AuthService.getCurrentUser();
  //   if (user) {
  //     setCurrentUser(user);

  //   }
  //   EventBus.on("logout", () => {
  //     logOut();
  //   });
  //   return () => {
  //     EventBus.remove("logout");
  //   };
  // }, []);

  // getCurrentUser = () => {
  //   return JSON.parse(localStorage.getItem("subo8"));
  // };
  // user = getCurrentUser();
  // if(user) {
  //   setCurrentUser(user);
  // }

  componentDidMount() {
    //localStorage.getItem("access-token");
    const accessToken = localStorage.getItem("subo8");
    if (accessToken != undefined) {
      this.setState({ loggedIn: true });
    }
  }

  // getCurrentUser = () => {
  //   //return JSON.parse(localStorage.getItem("subo8"));
  //   const userDetails = localStorage.getItem("subo8");
  //   this.setState({ currentUser: userDetails.charAt(2) });
  //   console.log("User details: " + userDetails);
  // };

  logout = () => {
    localStorage.removeItem("subo8");
    this.setState({ loggedIn: false });
    window.location = "/";
  };
  notlogin = () => {
    if (this.setState({ loggedIn: false })) {
      window.location = "/login";
    }
  };

  render() {
    return (
      <div>
        <Navbar className="nav-custom" expand="lg">
          <Container>
            <Navbar.Brand id="try" href="/">
              Maharishi Hotel
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto" style={{ color: "white" }}>
                {this.state.loggedIn ? (
                  <span onClick={this.notlogin}>
                    {" "}
                    <Nav.Link href="/booking" style={{ color: "white" }}>
                      Make A Reservation
                    </Nav.Link>
                  </span>
                ) : (
                  <span>
                    {" "}
                    <Nav.Link href="/booking" style={{ color: "white" }}>
                      Make A Reservation
                    </Nav.Link>
                  </span>
                )}
                {/* <Nav.Link href="/booking" style={{ color: "white" }}>
                  Make A Reservation
                </Nav.Link> */}
                <Nav.Link href="/rooms" style={{ color: "white" }}>
                  Rooms Management
                </Nav.Link>
                <NavDropdown title="More Actions" id="basic-nav-dropdown">
                  <NavDropdown.Item href="/bookings">Bookings</NavDropdown.Item>
                  <NavDropdown.Item href="/myreservations">
                    My Reservation
                  </NavDropdown.Item>
                </NavDropdown>
              </Nav>
              {/* <Nav.Link href="/login" style={{ color: "white" }}>    "  Logged in"  */}
              {this.state.loggedIn ? (
                <span onClick={this.logout}>
                  <Nav.Link style={{ color: "white" }}>Logout</Nav.Link>
                </span>
              ) : (
                // <span>
                //   <Nav.Link style={{ color: "white" }}>
                //     {currentUser.username}
                //   </Nav.Link>
                // </span>
                <Nav.Link href="/login" style={{ color: "white" }}>
                  Login
                </Nav.Link>
              )}
              {/* </Nav.Link> */}
              {/* <Nav.Link style={{ color: "white" }}>Username</Nav.Link> */}
            </Navbar.Collapse>
          </Container>
        </Navbar>
        <br></br>
      </div>
    );
  }
}
export default NavBar;
