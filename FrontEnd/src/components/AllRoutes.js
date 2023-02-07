import { Route, useHistory, Redirect } from "react-router-dom";

const AllRoute = ({ component: Component, ...props }) => {
  let history = useHistory();
  if (localStorage.getItem("subo8")) {
    return localStorage.getItem("role").replace(/"/g, "") === "ROLE_ADMIN" ||
      localStorage.getItem("role").replace(/"/g, "") === "USER" ? (
      <Route
        {...props}
        render={() => <Component role={localStorage.getItem("role")} />}
      />
    ) : (
      history.goBack()
    );
  } else {
    return <Redirect to="/" />;
  }
};

export default AllRoute;
