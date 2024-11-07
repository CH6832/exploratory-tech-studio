import axios from 'axios';

const API_URL = 'http://localhost:5001/products/'; // Update with your service URL

const getProducts = () => {
  return axios.get(API_URL);
};

export default {
  getProducts,
};
