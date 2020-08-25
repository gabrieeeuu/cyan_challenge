import React, { Component } from "react";
import { Link } from "react-router-dom";

import { Button, Form, FormGroup, Container, Input, Label } from 'reactstrap';

import api from '../../services/Api';

import Navbar from '../../assets/AppNavbar'

class SignUp extends Component {
  state = {
    name: "",
    email: "",
    password: "",
    error: ""
  };

  handleSignUp = async e => {
    e.preventDefault();
    const { name, email, password } = this.state;
    if (!name || !email || !password) {
      this.setState({ error: "Fill all the spaces correctly to proceed." });
    } else {
      try {
        await api.post("/api/user", { name, email, password });
        this.props.history.push("/");
      } catch (err) {
        console.log(err);
        const error = err.response.data.message;
        this.setState({ error: error });
      }
    }
  };

  render() {
    return (
        <div>
          <Navbar />
          <Container>
            <div className="jumbotron">
              <h1>Sign Up</h1>
            </div>
            <Form onSubmit={this.handleSignUp}>
              {this.state.error && <p>{this.state.error}</p>}
              <FormGroup>
                <Label for="name">Name</Label>
                <Input
                    type="text"
                    name="name" id="name"
                    placeholder="Ex: Gabriel"
                    onChange={e => this.setState({ name: e.target.value })}
                />
              </FormGroup>
              <FormGroup>
                <Label for="email">Email</Label>
                <Input
                    type="email"
                    name="email" id="email"
                    placeholder="Ex: gabriel@gmail.com"
                    onChange={e => this.setState({ email: e.target.value })}
                />
              </FormGroup>
              <FormGroup>
                <Label for="password">Password</Label>
                <Input
                    type="password"
                    name="password"
                    id="password"
                    placeholder="Password"
                    onChange={e => this.setState({ password: e.target.value })}
                />
              </FormGroup>
              <Button outline color="success" type="submit">Submit</Button>
            </Form>
            <p>Already have an Account? <Link to="/signIn">Sign In</Link></p>
          </Container>
        </div>
    );
  }
}

export default SignUp;