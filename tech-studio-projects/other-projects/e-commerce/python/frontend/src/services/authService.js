import axios from 'axios';

const API_URL = 'http://localhost:5002/auth/'; // Update with your service URL

const login = (credentials) => {
  return axios.post(`${API_URL}login/`, credentials);
};

const register = (userData) => {
  return axios.post(`${API_URL}register/`, userData);
};

export default {
  login,
  register,
};
