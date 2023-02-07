import React from "react";
import productService from "../services/productService";
import Home from "./Home";

class ProductComponent extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      products: [],
    };
  }
  componentDidMount() {
    productService.getProducts().then((response) => {
      this.setState({ products: response.data });
    });
  }
  render() {
    return (
      <div>
        <Home />
        <h2 className="text-center">Users</h2>
        <table className="table table-striped">
          <thead>
            <tr>
              <td>id</td>
              <td>name</td>
              <td>Email</td>
            </tr>
          </thead>
          <tbody>
            {this.state.products.map((product) => (
              <tr key={product.id}>
                <td>{product.id}</td>
                <td>{product.fullName}</td>
                <td>{product.email}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}
export default ProductComponent;
