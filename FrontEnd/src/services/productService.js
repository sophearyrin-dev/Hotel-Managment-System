import axios from "axios";

const PRODUCT_SERVICE_URL = "http://localhost:8080/users";


class Products {
  getProducts() {
    return axios.get(PRODUCT_SERVICE_URL, {
      headers: {
        Authorization:
          "Bearer " + localStorage.getItem("access-token").replace(/"/g, ""),
      },
    });

    //axios.get(PRODUCT_SERVICE_URL);
  }
}
export default new Products(); //Export the object of the product class.
