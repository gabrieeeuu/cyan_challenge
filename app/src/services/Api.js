import React, {Component} from "react";
import axios from 'axios';
import { getToken } from "./Auth";

const api = axios.create({
  baseURL: "http://localhost:8080/"
});

api.interceptors.request.use(async config => {
  const token = getToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;